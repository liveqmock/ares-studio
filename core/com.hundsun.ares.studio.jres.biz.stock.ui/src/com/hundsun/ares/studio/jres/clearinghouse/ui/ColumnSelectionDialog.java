package com.hundsun.ares.studio.jres.clearinghouse.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnLabelProvider;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnRefLabelProvider;
import com.hundsun.ares.studio.jres.database.utils.DatabaseUtils;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;

public class ColumnSelectionDialog extends Dialog {

	private IARESResource resource;
	private EObject input;
	private CheckboxTreeViewer treeViewer;
	private String title;
	
	private TableColumn[] result = new TableColumn[0];
	
	public ColumnSelectionDialog(Shell parentShell, IARESResource resource, EObject input) {
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
		treeViewer.setContentProvider(new ReferenceTreeContentProvider(DatabasePackage.Literals.TABLE_RESOURCE_DATA__COLUMNS));
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.getTree().setLinesVisible(true);
		createColumns(treeViewer);
		
//		treeViewer.addCheckStateListener(new ICheckStateListener() {
//			@Override
//			public void checkStateChanged(CheckStateChangedEvent event) {
//				Object[] objects = treeViewer.getCheckedElements();
//				List<TableColumn> columnList = new ArrayList<TableColumn>();
//				for (Object obj : objects) {
//					if (obj instanceof TableColumn) {
//						columnList.add((TableColumn) obj);
//					}
//				}
//				result = columnList.toArray(new TableColumn[0]);
//			}
//		});
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
		
		// 标记
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(90);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , resource);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 引用的标准字段
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
		}
		
		// 中文名
		{
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.ChineseName){
				@Override
				public Color getBackground(Object element) {
					if (getResource().isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		// 字段类型
		{			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段类型");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.Type){
				@Override
				public Color getBackground(Object element) {
					if (getResource().isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}

		// 字段说明
		{
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("字段说明");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.Desciption){
				@Override
				public String getToolTipText(Object element) {
					return getText(element);
				}
				@Override
				public Color getBackground(Object element) {
					if (getResource().isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}

		// 默认值
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__DEFAULT_VALUE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("默认值");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 是否为空
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__NULLABLE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("允许空");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , getResource()){
				@Override
				public Color getBackground(Object element) {
					TableColumn tc = (TableColumn) element;
					//if (tc.isPrimaryKey()) {
					if(DatabaseUtils.isPrimaryKey(tc)){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
				
				@Override
				public Image getImage(Object element) {
					TableColumn tc = (TableColumn) element;
//					if (tc.isPrimaryKey()) {
					if(DatabaseUtils.isPrimaryKey(tc)){
						return null;
					}
					return super.getImage(element);
				}
				
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 备注
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__COMMENTS;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("备注");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getResource()){

				@Override
				public String getToolTipText(Object element) {
					String text = super.getToolTipText(element);
					if(StringUtils.isBlank(text)){
						return getText(element);
					}
					return text;
				}
			
			};
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, getResource(), DatabasePackage.Literals.TABLE_COLUMN, exProblemView);
	}
	
	public CheckboxTreeViewer getTreeViewer() {
		return this.treeViewer;
	}
	
	@Override
	protected void okPressed() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		for (Object obj : treeViewer.getCheckedElements()) {
			columns.add((TableColumn) obj);
		}
		this.result = columns.toArray(new TableColumn[0]);
		super.okPressed();
	}

	public TableColumn[] getSelection() {
		return result;
	}
}
