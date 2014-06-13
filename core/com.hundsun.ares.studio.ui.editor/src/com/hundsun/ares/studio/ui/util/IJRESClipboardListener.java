/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.util;


/**
 * @author gongyf
 *
 */
public interface IJRESClipboardListener {
	void clipboardAboutToBeChanged(ARESEMFClipboard clipboard);
	void clipboardChanged(ARESEMFClipboard clipboard);
}
