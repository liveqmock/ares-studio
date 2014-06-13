/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.validate.IRefResourceProvider;

/**
 * 
 * @author sundl
 */
public class RefResourcesProviderRegistry extends CommonMapRegistry<RefResourcesProviderDescriptor> {

	private static RefResourcesProviderRegistry instance;
	
	public static RefResourcesProviderRegistry getInstance() {
		if (instance == null)
			instance = new RefResourcesProviderRegistry();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "refResourcesProvider";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		RefResourcesProviderDescriptor desc = new RefResourcesProviderDescriptor(element);
		for (String resType : desc.getResTypes()) {
			map.put(resType, desc);
		}		
	}
	
	public List<IRefResourceProvider> getProviders(String type) {
		Collection<RefResourcesProviderDescriptor> descs = map.get(type);
		List<IRefResourceProvider> pds = new ArrayList<IRefResourceProvider>();
		for (RefResourcesProviderDescriptor desc : descs) {
			pds.add(desc.getProviderInstance());
		}
		return pds;
	}

}
