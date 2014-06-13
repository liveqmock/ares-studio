/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.core.model.extendable.ExtendAbleModel;
import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 模块根属性
 * @author sundl
 */
public class ModuleRootProperty extends ExtendAbleModel implements ICommonModel{
	Map<String, String> properties = new HashMap<String, String>();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#getString(java.lang.String)
	 */
	public String getString(String key) {
		Object value = getValue(key);
		if (value == null)
			return StringUtil.EMPTY_STR;
		return String.valueOf(value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#getValue(java.lang.String)
	 */
	public Object getValue(String key) {
		return properties.get(key);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#setValue(java.lang.String, java.lang.Object)
	 */
	public void setValue(String key, Object value) {
		String valueStr = StringUtil.EMPTY_STR;
		if (value != null)
			valueStr = String.valueOf(value);
		
		properties.put(key, valueStr);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ModuleRootProperty)) {
			return false;
		}
		ModuleRootProperty other = (ModuleRootProperty) obj;
		return properties.equals(other.properties) && super.equals(obj);
	}
	
}
