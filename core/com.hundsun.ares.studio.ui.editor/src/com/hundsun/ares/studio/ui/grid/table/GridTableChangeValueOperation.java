/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.table;


import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * 可撤销重做的表格修改item操作
 * @author maxh
 *
 */
public class GridTableChangeValueOperation extends AbstractOperation { 
	GridTableViewerEditorableComponent tableComponent;
	LinkedHashMap<Integer, Object> indexs;
	Object element;
	String property;
	Object value;
	Object oldValue;

	public GridTableChangeValueOperation(String label,
			GridTableViewerEditorableComponent tableComponent, Object element,
			String property, Object value) {
		super(label);
		this.tableComponent = tableComponent;
		this.element = element;
		this.property = property;
		this.value = value;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		oldValue = tableComponent.getValue(element, property);
		tableComponent.setValue(element, property, value, true);
		/*
		 * 数据被修改时，将原先的计数-1，新值的计数+1
		 */
		Map<Object, Integer> theCache = (Map<Object, Integer>) tableComponent
				.getCheckCache().get(property);
		if (theCache.containsKey(oldValue)) {
			theCache.put(oldValue, theCache.get(oldValue) - 1);
		}

		if (theCache.containsKey(value)) {
			theCache.put(value, theCache.get(value) + 1);
		} else {
			theCache.put(value, 1);
		}
		tableComponent.getViewer().refresh();
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
		tableComponent.setValue(element, property, oldValue, true);
		/*
		 * 数据被修改时，将原先的计数-1，新值的计数+1
		 */
		Map<Object, Integer> theCache = (Map<Object, Integer>) tableComponent
				.getCheckCache().get(property);
		if (theCache.containsKey(value)) {
			theCache.put(value, theCache.get(value) - 1);
		}

		if (theCache.containsKey(oldValue)) {
			theCache.put(oldValue, theCache.get(oldValue) + 1);
		} else {
			theCache.put(oldValue, 1);
		}
		tableComponent.getViewer().refresh();
		return Status.OK_STATUS;
	}
}
