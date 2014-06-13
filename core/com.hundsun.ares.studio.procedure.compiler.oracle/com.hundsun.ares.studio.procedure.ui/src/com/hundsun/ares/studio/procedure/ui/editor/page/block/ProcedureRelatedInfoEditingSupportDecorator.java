/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page.block;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedInfoEditingSupportDecorator implements
		IEditingSupportDecorator {
	
	private EAttribute attribute;
	
	/**
	 * 
	 */
	public ProcedureRelatedInfoEditingSupportDecorator(EAttribute attribute) {
		this.attribute = attribute;
	}

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
		if(ProcdurePackage.Literals.RELATED_INFO__ID.equals(attribute)){
			return true;
		}else if(ProcdurePackage.Literals.RELATED_INFO__COMMENT.equals(attribute)) {
			return false;
		}else if(ProcdurePackage.Literals.RELATED_INFO__PATH.equals(attribute)) {
			return false;
		}
		return canEdit;
	}

}
