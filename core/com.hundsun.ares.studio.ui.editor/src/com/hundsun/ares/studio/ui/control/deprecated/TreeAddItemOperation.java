/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.deprecated;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreePath;


/**
 * 可撤销重做的树增加item操作
 * @author maxh
 *
 */
public class TreeAddItemOperation extends AbstractOperation {
	TreeViewerExComponent component;
	TreePath[] paths;
	List addItems;
	int index = 0;
	public TreeAddItemOperation(String label,TreeViewerExComponent component,TreePath[] paths,List addItems) {
		super(label);
		this.component = component;
		this.paths = paths;
		this.addItems = addItems;
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
		if(paths.length == 0){
			return Status.CANCEL_STATUS;
		}
		TreePath parentPath = paths[0].getParentPath();
		if (parentPath == TreePath.EMPTY) {
			index = input.indexOf(paths[0].getLastSegment());
			if (index == -1) {
				index = input.size();
			}
			for (Object obj : addItems) {
				input.add( index, obj);
			}
		} else {
			List<Object> lst = (List<Object>)component.getChildrenMap().get(parentPath.getLastSegment());
			index = lst.indexOf(paths[0].getLastSegment());
			if (index == -1) {
				index = lst.size();
			}
			for (Object obj : addItems) {
				lst.add(index,obj);
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
		List input;
		if(component.getInput() instanceof List){
			input = (List)component.getInput();
		}else{
			return Status.CANCEL_STATUS;
		}
		if(paths.length == 0){
			return Status.CANCEL_STATUS;
		}
		TreePath parentPath = paths[0].getParentPath();
		if (parentPath == TreePath.EMPTY) {
			for (Object obj : addItems) {
				input.remove(obj);
			}
		} else {
			List<Object> lst = (List<Object>)component.getChildrenMap().get(parentPath.getLastSegment());
			for (Object obj : addItems) {
				lst.remove(obj);
			}
		}
		return Status.OK_STATUS;
	}

}
