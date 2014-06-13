package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeList;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

public class IDExtendLabelProvider extends EObjectColumnLabelProvider {

	IExtensibleModelEditingSupport editingSupport;
	IExtensibleModelPropertyDescriptor descriptor;
	IDRangeList info;
	
	/**
	 * @param editingSupport
	 * @param descriptior
	 */
	public IDExtendLabelProvider(
			IExtensibleModelEditingSupport editingSupport,
			IExtensibleModelPropertyDescriptor descriptor , IDRangeList info) {
		super(descriptor.getStructuralFeature());
		this.editingSupport = editingSupport;
		this.descriptor = descriptor;
		this.info = info;
	}
	
	@Override
	protected EObject getOwner(Object element) {
		IDRangeItem item = getIDRangeItem(element);
		if(item != null){
			return item.getData2().get(editingSupport.getKey());
		}
		return null;
	}
	
	private IDRangeItem getIDRangeItem(Object element){
		if(element instanceof IARESElement){
			String path = ((IARESElement)element).getResource().getProjectRelativePath().toPortableString();
			for(IDRangeItem item : info.getItems()){
				if(StringUtils.equals(item.getName(), path)){
					return item;
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		EObject owner = getOwner(element);
		EStructuralFeature feature = descriptor.getStructuralFeature();
		if (owner != null) {
			ILabelProvider labelProvider = descriptor.getLabelProvider();
			Object value = owner.eGet(feature);
			return labelProvider.getText(value);
			
		}
		return feature.getDefaultValueLiteral();
	}
	
	@Override
	public Color getBackground(Object element) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(info);
		if(editingDomain != null && editingDomain.isReadOnly(info.eResource())){
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		//这里有一个默认约束，name为资源的类型组成，多个以逗号隔开
		String[] types = StringUtils.split(editingSupport.getName(), ",");
		if(element instanceof IARESModule){
			String type = ((IARESModule)element).getRoot().getType();
			for(String resType : types){
				String[] rootTypes = ModuleRootType2ResTypeMap.getInstance().getAllowedRootTypes(resType);
				for(String rootType : rootTypes){
					if(StringUtils.equals(type, rootType)){
						return super.getBackground(element);
					}
				}
			}
		}
		return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}
}
