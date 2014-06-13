/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 
 * @author sundl
 */
public class ARESElementAdapterFactory implements IAdapterFactory {

	private static Class[] ADAPTERS = new Class[] {
		IResource.class
	};
	
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		IARESElement element = getARESElement(adaptableObject);
		if (adapterType.equals(IResource.class)) {
			if (element != null) {
				return element.getResource();
			}
		}
		return null;
	}

	public Class[] getAdapterList() {
		return ADAPTERS;
	}

	private IARESElement getARESElement(Object element) {
		if (element instanceof IARESElement) {
			return (IARESElement)element;
		}
		return null;		
	}
	
}
