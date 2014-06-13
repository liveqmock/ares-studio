/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IExternalResPathEntry;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.IResPathEntryElement;

/**
 * RespathEntryElement(引用包，引用工程)的ActionFilter. 
 * plugin.xml里需要配置objectstate的时候会调用到这个类。
 * @author sundl
 */
public class RespathEntryElementActionFilter implements IActionFilter {

	private static final String EXTERNAL = "external";
	
	private static RespathEntryElementActionFilter instance;
	
	private RespathEntryElementActionFilter() {}
	
	public static RespathEntryElementActionFilter getInstance() {
		if (instance == null) {
			instance = new RespathEntryElementActionFilter();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if (target instanceof IResPathEntryElement) {
			IResPathEntryElement respathElement = (IResPathEntryElement) target;
			IResPathEntry entry = respathElement.getResPathEntry();
			if (StringUtils.equals(EXTERNAL, name)) {
				boolean external = entry instanceof IExternalResPathEntry;
				return StringUtils.equals(value, String.valueOf(external));
			}
		}
		return false;
	}

}
