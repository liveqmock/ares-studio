/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
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
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelperWipeOffRepeatStd;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author lvgao
 *
 */
public class STDModelDefineViewerBlock extends TreeViewerBlock {

	
	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;
	
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
	public STDModelDefineViewerBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}

	/**
	 * @return the editingDomain
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	/**
	 * @return the resource
	 */
	public IARESResource getResource() {
		return resource;
	}
	
	/**
	 * @return the problemPool
	 */
	public IProblemPool getProblemPool() {
		return problemPool;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyCellAction.ID);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyColumnAction.ID);
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
		
		// 引用的标准字段
		{
			EAttribute attribute = BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
//			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			
			// 2. proposal provider
			MetadataContentProposalHelperWipeOffRepeatStd helper = new MetadataContentProposalHelperWipeOffRepeatStd(resource.getARESProject());
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.StdField, resource.getARESProject());
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			tvColumn.setEditingSupport(es);
			
		}
		
		
		// 中文名
		{
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("中文名");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.ChineseName);
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		// 字段类型
		{			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段类型");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.Type);
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 字段说明
		{
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("字段说明");
			
			TableColumnRefLabelProvider provider = new TableColumnRefLabelProvider(resource.getBundle(), TableColumnRefLabelProvider.TYPE.Desciption){
				@Override
				public String getToolTipText(Object element) {
					return getText(element);
				}
			};
			tvColumn.setLabelProvider(provider);
			tvColumn.getColumn().setMoveable(true);
		}
		
		
		if (getProblemPool() != null) {
			getProblemPool().addView(problemView);
//			getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		}

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
				BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS,
				BasicdataPackage.Literals.STANDARD_FIELD_COLUMN);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS,
				BasicdataPackage.Literals.STANDARD_FIELD_COLUMN);
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
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		pasteAction =  new ColumnViewerPasteAction(getColumnViewer(), getEditingDomain(), null, null);
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
		
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveUpAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveDownAction));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		moveDownAction.setOwner((EObject) input);
		moveDownAction.setReference(BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS);
		moveUpAction.setOwner((EObject) input);
		moveUpAction.setReference(BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(BasicdataPackage.Literals.STANDARD_FIELD_PACKAGE_DEFINE__FIELDS);
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

	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	

}
