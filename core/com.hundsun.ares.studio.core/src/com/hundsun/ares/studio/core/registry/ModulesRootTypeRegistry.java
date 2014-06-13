/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.internal.core.registry.ModuleRootDescriptor;

/**
 * 模块根类型注册表
 * @author sundl
 */
public class ModulesRootTypeRegistry {

	private static Logger logger = Logger.getLogger(ModulesRootTypeRegistry.class.getName());
	private static ModulesRootTypeRegistry instance = null;
	
	private Map<String, IModuleRootDescriptor> rootTypes = new HashMap<String, IModuleRootDescriptor>();
	
	private ModulesRootTypeRegistry() {
		init();
	}
	
	public static ModulesRootTypeRegistry getInstance() {
		if (instance == null) {
			instance = new ModulesRootTypeRegistry();
		}
		
		return instance;
	}
	
	private void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESCore.PLUGIN_ID, ICommonExtensionConstants.EP_ID_MODULEROOT);
		for (IConfigurationElement element : elements) {
			IModuleRootDescriptor descriptor = new ModuleRootDescriptor(element);
			rootTypes.put(descriptor.getId(), descriptor);
			logger.fine("Module root extension readed, id: " + descriptor.getId());
		}
	}
	
	public IModuleRootDescriptor getModuleRootDescriptor(String id) {
		return rootTypes.get(id);
	}
	
	public Map<String, IModuleRootDescriptor> getRootTypes() {
		return rootTypes;
	}
	
}
