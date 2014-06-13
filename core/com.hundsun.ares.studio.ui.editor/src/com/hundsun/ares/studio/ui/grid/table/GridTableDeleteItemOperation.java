/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.table;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * 可撤销重做的表格删除item操作
 * @author maxh
 *
 */
public class GridTableDeleteItemOperation extends AbstractOperation {

	GridTableViewerBasicComponent tableComponent;
	List deleteItems;
	LinkedHashMap<Integer,Object> indexs;
	public GridTableDeleteItemOperation(String label,GridTableViewerBasicComponent tableComponent,List deleteItems) {
		super(label);
		this.tableComponent = tableComponent;
		this.deleteItems = deleteItems;
		indexs = new LinkedHashMap<Integer,Object>();
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		if(tableComponent != null && deleteItems != null){
			for(Object o:deleteItems){
				int index = ((List)tableComponent.getInput()).indexOf(o);
				if(index != -1){
					indexs.put(index,o);
				}
			}
			
			if(tableComponent.getInput() instanceof List){
				((List)tableComponent.getInput()).removeAll(deleteItems);
			}
			tableComponent.getViewer().getGrid().setRedraw(false);
			tableComponent.getViewer().refresh();
			tableComponent.getViewer().getGrid().setRedraw(true);
			tableComponent.getDirty().setValue(true);
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
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
		if(tableComponent.getInput() instanceof List){
			Integer[] keys = indexs.keySet().toArray(new Integer[indexs.keySet().size()]);
			Arrays.sort(keys, new Comparator<Integer>(){
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});
			for(int i = 0;i<keys.length;i++){
				((List)tableComponent.getInput()).add(keys[i], indexs.get(keys[i]));
			}
			tableComponent.getViewer().getGrid().setRedraw(false);
			tableComponent.getViewer().refresh();
			tableComponent.getViewer().getGrid().setRedraw(true);
			tableComponent.getDirty().setValue(true);
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

}
