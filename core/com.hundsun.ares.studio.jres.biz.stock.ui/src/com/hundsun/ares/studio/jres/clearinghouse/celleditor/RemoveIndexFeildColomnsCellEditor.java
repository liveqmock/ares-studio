/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.clearinghouse.celleditor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author liaogc
 * 
 */
public class RemoveIndexFeildColomnsCellEditor extends DialogCellEditor {

	private ColumnViewer columnViewer;
	private IARESResource resource;

	private List<TableIndexColumn> indexFields = new ArrayList<TableIndexColumn>();
	private TableIndex index;
	private TableResourceData tableResourceData = null;
	private RemoveIndexField removeIndexField;
	private List<TableIndexColumn> initIndexFields = new ArrayList<TableIndexColumn>();
	private int returnCode = Window.CANCEL;

	public RemoveIndexFeildColomnsCellEditor(ColumnViewer columnViewer,
			TableResourceData tableResourceData, IARESResource resource,
			String fromText, String toText) {
		super((Composite) columnViewer.getControl());
		this.columnViewer = columnViewer;
		this.resource = resource;
		this.tableResourceData = tableResourceData;
	}

	@Override
	protected Object doGetValue() {
		return indexFields;
	}

	@Override
	protected void doSetValue(Object value) {
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		} else {
			setValueValid(true);
		}
		if (value instanceof EObjectContainmentEList) {
			 removeIndexField = (RemoveIndexField) ((EObjectContainmentEList) value).getEObject();

			String indexName = removeIndexField.getName();
			for (TableIndex tableIndex : tableResourceData.getIndexes()) {
				if (StringUtils.equals(indexName, tableIndex.getName())) {
					this.index = EcoreUtil.copy(tableIndex);
					initIndexFields.addAll(EcoreUtil.copyAll(removeIndexField.getIndexFields()));
					break;
				}
			}
			

		}
		

		if (value instanceof List && null!= value) {
			indexFields.clear();
			for(Object oValue:((List)value)){
				if(oValue instanceof TableIndexColumn){
					TableIndexColumn tableIndexColumn = (TableIndexColumn) oValue;
					indexFields.add(tableIndexColumn);
				}
			}
		}

	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {

		if (index != null) {
			IndexFieldSelectEditorDialog dialog = new IndexFieldSelectEditorDialog(
					cellEditorWindow.getShell(), "选择要删除的索引字段", index,initIndexFields,
					tableResourceData, resource);
			
			if ((returnCode=dialog.open())== Window.OK) {
				return dialog.getSelectedColumns();
			}
		}

		return null;
	}

	@Override
	protected Control createControl(Composite parent) {
		Control control = super.createControl(parent);
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		} else {
			setValueValid(true);
		}
		return control;
	}

}
