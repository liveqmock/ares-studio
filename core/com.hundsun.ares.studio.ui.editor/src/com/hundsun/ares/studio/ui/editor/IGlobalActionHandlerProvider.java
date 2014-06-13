/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.ui.IActionBars;

/**
 * @author gongyf
 *
 */
public interface IGlobalActionHandlerProvider  {
	
	void addGlobalActionHandlerProviderListener(IGlobalActionHandlerProviderListener listener);
	
	
	void removeGlobalActionHandlerProviderListener(IGlobalActionHandlerProviderListener listener);
	
	/**
	 * 设置全局快捷键
	 * @param actionBars
	 */
	void shareGlobalActions(IActionBars actionBars);
	
	/**
	 * 取消全局快捷键
	 * @param actionBars
	 */
	void clearGlobalActions(IActionBars actionBars);
}
