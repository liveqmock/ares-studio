/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.deprecated;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
public class TableDeleteItemOperation extends AbstractOperation {

	TableViewerExComponent tableComponent;
	List deleteItems;
	LinkedHashMap<Integer,Object> indexs;
	public TableDeleteItemOperation(String label,TableViewerExComponent tableComponent,List deleteItems) {
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
			tableComponent.getViewer().refresh();
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
			int index = -1;
			for(int i = 0;i<indexs.keySet().size();i++){
				index = findMin(indexs.keySet(),index);
				((List)tableComponent.getInput()).add(index, indexs.get(index));
			}
			tableComponent.getViewer().refresh();
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}
	
	int findMin(Set<Integer> set,int min){
		int result = -1;
		for(int i:set){
			if(result == -1){
				result = i;
				continue;
			}
			if(i < result && i > min){
				result = i;
			}
		}
		return result;
	}

}
