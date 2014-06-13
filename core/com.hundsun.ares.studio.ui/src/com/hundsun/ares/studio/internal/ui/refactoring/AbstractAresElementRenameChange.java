/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.resources.IResource;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * ares
 * @author sundl
 */
public abstract class AbstractAresElementRenameChange extends ResourceChange {

	protected String oldName;
	protected String newName;
	protected IARESElement element;
	
	public AbstractAresElementRenameChange(IARESElement element, String oldName, String newName) {
		this.element = element;
		this.oldName = oldName;
		this.newName = newName;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.resource.ResourceChange#getModifiedResource()
	 */
	@Override
	protected IResource getModifiedResource() {
		return element.getResource();
	}

}
