/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.extend.AbstractEMPropertyDescriptor;

/**
 * @author gongyf
 *
 */
public class TableModifyEMPropertyDescriptor extends
		AbstractEMPropertyDescriptor {

	IARESResource resource;
	
	public TableModifyEMPropertyDescriptor(EStructuralFeature structuralFeature, IARESResource resource) {
		super(structuralFeature);
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new ModifyHistoryDialogLabelProvider();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new ModifyHistoryDialogCellEditor(parent, resource);
	}

}
