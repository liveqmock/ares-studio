/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page.block;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedInfoContentProvider extends
		CommonElementContentProvider {
	
	private EReference reference;
	
	/**
	 * 
	 */
	public ProcedureRelatedInfoContentProvider(EReference reference) {
		super();
		this.reference = reference;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.CommonElementContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Procedure) {
			Procedure procedure = (Procedure)parentElement;
			if(reference.equals(ProcdurePackage.Literals.PROCEDURE__RELATED_TABLE_INFO)){
				EList<RelatedInfo> infos = procedure.getRelatedTableInfo();
				return infos.toArray(new RelatedInfo[infos.size()]);
			}else if(reference.equals(ProcdurePackage.Literals.PROCEDURE__RELATED_BASIC_DATA_INFO)){
				EList<RelatedInfo> infos = procedure.getRelatedBasicDataInfo();
				return infos.toArray(new RelatedInfo[infos.size()]);
			}
		}
		return super.getChildren(parentElement);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.ARESElementContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
}
