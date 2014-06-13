/**
 * 源程序名称：PropertyHandlerFactoryManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.internal.core.registry.PropertyHandlerFactoryDescriptor;
import com.hundsun.ares.studio.internal.core.registry.PropertyHandlerFactoryRegistry;

/**
 * 
 * @author sundl
 */
public class PropertyHandlerFactoryManager {
	
	private static final Logger LOGGER = Logger.getLogger(PropertyHandlerFactoryManager.class);

	public static IPropertyHandlerFactory getPropertyHandlerFactory(EClass eClass) {
		Collection<PropertyHandlerFactoryDescriptor> descriptors = PropertyHandlerFactoryRegistry.INSTANCE.getDescriptors();
		for (PropertyHandlerFactoryDescriptor desc : descriptors) {
			IPropertyHandlerFactoryProvider provider = desc.createProvider();
			if (provider == null) {
				continue;
			}
			IPropertyHandlerFactory factory = provider.getFactory(eClass);
			if (factory != null) 
				return factory;
			else {
				LOGGER.error("找不到Eclass: " + eClass +"的PropertyHandlerFactory");
			}
		}
		return null;
	}
	
}
