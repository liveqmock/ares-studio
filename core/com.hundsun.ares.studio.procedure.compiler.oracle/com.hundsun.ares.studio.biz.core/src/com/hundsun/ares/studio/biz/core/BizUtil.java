/**
 * 源程序名称：BizUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.core;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.biz.constants.IBizConstants;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class BizUtil {
	
	/**
	 * 获取参数对象的中文名； 这个方法会自动判断参数类型，以及当前项目中是否使用了对象标准字段，
	 * 根据不同的情况返回正确的值。 其中遇到引用的标准字段或对象不存在的情况的时候，返回空字符串。
	 * @param p
	 * @param project
	 * @return
	 */
	public static String getCName(Parameter p, IARESProject project) {
		ParamType pType = p.getParamType();
		if (pType == ParamType.STD_FIELD) {
			StandardField stdField = getStdField(p.getId(), project);
			return stdField == null ? StringUtils.EMPTY : stdField.getChineseName();
		} else if (pType == ParamType.NON_STD_FIELD) {
			return p.getName() == null ? StringUtils.EMPTY : p.getName();
		} else if (pType == ParamType.OBJECT) {
			ARESObject obj = BizUtil.getObject(p, project);
			return obj == null ? StringUtils.EMPTY : obj.getChineseName();
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获取参数对象的说明； 这个方法会自动判断参数类型，以及当前项目中是否使用了对象标准字段，
	 * 根据不同的情况返回正确的值。 其中遇到引用的标准字段或对象不存在的情况的时候，返回空字符串。
	 * @param p
	 * @param project
	 * @return
	 */
	public static String getDesc(Parameter p, IARESProject project) {
		ParamType pType = p.getParamType();
		if (pType == ParamType.STD_FIELD) {
			StandardField stdField = getStdField(p.getId(), project);
			return stdField == null ? StringUtils.EMPTY : stdField.getDescription();
		} else if (pType == ParamType.NON_STD_FIELD) {
			return p.getDescription() == null ? StringUtils.EMPTY : p.getDescription();
		} else if (pType == ParamType.OBJECT) {
			ARESObject obj = BizUtil.getObject(p, project);
			return obj == null ? StringUtils.EMPTY : obj.getDescription();
		}
		return StringUtils.EMPTY;
	} 
	
	/**
	 * 根据对象全名(id)查找对象
	 * @param id
	 * @param project
	 * @return
	 */
	public static ARESObject getObject(String id, IARESProject project) {
		
		List<String> refTypes = ObjectRefTypes.getRefTypes();
		for (String refType : refTypes) {
			ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, refType, id, true);
			if (ref != null) {
				Object refObject = ref.getObject();
				if (refObject instanceof ARESObject) {
					return (ARESObject) ref.getObject();
				}
			}
		}
		return null;
	}
	
	/**
	 * 直接根据参数对象获取其对应的对象定义， 如果找不到，则返回null
	 * @param p
	 * @param project
	 * @return
	 */
	public static ARESObject getObject(Parameter p, IARESProject project) {
		if (p.getParamType() != ParamType.OBJECT && p.getParamType() != ParamType.PARAM_GROUP) 
			return null;
		
		if (hasStdObjList(project)) {
			// 如果存在对象标准字段资源，即应该是对象标准字段的用法
			// 首先根据名字找到对应的对象标准字段的定义，
			// 然后再根据对象标准字段的信息找到对应的对象
			String stdObjId = p.getId();
			ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Std_Obj, stdObjId, true);
			if (ref == null)
				return null;
			StandardObjField std = (StandardObjField) ref.getObject();
			if (std == null)
				return null;
			String objId = std.getType();
			return getObject(objId, project);
		} else {
			String objId = p.getType();
			return getObject(objId, project);
		}
	}
	
	public static StandardField getStdField(String id, IARESProject project) {
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, id, true);
		return (StandardField) (ref == null ? null : ref.getObject());
	}
	
	public static boolean hasCRESNature(IProject project) {
		if (project == null)
			return false;
		if (!project.isOpen())
			return false;
		
		try {
			return project.hasNature("com.hundsun.ares.studio.cresnature");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断项目是否有对象标准字段列表资源
	 * @param project
	 * @return
	 */
	public static boolean hasStdObjList(IARESProject project) {
		if (project == null  || !project.isOpen()) {
			return false;
		}
		
		try {
			for (IARESModuleRoot root : project.getModuleRoots()) {
				IARESModule module = root.getModule(StringUtils.EMPTY);
				IARESResource res = module.getARESResource("stdobj.xml");
				if (res != null && res.exists())
					return true;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static IARESModuleRoot getBizRoot(IARESProject project) {
		IFolder bizFolder = ARESElementUtil.getModuleRootFolder(project, IBizConstants.BIZ_ROOT2);
		if (bizFolder == null)
			bizFolder = ARESElementUtil.getModuleRootFolder(project, IBizConstants.BIZ_ROOT);
		
		if (bizFolder == null) {
			return null;
		}
		return (IARESModuleRoot) ARESCore.create(bizFolder);
	}
	
}
