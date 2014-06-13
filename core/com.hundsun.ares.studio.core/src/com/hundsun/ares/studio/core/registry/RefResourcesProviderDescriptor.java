/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.validate.IRefResourceProvider;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;

/**
 * 关联资源提供器描述
 * @author sundl
 */
public class RefResourcesProviderDescriptor extends CommonDescriptor {

	private static final Logger logger = Logger.getLogger(RefResourcesProviderDescriptor.class);
	private String resTypes;
	private IRefResourceProvider provider;
	
	public RefResourcesProviderDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	protected void loadFromExtension() {
		super.loadFromExtension();
		resTypes = configElement.getAttribute(ICommonExtensionConstants.RES_TYPES);
	}
	
	public String[] getResTypes() {
		return resTypes.split(",");
	}
	
	public IRefResourceProvider getProviderInstance() {
		if (provider == null) {
			try {
				provider = (IRefResourceProvider) configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
			} catch (CoreException e) {
				logger.warn("创建RefResourceProvider示例出错, ", e);
			}
		}
		return provider;
	}
	
}
