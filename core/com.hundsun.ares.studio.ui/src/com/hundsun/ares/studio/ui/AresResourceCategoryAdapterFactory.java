package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.internal.ui.ARESResourceCategoryResourceMapping;

// 2012-03-28 sundl 增加对ResourceMapping的适配。
public class AresResourceCategoryAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof ARESResourceCategory) {
			ARESResourceCategory category = (ARESResourceCategory) adaptableObject;
			if (adapterType.equals(IActionFilter.class)) {
				return AresResourceCategoryActionFilter.getInstance();
			} else if (adapterType.equals(ResourceMapping.class)) {
				return new ARESResourceCategoryResourceMapping(category);
			}
		}
		
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] {IActionFilter.class, ResourceMapping.class};
	}

}
