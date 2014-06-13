/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.deprecated;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreePath;


/**
 * 可撤销重做的树删除item操作
 * @author maxh
 *
 */
public class TreeDeleteItemOperation extends AbstractOperation {
	TreeViewerExComponent component;
	TreePath[] paths;
	
	public TreeDeleteItemOperation(String label,TreeViewerExComponent component,TreePath[] paths) {
		super(label);
		this.component = component;
		this.paths = paths;
	}
	
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {		
		List input;
		if(component.getInput() instanceof List){
			input = (List)component.getInput();
		}else{
			return Status.CANCEL_STATUS;
		}
		
		// 不需要的路径
		ArrayList<TreePath> needlessPaths = new ArrayList<TreePath>();
		
		// 需要用于删除的路径
		ArrayList<TreePath> deletePaths = new ArrayList<TreePath>();
		for (TreePath path : paths) {
			TreePath parentPath = path.getParentPath();
			if (deletePaths.indexOf(parentPath) != -1 || needlessPaths.indexOf(parentPath) != -1) {
				needlessPaths.add(path);
				continue;
			}
			
			deletePaths.add(path);
		}
		// 进行删除操作
		for (TreePath path : deletePaths) {
			TreePath parentPath = path.getParentPath();
			if (parentPath == TreePath.EMPTY) {
				input.remove(path.getLastSegment());
			} else {
				((List)component.getChildrenMap().get(parentPath.getLastSegment())).remove(path.getLastSegment());
				component.getChildrenLastLine().remove(path.getLastSegment());
			}
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return execute(monitor, info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
