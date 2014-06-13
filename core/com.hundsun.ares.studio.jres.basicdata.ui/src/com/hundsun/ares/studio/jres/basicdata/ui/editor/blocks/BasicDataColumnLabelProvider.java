package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class BasicDataColumnLabelProvider extends EObjectColumnLabelProvider {
	IARESResource resource;
	public BasicDataColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider,IARESResource resource) {
		super(attributeProvider);
		this.resource = resource;
	}
	public BasicDataColumnLabelProvider(EStructuralFeature attribute,IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}
	@Override
	public String getText(Object element) {
		if(null == getEStructuralFeature(element)){
			return "";
		}
		String text = StringUtils.EMPTY;
		{

			EStructuralFeature attribute = getEStructuralFeature(element);
			EObject owner = getOwner(element);
			Map<String , EAttribute> attMap = new HashMap<String, EAttribute>();
			for(EAttribute att : owner.eClass().getEAllAttributes()){
				attMap.put(att.getName(), att);
			}
			if (attribute != null && owner != null && attMap.get(attribute.getName()) != null) {
				Object value = owner.eGet(attMap.get(attribute.getName()));
				if (value == null) {
					value = "";
				}
				text = String.valueOf(value );
			}
		
		}
		StandardField stdField = JRESResProviderUtil.getMetadataModel(resource.getARESProject(), getEStructuralFeature(element).getName(),IMetadataRefType.StdField , StandardField.class);
		if(stdField != null && StringUtils.isNotBlank(stdField.getDictionaryType())){
			DeDictionaryType dicType = MetadataUtil.decrypt(stdField, resource).getDictionaryType();
			EList<DeDictionaryItem> items = dicType.getItems();
			String[] dictItemValues = StringUtils.split(StringUtils.defaultIfBlank(text, ""),",");
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<dictItemValues.length;i++){
				String value = dictItemValues[i];
				for(DeDictionaryItem item : items){
					if(StringUtils.equals(item.getValue(), value)){
						if(i==dictItemValues.length-1){
							sb.append(item.getValue() + "_" + item.getChineseName());
						}else{
							sb.append(item.getValue() + "_" + item.getChineseName()).append("||");
						}
						
					}
				}	
			}
			text = sb.toString();
			
		}
		return text;
	}

	@Override
	public Color getBackground(Object element) {
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}
	
}
