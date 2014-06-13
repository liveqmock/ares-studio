/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.databinding;

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * 
 * @author gongyf
 */
public class IndexToItemsConverter implements IConverter {

	private Object[] items;
	
	/**
	 * @param items
	 */
	public IndexToItemsConverter(Object[] items) {
		super();
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		return items[(Integer)fromObject];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getFromType()
	 */
	public Object getFromType() {
		return Integer.class;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getToType()
	 */
	public Object getToType() {
		return Object.class;
	}

}
