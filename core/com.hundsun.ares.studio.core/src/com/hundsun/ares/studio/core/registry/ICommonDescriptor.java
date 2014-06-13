/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * 基本的Descriptor
 * @author sundl
 */
public interface ICommonDescriptor {

	String getId();
	String getName();
	ImageDescriptor getImageDescriptor();
	IConfigurationElement getConfigurationElement();
	
}

