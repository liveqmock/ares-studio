package com.hundsun.ares.studio.ui;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IOrderable;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;

public class OrderableAdapterFactory implements IAdapterFactory {

	private static final Class[] types = new Class[] {IOrderable.class};
	
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IOrderable.class) {
			if (adaptableObject instanceof IARESModuleRoot) {
				IARESModuleRoot root = (IARESModuleRoot) adaptableObject;
				ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
				return reg.getModuleRootDescriptor(root.getType());
			} else if (adaptableObject instanceof IARESResource) {
				IARESResource res = (IARESResource) adaptableObject;
				ARESResRegistry reg = ARESResRegistry.getInstance();
				return reg.getResDescriptor(res.getType());
			} else if (adaptableObject instanceof ARESResourceCategory) {
				ARESResourceCategory cate = (ARESResourceCategory) adaptableObject;
				ARESResRegistry reg = ARESResRegistry.getInstance();
				return reg.getCategory(cate.getId());
			}
		}
		return null;
	}

	public Class[] getAdapterList() {
		return types;
	}

}
