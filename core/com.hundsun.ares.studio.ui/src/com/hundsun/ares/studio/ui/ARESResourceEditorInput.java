/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;

/**
 * ARES资源EditorInput
 * @author sundl
 */
public class ARESResourceEditorInput implements IARESResourceEditorInput{

	private IARESResource aresResoruce;
	
	public ARESResourceEditorInput(IARESResource resource) {
		this.aresResoruce = resource;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		return aresResoruce != null && aresResoruce.exists();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		ARESResRegistry reg = ARESResRegistry.getInstance();
		String type = aresResoruce.getType();
		IResDescriptor desc = reg.getResDescriptor(type);
		return desc.getImageDescriptor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		return aresResoruce.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return aresResoruce.getPath().toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.IARESResourceEditorInput#getARESResource()
	 */
	public IARESResource getARESResource() {
		return aresResoruce;
	}
	
}
