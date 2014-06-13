/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.ISourceManipulation;

/**
 * 
 * @author sundl
 */
public class MoveAresElementChange extends ResourceChange {

	private IARESElement elementToMove;
	private IARESElement target;
	
	public MoveAresElementChange(IARESElement element, IARESElement target) {
		this.elementToMove = element;
		this.target = target;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.resource.ResourceChange#getModifiedResource()
	 */
	@Override
	protected IResource getModifiedResource() {
		return elementToMove.getResource();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return "移动" + elementToMove.getElementName() + "到" + target.getElementName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		if (elementToMove instanceof ISourceManipulation) {
			((ISourceManipulation) elementToMove).move(target, null, true, pm);
		}
		// return new MoveAresElementChange(element, target);
		return null;
	}

}
