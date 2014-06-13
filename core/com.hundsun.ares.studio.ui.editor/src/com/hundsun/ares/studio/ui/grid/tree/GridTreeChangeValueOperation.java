/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.tree;

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
public class GridTreeChangeValueOperation  extends AbstractOperation {

	GridTreeViewerEditorableComponent treeComponent;
	LinkedHashMap<Integer, Object> indexs;
	Object element;
	String property;
	Object value;
	Object oldValue;

	public GridTreeChangeValueOperation(String label,
			GridTreeViewerEditorableComponent treeComponent, Object element,
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
		treeComponent.setValue(element, property, value);
		treeComponent.getViewer().refresh(element);
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
		treeComponent.setValue(element, property, oldValue);
		treeComponent.getViewer().refresh(element);
		return Status.OK_STATUS;
	}
}
