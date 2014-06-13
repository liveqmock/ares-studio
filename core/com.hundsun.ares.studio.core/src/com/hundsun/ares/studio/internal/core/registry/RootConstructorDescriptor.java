/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.IModuleRootConstructor;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IRootConstructorDescriptor;

/**
 * 
 * @author sundl
 */
public class RootConstructorDescriptor extends CommonDescriptor implements IRootConstructorDescriptor {

	private String rootId;
	
	public RootConstructorDescriptor(IConfigurationElement e) {
		super(e);		
	}

	protected void loadFromExtension() {
		super.loadFromExtension();
		rootId = configElement.getAttribute(ICommonExtensionConstants.ROOT_TYPE);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IRootConstructorDescriptor#createConstructor()
	 */
	public IModuleRootConstructor createConstructor() {
		String className = configElement.getAttribute("class");
		if (className != null) {
			try {
				return (IModuleRootConstructor)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IRootConstructorDescriptor#getRootId()
	 */
	public String getRootId() {
		return rootId;
	}

}
