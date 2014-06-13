package com.hundsun.ares.studio.core.model;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.hundsun.ares.studio.core.model.converter.ExtendableModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;


public class ModulePropertyConverter extends ExtendableModelConverter implements IModelConverter {

	private static final String INFO = "info";
	private static ModulePropertyConverter instance;
	
	public static ModulePropertyConverter getInstance() {
		if (instance == null)
			instance = new ModulePropertyConverter();
		return instance;
	}
	
	public ModulePropertyConverter() {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void readNonExtendedProperties(Element root, Object info) {
		ModuleProperty propertie = (ModuleProperty)info;
		Element infoElement = root.element(INFO);
		if (infoElement != null) {
			List<Attribute> attributes = infoElement.attributes();
			for (Attribute attr : attributes) {
				propertie.setValue(attr.getName(), attr.getValue());
			}
		}
	}

	@Override
	protected void writeNonExtendedProperties(Element root, Object info) {
		ModuleProperty property = (ModuleProperty)info;
		Element infoElement = root.addElement(INFO);
		for (String key : property.properties.keySet()) {
			infoElement.addAttribute(key, property.getString(key));
		}
	}

}
