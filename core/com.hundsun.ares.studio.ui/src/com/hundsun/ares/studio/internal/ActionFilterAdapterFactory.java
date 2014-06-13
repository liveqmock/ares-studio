package com.hundsun.ares.studio.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ReferencedLibrary;
import com.hundsun.ares.studio.ui.ARESElementActionFilter;
import com.hundsun.ares.studio.ui.AresResourceActionFilter;
import com.hundsun.ares.studio.ui.ReferenceLibActionFilter;

public class ActionFilterAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof IARESElement && adapterType.equals(IActionFilter.class)) {
			if (adaptableObject instanceof IARESResource) {
				return AresResourceActionFilter.getInstance();
			} else if (adaptableObject instanceof ReferencedLibrary) {// 2012-3-1 sundl 引用包有自己的扩展属性，交给特定的类处理。
				return ReferenceLibActionFilter.getInstance();
			}
			IARESElement element = (IARESElement) adaptableObject;
			return ARESElementActionFilter.getInstance();
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] {IActionFilter.class};
	}

}
