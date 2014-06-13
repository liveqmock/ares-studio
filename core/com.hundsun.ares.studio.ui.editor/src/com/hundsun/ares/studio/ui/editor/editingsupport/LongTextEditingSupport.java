/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.celleditor.TextAndDialogCellEditor;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author yanwj06282
 *
 */
public class LongTextEditingSupport extends TextEditingSupport {

	/**
	 * @param viewer
	 * @param feature
	 */
	public LongTextEditingSupport(ColumnViewer viewer,
			EStructuralFeature feature) {
		super(viewer, feature);
	}

	/**
	 * 
	 */
	public LongTextEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider) {
		super(viewer, featureProvider);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.TextEditingSupport#createCellEditor()
	 */
	@Override
	protected CellEditor createCellEditor() {
		return new TextAndDialogCellEditor((Composite)getViewer().getControl());
	}
	

}
