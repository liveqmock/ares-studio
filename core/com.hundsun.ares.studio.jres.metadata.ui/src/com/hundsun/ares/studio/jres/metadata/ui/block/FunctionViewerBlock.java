package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.EventObject;

import org.apache.commons.lang.StringUtils;
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
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
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
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class FunctionViewerBlock extends TreeViewerBlock {

	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;
	private IWorkbenchPartSite site;
	private FormPage page;
	private ColumnViewerAddAction addAction;
	private ColumnViewerInsertAction insertAction;
	private ColumnViewerPasteAction pasteAction;
	private ColumnViewerMoveUpAction actionMoveUp;
	private ColumnViewerMoveDownAction actionMoveDown;
	
	public FunctionViewerBlock(FormPage page,EditingDomain editingDomain,IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		this.page = page;
		this.site = site;
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		RefreshViewerJob.refresh(getColumnViewer());
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
	public IARESResource getARESResource() {
		return resource;
	}

	/**
	 * @return the problemPool
	 */
	public IProblemPool getProblemPool() {
		return problemPool;
	}

	/**
	 * @return the site
	 */
	public IWorkbenchPartSite getSite() {
		return site;
	}

	public FormPage getFormPage (){
		return page;
	}

	protected void createColumns(TreeViewer _viewer) {
		final TreeViewer viewer = (TreeViewer) _viewer;
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(viewer);
		if(!MenuUtils.isStockDepartment()){
			/**子交易码,银行模式下才显示*/
			{
				TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
				comlumn.getColumn().setText("子交易码");
				comlumn.getColumn().setWidth(130);
				EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE , getARESResource());
				provider.setDiagnosticProvider(problemView);
				comlumn.setLabelProvider(provider);
				comlumn.setEditingSupport(new TextEditingSupport(viewer, MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE));
			}
			/**服务,银行模式下才显示*/
			{
				TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
				comlumn.getColumn().setText("服务");
				comlumn.getColumn().setWidth(160);
				EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.FUNCTION__SERV_ID , getARESResource());
				provider.setDiagnosticProvider(problemView);
				comlumn.setLabelProvider(provider);
				comlumn.setEditingSupport(new TextEditingSupport(viewer, MetadataPackage.Literals.FUNCTION__SERV_ID));
				comlumn.getColumn().setMoveable(true);
			}
		}
		/**功能号*/
		{
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("功能编号");
			comlumn.getColumn().setWidth(160);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.NAMED_ELEMENT__NAME , getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, MetadataPackage.Literals.NAMED_ELEMENT__NAME));
			comlumn.getColumn().setMoveable(true);
		}
		
		/**功能名称*/
		{
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("功能名称");
			comlumn.getColumn().setWidth(100);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME , getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME));
			comlumn.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("说明");
			comlumn.getColumn().setWidth(200);
			comlumn.setLabelProvider(new MetadataColumnLabelProvider(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION , getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = super.getToolTipText(element);
					if(StringUtils.isBlank(text)){
						return getText(element);
					}
					return text;
				}
			});
			comlumn.setEditingSupport(new LongTextEditingSupport(viewer, MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION));
			comlumn.getColumn().setMoveable(true);
		}
		
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				viewer, getARESResource(), MetadataPackage.Literals.FUNCTION, exProblemView);
		
		getProblemPool().addView(problemView);
		getProblemPool().addView(exProblemView);
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		
		addAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(), 
				null,
				MetadataPackage.Literals.MENU_LIST__FUNCTIONS,
				MetadataPackage.Literals.FUNCTION) {
		};
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.MENU_LIST__FUNCTIONS,
				MetadataPackage.Literals.FUNCTION);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
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
		
		actionMoveUp = new ColumnViewerMoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(actionMoveUp);
		getSelectionActions().add(actionMoveUp.getId());
		getStackActions().add(actionMoveUp.getId());
		
		actionMoveDown = new ColumnViewerMoveDownAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(actionMoveDown);
		getSelectionActions().add(actionMoveDown.getId());
		getStackActions().add(actionMoveDown.getId());
		
		//只读控制
		getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(actionMoveUp));
		getEditableControl().addEditableUnit(new ActionEditableUnit(actionMoveDown));
	}
	
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(MetadataPackage.Literals.MENU_LIST__FUNCTIONS);
		actionMoveUp.setOwner((EObject) input);
		actionMoveUp.setReference(MetadataPackage.Literals.MENU_LIST__FUNCTIONS);
		actionMoveDown.setOwner((EObject) input);
		actionMoveDown.setReference(MetadataPackage.Literals.MENU_LIST__FUNCTIONS);
		super.setInput(input);
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);

		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
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
	
	
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(MetadataPackage.Literals.MENU_LIST__FUNCTIONS);
	}
	
	@Override
	protected String getID() {
		return getClass().getName();
	}
	
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}

}
