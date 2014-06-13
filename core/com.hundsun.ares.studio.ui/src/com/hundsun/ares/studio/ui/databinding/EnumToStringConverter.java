package com.hundsun.ares.studio.ui.databinding;

import org.eclipse.core.databinding.conversion.IConverter;


public class EnumToStringConverter implements IConverter {

	private Class<? extends Enum> enumClass;
	
	/**
	 * @param enumClass
	 */
	public EnumToStringConverter(Class<? extends Enum> enumClass) {
		super();
		this.enumClass = enumClass;
	}

	public Object convert(Object fromObject) {
		return ((Enum)fromObject).name();
	}

	public Object getFromType() {
		return enumClass;
	}

	public Object getToType() {
		return String.class;
	}

}
