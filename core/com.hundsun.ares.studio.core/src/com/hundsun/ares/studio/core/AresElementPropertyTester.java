/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

/**
 * 
 * @author sundl
 */
public class AresElementPropertyTester extends PropertyTester {

	protected static final String PROJECT_NATURE = "projectNature";
	protected static final String NAME = "name";
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof IARESElement))
			return false;
		
		IARESElement element = (IARESElement) receiver;
		if (NAME.equals(property)) {
			return String.valueOf(expectedValue).equals(element.getElementName());
		} else if (PROJECT_NATURE.equals(property)) {
			try {
				IProject proj = element.getARESProject().getProject();
				return proj != null && proj.isAccessible() && proj.hasNature(String.valueOf(expectedValue));
			} catch (CoreException e) {
				return false;
			}
		}
		
		return false;
	}

}
