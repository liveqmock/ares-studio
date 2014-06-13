/**
 * 源程序名称：AddConstraintComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.biz.stock.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.celleditor.ConstraintColumnsEditingSupport;
import com.hundsun.ares.studio.jres.clearinghouse.celleditor.ConstraintForeignKeyEditingSupport;
import com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.ui.editor.editingsupport.EnumEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 增加约束界面
 * @author sundl
 *
 */
public class AddConstraintComposite extends ModifyActionColumnComposite<ConstraintModifyDetail>{

	public AddConstraintComposite(Composite parent, TableResourceData tableData, IARESResource resource, Modification action) {
		super(parent, tableData, resource, action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getEReference()
	 */
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.ADD_CONSTRAINT_MODIFICATION__DETAILS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected ConstraintModifyDetail creatBlankItem() {
		return ChouseFactory.eINSTANCE.createConstraintModifyDetail();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected EList<ConstraintModifyDetail> getActionItems(Modification modification) {
		return ((AddConstraintModification) action).getDetails();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer, final IARESResource resource) {
		
		{
			EAttribute attribute = ChousePackage.Literals.CONSTRAINT_MODIFY_DETAIL__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
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
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
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
			
			tvColumn.setEditingSupport(new EnumEditingSupport(treeViewer, attribute));
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
			
			tvColumn.setEditingSupport(new ConstraintColumnsEditingSupport(treeViewer,feature,resource, tableData));
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
					if (resource != null && resource.isReadOnly()) {
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
			
//			ForeignKeyColumnEditingSupport editingSupport = new ForeignKeyColumnEditingSupport(treeViewer,feature,resource);
			ConstraintForeignKeyEditingSupport editingSupport = new ConstraintForeignKeyEditingSupport(treeViewer, feature, resource, tableData);
			editingSupport.setDecorator(new IEditingSupportDecorator() {
				@Override
				public CellEditor decorateGetCellEditor(CellEditor cellEditor,	Object element) {
					return cellEditor;
				};
				@Override
				public boolean decorateCanEdit(boolean canEdit, Object element) {
					ConstraintModifyDetail key = (ConstraintModifyDetail)element;
					return key.getType().equals(key_type.FOREIGN);
				}
			});
			tvColumn.setEditingSupport(editingSupport);
			tvColumn.getColumn().setMoveable(true);
		}
		
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, resource, DatabasePackage.Literals.TABLE_KEY, null);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {
		if(modification instanceof AddConstraintModification){
			action = modification;
		}
		else{
			action = ChouseFactory.eINSTANCE.createAddConstraintModification();
		}
	}

}
