/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.celleditors.EEnumComboBoxCellEditor;

/**
 * @author qinyuan
 *
 */
public class ComboBoxEMPropertyDescriptor extends ReadonlyTextEMPropertyDescriptor{

	EEnum eEnum;
	ILabelProvider itemLabelProvider;
	/**
	 * @param structuralFeature
	 */
	public ComboBoxEMPropertyDescriptor(EStructuralFeature structuralFeature,EEnum eEnum, ILabelProvider itemLabelProvider) {
		super(structuralFeature);
		this.eEnum = eEnum;
		this.itemLabelProvider = itemLabelProvider;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.ReadonlyTextEMPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		EEnumComboBoxCellEditor ce = new EEnumComboBoxCellEditor(parent);
		ce.setEEnum(eEnum, itemLabelProvider);
		 
		return ce;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.ReadonlyTextEMPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		if(null != itemLabelProvider) {
			return itemLabelProvider;
		}
		return super.getLabelProvider();
	}

}
