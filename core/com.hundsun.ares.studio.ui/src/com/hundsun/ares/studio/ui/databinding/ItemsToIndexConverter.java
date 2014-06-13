/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.databinding;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.databinding.conversion.IConverter;

/**
 * 
 * @author gongyf
 */
public class ItemsToIndexConverter implements IConverter {

	private Object[] items;
	
	/**
	 * @param items
	 */
	public ItemsToIndexConverter(Object[] items) {
		super();
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		return ArrayUtils.indexOf(items, fromObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getFromType()
	 */
	public Object getFromType() {
		return Object.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getToType()
	 */
	public Object getToType() {
		return Integer.class;
	}

}
