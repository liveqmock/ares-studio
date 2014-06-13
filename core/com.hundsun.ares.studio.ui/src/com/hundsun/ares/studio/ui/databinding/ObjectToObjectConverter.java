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
public class ObjectToObjectConverter implements IConverter {

	private Object[] fromObjects;
	private Object[] toObjects;

	/**
	 * @param fromObjects
	 * @param toObjects
	 */
	public ObjectToObjectConverter(Object[] fromObjects, Object[] toObjects) {
		super();
		this.fromObjects = fromObjects;
		this.toObjects = toObjects;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		int index = ArrayUtils.indexOf(fromObjects, fromObject);
		return index != ArrayUtils.INDEX_NOT_FOUND ? toObjects[index] : "";
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
		return Object.class;
	}

}
