/**
 * 源程序名称：IValidateConstant.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core;

/**
 * @author lvgao
 *
 */
public interface IValidateConstant {
	
	public static final String KEY_RESOUCE = "资源";
	
	public static final String KEY_REFERENCE_CALCULATOR = "资源引用帮助类";
	
	public static final String KEY_STATIC_PROVIDER = "资源信息统计类";

	public static final String KEY_CURRENT_STATISTIC_PROVIDER = "本工程自身资源信息统计类";
	
	public static final String KEY_REFERENCE_PROVIDER = "引用信息统计类";
	
	/**
	 * 资源信息统计扩展点
	 */
	public static final String EXTENTION_TYPE_STATIC_TABLE_PROVIDER = "jres.static";
	/**
	 * 资源引用信息
	 */
	public static final String EXTENTION_TYPE_REFERENCE_TABLE_PROVIDER = "jres.reference";
	
	/**
	 * 
	 */
	public static final String EXTENTION_VALIDATE_SOURCE = "www.hundsun.com/ext/validate";
	
	/**
	 * 
	 */
	public static final String EXTENTION_VALIDATE_KEY_NEEDVALIDATE = "needValidate";
	
	
	public static final String KEY_RESOUCE_PROJECT = "资源所在项目";//资源所在项目
}
