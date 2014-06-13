/**
 * 源程序名称：IndexTable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.dialog.IndexAddSelectEditorDialog;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 编辑索引列的表格
 * @author yanyl
 * 
 */
public class IndexTable extends TableViewerWithButtons {
	private IARESResource resource;
	private TableResourceData tableResourceData;
	
	/**
	 * @param parent
	 */
	public IndexTable(Composite parent, IARESResource resource,TableResourceData tableResourceData) {
		super(parent);
		this.resource = resource;
		this.tableResourceData = tableResourceData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.
	 * TableViewerWithButtons#createButtons(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtons(Composite parent) {
		final TableViewer tableViewer = this;
		Button add = new Button(parent, SWT.None);
		add.setText("添加");
		final Shell shell = parent.getShell();

		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IndexAddSelectEditorDialog dialog = new IndexAddSelectEditorDialog(shell,"索引选择框", tableResourceData,getInput(), resource);
				if (dialog.open() == Window.OK) {
					@SuppressWarnings("unchecked")
					Collection<TableIndexColumn> inputs = (Collection<TableIndexColumn>) getInput();
					for (TableColumn select : dialog.getSelectedColumns()) {
						TableIndexColumn column = DatabaseFactory.eINSTANCE.createTableIndexColumn();
						column.setColumnName(select.getName());
						column.setColumnType(select.getColumnType());
						column.setAscending(true);
						inputs.add(column) ;
					}
				}
				// 创建的实例为null时可能是添加逻辑已经在create()方法体中执行了
				tableViewer.refresh();
			}
		});
		
		TableButtonFactory.createRemoveBtn(parent, this);
		TableButtonFactory.createMoveUpBtn(parent, this);
		TableButtonFactory.createMoveDownBtn(parent, this);
	}

	private static final String[] TITLES = new String[] { "字段名", "升序", "中文名", "说明" };
	private static final int[] WIDTHS = new int[] { 80, 50, 100, 100 };
	private static final EAttribute[] ATTRIBUTES = {
			DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME,
			DatabasePackage.Literals.TABLE_INDEX_COLUMN__ASCENDING};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.
	 * BasicTableViewer#createTitles()
	 */
	@Override
	protected String[] createTitles() {
		return TITLES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.
	 * BasicTableViewer#createTitlesWidth()
	 */
	@Override
	protected int[] createTitlesWidth() {
		// TODO Auto-generated method stub
		return WIDTHS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.
	 * BasicTableViewer#createLabelProvider(int)
	 */
	@Override
	protected ColumnLabelProvider createLabelProvider(int index) {
		final ReferenceManager manager = ReferenceManager.getInstance();
		switch (index) {
		case 0:
			return new EObjectColumnLabelProvider(ATTRIBUTES[index]);
		case 1:
			return new CheckBoxColumnLabelProvider(ATTRIBUTES[index] ,resource);
		case 2:
			return new EObjectColumnLabelProvider(DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME) {
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
			};
		case 3:
			return new EObjectColumnLabelProvider(DatabasePackage.Literals.TABLE_INDEX_COLUMN__COLUMN_NAME) {
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
			};
		default:
			return new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					return "";
				}
			};
			
		}
	}
	
	private TableColumn findColumn(String name) {
		for (TableColumn c : tableResourceData.getColumns()) {
			if (StringUtils.equals(c.getName(), name))
				return c;
		}
		return null;
	}

	private Collection<String> contents = null;

	/**
	 * 获取所有的可选的列名
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String> getSelectableCols() {
		if (contents == null) {
			// 所有的表格列在当前TableViewer初始化后就不会变了
			contents = new ArrayList<String>();
			for (TableColumn column : tableResourceData.getColumns()) {
				contents.add(column.getName());
			}

		}
		Collection<String> exsits = new ArrayList<String>();
		Collection<TableIndexColumn> inputs = (Collection<TableIndexColumn>) getInput();
		for (TableIndexColumn column : inputs) {
			exsits.add(column.getColumnName());
		}

		List<String> selectableCols = new ArrayList<String>();
		for (String column : contents) {
			if (!exsits.contains(column)) {
				selectableCols.add(column);
			}
		}

		return selectableCols;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.dialog.inner.
	 * BasicTableViewer#createEditingSupport(int)
	 */
	@Override
	protected EditingSupport createEditingSupport(int index) {
		switch (index) {
		case 0:
			return new JresTextEditingSupportWithContentAssist(this,
					ATTRIBUTES[index], new AresContentProposalProvider() {
						@Override
						public void updateContent(Object element) {
							Collection<String> strs = getSelectableCols();
							setInput(strs.toArray(new String[strs.size()]));
						}

						@Override
						public IContentProposal[] getProposals(String contents,
								int position) {
							List<IContentProposal> proposals = new ArrayList<IContentProposal>();
							if (input != null) {
								for (Object obj : input) {
									if (StringUtils.startsWithIgnoreCase(obj.toString(), contents)) {
										IContentProposal proposal = new StringProposal(
												obj);
										if (proposal != null) {
											proposals.add(proposal);
										}
									}

								}
							}
							return proposals.toArray(new IContentProposal[0]);
						}
					});
		case 1:
			return new BooleanEditingSupport(this, ATTRIBUTES[index]);
		default:
			return null;
		}
	}

}