package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.IEMLabelProviderExtension;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

public class MenuExtensibleModelColumnLabelProvider extends
		ExtensibleModelColumnLabelProvider {

	IExtensibleModelEditingSupport editingSupport;
	IExtensibleModelPropertyDescriptor descriptor;
	IARESResource resource;
	
	public MenuExtensibleModelColumnLabelProvider(
			IExtensibleModelEditingSupport editingSupport,
			IExtensibleModelPropertyDescriptor descriptor,
			IARESResource resource) {
		super(editingSupport, descriptor , resource);
		this.editingSupport = editingSupport;
		this.descriptor = descriptor;
		this.resource = resource;
		
	}
	
	@Override
	public Color getBackground(Object element) {
		if(element instanceof MenuItem){
			MenuItem item = (MenuItem)element;
			if(StringUtils.isNotBlank(item.getRefId())){
				return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
			}
		}
		return super.getBackground(element);
	}
	
	@Override
	public String getText(Object element) {
		Object item = MenuUtils.resolve((MenuItem) element, resource).first;
		if(item == null){
			item = element;
		}
		EObject owner = getOwner(item);
		if (owner != null) {
			EStructuralFeature feature = descriptor.getStructuralFeature();
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			if (labelProvider instanceof IEMLabelProviderExtension) {
				((IEMLabelProviderExtension) labelProvider).setExtensibleModel((ExtensibleModel) element);
			}
			
			Object value = owner.eGet(feature);
			if (descriptor instanceof IMapExtensibleModelPropertyDescriptor) {
				Assert.isTrue(value instanceof EMap<?, ?>);
				value = ((EMap<?, ?>) value).get(((IMapExtensibleModelPropertyDescriptor) descriptor).getKey());
			}
			
			return labelProvider.getText(value);
		}
		return StringUtils.EMPTY;
	}
}
