/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author liaogc
 * 
 */
public class MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider
		extends EObjectColumnLabelProvider{ 
	private EAttribute attribute; 

	/**
	 * @param attribute
	 */
	public MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider(
			EAttribute attribute) {
		super(attribute);
		this.attribute = attribute;
	}

	/**
	 * @param attributeProvider
	 */
	public MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}

	/*
	 * (non-Javadoc)      
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		EObject owner = getOwner(element).eContainer();
		if (attribute != null && owner != null && owner.eClass().getEAllAttributes().contains(attribute)) {
			Object value = owner.eGet(attribute);
			if (value == null) {
				value = "";
			}
			return String.valueOf(value );
		}
		return "";
	}

}
