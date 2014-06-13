package com.hundsun.ares.studio.internal.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;

public class ModulePropertyFilter extends ViewerFilter {

	// 过滤模块属性节点
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IARESResource) {
			IARESResource resource = (IARESResource)element;
			return !resource.getElementName().equals(IARESModule.MODULE_PROPERTY_FILE);
		}
		return true;
	}

}
