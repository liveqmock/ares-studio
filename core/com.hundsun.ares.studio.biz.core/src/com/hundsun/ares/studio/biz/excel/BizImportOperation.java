/**
 * 源程序名称：ImportOperation.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.biz.excel;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.ui.dialogs.IOverwriteQuery;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.excel.AbstractSheetHandler;
import com.hundsun.ares.studio.core.excel.ExcelHandlerException;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ImportOperation;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataModifyOperation;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public abstract class BizImportOperation extends ImportOperation{
	
	/**
	 * @param files
	 * @param overwriteQuery
	 */
	public BizImportOperation(File[] files, IOverwriteQuery overwriteQuery) {
		super(files, overwriteQuery);
	}

	/**
	 * 修改标准字段的操作
	 */
	private MetadataModifyOperation<StandardField> stdModifyOperation = null;
	
	@Override
	public void run(IProgressMonitor monitor) throws CoreException{
		monitor.beginTask("导入：", 5000);
		logger.debug("start");
		
		stdModifyOperation = new MetadataModifyOperation<StandardField>(project, IMetadataResType.StdField);
		
		// 第一步： 解析文件，解析所有的资源
		IProgressMonitor subMonitor1 = new SubProgressMonitor(monitor, 1000);
		subMonitor1.beginTask("", files.length);
		for (File file : files) {
			if (subMonitor1.isCanceled())
				return;
			
			subMonitor1.subTask("解析文件：" + file.getName());
			exlPaser = createParser(file, log);
			exlPaser.postParseOperation = postParseOperation;
			exlPaser.context.put("std_filed_modify_operation", stdModifyOperation);
			exlPaser.parse();
			
			for (Module m : exlPaser.resources.keySet()) {
				m.processName(exlPaser.moduleNameMap, log);
			}
			
			resources.putAll(exlPaser.resources);
			subMonitor1.worked(1);
		}
		subMonitor1.done();
		log.totle = resources.size();
		
		// 第二步： 所有资源解析完成后，进行一下重名检查
		IProgressMonitor spmCheck = new SubProgressMonitor(monitor, 1000);
		checkRes(spmCheck);
		
		// 第三步： 对所有资源和对象
		IProgressMonitor spmProcessParma = new SubProgressMonitor(monitor, 1000);
		setParameterTypes(spmProcessParma);
		
		// 这两个PostOperation必须等上面确定了参数类型以后才能执行
		postParseOperation.run();
		stdModifyOperation.run(log, new SubProgressMonitor(monitor, 1000));
		
		// 第四步: 创建资源
		IProgressMonitor spmCreate = new SubProgressMonitor(monitor, 1000);
		try {
			createResources(spmCreate);
		} catch (ExcelHandlerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		monitor.done();
	}
	
	/**
	 * 对于服务接口资源处理中的参数类型,即确定是标准字段，非标准字段还是对象类型的参数
	 */
	protected void setParameterTypes(IProgressMonitor monitor) {
		monitor.beginTask("分析参数类型...", resources.size());
		for (Resource res : resources.values()) {
			if (res.info instanceof BizInterface) {
				BizInterface iface  = (BizInterface) res.info;
				for (Parameter p : iface.getInputParameters()) {
					setParameterType(p);
				}
				
				for (Parameter p : iface.getOutputParameters()) {
					setParameterType(p);
				}
			} else if (res.info instanceof ARESObject) {
				ARESObject obj = (ARESObject) res.info;
				for (Parameter p : obj.getProperties()) {
					setParameterType(p);
				}
			}
			monitor.worked(1);
		}
		monitor.done();
	}
	
	/**
	 * 决定参数类型
	 */
	protected void setParameterType(Parameter param) {
		String type = param.getType();
		// 如果业务类型中含有点，就认为是对象类型的参数，填的是长名（也可能是短名，看下面代码）
		if (StringUtils.contains(type, '.')) {
			param.setParamType(ParamType.OBJECT);
			
			// 对象资源、模块在导入的过程中，如果英文名中有不符合命名规范的特殊字符，会被替换；
			// 所以此处引用对象资源也需要替换
			String correctType = AbstractSheetHandler.correctResName(type);
			if (correctType != null) 
				param.setType(correctType);
			return;
		}
		
		String obj = getObjectFullName(type);
		if (obj != null) {
			// 由于文档里都是短名，确定类型后还需要替换成长名
			param.setParamType(ParamType.OBJECT);
			param.setType(obj);
		} else if (isStdField(param)) {
			param.setParamType(ParamType.STD_FIELD);
		} else {
			param.setParamType(ParamType.NON_STD_FIELD);
		}
	}
	
	//变量名与标准字段同名，并且数据类型也与同名标准字段的相同，才为标准字段
	protected boolean isStdField(Parameter p) {
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, p.getId(), false);
		if(null != ref && ref.getObject() instanceof StandardField) {
			// 2013-09-17 sundl 如果类型为空，则只根据字段名匹配标准字段即可
			if (StringUtils.isEmpty(p.getType())) {
				return true;
			}
			
			// 如果指定了类型，则必须字段名、类型、中文名和标准字段完全相符
			StandardField field = (StandardField)ref.getObject();
			if(field != null && StringUtils.equals(p.getType(), field.getDataType())
								&& StringUtils.equals(p.getName(), field.getChineseName())){
				return true;
			}
		}
		return false;
	}
	
	protected String getObjectFullName(String id) {
		if (StringUtils.isEmpty(id))
			return null;
		
		// 对象资源在导入的过程中，如果英文名中有不符合命名规范的特殊字符，会被替换；
		// 所以此处引用对象资源也需要替换
		String correctType = AbstractSheetHandler.correctResName(id);
		if (correctType != null) 
			id = correctType;
		
		// 缓存里有没有
		List<ReferenceInfo> references = ReferenceManager.getInstance().getReferenceInfos(project, IBizRefType.Object, true);
		for (ReferenceInfo ref : references) {
			ARESObject obj = (ARESObject) ref.getObject();
			if (obj != null && StringUtils.equals(id, obj.getName()))
				return ref.getResource().getFullyQualifiedName();
		}
		// 如果缓存里找不到，在本次解析出来的资源中找
		for (Module module : resources.keySet()) {
			for (Resource res : resources.get(module)) {
				if (StringUtils.equals(res.type, "object")) {
					if (StringUtils.equals(id, res.name))
						return module.getFullName() + "." + res.name;
				}
			}
		}
		return null;
	}

}
