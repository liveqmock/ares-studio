/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.apache.commons.io.CopyUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.provider.RemoveIndexFieldColumnLabelProvider;
import com.hundsun.ares.studio.jres.clearinghouse.support.IndexFeildColumnEditingSupport;
import com.hundsun.ares.studio.jres.clearinghouse.ui.IndexSelectDialog;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author liaogc
 *
 */
public class RemoveIndexFieldComposite  extends ModifyActionColumnComposite<RemoveIndexField>{

	/**
	 * @param parent
	 * @param tableData
	 * @param resource
	 * @param action
	 */
	public RemoveIndexFieldComposite(Composite parent,
			TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getEReference()
	 */
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.REMOVE_INDEX_FIELD_MODIFICATION__INDEXS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected RemoveIndexField creatBlankItem() {
		return ChouseFactory.eINSTANCE.createRemoveIndexField();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected EList<RemoveIndexField> getActionItems(Modification modification) {
		return ((RemoveIndexFieldModification)action).getIndexs();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		{
			EAttribute attribute = ChousePackage.Literals.REMOVE_INDEX_FIELD__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
	
		// 索引名
		{
			EAttribute attribute = ChousePackage.Literals.REMOVE_INDEX_FIELD__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("索引名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
		
		}
			
			// 索引字段列表
		  {
				EStructuralFeature feature = ChousePackage.Literals.REMOVE_INDEX_FIELD__INDEX_FIELDS;
			
				TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
				tvColumn.getColumn().setWidth(250);
				tvColumn.getColumn().setText("删除索引字段列表");
				
				EObjectColumnLabelProvider provider = new RemoveIndexFieldColumnLabelProvider(feature);
				tvColumn.setLabelProvider(provider);
				
				tvColumn.setEditingSupport(new IndexFeildColumnEditingSupport(treeViewer, feature,resource));
				tvColumn.getColumn().setMoveable(true);
			}
		  this.treeViewer.setInput(((RemoveIndexFieldModification)this.action).getIndexs());
		
	
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#handleAdd()
	 */
	@Override
	protected void handleAdd() {

		IndexSelectDialog dialog = new IndexSelectDialog(getShell(), resource, tableData);
		dialog.setTitle("选择要删除字段的索引");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableIndex[] tableIndexs = dialog.getSelection();
			for (TableIndex tableIndex : tableIndexs) {
				RemoveIndexField removedIndexField = ChouseFactory.eINSTANCE.createRemoveIndexField();
				removedIndexField.setName(tableIndex.getName());
				
				for (TableIndexColumn indexColumn : tableIndex.getColumns()) {
					removedIndexField.getIndexFields().add(EcoreUtil.copy(indexColumn));
				}
				
				input.add(removedIndexField);
			}
			treeViewer.refresh();
		}
	
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {

		if(modification != null && modification instanceof RemoveIndexFieldModification){
			action = (RemoveIndexFieldModification) modification;
		}
		else
		{
			action = ChouseFactory.eINSTANCE.createRemoveIndexFieldModification();
		}
	
		
	}

}
