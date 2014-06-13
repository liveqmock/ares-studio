/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.viewers;

/**
 * @author gongyf
 *
 */
public interface IColumnViewerTextCopySupport {
	
	/**
	 * 给出复制到剪贴板中的文本
	 * @param element
	 * @return
	 */
	String getCopyText(Object element);
}
