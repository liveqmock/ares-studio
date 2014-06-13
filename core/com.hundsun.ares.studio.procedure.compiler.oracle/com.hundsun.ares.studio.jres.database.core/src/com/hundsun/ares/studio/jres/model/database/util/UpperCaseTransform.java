/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.model.database.util;

import com.hundsun.ares.studio.validate.IValueTransform;

/**
 * @author gongyf
 *
 */
public class UpperCaseTransform implements IValueTransform {

	public static final UpperCaseTransform INSTANCE = new UpperCaseTransform();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.util.validate.IValueTransform#transform(java.lang.Object)
	 */
	@Override
	public Object transform(Object value) {
		if (value instanceof String) {
			return ((String) value).toUpperCase();
		}
		return value;
	}

}
