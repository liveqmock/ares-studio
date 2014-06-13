/**
 * 源程序名称：IndexSelectEditorDialog.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.IndexTable;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author wangxh
 * 
 */
public class IndexSelectEditorDialog extends Dialog {

	private ColumnViewer viewer;
	private String title;
	private IARESResource resource;
	private TableResourceData tableResourceData;
	/**
	 * @param parentShell
	 * @param Title
	 * @param Viewer
	 * @param resource
	 */
	public IndexSelectEditorDialog(Shell parentShell, String Title,
			ColumnViewer Viewer, IARESResource resource,TableResourceData tableResourceData) {
		super(parentShell);
		this.title = Title;
		this.viewer = Viewer;
		this.resource = resource;
		this.tableResourceData = tableResourceData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
		newShell.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	}
	
	private Object outputData;
	protected void okPressed() {
		outputData=table.getInput();
		super.okPressed();
	}
	
	IndexTable table;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite= (Composite)super.createDialogArea(parent);
		GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).hint(400, 300).applyTo(composite);
		table=new IndexTable(composite, resource ,tableResourceData);
		IStructuredSelection select = (IStructuredSelection) viewer.getSelection();
		Object obj = select.getFirstElement();
		if(obj instanceof TableIndex){
			Collection<TableIndexColumn> temp=((TableIndex) obj).getColumns();
			Collection<TableIndexColumn> inputs=new ArrayList<TableIndexColumn>();
			for(TableIndexColumn column:temp){
				TableIndexColumn copy=DatabaseFactory.eINSTANCE.createTableIndexColumn();
				copy.setAscending(column.isAscending());
				copy.setColumnName(column.getColumnName());
				copy.setColumnType(column.getColumnType());
				inputs.add(copy);
			}
			table.setInput(inputs);
		}
		return composite;
	}
	
	public Object getResult(){
		return outputData;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
	 */
	@Override
	protected boolean isResizable() {
		return true;
	}

}
