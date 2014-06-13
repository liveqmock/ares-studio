/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.Map;

/**
 * @author lvgao
 * 
 */
public class UserOptionControl implements IControl {

	protected String ID, text, value, defaultValue, type,controlType ,moduleRoot;

	protected Object control;

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String itemType) {
		this.controlType = itemType;
	}

	public String getModuleRoot() {
		return moduleRoot;
	}

	public void setModuleRoot(String moduleRoot) {
		this.moduleRoot = moduleRoot;
	}

	/**
	 * @return the control
	 */
	public Object getControl() {
		return control;
	}

	/**
	 * @param control
	 *            the control to set
	 */
	public void setControl(Object control) {
		this.control = control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.script.internal.useroption.IUserOption#setValue
	 * (java.util.Map)
	 */
	@Override
	public void setOptionValue(Map<String, Object> option) {
		option.put(getID(), defaultValue);
	}

}
