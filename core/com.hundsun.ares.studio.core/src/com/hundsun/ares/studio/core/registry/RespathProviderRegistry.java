/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.IRespathProvider;
import com.hundsun.ares.studio.internal.core.registry.RespathProviderDescriptor;

/**
 * 
 * @author sundl
 */
public class RespathProviderRegistry extends CommonMapRegistry<IRespathProviderDescriptor> {

	private static RespathProviderRegistry instance;
	
	private RespathProviderRegistry() {}
	
	public static RespathProviderRegistry getInstance() {
		if (instance == null)
			instance = new RespathProviderRegistry();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return ICommonExtensionConstants.EP_ID_RESPATH_PROVIDER;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		RespathProviderDescriptor desc = new RespathProviderDescriptor(element);
		map.put(desc.getId(), desc);
	}

	// 2012-2-24 sundl 修改为返回Descriptor，方便解析respath的时候记录provider
	public Collection<IRespathProviderDescriptor> getProviders() {
		List<IRespathProviderDescriptor> providers = new ArrayList<IRespathProviderDescriptor>();
		for (IRespathProviderDescriptor desc : map.values()) {
			providers.add(desc);
		}
		return providers;
	}
	
}
