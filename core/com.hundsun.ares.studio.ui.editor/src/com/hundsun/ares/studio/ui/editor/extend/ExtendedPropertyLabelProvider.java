/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;

/**
 * 
 * @author sundl
 */
public class ExtendedPropertyLabelProvider extends LabelProvider implements IEMLabelProviderExtension{

	private IBasicExtendPropertyDescriptor descriptor;
	private ExtensibleModel model;
	
	public ExtendedPropertyLabelProvider(IBasicExtendPropertyDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	public String getText(Object element) {
		if (model != null) {
			String value = descriptor.getValue(model);
			return value == null ? StringUtils.EMPTY : value;
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IEMLabelProviderExtension#setExtensibleModel(com.hundsun.ares.studio.core.model.ExtensibleModel)
	 */
	@Override
	public void setExtensibleModel(ExtensibleModel model) {
		this.model = model;
	}
	
}
