/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.refactoring;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.ResourceChange;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public abstract class AresResourceChange extends ResourceChange {

	protected IARESResource resource;
	
	public AresResourceChange(IARESResource resource) {
		this.resource = resource;
	}
	
	// 暂时没有实现，用这个实现多个子change组成一个真正的Change，这样可以在预览的时候，显示为子节点，能够显示详细的如何改变属性
//	private AresResourceInfoChange[] changes;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.resource.ResourceChange#getModifiedResource()
	 */
	@Override
	protected IResource getModifiedResource() {
		return resource.getResource();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return "更新" + resource.getName() + "中的引用";
	}

	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		Object info = resource.getWorkingCopy(getInfoClassType());
		modifyInfo(info);
		resource.save(info, true, null);
		return createUndoChange();
	}
	
	protected abstract Class<?> getInfoClassType();
	
	protected abstract Change createUndoChange();
	
	protected abstract void modifyInfo(Object info);
}
