/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model;

import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 
 * @author sundl
 */
public abstract class CommonModel implements ICommonModel {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#getString(java.lang.String)
	 */
	public String getString(String key) {
		Object value = getValue(key);
		if (value == null)
			return StringUtil.EMPTY_STR;
		return String.valueOf(value);
	}

}
