/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;

/**
 * 
 * @author sundl
 */
public class AresContextDescriptor extends CommonDescriptor {

	private static Logger logger = Logger.getLogger(AresContextDescriptor.class);
	
	/**
	 * @param e
	 */
	public AresContextDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	public IAresContext createContext() {
		try {
			return (IAresContext) configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
		} catch (CoreException e) {
			//e.printStackTrace();
			logger.warn("创建Context失败！", e);
		}
		return null;
	}

}
