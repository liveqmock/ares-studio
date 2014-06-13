/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IExternalResPathEntry;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.IResPathEntryElement;
import com.hundsun.ares.studio.ui.RefLibContainer.RequiredProject;

/**
 * 将引用包/引用工程从Res Path删除
 * @author sundl
 */
public class RemoveFromResPathAction extends ResPathModifyAction {

	private List<IResPathEntry> entriesToRemove = new ArrayList<IResPathEntry>();
	
	protected RemoveFromResPathAction() {
		super("删除引用");
	}

	public void run() {
		IStructuredSelection ss = getStructuredSelection();
		Object firstElement = ss.getFirstElement();
		IARESProject aresProject = null;
		if (firstElement instanceof IReferencedLibrary) {
			aresProject = ((IReferencedLibrary) firstElement).getARESProject();
		} else if (firstElement instanceof RequiredProject) {
			RequiredProject requiredProject = (RequiredProject) firstElement;
			aresProject = requiredProject.getHostAresProject();
		}
		
		if (aresProject != null) {
			removeEntryFromResPath(entriesToRemove.toArray(new IResPathEntry[0]), aresProject);
		}
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		if (selection.isEmpty())
			return false;
		
		entriesToRemove.clear();
		for (Object obj : selection.toArray()) {
			if (obj instanceof IResPathEntryElement) {
				IResPathEntryElement element = (IResPathEntryElement) obj;
				IResPathEntry entry = element.getResPathEntry();
				if (entry instanceof IExternalResPathEntry) {
					return false;
				}
				entriesToRemove.add(element.getResPathEntry());
			} else {
				return false;
			}
		}
		
		return entriesToRemove.size() > 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.action.ResPathModifyAction#canHandle(java.util.List)
	 */
	@Override
	protected boolean canHandle(List<IFile> files) {
		for (IFile file : files) {
			IProject project = file.getProject();
			IARESProject aresProject = ARESCore.create(project);
			if (aresProject.exists()) {
				boolean onResPath = false;
				for (IResPathEntry entry : aresProject.getRawResPath()) {
					if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
						if (entry.getPath().equals(file.getProjectRelativePath())) {
							onResPath = true;
							break;
						}
					}
				}
				if (!onResPath)
					return false;
			}
		}
		return true;
	}

}
