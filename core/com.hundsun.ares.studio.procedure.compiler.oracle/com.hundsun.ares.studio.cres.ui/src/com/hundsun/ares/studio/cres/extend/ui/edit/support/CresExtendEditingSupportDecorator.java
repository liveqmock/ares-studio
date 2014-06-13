/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.edit.support;

import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;

/**
 * @author qinyuan
 *
 */
public class CresExtendEditingSupportDecorator implements IEditingSupportDecorator {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator#decorateGetCellEditor(org.eclipse.jface.viewers.CellEditor, java.lang.Object)
	 */
	@Override
	public CellEditor decorateGetCellEditor(CellEditor cellEditor,
			Object element) {
		return cellEditor;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator#decorateCanEdit(boolean, java.lang.Object)
	 */
	@Override
	public boolean decorateCanEdit(boolean canEdit, Object element) {
		return true;
	}
}