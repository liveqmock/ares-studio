package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class MenuHeaderColumnLabelProvider extends EObjectColumnLabelProvider {

	private static Image IMG_MENU = MetadataUI.getImage2("icons/full/obj16/menu.gif");
	private IARESResource resource;
	
	public MenuHeaderColumnLabelProvider(EStructuralFeature attribute , IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}
	
	public MenuHeaderColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}
	
	@Override
	protected Image doGetImage(Object element) {
		if (element instanceof MenuItem) {
			return IMG_MENU;
		}
		return super.doGetImage(element);
	}
	
	@Override
	public Color getBackground(Object element) {
		if(element instanceof MenuItem){
			MenuItem item = (MenuItem)element;
			if(StringUtils.isNotBlank(item.getRefId())){
				return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
			}
		}
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}

}
