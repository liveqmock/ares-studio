package com.hundsun.ares.studio.jres.database.ui.editors.celleditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.dialog.SelectDialog;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

public class TableKeyColumnsCellEditor extends DialogCellEditor {

	ColumnViewer viewer;
	IARESResource resource;
	
	List<String> columns = new ArrayList<String>();
	TableKey tableKey;
	
	private int returnCode = Window.CANCEL;
	/**
	 * @param viewer
	 * @param resource
	 */
	public TableKeyColumnsCellEditor(ColumnViewer viewer, IARESResource resource) {
		super((Composite) viewer.getControl());
		this.viewer = viewer;
		this.resource = resource;
	}

	@Override
	protected Object doGetValue() {
		return columns;
	}

	@Override
	protected void doSetValue(Object value) {
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		}else {
			setValueValid(true);
		}
		
		Object obj = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
		if(obj != null && obj instanceof TableKey){
			tableKey = (TableKey)obj;
		}
		
		if(value instanceof Collection){
			columns.clear();
			columns.addAll((Collection<String>) value);
		}
	}

	protected List<TableColumn> getCellEditorInput(){
		List<TableColumn> tableColumns = new ArrayList<TableColumn>();
		TableResourceData tableResourceData = null;
		EObject obj =  tableKey.eContainer();
		if(obj != null && obj instanceof TableResourceData){
			tableResourceData = (TableResourceData)obj;
		}else{
			try {
				tableResourceData = resource.getInfo(TableResourceData.class);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		for(TableColumn col : tableResourceData.getColumns()){
			tableColumns.add(col);
		}
		return tableColumns;
	}
	
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		EList<TableColumn> keyColumns = tableKey.getColumns();
		SelectDialog dialog = new SelectDialog(cellEditorWindow.getShell(), "Ñ¡Ôñ±í×Ö¶Î", getCellEditorInput(),keyColumns ,new LabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof TableColumn){
					return ((TableColumn)element).getName();
				}
				return super.getText(element);
			}
		});
		returnCode = dialog.open();
		Object result = (returnCode == Window.OK) ? dialog.getResult() : null;
		if(result == null){
			setValueValid(false);
		}
		return result;
	}

	@Override
	protected Control createControl(Composite parent) {
		Control control = super.createControl(parent);
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		}else {
			setValueValid(true);
		}
		return control;
	}

}
