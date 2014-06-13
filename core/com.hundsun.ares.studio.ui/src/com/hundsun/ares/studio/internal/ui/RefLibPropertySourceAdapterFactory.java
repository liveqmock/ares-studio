package com.hundsun.ares.studio.internal.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import com.hundsun.ares.studio.core.IReferencedLibrary;

public class RefLibPropertySourceAdapterFactory implements IAdapterFactory {

	private static final Class[] adapters = new Class[] {IPropertySource.class};
	
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof IReferencedLibrary) {
			IReferencedLibrary lib = (IReferencedLibrary) adaptableObject;
			if (adapterType == IPropertySource.class) {
				if (lib.getResource() != null)
					return new RefLibPropertySource(lib);
				else {
					ReferencedLibPropertySource basicSource = new ReferencedLibPropertySource(lib);
					return new ExternalRefLibPropertySource(basicSource);
				}
					
			}
		}
		
		return null;
	}

	public Class[] getAdapterList() {
		return adapters;
	}

}
