/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.internal.core.ARESResource;

/**
 * 资源扩展的Descriptr
 * @author sundl
 */
public class ResDescriptor extends OrderableDescriptor implements IResDescriptor {

	private IModelConverter converter;
	private String category;
	private String fileName;
	private boolean copyable;
	private boolean deleteable;
	private boolean moveable;
	private boolean renameable;
	
	public boolean isCopyable() {
		return copyable;
	}

	public boolean isDeleteable() {
		return deleteable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public ResDescriptor(IConfigurationElement e) {
		super(e);	
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IResDescriptor#createCommonResource()
	 */
	public IARESResource createCommonResource() {
		return new ARESResource();
	}

	protected ImageDescriptor getDefaultImageDescriptor() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(ARESCore.PLUGIN_ID, "icons/obj16/p_data.gif");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IResDescriptor#createInfo()
	 */
	public Object createInfo() {
		String info_class = configElement.getAttribute(ICommonExtensionConstants.INFO_CLASS);
		if(info_class != null) {
			try {
				return configElement.createExecutableExtension(ICommonExtensionConstants.INFO_CLASS);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.registry.CommonDescriptor#loadFromExtension()
	 */
	@Override
	protected void loadFromExtension() {
		super.loadFromExtension();
		category = configElement.getAttribute(ICommonExtensionConstants.CATEGORY);
		fileName = configElement.getAttribute(ICommonExtensionConstants.FILENAME);
		copyable = readBoolean(ICommonExtensionConstants.COPYABLE, true);
		deleteable = readBoolean(ICommonExtensionConstants.DELTEABLE, true);
		moveable = readBoolean(ICommonExtensionConstants.MOVEABLE, true);
		renameable = readBoolean(ICommonExtensionConstants.RENAMEABLE, true);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IResDescriptor#getConverter()
	 */
	public IModelConverter getConverter() {
		if (converter == null) {
			try {
				converter = (IModelConverter)configElement.createExecutableExtension(ICommonExtensionConstants.CONVERTER);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return converter;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IResDescriptor#getCategory()
	 */
	public String getCategory() {
		return category;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IResDescriptor#getFileName()
	 */
	public String getFileName() {
		return fileName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IResDescriptor#isRenameable()
	 */
	public boolean isRenameable() {
		return renameable;
	}
	
}
