/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 修改Res-Path的操作
 * @author sundl
 */
public abstract class ResPathModifyAction extends SelectionListenerAction {

	private List<IFile> selectedFiles = new ArrayList<IFile>();
	
	protected ResPathModifyAction(String text) {
		super(text);
	}
	
    protected boolean updateSelection(IStructuredSelection selection) {
    	selectedFiles.clear();
    	if (getSelectedNonResources().size() != 0)
    		return false;
    	
    	if (selectionIsOfType(IResource.FILE)) {
    		for (Object obj : getSelectedResources()) {
    			selectedFiles.add((IFile)obj);
    		}
    		if (selectedFiles.size() == 0) {
    			return false;
    		}
    		return canHandle(selectedFiles);
    	}
        return false;
    }
    
    protected abstract boolean canHandle(List<IFile> files);
    
    protected List<IFile> getSelectedFiles() {
    	return selectedFiles;
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
		// 2012-03-09 sundl deltaprocessor会监听.respath文件的变化自动更新内存数据，不需要这里调用通知。额外的通知也会导致更多的elementchange事件发出。
		//ARESModelManager.getManager().getDeltaProcessor().entriesAdded(project, newEntries);
    }
    
    protected void removeEntryFromResPath(IResPathEntry[] entriesToRemove, IARESProject project) {
    	IResPathEntry[] oldpath = project.getRawResPath();
    	List<IResPathEntry> newEntries = new ArrayList<IResPathEntry>();
		for (IResPathEntry entry : oldpath) {
			if (!ArrayUtils.contains(entriesToRemove, entry))
				newEntries.add(entry);
		}
		project.setRawResPath(newEntries.toArray(new IResPathEntry[0]), null);
		// 2012-03-09 sundl deltaprocessor会监听.respath文件的变化自动更新内存数据，不需要这里调用通知。额外的通知也会导致更多的elementchange事件发出。
		//ARESModelManager.getManager().getDeltaProcessor().entriesRemoved(project, entriesToRemove);
    }
    
}
