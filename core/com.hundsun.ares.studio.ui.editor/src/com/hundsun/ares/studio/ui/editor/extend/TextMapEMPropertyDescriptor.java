/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.Constants;

/**
 * @author gongyf
 *
 */
public class TextMapEMPropertyDescriptor extends
		AbstractMapEMPropertyDescriptor {

	public TextMapEMPropertyDescriptor(EStructuralFeature structuralFeature,
			Object key) {
		this(structuralFeature, key,Constants.USER_DATA2_KEY);
	}
	
	public TextMapEMPropertyDescriptor(EStructuralFeature structuralFeature,
			Object key,String extendModelKey) {
		super(structuralFeature,key,extendModelKey);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new ExtendsLabelProvider((String)getKey(),getStructuralFeature(),getExtendModelKey());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new TextCellEditor(parent);
	}

}
