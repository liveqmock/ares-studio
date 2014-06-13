/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreePath;

import com.hundsun.ares.studio.ui.util.KeyValue;

/**
 * 可撤销重做的树删除item操作
 * @author maxh
 *
 */
public class GridTreeDeleteItemOperation extends AbstractOperation {
	
	GridTreeViewerExComponent component;
	TreePath[] paths;
	LinkedHashMap<TreePath, KeyValue> cache = new LinkedHashMap<TreePath, KeyValue>();
	
	public GridTreeDeleteItemOperation(String label,GridTreeViewerExComponent component,TreePath[] paths) {
		super(label);
		this.component = component;
		this.paths = paths;
	}
	
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		cache.clear();
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
			Object value = null;
			if (parentPath == TreePath.EMPTY) {
				input.remove(path.getLastSegment());
			} else {
				((List)component.getChildrenMap().get(parentPath.getLastSegment())).remove(path.getLastSegment());
				value = component.getChildrenLastLine().remove(path.getLastSegment());
			}
			cache.put(path,new KeyValue(path.getLastSegment(),value));
		}
		component.getViewer().getGrid().setRedraw(false);
		component.getViewer().refresh();
		component.getViewer().getGrid().setRedraw(true);
		component.getDirty().setValue(true);
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
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
		for(TreePath path :cache.keySet()){
			TreePath parentPath = path.getParentPath();
			if (parentPath == TreePath.EMPTY) {
				input.add(cache.get(path).getKey());
			} else {
				((List)component.getChildrenMap().get(parentPath.getLastSegment())).add(cache.get(path).getKey());
				component.getChildrenLastLine().put(cache.get(path).getKey(),cache.get(path).getValue());
			}
		}
		component.getViewer().getGrid().setRedraw(false);
		component.getViewer().refresh();
		component.getViewer().getGrid().setRedraw(true);
		component.getDirty().setValue(true);
		return Status.OK_STATUS;
	}


}


