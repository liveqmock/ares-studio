/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.celleditors.BooleanComboBoxCellEditor;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author gongyf
 *
 */
public class BooleanEditingSupport extends EMFEditingSupport {
	
	/**
	 * @param viewer
	 * @param feature
	 */
	public BooleanEditingSupport(ColumnViewer viewer, EStructuralFeature feature) {
		super(viewer, feature);
	}

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public BooleanEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider) {
		super(viewer, featureProvider);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.BaseEditingSupport#createCellEditor()
	 */
	@Override
	protected CellEditor createCellEditor() {
		return new BooleanComboBoxCellEditor((Composite) getViewer().getControl(), "ÊÇ", "·ñ");
	}

}
