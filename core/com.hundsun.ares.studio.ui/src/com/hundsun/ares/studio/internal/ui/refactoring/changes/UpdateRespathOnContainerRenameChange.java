/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring.changes;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.internal.core.ResPathEntry;

/**
 * 当某个项目或文件夹重命名的时候更新.respath文件的Change对象
 * @author sundl
 */
public class UpdateRespathOnContainerRenameChange extends Change{
	
	public IARESProject project;
	public IContainer container;
	public IPath newPath;
	
	/**
	 * 
	 * @param project 要重构respath的项目(引用方)
	 * @param container 将要被重命名的Container(被引用方)
	 * @param newPath 新路径
	 */
	public UpdateRespathOnContainerRenameChange(IARESProject project, IContainer container, IPath newPath) {
		this.project = project;
		this.container = container;
		this.newPath = newPath;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return String.format("更新项目：\"%s\"的respath", project.getElementName());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#initializeValidationData(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void initializeValidationData(IProgressMonitor pm) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#isValid(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		IARESProject project = this.project;
		// 如果重命名项目，并且这个Change处理的也是这个项目，那么project handler也需要改成重命名后的新项目，这个Change是在重命名发生之后才执行的。
		if (container instanceof IProject && this.project.getProject().equals(container)) {
			IProject newProj = container.getProject().getWorkspace().getRoot().getProject(newPath.lastSegment());
			project = ARESCore.create(newProj);
		}
		IResPathEntry[] respath = project.getRawResPath();
		IPath oldPath = container.getFullPath();
		for (IResPathEntry entry : respath) {
			IPath entryPath = entry.getPath();
			if (oldPath.isPrefixOf(entryPath)) {
				IPath trailing = entryPath.removeFirstSegments(oldPath.segmentCount());
				((ResPathEntry) entry).setPath(newPath.append(trailing));
			}
		}
		project.setRawResPath(respath, pm);
		IWorkspaceRoot root = container.getWorkspace().getRoot();
		IContainer newContainer = root.getContainerForLocation(newPath);
		return new UpdateRespathOnContainerRenameChange(project, newContainer, container.getFullPath());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	@Override
	public Object getModifiedElement() {
		return project.getProject().getFile(ARESProject.RES_PATH_FILE);
	}

}
