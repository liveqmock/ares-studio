/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.deprecated;

import java.util.LinkedHashMap;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;


/**
 * 可撤销重做的树修改item操作
 * @author maxh
 *
 */
public class TreeChangeValueOperation  extends AbstractOperation {

	TreeViewerExComponent treeComponent;
	LinkedHashMap<Integer, Object> indexs;
	Object element;
	String property;
	Object value;
	Object oldValue;

	public TreeChangeValueOperation(String label,
			TreeViewerExComponent treeComponent, Object element,
			String property, Object value) {
		super(label);
		this.treeComponent = treeComponent;
		this.element = element;
		this.property = property;
		this.value = value;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		oldValue = treeComponent.getValue(element, property);
		treeComponent.setValue(element, property, value, true);

		treeComponent.getViewer().refresh();
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
		treeComponent.setValue(element, property, oldValue, true);

		treeComponent.getViewer().refresh();
		return Status.OK_STATUS;
	}
}
