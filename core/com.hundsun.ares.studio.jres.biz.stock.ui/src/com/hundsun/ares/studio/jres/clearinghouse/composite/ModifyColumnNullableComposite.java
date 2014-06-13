/**
 * 源程序名称：ModifyColumnTypeComposite.java
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.ColumnSelectionDialog;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class ModifyColumnNullableComposite extends ModifyActionColumnComposite<CTCNMDetail>{

	
	public ModifyColumnNullableComposite(Composite parent, TableResourceData tableData, IARESResource resource, Modification action) {
		super(parent, tableData, resource, action);
	}
	
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.CHANGE_TABLE_COLUMN_NULLABLE_MODIFYCATION__DETAILS;
	}

	@Override
	protected CTCNMDetail creatBlankItem() {
		CTCNMDetail detail =  ChouseFactory.eINSTANCE.createCTCNMDetail();
		return detail;
	}

	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		
		{
			EAttribute attribute = ChousePackage.Literals.MODIFY_DETAIL__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
//			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 字段名
		{
			EAttribute attribute = ChousePackage.Literals.MODIFY_DETAIL__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			ColumnProposalProvider proposalProvider = new ColumnProposalProvider(IDatabaseRefType.TableField);
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,resource));
			tvColumn.setEditingSupport(es);
		}
		
		// 是否为空
		{
			EAttribute attribute = ChousePackage.Literals.CTCNM_DETAIL__NULLABLE;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("允许空");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new BooleanEditingSupport(treeViewer, attribute));
		}
		
	}

	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof ChangeTableColumnNullableModifycation){
			action = modification;
			input = ((ChangeTableColumnNullableModifycation)action).getDetails();
		}
		else{
			action = ChouseFactory.eINSTANCE.createChangeTableColumnNullableModifycation();
			input = ((ChangeTableColumnNullableModifycation)action).getDetails();
			if (tableData == null) {
				try {
					tableData = resource.getWorkingCopy(TableResourceData.class);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
//			for (TableColumn column : tableData.getColumns()){
//				CTCNMDetail de = ChouseFactory.eINSTANCE.createCTCNMDetail();
//				de.setName(column.getName());
//				if (column.isNullable()) {
//					de.setNullable(column.isNullable());
//					input.add(de);
//				}
//			}
		}
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
							for (CTCNMDetail removed : input) {
								if (StringUtils.equals(column.getName(), removed.getSnapshot().getName())) {
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
		dialog.setTitle("选择要修改的列");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableColumn[] columns = dialog.getSelection();
			for (TableColumn c : columns) {
				CTCNMDetail detail = ChouseFactory.eINSTANCE.createCTCNMDetail();
				detail.setSnapshot(EcoreUtil.copy(c));
				detail.setNullable(c.isNullable());
				input.add(detail);
			}
			treeViewer.refresh();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected EList<CTCNMDetail> getActionItems(Modification modification) {
		return ((ChangeTableColumnNullableModifycation) action).getDetails();
	}
	
}
