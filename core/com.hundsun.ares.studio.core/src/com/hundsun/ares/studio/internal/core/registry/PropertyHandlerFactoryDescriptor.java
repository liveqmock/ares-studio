/**
 * 源程序名称：PropertyHandlerFactoryDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.model.extend.IPropertyHandlerFactoryProvider;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;

/**
 * 
 * @author dollyn
 */
public class PropertyHandlerFactoryDescriptor extends CommonDescriptor{

	Logger logger = Logger.getLogger(PropertyHandlerFactoryDescriptor.class);
	/**
	 * @param e
	 */
	public PropertyHandlerFactoryDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	public IPropertyHandlerFactoryProvider createProvider() {
		try {
			return (IPropertyHandlerFactoryProvider)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
		} catch (CoreException e) {
			logger.error(e);
		}
		return null;
	}

}
