/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.editor;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.CommonMapRegistry;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 扩展字段注册表
 * @author sundl
 */
public class ExtensionFieldEditorRegistry extends CommonMapRegistry<ExtensionFieldEditorDescriptor>{

	private static ExtensionFieldEditorRegistry instance;
	
	private ExtensionFieldEditorRegistry() {}

	public static ExtensionFieldEditorRegistry getInstance() {
		if (instance == null)
			instance = new ExtensionFieldEditorRegistry();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "project_property_extensionfield_editor";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointPluginId()
	 */
	@Override
	protected String getExtensionPointPluginId() {
		return ARESEditorPlugin.PLUGIN_ID;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		ExtensionFieldEditorDescriptor desc = new ExtensionFieldEditorDescriptor(element);
		map.put(desc.getId(), desc);
	}
	
}
