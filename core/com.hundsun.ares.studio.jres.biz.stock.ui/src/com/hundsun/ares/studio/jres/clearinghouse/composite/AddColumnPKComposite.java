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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class AddColumnPKComposite extends ModifyActionColumnComposite<ModifyDetail>{

	public AddColumnPKComposite(Composite parent, TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}
	
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.ADD_TABLE_COLUMN_PK_MODIFICATION__DETAILS;
	}

	@Override
	protected ModifyDetail creatBlankItem() {
		ModifyDetail detail = ChouseFactory.eINSTANCE.createModifyDetail();
		return detail;
	}

	@Override
	protected EList<ModifyDetail> getActionItems(Modification modification) {
		return ((AddTableColumnPKModification)action).getDetails();
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
		
	}

	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof AddTableColumnPKModification){
			action = modification;
		}
		else{
			action = ChouseFactory.eINSTANCE.createAddTableColumnPKModification();
		}
	}
	
}
