/**
 * <p>Copyright: Copyright   2010</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.nebula.jface.gridviewer.GridViewerColumn;
import org.eclipse.nebula.widgets.grid.GridColumn;

import com.hundsun.ares.studio.ui.celleditor.ISprecialCellEditor;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.util.KeyValue;
import com.hundsun.ares.studio.ui.util.ListOperation;

/**
 * 增加可编辑功能 默认提供setValue和getValue
 * 
 * @author maxh
 * 
 * @param <T>
 */
public abstract class GridTreeViewerEditorableComponent<T> extends GridTreeViewerBasicComponent<T> {
	/**
	 * 将附加行添加到实际数据中去
	 * 
	 * @param parent
	 * @param child
	 */
	protected void commit(Object parent, Object child, GridTreeChangeValueOperation change) {
		TreeCommitOperation operation = new TreeCommitOperation("commit", parent, child);
		if (undoContext != null) {
			operation.addContext(undoContext);
		}
		try {
			AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		List<AbstractOperation> operations = new ArrayList<AbstractOperation>();
		operations.add(change);
		operations.add(operation);
		ListOperation operationList = new ListOperation("list", operations);
		operationList.addContext(undoContext);
		operationList.replace();
	}

	// static Field fieldEditingSupport = null;
	// static Field fieldLabelProvider = null;
	// static {
	// try {
	// fieldEditingSupport = GridViewerColumn.class.getField("editingSupport");
	// fieldLabelProvider = GridViewerColumn.class.getField("labelProvider");
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (NoSuchFieldException e) {
	// e.printStackTrace();
	// }
	// }

	public void resetColumnProperties(String[] oldPropertys, String[] newPropertys) {
		List<CellEditor> editors = new ArrayList<CellEditor>();
		List<GridViewerColumn> columns = new ArrayList<GridViewerColumn>();
		List<HashMap<Object, CellEditor>> spEditors = new ArrayList<HashMap<Object, CellEditor>>();

		Assert.isTrue(oldPropertys.length == newPropertys.length);

		for (String op : oldPropertys) {
			editors.add(editorMap.get(op));
			columns.add(columnMap.get(op));
			spEditors.add(specialEditorMap.get(op));

			editorMap.remove(op);
			columnMap.remove(op);
			specialEditorMap.remove(op);
		}

		for (int i = 0; i < newPropertys.length; i++) {
			editorMap.put(newPropertys[i], editors.get(i));
			columnMap.put(newPropertys[i], columns.get(i));
			specialEditorMap.put(newPropertys[i], spEditors.get(i));

			try {
				editSupportMap.get(columns.get(i)).property = newPropertys[i];
				labelProviderMap.get(columns.get(i)).property = newPropertys[i];
			} catch (Exception e) {
			}
		}
	}

	public void resetTitles(String[] propertys, String[] newTitles) {
		for (int i = 0; i < propertys.length; i++) {
			columnMap.get(propertys[i]).getColumn().setText(newTitles[i]);
		}
	}

	protected Map<GridViewerColumn, DelegateEditingSupport> editSupportMap = new HashMap<GridViewerColumn, DelegateEditingSupport>();
	protected Map<GridViewerColumn, DelegateCellLabelProvider> labelProviderMap = new HashMap<GridViewerColumn, DelegateCellLabelProvider>();

	/**
	 * 注意必须正确加入<code>editSupportMap</code>和<code>labelProviderMap</code>
	 */
	@Override
	final protected GridViewerColumn createColumn(String property, GridColumn column) {
		GridViewerColumn viewercolumn = new GridViewerColumn(viewer, column);
		DelegateEditingSupport editSupport;
		DelegateCellLabelProvider labelProvider;
		viewercolumn.setEditingSupport(editSupport = createEditingSupport(property));
		viewercolumn.setLabelProvider(labelProvider = createCellLabelProvider(property));

		editSupportMap.put(viewercolumn, editSupport);
		labelProviderMap.put(viewercolumn, labelProvider);

		return viewercolumn;
	}

	protected DelegateEditingSupport createEditingSupport(String property) {
		return new DelegateEditingSupport(property);
	}

	protected DelegateCellLabelProvider createCellLabelProvider(String property) {
		return new DelegateCellLabelProvider(property);
	}

	protected class DelegateEditingSupport extends EditingSupport {

		protected String property;

		public DelegateEditingSupport(String property) {
			super(GridTreeViewerEditorableComponent.this.viewer);
			this.property = property;
		}

		@Override
		protected boolean canEdit(Object element) {
			return !readOnly && GridTreeViewerEditorableComponent.this.canEdit(element, property);
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return GridTreeViewerEditorableComponent.this.getCellEditor(element, property);
		}

		@Override
		protected Object getValue(Object element) {
			Object value = GridTreeViewerEditorableComponent.this.getValue(element, property);
			if (getCellEditor(element) instanceof ISprecialCellEditor) {
				ISprecialCellEditor editor = (ISprecialCellEditor) getCellEditor(element);
				value = editor.getRealGetValue(value);
			}
			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Object oldValue = getValue(element);
			if (value != null && !value.equals(oldValue)) {
				if (getCellEditor(element) instanceof ISprecialCellEditor) {
					ISprecialCellEditor editor = (ISprecialCellEditor) getCellEditor(element);
					value = editor.getRealSetValue(value);
				}
				GridTreeChangeValueOperation operation = new GridTreeChangeValueOperation("change",
						GridTreeViewerEditorableComponent.this, element, property, value);
				if (undoContext != null) {
					operation.addContext(undoContext);
				}
				try {
					AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				// 判断是否在对最后一行进行写入
				if (isLastLine(element)) {
					if (value != null && !value.equals(oldValue)) {
						commit(GridTreeViewerEditorableComponent.this.getLastLineParent(element), element, operation);
					}
				}
				// getViewer().refresh();
			}
		}

	}

	protected class TreeCommitOperation extends AbstractOperation {
		Object parent;
		Object child;
		Object oldLastLine;
		KeyValue keyValue;

		public TreeCommitOperation(String label, Object parent, Object child) {
			super(label);
			this.parent = parent;
			this.child = child;
		}

		@Override
		public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			if (parent == null) {
				input.add((T) child);
				oldLastLine = lastLine;
				lastLine = (T) createBlankData(null);
				getViewer().refresh();
			} else {
				List<Object> lstChildren = childrenMap.get(parent);
				lstChildren.add(child);
				Object object = childrenLastLine.remove(parent);
				keyValue = new KeyValue(parent, object);
				getViewer().refresh(parent);
			}

			return Status.OK_STATUS;
		}

		@Override
		public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			// TODO Auto-generated method stub
			return execute(monitor, info);
		}

		@Override
		public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			if (parent == null) {
				input.remove(child);
				lastLine = (T) oldLastLine;
			} else {
				List<Object> lstChildren = childrenMap.get(parent);
				lstChildren.remove(child);
				childrenLastLine.put(keyValue.getKey(), keyValue.getValue());
			}
			return Status.OK_STATUS;
		}

	}
}
