/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author gongyf
 *
 */
public abstract class AbstractMapEMPropertyDescriptor extends
		AbstractEMPropertyDescriptor implements IMapExtensibleModelPropertyDescriptor {

	private Object key;
	private String extendModelKey;
	
	
	public AbstractMapEMPropertyDescriptor(
			EStructuralFeature structuralFeature, Object key,String extendModelKey) {
		super(structuralFeature);
		this.key = key;
		this.extendModelKey = extendModelKey;
	}

	public Object getKey() {
		return key;
	}
	
	public String getExtendModelKey() {
		return extendModelKey;
	}

}
