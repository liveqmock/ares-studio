/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.ui.celleditor.LongTxtCellEditor;


/**
 * @author gongyf
 *
 */
public class LongTextMapEMPropertyDescriptor extends TextMapEMPropertyDescriptor {

	public LongTextMapEMPropertyDescriptor(
			EStructuralFeature structuralFeature, Object key) {
		this(structuralFeature, key,Constants.USER_DATA2_KEY);
	}

	public LongTextMapEMPropertyDescriptor(
			EStructuralFeature structuralFeature, Object key,String extendModelKey) {
		super(structuralFeature, key,extendModelKey);
	}
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new LongTxtCellEditor(parent);
//		return super.createPropertyEditor(parent);
	}
	

}
