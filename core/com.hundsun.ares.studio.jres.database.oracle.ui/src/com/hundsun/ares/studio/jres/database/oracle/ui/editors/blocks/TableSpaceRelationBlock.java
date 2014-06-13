/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.ui.editors.blocks;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers.OracleBlockLabelProvider;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceColumnProposalProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author wangxh
 *
 */
public class TableSpaceRelationBlock extends TreeViewerBlock {
	
	private ColumnViewerAddAction addAction;
	private ColumnViewerInsertAction insertAction;
	private ColumnViewerMoveUpAction moveUpAction;
	private ColumnViewerMoveDownAction moveDownAction;
	private ColumnViewerPasteAction pasteAction;
	
	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public TableSpaceRelationBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;

	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS);
	}

	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE;
	}
	
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		menuManager.add(action);
		
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		menuManager.add(action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		// 用于一般的列
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(treeViewer);
		
				// 主表控件
		{
			EAttribute attribute = OraclePackage.Literals.TABLE_SPACE_RELATION__MAIN_SPACE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("主表空间");
			
			EObjectColumnLabelProvider provider = new OracleBlockLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			// 2. proposal provider
			OracleSpaceColumnProposalProvider proposalProvider = new OracleSpaceColumnProposalProvider(IOracleRefType.Space);
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,resource));
			tvColumn.setEditingSupport(es);
			
		}
		// 索引表空间
		{
			EAttribute attribute = OraclePackage.Literals.TABLE_SPACE_RELATION__INDEX_SPACE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("索引表空间");
			
			EObjectColumnLabelProvider provider = new OracleBlockLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			OracleSpaceColumnProposalProvider proposalProvider = new OracleSpaceColumnProposalProvider(IOracleRefType.Space);
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			tvColumn.setEditingSupport(es);
			tvColumn.getColumn().setMoveable(true);
		}
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, getARESResource(), OraclePackage.Literals.TABLE_SPACE_RELATION, exProblemView);
		
		//错误信息
		getProblemPool().addView(problemView);
		getProblemPool().addView(exProblemView);
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		addAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS,
				OraclePackage.Literals.TABLE_SPACE_RELATION);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS,
				OraclePackage.Literals.TABLE_SPACE_RELATION);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
		moveUpAction = new ColumnViewerMoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveUpAction);
		getSelectionActions().add(moveUpAction.getId());
		getStackActions().add(moveUpAction.getId());
		
		moveDownAction = new ColumnViewerMoveDownAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveDownAction);
		getSelectionActions().add(moveDownAction.getId());
		getStackActions().add(moveDownAction.getId());
		
		IAction copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		pasteAction =  new ColumnViewerPasteAction(getColumnViewer(), getEditingDomain(), null, null);
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
		
		//只读控制
		getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveUpAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(moveDownAction));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		moveDownAction.setOwner((EObject) input);
		moveDownAction.setReference(OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS);
		moveUpAction.setOwner((EObject) input);
		moveUpAction.setReference(OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(OraclePackage.Literals.ORACLE_SPACE_RESOURCE_DATA__RELATIONS);
		super.setInput(input);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}
}
