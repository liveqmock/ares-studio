/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;

/**
 * @author lvgao
 *
 */
public class BaiscDataReferInfoColumnLabelProvider  extends BaseEObjectColumnLabelProvider{

	EStructuralFeature reference;
	/**
	 * @param attribute
	 */
	public BaiscDataReferInfoColumnLabelProvider(EStructuralFeature reference,EStructuralFeature attribute) {
		super(attribute);
		this.reference = reference;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		EObject eobj = (EObject) element;
		Object refer =  eobj.eGet(reference);
		if(null == refer){
			return "";
		}
		return super.getText(refer);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object element) {
		return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}

}
