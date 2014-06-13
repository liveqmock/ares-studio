/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;

/**
 * 用于合并操作
 * @author maxh
 *
 */
public class ListOperation  extends AbstractOperation {

	List<AbstractOperation> operations;
	
	public ListOperation(String label,List<AbstractOperation> operations) {
		super(label);
		this.operations = operations;
	}
	
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		for(int i = 0;i < operations.size();i++){
			operations.get(i).execute(monitor, info);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return execute(monitor,info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		for(int i = operations.size()-1;i >= 0 ;i--){
			operations.get(i).undo(monitor, info);
		}
		return Status.OK_STATUS;
	}

	public void replace(){
		for(AbstractOperation operation:operations){
			AbstractHSFormEditor.getOperationHistory().replaceOperation(operation, new IUndoableOperation[]{});
		}
		AbstractHSFormEditor.getOperationHistory().add(this);
	}
}
