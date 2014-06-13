/**
 * 源程序名称：ConstraintModifyCellEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.biz.stock.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.celleditor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.ColumnSelectionDialog;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;

/**
 * @author sundl
 *
 */
public class ConstraintColumnsCellEditor extends DialogCellEditor {
	
	private TableResourceData table;
	private IARESResource resource;
	private ConstraintModifyDetail constraint;
	
	public ConstraintColumnsCellEditor(Composite parent, ConstraintModifyDetail constraint, TableResourceData table, IARESResource resource) {
		super(parent);
		this.table = table;
		this.resource = resource;
		this.constraint = constraint;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		ColumnSelectionDialog dialog = new ColumnSelectionDialog(getControl().getShell(), resource, table) {
			protected Control createDialogArea(Composite parent) {
				Control control = super.createDialogArea(parent);
				List<TableColumn> checked = new ArrayList<TableColumn>();
				for (TableColumn c : constraint.getColumns()) {
					TableColumn c2 = DatabaseUtil.findColumn(c, table);
					checked.add(c2);
				}
				getTreeViewer().setCheckedElements(checked.toArray());
				return control;
			}
		};
		if (dialog.open() == Dialog.OK) {
			TableColumn[] columns = dialog.getSelection();
			List<TableColumn> str = new ArrayList<TableColumn>();
			for (int i=0; i < columns.length; i++) {
				TableColumn c = EcoreUtil.copy(columns[i]);
				str.add(c);
			}
			return str;
		}
		return null;
	}
	
    protected void updateContents(Object value) {
    	if (value instanceof List<?>) {
    		List<?> list = (List<?>) value;
    		int length = list.size();
    		String label = "";
    		for (int i = 0; i < length; i++) {
    			Object obj = list.get(i);
    			TableColumn c = (TableColumn) obj;
    			label += c.getName();
    		}
    		super.updateContents(label);
    	}
    }

}
