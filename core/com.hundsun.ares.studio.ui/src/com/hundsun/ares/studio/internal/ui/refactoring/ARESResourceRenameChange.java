/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 重命名资源的Change类
 * @author sundl
 */
public class ARESResourceRenameChange extends AbstractAresElementRenameChange{

	/**
	 * newName oldname都是不带扩展名的短名
	 */
	public ARESResourceRenameChange(IARESResource element, String oldName, String newName) {
		super(element, oldName, newName);	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		IARESResource resource = getARESResource();
		return resource.getElementName() + " 重命名为: " + this.newName + "." + resource.getType();
	}
	
	private IARESResource getARESResource() {
		return (IARESResource)element;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		getARESResource().rename(newName, true, pm);
		return new ARESResourceRenameChange(getARESResource(), newName, oldName);
	}

}
