/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;

/**
 * 项目属性
 * @author sundl
 */
public class ProjectPropertyConverter extends ExtendableModelConverter {
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String VERSION = "version";
	private static final String CONTACT = "contact";
	private static final String DEPENDENCIES = "dependencies";
	private static final String EXT = "ext";
	private static final String NOTE = "note";
	
	private static ProjectPropertyConverter instance;
	
	public static ProjectPropertyConverter getInstance() {
		if (instance == null)
			instance = new ProjectPropertyConverter();
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.ExtendableModelConverter#readNonExtendedProperties(org.dom4j.Element, java.lang.Object)
	 */
	@Override
	protected void readNonExtendedProperties(Element root, Object info) {
		ARESProjectProperty properties = (ARESProjectProperty)info;
		Element id = root.element(ID);
		if (id != null)
			properties.setId(id.getText());
		
		Element name = root.element(NAME);
		if (name != null)
			properties.setName(name.getText());
		
		Element version = root.element(VERSION);
		if (version != null)
			properties.setVersion(version.getText());
		
		Element contact = root.element(CONTACT);
		if (contact != null)
			properties.setContact(contact.getText());
		
		Element note = root.element(NOTE);
		if (note != null)
			properties.setNote(note.getText());
		
		Element ext = root.element(EXT);
		if (ext != null) {
			for (Object obj : ext.attributes()) {
				Attribute attr = (Attribute)obj;
				properties.setValue(attr.getName(), attr.getValue());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.ExtendableModelConverter#writeNonExtendedProperties(org.dom4j.Element, java.lang.Object)
	 */
	@Override
	protected void writeNonExtendedProperties(Element root, Object info) {
		ARESProjectProperty properties = (ARESProjectProperty)info;
		Element id = root.addElement(ID);
		id.setText(StringUtil.getStringSafely(properties.getId()));
		Element name = root.addElement(NAME);
		name.setText(StringUtil.getStringSafely(properties.getName()));
		Element version = root.addElement(VERSION);
		version.setText(StringUtil.getStringSafely(properties.getVersion()));
		Element contact = root.addElement(CONTACT);
		contact.setText(StringUtil.getStringSafely(properties.getContact()));
		Element note = root.addElement(NOTE);
		note.setText(StringUtil.getStringSafely(properties.getNote()));
		Element ext = root.addElement(EXT);
		Map<String, Object> extProperties = properties.getProperties();
		for (String extKeys : extProperties.keySet()) {
			Object obj = extProperties.get(extKeys);
			if (obj != null) {
				String value = String.valueOf(obj);
				ext.addAttribute(extKeys, value);
			}
		}
	}

}
