/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.provider.IndexFieldAddColumnLabelProvider;
import com.hundsun.ares.studio.jres.clearinghouse.support.IndexFeildAddColumnEditingSupport;
import com.hundsun.ares.studio.jres.clearinghouse.ui.IndexSelectDialog;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author liaogc
 *
 */
public class AddIndexFieldComposite extends ModifyActionColumnComposite<AddIndexField>{

	/**
	 * @param parent
	 * @param tableData
	 * @param resource
	 * @param action
	 */
	public AddIndexFieldComposite(Composite parent,
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
		return ChousePackage.Literals.ADD_INDEX_FIELD_MODIFICATION__INDEXS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected AddIndexField creatBlankItem() {
		return ChouseFactory.eINSTANCE.createAddIndexField();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected EList<AddIndexField> getActionItems(Modification modification) {
		return ((AddIndexFieldModification)action).getIndexs();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			final IARESResource resource) {
	

		{
			EAttribute attribute = ChousePackage.Literals.ADD_INDEX_FIELD__MARK;
			
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
			EAttribute attribute = ChousePackage.Literals.ADD_INDEX_FIELD__NAME;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("索引名");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
		}
//		// 索引字段列表
		{
			EStructuralFeature feature = ChousePackage.Literals.ADD_INDEX_FIELD__INDEX_FIELDS;
					
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("增加索引字段列表");
			
			IndexFieldAddColumnLabelProvider provider = new IndexFieldAddColumnLabelProvider(feature);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new IndexFeildAddColumnEditingSupport(treeViewer,feature,resource));
			tvColumn.getColumn().setMoveable(true);
		}
		  this.treeViewer.setInput(((AddIndexFieldModification)this.action).getIndexs());
		
	
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionColumnComposite#handleAdd()
	 */
	@Override
	protected void handleAdd() {

		IndexSelectDialog dialog = new IndexSelectDialog(getShell(), resource, tableData);
		dialog.setTitle("选择增加的字段的索引");
		if (dialog.open() == IDialogConstants.OK_ID) {
			TableIndex[] tableIndexs = dialog.getSelection();
			for (TableIndex tableIndex : tableIndexs) {
				AddIndexField addIndexField = ChouseFactory.eINSTANCE.createAddIndexField();
				addIndexField.setName(tableIndex.getName());
				input.add(addIndexField);
			}
			treeViewer.refresh();
		}
	
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.chouse.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {

		if(modification != null && modification instanceof AddIndexFieldModification){
			action = (AddIndexFieldModification) modification;
		}
		else
		{
			action = ChouseFactory.eINSTANCE.createAddIndexFieldModification();
		}
	
		
	}
}