/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 
 * @author sundl
 */
public class RenameModuleRootChange extends AbstractAresElementRenameChange {
	
	public RenameModuleRootChange(IARESElement element, String oldName,	String newName) {
		super(element, oldName, newName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return oldName + "重命名为" + newName;
	}
	
	private IARESModuleRoot getRoot() {
		return (IARESModuleRoot) element;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		final IFolder folder = (IFolder) getRoot().getResource();
		IWorkspace ws = folder.getWorkspace();
		ws.run(new IWorkspaceRunnable() {
			
			public void run(IProgressMonitor monitor) throws CoreException {
				IPath path = folder.getFullPath();
				IPath dest = path.removeLastSegments(1).append(newName);
				folder.move(dest, true, null);
				
				IARESProject project = element.getARESProject();
				IResPathEntry[] resPath = project.getRawResPath();
				IResPathEntry[] newResPath = (IResPathEntry[]) ArrayUtils.clone(resPath);
				IPath relativePath = path.removeFirstSegments(1);
				for (int i = 0; i < newResPath.length; i++) {
					IResPathEntry entry = newResPath[i];
					if (relativePath.equals(entry.getPath())) {
						IPath newPath = dest.removeFirstSegments(1);
						IResPathEntry newEntry = ARESCore.newSourceEntry(entry.getType(), newPath);
						newResPath[i] = newEntry;
					}
				}
				project.setRawResPath(newResPath, null);
			}
			
		}, pm);
		return new RenameModuleRootChange(element, newName, oldName);
	}

}
