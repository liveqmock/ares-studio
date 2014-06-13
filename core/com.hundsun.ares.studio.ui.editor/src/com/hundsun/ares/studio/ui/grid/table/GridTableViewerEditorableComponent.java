/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.nebula.jface.gridviewer.GridViewerColumn;
import org.eclipse.nebula.widgets.grid.GridColumn;

import com.hundsun.ares.studio.core.model.extendable.IExtendFieldModel;
import com.hundsun.ares.studio.core.util.ArrayUtil;
import com.hundsun.ares.studio.ui.celleditor.ISprecialCellEditor;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.util.ListOperation;
import com.hundsun.ares.studio.ui.util.ReflectInvokeHelper;

/**
 * 这一层为控件提供编辑功能 setValue 和 getValue的默认实现
 * @author maxh
 *
 * @param <T>
 */
public abstract class GridTableViewerEditorableComponent<T> extends GridTableViewerBasicComponent<T> {
	public GridTableAddItemOperation add(List items,int startIndex){
		if(items.size() > 0){
			GridTableAddItemOperation add = new GridTableAddItemOperation("add",this,items,startIndex);
			if(undoContext != null){
				add.addContext(undoContext);
			}
			try {
				AbstractHSFormEditor.getOperationHistory().execute(add, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return add;
		}
		return null;
	}
	
	public void commit(GridTableChangeValueOperation change) {
		List list = new ArrayList();
		list.add(lastLine);
		GridTableAddItemOperation add = add(list,getInput().size());
		if(change != null && add != null){
			List<AbstractOperation> operations = new ArrayList<AbstractOperation>();
			operations.add(change);
			operations.add(add);
			ListOperation operationList = new ListOperation("list",operations);
			if(undoContext != null) {
				operationList.addContext(undoContext);
			}
			operationList.replace();
		}
		lastLine = createBlankData();
		dirty.setValue(true);
		viewer.refresh();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.grid.GridViewerExComponent#createColumn(java.lang.String, org.eclipse.nebula.widgets.grid.GridColumn)
	 * 创建列
	 */
	@Override
	protected GridViewerColumn createColumn(String property,GridColumn column) {
		GridViewerColumn viewercolumn = new GridViewerColumn(viewer, column);
		viewercolumn.setEditingSupport(new DelegateEditingSupport(property));
		viewercolumn.setLabelProvider(new DelegateCellLabelProvider(property));
		checkCache.put(property, new HashMap<Object, Integer>());
		checkStatus.put(property, false);
		return viewercolumn;
	}
	
	/**
	 * 获得指定单元格的值，用于CellEditor
	 * 
	 * @param data
	 * @param property
	 * @return
	 */
	public Object getValue(T data, String property){
		if(ArrayUtil.findInArray(viewerPropertys, property) != -1){
			//如果不是拓展列 默认使用放射方式
			ReflectInvokeHelper helper;
			try {
				helper = new ReflectInvokeHelper(data,property);
				Object object = helper.invokeGetMothod();
				return object == null?"":object.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//用户拓展列
			if(data instanceof IExtendFieldModel){
				Object result = ((IExtendFieldModel)data).getExtendStrings().get(property);
				return result == null?"":result;
			}
		}
		return null;
	}
	
	/**
	 * 将CellEditor的值设置回单元格
	 * 
	 * @param data
	 * @param property
	 * @param value
	 */
	public void setValue(T data, String property, Object value, boolean shouldRefresh){
		ReflectInvokeHelper helper;
		if(!getValue(data,property).equals(value) ){
			dirty.setValue(true);
		}
		if(ArrayUtil.findInArray(viewerPropertys, property) != -1){
			//如果不是拓展列 默认使用放射方式
			try {
				helper = new ReflectInvokeHelper(data,property);
				helper.invokeSetMothod(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//用户拓展列
			if(data instanceof IExtendFieldModel){
				((IExtendFieldModel)data).getExtendStrings().put(property, value.toString());
			}
		}
		if(shouldRefresh){
			getViewer().refresh(data);
		}
	}
	
	private class DelegateEditingSupport extends EditingSupport {

		private String property;

		public DelegateEditingSupport(String property) {
			super(GridTableViewerEditorableComponent.this.viewer);
			this.property = property;
		}

		@Override
		protected boolean canEdit(Object element) {
			return !readOnly && GridTableViewerEditorableComponent.this.canEdit((T) element, property);
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return GridTableViewerEditorableComponent.this.getCellEditor(element, property);
		}

		@Override
		protected Object getValue(Object element) {
			Object value = GridTableViewerEditorableComponent.this.getValue((T) element, property);
			if(getCellEditor(element) instanceof ISprecialCellEditor){
				ISprecialCellEditor editor = (ISprecialCellEditor)getCellEditor(element);
				value = editor.getRealGetValue(value);
			}
			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Object oldValue = getValue(element);
			if(!oldValue.equals(value)){	
				if(getCellEditor(element) instanceof ISprecialCellEditor){
					ISprecialCellEditor editor = (ISprecialCellEditor)getCellEditor(element);
					value = editor.getRealSetValue(value);
				}
				GridTableChangeValueOperation operation = new GridTableChangeValueOperation("change",GridTableViewerEditorableComponent.this,element,property,value);
				if(undoContext != null){
					operation.addContext(undoContext);
				}
				try {
					AbstractHSFormEditor.getOperationHistory().execute(operation, null, null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				// 正在对最后一行进行操作
				if (element == lastLine) {
					Object newValue = getValue(element);
					if ((oldValue == null && value != null && !value.toString().equals("")) || (!oldValue.equals(value) && !newValue.equals(oldValue))) {
						commit(operation);
					} else {
						return;
					}
				}
			}
		}
	}
}
