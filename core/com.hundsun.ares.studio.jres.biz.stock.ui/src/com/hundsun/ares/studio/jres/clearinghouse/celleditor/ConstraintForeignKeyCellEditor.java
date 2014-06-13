/**
 * 源程序名称：ConstraintForeignKeyCellEditor.java
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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class ConstraintForeignKeyCellEditor extends DialogCellEditor{

	private IARESResource currTableResource;
	private ConstraintModifyDetail constraint;
	private TableResourceData tableData;
	
	/**
	 * @param currTableResource
	 * @param constraint
	 * @param tableData
	 */
	public ConstraintForeignKeyCellEditor(Composite parent, IARESResource currTableResource, ConstraintModifyDetail constraint, TableResourceData tableData) {
		super(parent);
		this.currTableResource = currTableResource;
		this.constraint = constraint;
		this.tableData = tableData;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		String tableName = null;
		if (constraint.getForeignKey().size() > 0) {
			tableName = constraint.getForeignKey().get(0).getTableName();
		}
		
		List<TableColumn> columns = new ArrayList<TableColumn>();
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(currTableResource.getARESProject(), IDatabaseRefType.Table, tableName, true);
		if (ref != null) {
			TableResourceData refTableData = (TableResourceData) ref.getObject();
			if (refTableData != null) {
				for (ForeignKey key : constraint.getForeignKey()) {
					TableColumn c = DatabaseUtil.findColumn(key.getFieldName(), tableData);
					if (c != null) {
						columns.add(EcoreUtil.copy(c));
					}
				}
			}
		}
		
		ForeignKeyFieldDialog dialog = new ForeignKeyFieldDialog(getControl().getShell(), 
											currTableResource.getARESProject(),
											constraint);
		if (dialog.open() == Dialog.OK) {
			return dialog.getResult();
		}
		return null;
	}

	@Override
    protected void updateContents(Object value) {
    	if (value instanceof List<?>) {
    		List<?> list = (List<?>) value;
    		int length = list.size();
    		String label = "";
    		for (int i = 0; i < length; i++) {
    			Object obj = list.get(i);
    			ForeignKey c = (ForeignKey) obj;
    			if (i==0)
    				label += c.getTableName() + "(";
    			
    			label += c.getFieldName();
    			
    			if (i<length-1)
    				label+=",";
    			else
    				label+=")";
    		}
    		super.updateContents(label);
    	}
    }
}
