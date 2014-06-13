package com.hundsun.ares.studio.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IResPathEntryElement;

public class RespathEntryElementAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof IResPathEntryElement && adapterType.equals(IActionFilter.class)) {
			return RespathEntryElementActionFilter.getInstance();
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IActionFilter.class};
	}

}
