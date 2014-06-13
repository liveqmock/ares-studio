package com.hundsun.ares.studio.ui.databinding;

import org.eclipse.core.databinding.conversion.IConverter;

public class StringToEnumConverter implements IConverter {

	private Class<? extends Enum> enumClass;
	
	/**
	 * @param enumClass
	 */
	public StringToEnumConverter(Class<? extends Enum> enumClass) {
		super();
		this.enumClass = enumClass;
	}
	
	
	
	public Object convert(Object fromObject) {
		return Enum.valueOf(enumClass, fromObject.toString());
	}

	public Object getFromType() {
		return String.class;
	}

	public Object getToType() {
		return enumClass;
	}

}
