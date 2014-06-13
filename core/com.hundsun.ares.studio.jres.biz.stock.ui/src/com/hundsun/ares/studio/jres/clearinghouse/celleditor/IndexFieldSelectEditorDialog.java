/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.celleditor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;

/**
 * @author liaogc
 *
 */
public class IndexFieldSelectEditorDialog  extends Dialog{

	private CheckboxTableViewer tableViewer;
	private String title;
	private IARESResource resource;
	private TableResourceData tableResourceData;
	private List<TableIndexColumn> result;
	private TableIndex index;
	private List<TableIndexColumn>initIndexFields;//初始的已经选择的索引字段
	final ReferenceManager manager = ReferenceManager.getInstance();
	
	/**
	 * @param parentShell
	 * @param info
	 * @param resource
	 */
	public IndexFieldSelectEditorDialog(Shell parentShell, String Title,TableIndex index ,List<TableIndexColumn>initIndexFields,TableResourceData tableResourceData,IARESResource resource) {
		super(parentShell);
		    this.title = Title;
		    this.index = index;
		    this.initIndexFields = initIndexFields;
			this.resource = resource;
			this.tableResourceData = tableResourceData;}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#getShellStyle()
	 */
	@Override
	protected int getShellStyle() {
		return super.getShellStyle() |SWT.RESIZE | SWT.MAX;
	}
	
	public List<TableIndexColumn> getSelectedColumns() {
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
		
		tableViewer.setContentProvider(new ReferenceContentProvider(DatabasePackage.Literals.TABLE_INDEX__COLUMNS));
		
		// 字段名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		// 升序
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX_COLUMN__ASCENDING;
			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("升序");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		
		// 中文名
		{
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			FieldChineseNameColumnLabelProvider provider = new FieldChineseNameColumnLabelProvider(DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME);
			tvColumn.setLabelProvider(provider);
		}
		// 字段类型
		{			
			final TableViewerColumn tvColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("说明");
			
			FieldDescColumnLabelProvider provider = new FieldDescColumnLabelProvider(DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME);
			tvColumn.setLabelProvider(provider);
		}
		
		    
			for(TableIndexColumn field:initIndexFields){
				boolean find = false;
				Iterator<TableIndexColumn> iterator  = index.getColumns().iterator();
				while(iterator.hasNext()){
					TableIndexColumn indexField = iterator.next();
					if(StringUtils.equals(field.getColumnName(),indexField.getColumnName())){
						find = true;
						break;
					}
				}
				if(!find){
					index.getColumns().add(field);
				}
			
		}
		

		
		tableViewer.setInput(index);
		setInitState();
		 return composite;
	}
	
	
	/**
	 * 初始字段选择状态
	 */
	private void setInitState(){
		for(TableIndexColumn field:initIndexFields){
			for(TableIndexColumn indexField:index.getColumns()){
				if(StringUtils.equals(field.getColumnName(),indexField.getColumnName())){
					tableViewer.setChecked(indexField, true);
					break;
				}
			}
		}
		
	}
	
	private class FieldChineseNameColumnLabelProvider extends EObjectColumnLabelProvider{

		/**
		 * @param attribute
		 */
		public FieldChineseNameColumnLabelProvider(EStructuralFeature attribute) {
			super(attribute);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getText(Object element) {
			TableIndexColumn column = (TableIndexColumn) element;
			String name = column.getColumnName();
			if (column.getColumnType() == ColumnType.STD_FIELD) {
				ReferenceInfo ref = (ReferenceInfo) manager.getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, name, true);
				if (ref != null) {
					StandardField stdField = (StandardField) ref.getObject();
					return stdField == null ? StringUtils.EMPTY : StringUtils.defaultString(stdField.getChineseName());
				}else {
					return StringUtils.EMPTY;
				}
			} else {
				TableColumn c = findColumn(name);
				if (c != null) {
					return StringUtils.defaultString(c.getChineseName());
				}
				return StringUtils.EMPTY;
			}
		}
	
		
	}
	
	private class FieldDescColumnLabelProvider  extends EObjectColumnLabelProvider{

		/**
		 * @param attribute
		 */
		public FieldDescColumnLabelProvider(EStructuralFeature attribute) {
			super(attribute);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getText(Object element) {
			TableIndexColumn column = (TableIndexColumn) element;
			String name = column.getColumnName();
			if (column.getColumnType() == ColumnType.STD_FIELD) {
				ReferenceInfo ref = (ReferenceInfo) manager.getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, name, true);
				if (ref != null) {
					StandardField stdField = (StandardField) ref.getObject();
					return stdField == null ? StringUtils.EMPTY : StringUtils.defaultString(stdField.getDescription());
				}else {
					return StringUtils.EMPTY;
				}
			} else {
				TableColumn c = findColumn(name);
				if (c != null) {
					return StringUtils.defaultString(c.getDescription());
				}
				return StringUtils.EMPTY;
			}
		}
	
		
	}
	
	/**
	 * 根据字段名找出对应的表格列
	 * @param name
	 * @return
	 */
	private TableColumn findColumn(String name) {
		
		for (TableColumn c : tableResourceData.getColumns()) {
			if (StringUtils.equals(c.getName(), name))
				return c;
		}
		return null;
	}

}
