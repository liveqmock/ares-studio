/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.CommonMapRegistry;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 
 * @author sundl
 */
public class UserExtendedPropertyTypeManager extends CommonMapRegistry<UserExtendedPropertyTypeProviderDescriptor>{

	private static UserExtendedPropertyTypeManager INSTANCE;
	
	private UserExtendedPropertyTypeManager() {super();}
	
	public static UserExtendedPropertyTypeManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserExtendedPropertyTypeManager();
		}
		return INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "user_extended_property_type_provider";
	}
	
	protected String getExtensionPointPluginId() {
		return ARESEditorPlugin.PLUGIN_ID;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		UserExtendedPropertyTypeProviderDescriptor desc = new UserExtendedPropertyTypeProviderDescriptor(element);
		map.put(desc.getId(), desc);
	}
	
}
