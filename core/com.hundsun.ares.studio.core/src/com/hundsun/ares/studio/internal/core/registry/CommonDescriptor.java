/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.registry.ICommonDescriptor;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;

/**
 * 基本的注册描述信息
 * @author sundl
 */
public class CommonDescriptor implements ICommonDescriptor {

	private String id;
	private String name;
	private ImageDescriptor imageDescriptor;
	protected IConfigurationElement configElement;
	
	public CommonDescriptor(IConfigurationElement e) {
		configElement = e;
		loadFromExtension();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonDescriptor#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonDescriptor#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		if (imageDescriptor == null) {
			String iconName = configElement.getAttribute(ICommonExtensionConstants.ICON);
			// If the icon attribute was omitted, use the default one
	        if (iconName == null) {
				return getDefaultImageDescriptor();
			}
	        IExtension extension = configElement.getDeclaringExtension();
	        String extendingPluginId = extension.getContributor().getName();
	        imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(extendingPluginId, iconName);
	        // If the icon attribute was invalid, use the error icon
	        if (imageDescriptor == null) {
	            imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
	        }
		}
		return imageDescriptor;
	}

	protected ImageDescriptor getDefaultImageDescriptor() {
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEF_VIEW);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonDescriptor#getName()
	 */
	public String getName() {
		return name;
	}
	
	protected void loadFromExtension() {
		id = configElement.getAttribute(ICommonExtensionConstants.ID);
		name = configElement.getAttribute(ICommonExtensionConstants.NAME);		
	}
	
	protected boolean readBoolean(String key, boolean dft) {
		String strValue = configElement.getAttribute(key);
		if (StringUtils.isEmpty(strValue))
			return dft;
		return Boolean.parseBoolean(strValue);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonDescriptor#getConfigurationElement()
	 */
	public IConfigurationElement getConfigurationElement() {
		return configElement;
	}

}
