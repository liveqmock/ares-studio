/**
 * 源程序名称：RemoveColumnComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.provider.RemoveColumnRefLabelProvider;
import com.hundsun.ares.studio.jres.clearinghouse.ui.ColumnSelectionDialog;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author wangxh
 *
 */
public class RemoveColumnComposite extends ModifyActionColumnComposite<RemovedTableColumn>{

	/**
	 * 删除表字段
	 * @param parent
	 * @param tableData 
	 * @param resource
	 * @param action
	 */
	public RemoveColumnComposite(Composite parent, TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getEReference()
	 */
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.REMOVE_TABLE_COLUMN_MODIFICATION__COLUMNS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected RemovedTableColumn creatBlankItem() {
		return ChouseFactory.eINSTANCE.createRemovedTableColumn();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected EList<RemovedTableColumn> getActionItems(Modification modification) {
		return ((RemoveTableColumnModification)action).getColumns();
	}
	
	@Override
	protected void handleAdd() {
		ColumnSelectionDialog dialog = new ColumnSelectionDialog(getShell(), resource, tableData) {
			protected Control createDialogArea(Composite parent) {
				Control control = super.createDialogArea(parent);
				getTreeViewer().addFilter(new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof TableColumn) {
							TableColumn column = (TableColumn) element;
							for (RemovedTableColumn removed : input) {
								if (StringUtils.equals(column.getName(), removed.getName())) {
									return false;
								}
							}
							return true;
						}
						return false;
					}
				});
				return control;
			}
		};
		dialog.setTitle("选择要删除的列");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableColumn[] columns = dialog.getSelection();
			for (TableColumn c : columns) {
				RemovedTableColumn removed = ChouseFactory.eINSTANCE.createRemovedTableColumn();
				removed.setColumnType(c.getColumnType());
				removed.setName(c.getName());
				removed.setMark(c.getMark());
				removed.setChineseName(c.getChineseName());
				removed.setDataType(c.getDataType());
				removed.setDescription(c.getDescription());
				input.add(removed);
			}
			treeViewer.refresh();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			// 2013-12-16 sundl 修改记录里的标志位属性都改成了只读
//			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 字段名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_COLUMN__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			//tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			ColumnProposalProvider proposalProvider = new ColumnProposalProvider(IDatabaseRefType.TableField);
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,resource));
			tvColumn.setEditingSupport(es);
		}
		
		// 中文名
		{
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			RemoveColumnRefLabelProvider provider = new RemoveColumnRefLabelProvider(resource.getBundle(), RemoveColumnRefLabelProvider.TYPE.ChineseName);
			tvColumn.setLabelProvider(provider);
		}
		// 字段类型
		{
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段类型");
			
			RemoveColumnRefLabelProvider provider = new RemoveColumnRefLabelProvider(resource.getBundle(), RemoveColumnRefLabelProvider.TYPE.Type);
			tvColumn.setLabelProvider(provider);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof RemoveTableColumnModification){
			action = (RemoveTableColumnModification) modification;
		}
		else
		{
			action = ChouseFactory.eINSTANCE.createRemoveTableColumnModification();
		}
	}
	
}
