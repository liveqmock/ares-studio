/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

/**
 * Factory。
 * @author sundl
 */
public interface IARESElementFactory {

	IARESProject create(IProject project);

	IARESElement create(IResource resource);
	
	IARESElement create(IPath path);
	
	IARESElement create(String handleIdentifier);
	
	IARESModel getModel();
}
