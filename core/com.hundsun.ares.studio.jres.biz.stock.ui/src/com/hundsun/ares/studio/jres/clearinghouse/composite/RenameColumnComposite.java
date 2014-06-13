/**
 * 源程序名称：RenameColumnComposite.java
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

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.ColumnSelectionDialog;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelper;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author wangxh
 *
 */
public class RenameColumnComposite extends ModifyActionColumnComposite<RTCMDetail>{
	
	/**
	 * 重命名表字段
	 * @param parent
	 * @param tableData 
	 * @param resource
	 * @param action
	 */
	public RenameColumnComposite(Composite parent, TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getEReference()
	 */
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.RENAME_TABLE_COLUMN_MODIFICATION__DETAILS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected RTCMDetail creatBlankItem() {
		return ChouseFactory.eINSTANCE.createRTCMDetail();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected EList<RTCMDetail> getActionItems(Modification modification) {
		return ((RenameTableColumnModification)action).getDetails();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		
		{
			EAttribute attribute = ChousePackage.Literals.RTCM_DETAIL__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			//tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 老字段名
		{
			EAttribute attribute = ChousePackage.Literals.RTCM_DETAIL__OLD_NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("老字段名");
			
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
		// 新字段名
		{
			EAttribute attribute = ChousePackage.Literals.RTCM_DETAIL__NEW_NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("新字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			//tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(resource.getARESProject());
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.StdField, resource.getARESProject()) {
				@Override
				protected boolean filter(Object inputItem, Object element) {
					if (element instanceof RTCMDetail) {
						RTCMDetail d = (RTCMDetail) element;
						TableColumn c = d.getSnapshot();
						// 非标准字段不提示
						if (c != null && c.getColumnType() == ColumnType.NON_STD_FIELD)
							return false;
						// c == null 应该是老数据，不存在非标的可能性呢
					}
					return super.filter(inputItem, element);
				}
			};			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,resource));
			tvColumn.setEditingSupport(es);
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
							for (RTCMDetail removed : input) {
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
		dialog.setTitle("选择要删除的列");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableColumn[] columns = dialog.getSelection();
			for (TableColumn c : columns) {
				RTCMDetail detail = ChouseFactory.eINSTANCE.createRTCMDetail();
				detail.setSnapshot(EcoreUtil.copy(c));
				input.add(detail);
			}
			treeViewer.refresh();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof RenameTableColumnModification){
			action = (RenameTableColumnModification) modification;
		}
		else
		{
			action = ChouseFactory.eINSTANCE.createRenameTableColumnModification();
		}
	}
	
}
