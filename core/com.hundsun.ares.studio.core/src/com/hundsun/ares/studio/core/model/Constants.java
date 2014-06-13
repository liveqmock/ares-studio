/**
 * 源程序名称：Constants.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model;

/**
 * 
 * @author sundl
 */
public interface Constants {

	public interface RevisionHistory {
		/** 模块中的修改记录对应的扩展模型的Key */
		public static final String MODULE_REVISION_EXT_KEY = "module_rev_his_key";
		/** 项目属性中的修改记录扩展模型的Key **/
		public static final String PROJECT_REVISION_EXT_KEY = "ProjectRevisionHistory";
	}

	/**
	 * Ecore模型中用于标记引用的标记源名
	 */
	public static final String EANNOTATION_REF_SOURCE = "http://www.hundsun.com/ares/studio/jres/2011/references";
	public static final String USER_DATA2_KEY = "user";
	public static final String MODULE_EXTENSIBLEMODEL_KEY = "ModuleExtensibleModel";
	public static final String EXTENSIBLE_MODEL_CONFIG_PROPERTY_KEY = "ExtensibleModelConfigProperty";
	/**
	 * 项目属性中的用户扩展
	 */
	public static final String PROJECT_EXTENSIBLEMODEL_KEY = "ProjectExtensibleModel";

}
