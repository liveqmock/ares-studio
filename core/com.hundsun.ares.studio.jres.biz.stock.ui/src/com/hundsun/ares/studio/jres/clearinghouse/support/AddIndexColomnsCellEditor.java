/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.dialog.IndexFieldAddSelectEditorDialog;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author liaogc
 *
 */
public class AddIndexColomnsCellEditor extends DialogCellEditor {

	private ColumnViewer columnViewer;
	private IARESResource resource;
	
	private List<TableIndexColumn> addIndexColumns = new ArrayList<TableIndexColumn>();
	private List<TableIndexColumn> oldFeildColumnList = new ArrayList<TableIndexColumn>();;
	
	private TableIndex index;
	private AddIndexField addIndexField;
	private TableResourceData tableResourceData = null;
	private int returnCode = Window.CANCEL;
	
	public AddIndexColomnsCellEditor(ColumnViewer columnViewer,IARESResource resource,TableResourceData tableResourceData,
			String fromText, String toText) {
		super((Composite) columnViewer.getControl());
		this.columnViewer = columnViewer;
		this.resource = resource;
		this.tableResourceData = tableResourceData;
	}

	@Override
	protected Object doGetValue() {
		return addIndexColumns;
	}

	@Override
	protected void doSetValue(Object value) {
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		}else {
			setValueValid(true);
		}
		
		if (value instanceof EObjectContainmentEList) {
			addIndexField = (AddIndexField) ((EObjectContainmentEList) value).getEObject();

			String indexName = addIndexField.getName();
			for (TableIndex tableIndex : tableResourceData.getIndexes()) {
				if (StringUtils.equals(indexName, tableIndex.getName())) {
					this.index = tableIndex;
					oldFeildColumnList.addAll(EcoreUtil.copyAll(tableIndex.getColumns()));
					break;
				}
			}
			

		}
		addIndexColumns.clear();
		addIndexColumns.addAll(EcoreUtil.copyAll((Collection<TableIndexColumn>)value));
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		TableResourceData tableResourceData = null;
		EObject obj = index.eContainer();
		if (obj == null || !(obj instanceof TableResourceData)) {
			try {
				tableResourceData = resource.getInfo(TableResourceData.class);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}else {
			tableResourceData = (TableResourceData)obj;
		}
		IndexFieldAddSelectEditorDialog dialog = new IndexFieldAddSelectEditorDialog(
				cellEditorWindow.getShell(), "选择表字段", resource,tableResourceData,addIndexColumns,oldFeildColumnList);
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