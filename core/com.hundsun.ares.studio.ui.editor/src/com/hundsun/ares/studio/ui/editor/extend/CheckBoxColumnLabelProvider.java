package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author wangxh
 *
 */
public class CheckBoxColumnLabelProvider extends ColumnLabelProvider {
	
	private CheckBoxLabelProvider provider = new CheckBoxLabelProvider();
	private IEStructuralFeatureProvider attributeProvider;
	private IARESResource resource ;

	/**
	 * @param attribute
	 */
	public CheckBoxColumnLabelProvider(EStructuralFeature attribute , IARESResource resource) {
		this(new IEStructuralFeatureProvider.Impl(attribute));
		this.resource = resource;
	}

	/**
	 * @param attributeProvider
	 */
	public CheckBoxColumnLabelProvider(IEStructuralFeatureProvider attributeProvider) {
		super();
		this.attributeProvider = attributeProvider;
	}
	
	public EStructuralFeature getEStructuralFeature(Object element) {
		return attributeProvider.getFeature(element);
	}
	
	public void setAttributeProvider(IEStructuralFeatureProvider attributeProvider) {
		this.attributeProvider = attributeProvider;
	}
	
	@Override
	public String getText(Object element) {
		return provider.getText(getValue(element));
	}

	@Override
	public Image getImage(Object element) {
		return provider.getImage(getValue(element));
	}
	
	@Override
	public Color getBackground(Object element) {
		if (resource != null && resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}
	
	protected Object getValue(Object element) {
		EStructuralFeature attribute = getEStructuralFeature(element);
		EObject owner = (EObject) element;
		
		return owner.eGet(attribute);
	}

}
