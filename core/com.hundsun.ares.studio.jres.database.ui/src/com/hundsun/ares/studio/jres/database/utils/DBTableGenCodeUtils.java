/**
 * 源程序名称：DBTableGenCodeUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.CompileManager;
import com.hundsun.ares.studio.jres.compiler.CompileUtils;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.database.compiler.DBCompilationResult;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author qinyuan
 *
 */
public class DBTableGenCodeUtils {
	
	private static final String SEPARATOR = "-------------------------------------------------\r\n";
	private static final String NEWLINE = "\r\n";
	
	/**
	 * 生成数据库全量脚本
	 * @param resource
	 * @param eObj
	 * @return
	 */
	public static StringBuffer genTableFullCode( IARESResource resource, EObject eObj) {
		StringBuffer buffer = new StringBuffer();
		if (eObj instanceof TableResourceData) {
			buffer.append(SEPARATOR);
			buffer.append("--全量脚本");
			buffer.append(NEWLINE);
			buffer.append(SEPARATOR);
		}
		
		// 上下文中需要输入IARESResource对象
		Map<Object, Object> context = getContext(resource);
		
		context.put("type", IDBConstant.COMPILE_DATABASE_FULL);
		appendTextCompilationResult(buffer, IDBConstant.COMPILE_DATABASE_FULL, eObj, context);
		
		return buffer;
	}
	
	public static StringBuffer genTablePatchCode( IARESResource resource, EObject eObj) {
		StringBuffer buffer = new StringBuffer();
		
		if (eObj instanceof TableResourceData) {
			buffer.append(NEWLINE);
			buffer.append(SEPARATOR);
			buffer.append("--升级脚本");
			buffer.append(NEWLINE);
			buffer.append(SEPARATOR);
		}
		
		// 上下文中需要输入IARESResource对象
		Map<Object, Object> context = getContext(resource);
		
		context.put("type", IDBConstant.COMPILE_DATABASE_PATCH);
		appendTextCompilationResult(buffer, IDBConstant.COMPILE_DATABASE_PATCH, eObj, context);
		
		return buffer;
	}
	
	/**
	 * 获取上下文
	 * @param resource
	 * @return
	 */
	private static Map<Object, Object> getContext(IARESResource resource){
		// 上下文中需要输入IARESResource对象
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put(CompileUtils.ARES_RESOURCE, resource);
		
		IARESProject project = resource.getARESProject();
		DBModuleCommonProperty property;
		try {
			property = (DBModuleCommonProperty) project.getProjectProperty().getMap().get(IDBConstant.MODULE_DATABASE_EXTEND_PROPERTY_KEY);
			property = (DBModuleCommonProperty) ObjectUtils.defaultIfNull(
					property, 
					DatabaseFactory.eINSTANCE.createDBModuleCommonProperty());
			context.put(IDBConstant.DATABASE_TYPE, property.getDatabase());
		} catch (ARESModelException e) {
			e.printStackTrace();
		} 
		return context;
	}
 	
	private static void appendTextCompilationResult(StringBuffer buffer, String type, EObject eObj, Map<Object, Object> context) {
		IResourceCompilerFactory factory = CompileManager.getInstance().getFactoryByType(type);
		if (factory != null) {
			CompilationResult result = factory.create(eObj).compile(eObj, context);
			if (result instanceof DBCompilationResult) {
				Map<String, StringBuffer> sqlMap = ((DBCompilationResult) result).getSqlByUser();
				StringBuffer sbs = new StringBuffer();
				for (StringBuffer sb : sqlMap.values()){
					sbs.append(sb);
				}
				buffer.append(sbs.toString());
			} else {
				buffer.append("--无法显示编译结果，编译结果不是文本");
			}
		} else {
			buffer.append("--无法显示编译结果，没有对应的编译器支持");
		}
	}


}
