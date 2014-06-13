package com.hundsun.ares.studio.jres.clearinghouse.composite;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.ui.ConstraintSelectionDialog;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;


/**
 * 删除约束界面
 * @author sundl
 *
 */
public class RemoveConstraintComposite extends ModifyActionColumnComposite<ConstraintModifyDetail> {

	public RemoveConstraintComposite(Composite parent,
			TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}

	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.ADD_CONSTRAINT_MODIFICATION__DETAILS;
	}

	@Override
	protected ConstraintModifyDetail creatBlankItem() {
		return ChouseFactory.eINSTANCE.createConstraintModifyDetail();
	}

	@Override
	protected EList<ConstraintModifyDetail> getActionItems(Modification modification) {
		return ((RemoveConstraintModification) action).getDetails();
	}

	@Override
	protected void creatColumnComposite(TreeViewer treeViewer, final IARESResource res) {
		
		{
			EAttribute attribute = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 字段名
		{
			EAttribute attribute = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("约束名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.getColumn().setMoveable(true);
		}

		// 类型
		{
			EAttribute attribute = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__TYPE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("类型");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		}
		
		// 字段列表
		{
			EStructuralFeature feature = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__COLUMNS;
					
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("字段列表");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(feature){
				@Override
				public String getText(Object element) {
					StringBuffer buffer = new StringBuffer();
					if(element instanceof ConstraintModifyDetail){
						ConstraintModifyDetail tableKey = (ConstraintModifyDetail)element;
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
			EStructuralFeature feature = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__FOREIGN_KEY;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("外键");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(feature) {
				public String getText(Object element) {
					if(element instanceof ConstraintModifyDetail){
						ConstraintModifyDetail modify = (ConstraintModifyDetail) element;
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
					if (res != null && res.isReadOnly()) {
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					ConstraintModifyDetail key = (ConstraintModifyDetail)element;
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
				treeViewer, resource, DatabasePackage.Literals.TABLE_KEY, null);
	}
	
	@Override
	protected void handleAdd() {
		ConstraintSelectionDialog dialog = new ConstraintSelectionDialog(getShell(), resource, tableData) {
			protected Control createDialogArea(Composite parent) {
				Control control = super.createDialogArea(parent);
				getTreeViewer().addFilter(new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof TableKey) {
							TableKey column = (TableKey) element;
							for (ConstraintModifyDetail removed : input) {
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
		dialog.setTitle("选择要删除的约束");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableKey[] constraint = dialog.getSelection();
			for (TableKey c : constraint) {
				ConstraintModifyDetail removed = ChouseFactory.eINSTANCE.createConstraintModifyDetail();
				removed.setMark(c.getMark());
				removed.setName(c.getName());
				removed.setType(c.getType());
				for (TableColumn col : c.getColumns()) {
					removed.getColumns().add(EcoreUtil.copy(col));
				}
				for (ForeignKey key : c.getForeignKey()) {
					removed.getForeignKey().add(EcoreUtil.copy(key));
				}
				input.add(removed);
			}
			treeViewer.refresh();
		}
	}

	@Override
	protected void initAction(Modification modification) {
		if(modification instanceof RemoveConstraintModification){
			action = modification;
		}
		else{
			action = ChouseFactory.eINSTANCE.createRemoveConstraintModification();
		}
	}

}
