/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.IRespathProvider;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor;

public class RespathProviderDescriptor extends CommonDescriptor implements IRespathProviderDescriptor{

	private IRespathProvider provider;
	
	public RespathProviderDescriptor(IConfigurationElement e) {
		super(e);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor#getProvider()
	 */
	@Override
	public IRespathProvider getProvider() {
		if (provider == null) {
			try {
				provider = (IRespathProvider)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
			} catch (CoreException e) {
				//e.printStackTrace();
				// cann't create instance, use default
			}
		}
		return provider;
	}

}
