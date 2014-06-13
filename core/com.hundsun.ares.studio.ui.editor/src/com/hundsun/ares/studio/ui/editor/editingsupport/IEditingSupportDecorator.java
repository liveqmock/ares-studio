/**
 * 源程序名称：IEditingSupportDecorator.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.eclipse.jface.viewers.CellEditor;

/**
 * @author gongyf
 *
 */
public interface IEditingSupportDecorator {
	CellEditor decorateGetCellEditor(CellEditor cellEditor, Object element);
	boolean decorateCanEdit(boolean canEdit, Object element);
}
