/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

/**
 * 
 * @author sundl
 */
public class UserExtendedPropertyColumnLabelProvider extends ColumnLabelProvider {

	private IExtensibleModelPropertyDescriptor descriptor;
	
	public UserExtendedPropertyColumnLabelProvider(IExtensibleModelPropertyDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	@Override
	public String getText(Object element) {
		ILabelProvider labelProvider =  descriptor.getLabelProvider();
		return labelProvider.getText(element);
	}

}
