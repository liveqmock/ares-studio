/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESElement;


/**
 * @author gongyf
 *
 */
public abstract class AbstractExtensibleModelEditingSupport implements
		IExtensibleModelEditingSupport {
	
	protected final static IExtensibleModelPropertyDescriptor[] EMPTY = new IExtensibleModelPropertyDescriptor[0];
	
	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		return true;
	}
	
	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(
			IARESElement aresElement, EClass eClass) {
		return EMPTY; 
	}
}
