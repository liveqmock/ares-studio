/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.internal.core.registry.ProjectValidatorDescriptor;

/**
 * 
 * @author sundl
 */
public class ProjectValidatorRegistry extends CommonMapRegistry<IProjectValidatorDescriptor> {

	private static ProjectValidatorRegistry instance;
	
	private ProjectValidatorRegistry() {}
	
	public static ProjectValidatorRegistry getInstance() {
		if (instance == null)
			instance = new ProjectValidatorRegistry();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "projectPropertyValidator";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		ProjectValidatorDescriptor desc = new ProjectValidatorDescriptor(element);
		map.put(desc.getNature(), desc);
	}

}
