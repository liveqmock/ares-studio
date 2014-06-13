/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.editingsupport;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.ui.editor.celleditors.EEnumComboBoxCellEditor;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author gongyf
 *
 */
public class EnumEditingSupport extends EMFEditingSupport {

	private ILabelProvider itemLableProvider = new LabelProvider();

	/**
	 * @param viewer
	 * @param feature
	 */
	public EnumEditingSupport(ColumnViewer viewer, EStructuralFeature feature) {
		super(viewer, feature);
	}

	/**
	 * @param viewer
	 * @param featureProvider
	 */
	public EnumEditingSupport(ColumnViewer viewer,
			IEStructuralFeatureProvider featureProvider) {
		super(viewer, featureProvider);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.BaseEditingSupport#createCellEditor()
	 */
	@Override
	protected CellEditor createCellEditor() {
		return new EEnumComboBoxCellEditor((Composite) getViewer().getControl());
	}
	
	/**
	 * @param itemLableProvider the itemLableProvider to set
	 */
	public void setItemLableProvider(ILabelProvider itemLableProvider) {
		this.itemLableProvider = itemLableProvider;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.BaseEditingSupport#doGetCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor doGetCellEditor(Object element) {
		EEnumComboBoxCellEditor cellEditor = (EEnumComboBoxCellEditor) super.doGetCellEditor(element);
		EStructuralFeature feature = getFeature(element);
		if (feature.getEType() instanceof EEnum) {
			EEnum eEnum = (EEnum) feature.getEType();
			cellEditor.setEEnum(eEnum, itemLableProvider);
		} else {
			cellEditor = null;
		}
		
		return cellEditor;
	}

}
