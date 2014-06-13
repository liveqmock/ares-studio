/**
 * 源程序名称：ExtensionPointConst.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui;

/**
 * 扩展点相关的常量定义
 * @author gongyf
 *
 */
public interface ExtensionPointConst {
	/**
	 * 语言扩展点
	 * @author gongyf
	 *
	 */
	public interface Languages {
		
		/**
		 * 扩展点名
		 */
		String NAME = MetadataUI.PLUGIN_ID + ".languages";
		
		/**
		 * 扩展点元素
		 * @author gongyf
		 *
		 */
		public interface Language {
			String NAME = "language";
			String ATTR_ID = "id";
			String ATTR_NAME = "name";
		}
	}
	
}
