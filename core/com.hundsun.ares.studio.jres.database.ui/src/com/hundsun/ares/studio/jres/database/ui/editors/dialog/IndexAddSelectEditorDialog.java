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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnLabelProvider;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnRefLabelProvider;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;

/**
 * @author wangxh
 * 
 */
public class IndexAddSelectEditorDialog extends Dialog{

	private IARESResource resource;
	private TableResourceData info;
	private CheckboxTableViewer tableViewer;
	private Object indexs;
	private List<TableColumn> result;
	private String title;
	/**
	 * @param parentShell
	 * @param info
	 * @param resource
	 */
	public IndexAddSelectEditorDialog(Shell parentShell,String title,
			TableResourceData info,Object index, IARESResource resource) {
		super(parentShell);
		this.title = title;
		this.info = info;
		this.indexs = index;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#getShellStyle()
	 */
	@Override
	protected int getShellStyle() {
		return super.getShellStyle() |SWT.RESIZE | SWT.MAX;
	}
	
	public List<TableColumn> getSelectedColumns() {
		return result;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		result = (List)Arrays.asList(tableViewer.getCheckedElements()) ;
		super.okPressed();
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		tableViewer = CheckboxTableViewer.newCheckList(composite, SWT.BORDER|SWT.FULL_SELECTION|SWT.V_SCROLL|SWT.H_SCROLL);
		tableViewer.getTable().setLinesVisible(true);
		tableViewer.getTable().setHeaderVisible(true);
		
		GridDataFactory.swtDefaults().hint(-1, 200).grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(tableViewer.getTable());
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection sel =  (IStructuredSelection) event.getSelection();
				if (!sel.isEmpty()) {
					Object element = sel.getFirstElement();
					tableViewer.setChecked(element, !tableViewer.getChecked(element));
				}
			}
		});
		
		tableViewer.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				TableColumn column = (TableColumn) element;
				if (indexs instanceof List) {
					List<TableIndexColumn> inds = (List<TableIndexColumn>) indexs;
					for (TableIndexColumn indexColumn : inds) {
						if (column.getName().equals(indexColumn.getColumnName())) {
							return false;
						}
					}
				}

//				EList<TableIndexColumn> indexColumns = index.getColumns();
//				column.getName() not in index.getColumns()
				return true;
			}
		});
		tableViewer.setContentProvider(new ReferenceContentProvider(DatabasePackage.Literals.TABLE_RESOURCE_DATA__COLUMNS));
		
		// 字段名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		// 重命名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__COLUMN_NAME;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("重命名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		
		// 中文名
		{
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.ChineseName);
			tvColumn.setLabelProvider(provider);
		}
		// 字段类型
		{			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段类型");
			
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE;
			TableColumnLabelProvider provider = new TableColumnLabelProvider(attribute, resource);
			tvColumn.setLabelProvider(provider);
		}
		// 是否主键
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__PRIMARY_KEY;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("是否主键");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
		}
		// 是否为空
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__NULLABLE;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("是否为空");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
		}
		
		tableViewer.setInput(info);
		
		 return composite;
	}

}
