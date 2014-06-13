/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public class AresResourceActionFilter extends ARESElementActionFilter implements IActionFilter {

	public static final String ATTR_TYPE = "type";
	
	private static AresResourceActionFilter instance;
	
	public static AresResourceActionFilter getInstance() {
		if (instance == null) {
			instance = new AresResourceActionFilter();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target instanceof IARESResource) {
			IARESResource res = (IARESResource) target;
			if (ATTR_TYPE.equals(name)) {
				return value.equals(res.getType());
			}
		}
		return super.testAttribute(target, name, value);
	}

}
