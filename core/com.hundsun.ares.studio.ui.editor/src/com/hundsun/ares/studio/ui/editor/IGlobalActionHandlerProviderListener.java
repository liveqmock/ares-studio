/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor;


/**
 * 全局快捷键提供器的状态变化监听器
 * @author gongyf
 *
 */
public interface IGlobalActionHandlerProviderListener {

	/**
	 * 提供器已激活
	 * @param provider
	 */
	void activated(IGlobalActionHandlerProvider provider);

	/**
	 * 提供器已
	 * @param provider
	 */
	void deactivated(IGlobalActionHandlerProvider provider);

}
