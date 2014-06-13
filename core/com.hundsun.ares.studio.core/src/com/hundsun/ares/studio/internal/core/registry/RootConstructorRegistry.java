/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.CommonMapRegistry;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IRootConstructorDescriptor;
import com.hundsun.ares.studio.core.registry.IRootConstructorRegistry;

/**
 * 
 * @author sundl
 */
public class RootConstructorRegistry extends CommonMapRegistry<IRootConstructorDescriptor> implements IRootConstructorRegistry{

	private static RootConstructorRegistry instance;
	
	private RootConstructorRegistry() {
		super();
	}
	
	public static RootConstructorRegistry getInstance() {
		if (instance == null)
			instance = new RootConstructorRegistry();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return ICommonExtensionConstants.EP_ID_ROOT_CONSTRUCTOR;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		IRootConstructorDescriptor desc = new RootConstructorDescriptor(element);
		map.put(desc.getRootId(), desc);
	}

}
