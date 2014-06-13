/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.internal.core.ARESModelManager;
import com.hundsun.ares.studio.internal.core.ReferencedLibrayUtil;

/**
 * 把选择的文件添加到ResPath
 * @author sundl
 */
public class AddSelectedFileToResPathAction extends ResPathModifyAction{

	protected AddSelectedFileToResPathAction() {
		super("添加为引用包");
	}
	
	@Override
	public void run() {
		List<IFile> files = getSelectedFiles();
		
		// 取到项目
		IFile testfile = files.get(0);
		IProject project = testfile.getProject();
		IARESProject aresProject = ARESCore.create(project);

		List<IResPathEntry> respath = new ArrayList<IResPathEntry>();
		for (IFile file : files) {
			IResPathEntry path = ARESCore.newLibEntry(file.getFullPath());
			respath.add(path);
		}
		IResPathEntry[] oldpath = aresProject.getRawResPath();
		IResPathEntry[] added = respath.toArray(new IResPathEntry[0]);
		IResPathEntry[] newPath = new IResPathEntry[oldpath.length + added.length];
		System.arraycopy(oldpath, 0, newPath, 0, oldpath.length);
		System.arraycopy(added, 0, newPath, oldpath.length, added.length);
		aresProject.setRawResPath(newPath, null);
		ARESModelManager.getManager().getDeltaProcessor().entriesAdded(aresProject, added);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.action.ResPathModifyAction#canHandle(org.eclipse.core.resources.IFile[])
	 */
	@Override
	protected boolean canHandle(List<IFile> files) {
		for (IFile file : files) {
			// 是否合法的引用包			
			IStatus status = ReferencedLibrayUtil.testFile(file);
			if (!status.isOK()) {
				return false;
			}
			// 是否已经在respath上了
			IProject project = file.getProject();
			IARESProject aresProject = ARESCore.create(project);
			if (aresProject.exists()) {
				for (IResPathEntry entry : aresProject.getRawResPath()) {
					if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
						if (entry.getPath().equals(file.getFullPath())) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	// 2012-04-11 sundl 增加一个判断，引用包上面右键，不能添加。
	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		if (selection.isEmpty())
			return false;
		
		for (Object obj : selection.toArray()) {
			if (obj instanceof IReferencedLibrary) {
				return false;
			}
		}
		return super.updateSelection(selection);
	}

}
