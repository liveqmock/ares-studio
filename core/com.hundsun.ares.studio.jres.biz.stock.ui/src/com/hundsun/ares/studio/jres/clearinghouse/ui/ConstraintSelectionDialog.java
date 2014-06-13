package com.hundsun.ares.studio.jres.clearinghouse.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;

/**
 * 选择约束的对话框
 * @author sundl
 *
 */
public class ConstraintSelectionDialog extends Dialog {

	private IARESResource resource;
	private EObject input;
	private CheckboxTreeViewer treeViewer;
	private String title;
	
	private TableKey[] result = new TableKey[0];
	
	public ConstraintSelectionDialog(Shell parentShell, IARESResource resource, EObject input) {
		super(parentShell);
		this.resource = resource;
		this.input = input;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title == null ? "选择列" : title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IARESResource getResource() {
		return this.resource;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));
		
		treeViewer = new CheckboxTreeViewer(composite, SWT.CHECK | SWT.BORDER);
		treeViewer.setContentProvider(new ReferenceTreeContentProvider(DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS));
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.getTree().setLinesVisible(true);
		createColumns(treeViewer);
		
		treeViewer.setInput(input);
		GridDataFactory.fillDefaults().hint(-1, 300).applyTo(treeViewer.getTree());
		return composite;
	}
	
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		// 用于一般的列
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(treeViewer);
		
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 字段名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("约束名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.getColumn().setMoveable(true);
		}

		// 类型
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__TYPE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("类型");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		
		// 字段列表
		{
			EStructuralFeature feature = DatabasePackage.Literals.TABLE_KEY__COLUMNS;
					
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("字段列表");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(feature){
				@Override
				public String getText(Object element) {
					StringBuffer buffer = new StringBuffer();
					if(element instanceof TableKey){
						TableKey tableKey = (TableKey)element;
						EList<TableColumn> colunms = tableKey.getColumns();
						for(int index=0; index<colunms.size(); index++){
							if(index > 0){
								buffer.append(",");
							}
							buffer.append(colunms.get(index).getName());
						}
					}
					return buffer.toString();
				}
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		// 外键
		{
			EStructuralFeature feature = DatabasePackage.Literals.TABLE_KEY__FOREIGN_KEY;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("外键");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(feature) {
				public String getText(Object element) {
					if(element instanceof TableKey){
						TableKey modify = (TableKey) element;
						EList<ForeignKey> foreignKey = modify.getForeignKey();
						StringBuffer buffer = new StringBuffer();
						
						for (int i = 0, length = foreignKey.size(); i < length; i++) {
							if (i == 0) {
								String tableName = foreignKey.get(0).getTableName();
								String shortName = StringUtils.substringAfterLast(tableName, ".");
								buffer.append(shortName + "(");
							}
							buffer.append(foreignKey.get(i).getFieldName());
							if (i < length - 1)
								buffer.append(",");
							else
								buffer.append(")");
						}
						return buffer.toString();
					}
					return StringUtils.EMPTY;
				}
				
				@Override
				public Color getBackground(Object element) {
					if (resource != null && resource.isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					TableKey key = (TableKey)element;
					if(!key.getType().equals(key_type.FOREIGN)){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			}; 
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, resource, DatabasePackage.Literals.TABLE_KEY, exProblemView);
	}
	
	public CheckboxTreeViewer getTreeViewer() {
		return this.treeViewer;
	}
	
	@Override
	protected void okPressed() {
		List<TableKey> columns = new ArrayList<TableKey>();
		for (Object obj : treeViewer.getCheckedElements()) {
			columns.add((TableKey) obj);
		}
		this.result = columns.toArray(new TableKey[0]);
		super.okPressed();
	}

	public TableKey[] getSelection() {
		return result;
	}
}
