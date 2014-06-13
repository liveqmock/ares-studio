/**
 * 源程序名称：ICompilerFactoriyExtentionPoint.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.compiler;

/**
 * @author lvgao
 *
 */
public interface ICompilerFactoriyExtentionPoint {

	public static final String NAMESPACE = "com.hundsun.ares.studio.jres.core";
	public static final String EP_Name = "compilerFactories";
	public static final String EP_Attribute_ID = "id";
	public static final String EP_Attribute_Class = "class";
	public static final String EP_Attribute_Types = "types";
	public static final String EP_Attribute_Name = "name";
	public static final String EP_Attribute_Priority = "priority";
	
	public static final String EP_Element_EClass = "EClass";
	public static final String EP_Attribute_EClass_Uri = "uri";
	public static final String EP_Attribute_EClass_Name = "name";
	
}
