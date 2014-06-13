/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.ui.RefLibContainer;

/**
 * 添加引用资源包的Action
 * @author sundl
 */
public class AddToResPathAction extends SelectionListenerAction {
	
	protected IProject project;
	protected Shell shell;

	protected AddToResPathAction(Shell shell, String text) {
		super(text);
		this.shell = shell;
	}

	protected boolean updateSelection(IStructuredSelection selection) {
		project = null;

		IStructuredSelection ss = getStructuredSelection();
		if (ss.isEmpty())
			return false;

		Object obj = ss.getFirstElement();
		if (obj instanceof IProject)
			project = (IProject) obj;
		else if (obj instanceof RefLibContainer) {
			project = ((RefLibContainer) obj).getProject().getProject();
		}

		if (project != null)
			return true;

		return false;
	}

    /**
     * 将指定的ResPath-Entry添加到给定的项目的Res-Path上
     * @param newEntries
     * @param project
     */
    protected void addResEntryToResPath(IResPathEntry[] newEntries, IARESProject project) {
		IResPathEntry[] oldpath = project.getRawResPath();
		IResPathEntry[] newPath = new IResPathEntry[oldpath.length + newEntries.length];
		System.arraycopy(oldpath, 0, newPath, 0, oldpath.length);
		System.arraycopy(newEntries, 0, newPath, oldpath.length, newEntries.length);
		project.setRawResPath(newPath, null);
		//ARESModelManager.getManager().getDeltaProcessor().entriesAdded(project, newEntries);
    }
    
    protected boolean isOnRespath(IFile file, IARESProject project) {
    	IResPathEntry[] path = project.getRawResPath();
    	for (IResPathEntry entry : path) {
    		if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY && entry.getPath().equals(file.getFullPath())) {
    			return true;
    		}
    	}
    	return false;
    }
	
}
