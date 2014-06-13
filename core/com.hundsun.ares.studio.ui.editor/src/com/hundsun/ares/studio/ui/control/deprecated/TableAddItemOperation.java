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


/**
 * 可撤销重做的表格增加item操作
 * @author maxh
 *
 */
public class TableAddItemOperation extends AbstractOperation {

	TableViewerExComponent tableComponent;
	List addItems;
	int startIndex;
	public TableAddItemOperation(String label,TableViewerExComponent tableComponent,List addItems,int startIndex) {
		super(label);
		this.tableComponent = tableComponent;
		this.addItems = addItems;
		this.startIndex = startIndex;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		if(tableComponent != null && addItems != null){
			if(tableComponent.getInput() instanceof List){
				((List)tableComponent.getInput()).addAll(startIndex, addItems);
				tableComponent.getViewer().refresh();
				return Status.OK_STATUS;
			}
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
			for(int i = 0;i < addItems.size();i++){
				((List)tableComponent.getInput()).remove(startIndex);
			}
			tableComponent.getViewer().refresh();
			return Status.OK_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

}
