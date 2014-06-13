/**
 * 源程序名称：CompileUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.compiler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;


/**
 * @author gongyf
 *
 */
public class CompileUtils {
	
	/**
	 * 用于一些直接编译模型的编译器获取对应的资源对象<BR>
	 * 在上下文中可以如下操作：<BR>
	 * <code>
	 * IARESResource resource = 
	 * 	(IARESResource)context.get(CompileUtils.ARES_RESOURCE);
	 * </code>
	 */
	public static final String ARES_RESOURCE = "#IARESResource#";
	
	/**
	 * 在编译器中获取资源对象
	 * @param resource
	 * @param context
	 * @return
	 */
	public static IARESResource getARESResource(Object resource,
			Map<Object, Object> context) {
		if (resource instanceof IARESResource) {
			return (IARESResource) resource;
		}
		return (IARESResource) context.get(ARES_RESOURCE);
	}
	
	/**
	 * 根据EClass获取编译类型
	 * @param eClass
	 * @return
	 */
	public static String getEClassCompileType(EClass eClass) {
		return getEClassCompileType(eClass.getEPackage().getNsURI(), eClass.getName());
	}
	
	public static String getEClassCompileType(String uri, String name) {
		return String.format(CompilerConstants.Compile_EObject_Format, uri, name);
	}
	
	/**
	 * 根据EMF对象实例获取编译类型
	 * @param eObj
	 * @return
	 */
	public static String getEObjectCompileType(EObject eObj) {
		return getEClassCompileType(eObj.eClass());
	}
	
	/**
	 * 打印编译日志
	 * 
	 * @param logger logger对象
	 * @param compilationResult 编译结果对象
	 * @param message 日志主信息
	 */
	public static void writeCompilationResultLog (Logger logger, CompilationResult compilationResult ,String message){
		IStatus status = compilationResult.getStatus();
		IStatus[] statuses = status.getChildren();
		
		StringBuilder sb = new StringBuilder();
		sb.append(status.getMessage());
		
		for (int i = 0; i<statuses.length ; i++) {
			IStatus stat = statuses[i];
			if (i == 0) {
				sb.append("(");
			}
			if (i > 0) {
				sb.append("；");
			}
			sb.append(stat.getMessage());
			if (i == statuses.length - 1) {
				sb.append(")");
			}
		}
		message = message + sb;
		if (statuses.length == 0 || status.getSeverity() == IStatus.INFO || status.getSeverity() == IStatus.OK || status.getSeverity() == IStatus.CANCEL) {
			logger.info(message);
		}
		if (status.getSeverity() == IStatus.ERROR) {
			logger.error(message);
		}
		if (status.getSeverity() == IStatus.WARNING) {
			logger.warn(message);
		}
	}
	
}
