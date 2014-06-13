/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public class ARESElementActionFilter implements IActionFilter {

	private static ARESElementActionFilter instance;
	
	public static ARESElementActionFilter getInstance() {
		if (instance == null)
			instance = new ARESElementActionFilter();
		return instance;
	}
	
	private static final String HAS_ERROR = "hasError";
	private static final String HAS_WORNING = "hasWarning";
	private static final String NATURE = "projectNature";
	private static final String ROOT_TYPE = "rootType";
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target instanceof IARESElement) {
			IARESElement element = (IARESElement)target;
			IResource resource = element.getResource();
			if (resource == null || !resource.exists())
				return false;
			
			int severity = -1;
			try {
				severity = resource.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			
			if (name.equals(HAS_ERROR)) {
				return severity == IMarker.SEVERITY_ERROR;
			} else if (name.equals(HAS_WORNING)) {
				return severity == IMarker.SEVERITY_WARNING;
			}
			
			if (name.equals(NATURE)) {
				try {
					IProject proj = resource.getProject();
					return proj.isAccessible() && proj.hasNature(value);
				} catch (CoreException e) {
					return false;
				}
			} else if (name.equals(ROOT_TYPE)) {
				IARESModuleRoot root = null;
				if (element instanceof IARESResource) {
					root = ((IARESResource) element).getRoot();
				} else if (element instanceof IARESModule) {
					root = ((IARESModule) element).getRoot();
				} else if (element instanceof IARESModuleRoot) {
					root = (IARESModuleRoot) element;
				}
				if (root  != null) {
					return StringUtils.equals(value, root.getType());
				} else  {
					return false;
				}
			}
			
		}
		return false;
	}

}
