/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;

/**
 * 
 * @author sundl
 */
public class UserExtendedPropertyTypeProviderDescriptor extends CommonDescriptor{

	private IUserExtendPropertyTypeProvider provider;
	
	/**
	 * @param e
	 */
	public UserExtendedPropertyTypeProviderDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	public IUserExtendPropertyTypeProvider get() {
		try {
			provider = (IUserExtendPropertyTypeProvider) configElement.createExecutableExtension("class");
		} catch (CoreException e) {
				e.printStackTrace();
		}
		return provider;
	}

}
