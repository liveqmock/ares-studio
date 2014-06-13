/**
 * 源程序名称：PropertyHandlerFactoryRegistry.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.CommonMapRegistry;

/**
 * 
 * @author dollyn
 */
public class PropertyHandlerFactoryRegistry extends CommonMapRegistry<PropertyHandlerFactoryDescriptor>{

	public static PropertyHandlerFactoryRegistry INSTANCE = new PropertyHandlerFactoryRegistry();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "propertyHandlerFactory";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		PropertyHandlerFactoryDescriptor desc = new PropertyHandlerFactoryDescriptor(element);
		map.put(desc.getId(), desc);
	}

}
