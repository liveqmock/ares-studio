package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class MetadataDescColumnLabelProvider extends
		MetadataColumnLabelProvider {

	public MetadataDescColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider,
			IARESResource resource) {
		super(attributeProvider, resource);
	}
	
	public MetadataDescColumnLabelProvider(EAttribute attribute,
			IARESResource resource) {
		super(attribute, resource);
	}


	@Override
	public String getToolTipText(Object element) {
		String text = super.getToolTipText(element);
		if(StringUtils.isBlank(text)){
			return getText(element);
		}
		return text;
	}
}
