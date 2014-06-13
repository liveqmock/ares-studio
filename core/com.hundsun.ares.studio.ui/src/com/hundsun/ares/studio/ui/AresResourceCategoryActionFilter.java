/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IActionFilter;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public class AresResourceCategoryActionFilter implements IActionFilter {
	
	private static final String HAS_ERROR = "hasError";
	private static final String HAS_WORNING = "hasWarning";

	private static AresResourceCategoryActionFilter instance;
	
	public static AresResourceCategoryActionFilter getInstance() {
		if (instance == null) {
			instance = new AresResourceCategoryActionFilter();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target instanceof ARESResourceCategory) {
			ARESResourceCategory cate = (ARESResourceCategory) target;
			for (IARESResource res : cate.getResources()) {
				IResource resource = res.getResource();
				if (resource != null) {
					
					int severity = -1;
					try {
						severity = resource.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
					if (name.equals(HAS_ERROR) && severity == IMarker.SEVERITY_ERROR) {
						return true;
					} else if (name.equals(HAS_WORNING) && severity == IMarker.SEVERITY_WARNING) {
						return true;
					} else {
						// 没有检查到错误则继续检查下面的资源，直到检查到错误，或者全部资源检查完
					}
				}
			}
		}
		return false;
	}

}
