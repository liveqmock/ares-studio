/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

/**
 * 
 * @author sundl
 */
public class UserExtendedPropertyEditingSupport extends EditingSupport{
	
	private IExtensibleModelPropertyDescriptor descriptor;
	
	public UserExtendedPropertyEditingSupport(ColumnViewer viewer, IExtensibleModelPropertyDescriptor descriptor) {
		super(viewer);
		this.descriptor = descriptor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		return descriptor.createPropertyEditor((Composite) getViewer().getControl());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		// 不应该有意外情况，所有的都应该是这种类型
		if (descriptor instanceof IUserExtendedPropertyDescriptor
				&& element instanceof ExtensibleModel) {
			ExtensibleModel model = (ExtensibleModel) element;
			IUserExtendedPropertyDescriptor userExtProDesc = (IUserExtendedPropertyDescriptor) descriptor;
			return userExtProDesc.getValue(model);
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		// 不应该有意外情况，所有的都应该是这种类型
		if (descriptor instanceof IUserExtendedPropertyDescriptor
				&& element instanceof ExtensibleModel) {
			ExtensibleModel model = (ExtensibleModel) element;
			IUserExtendedPropertyDescriptor userExtProDesc = (IUserExtendedPropertyDescriptor) descriptor;
			userExtProDesc.setValue(model, value == null? null : String.valueOf(value));
		}
		getViewer().update(element, null);
	}

}
