package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.FuncProxyContentProposalHelper;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.FuncProxyContentProposalProvider;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.BatchAddFunctionProxyAction;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.RecoverRefMenuFunctionsAction;
import com.hundsun.ares.studio.jres.metadata.ui.utils.MenuUtils;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
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
import com.hundsun.ares.studio.ui.editor.blocks.TableViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class FunctionProxyViewerBlock extends TableViewerBlock {

	private ColumnViewerAddAction itemAddAction;
	private ColumnViewerInsertAction insertAction;
	private ColumnViewerDeleteAction itemDeleteAction;
	private ColumnViewerMoveUpAction itemMoveUpAction;
	private ColumnViewerMoveDownAction itemMoveDownAction;
	private ColumnViewerPasteAction pasteAction;
	

	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public FunctionProxyViewerBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}
	
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceContentProvider(MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS);
	}

	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE;
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		
		IAction action = itemAddAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(), 
				null,
				MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS,
				MetadataPackage.Literals.FUNCTION_PROXY);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS,
				MetadataPackage.Literals.FUNCTION_PROXY);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		action = itemDeleteAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = itemMoveUpAction = new ColumnViewerMoveUpAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		action = itemMoveDownAction = new ColumnViewerMoveDownAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
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
		
		action = new BatchAddFunctionProxyAction(getColumnViewer(), getEditingDomain(),resource);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		IAction recoverAction = new RecoverRefMenuFunctionsAction(getColumnViewer(), getEditingDomain(), resource);
		getActionRegistry().registerAction(recoverAction);
		getSelectionActions().add(recoverAction.getId());
		
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemAddAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemDeleteAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveUpAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveDownAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(action));
		getEditableControl().addEditableUnit(new ActionEditableUnit(recoverAction));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		insertAction.setOwner((EObject) input);
		
		itemAddAction.setOwner((EObject) input);
		itemMoveUpAction.setOwner((EObject) input);
		itemMoveDownAction.setOwner((EObject) input);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS);
		super.setInput(input);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_BATCH_ADD);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_RECOVER_FUNCTIONS);
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
	protected void createColumns(TableViewer columnViewer) {
		final TableViewer treeViewer = (TableViewer) columnViewer;
		
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		/**证劵模式先为菜单号,银行模式下为交易码*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			if(MenuUtils.isStockDepartment()){
				column.getColumn().setText("菜单号");
			}else{
				column.getColumn().setText("交易码");
			}
			column.getColumn().setWidth(120);
			CellLabelProvider provider = new ColumnLabelProvider(){
				@Override
				public String getText(Object element) {
					if(element instanceof FunctionProxy){
						return ((MenuItem)((FunctionProxy) element).eContainer()).getName();
					}else{
						return StringUtils.EMPTY;
					}
				}
				@Override
				public Color getBackground(Object element) {
					return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
				}
			};
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		
		/**证劵模式下为功能号,银行模式下为子交易码*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			if(MenuUtils.isStockDepartment()){
				column.getColumn().setText("功能编号");
			}else{
				column.getColumn().setText("子交易码");
			}
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE ,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			FuncProxyContentProposalHelper helper = new FuncProxyContentProposalHelper(resource);
			FuncProxyContentProposalProvider proposalProvider = new FuncProxyContentProposalProvider(helper,resource);
			
			//创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					MetadataPackage.Literals.FUNCTION_PROXY__FUN_CODE, 
					proposalProvider);
			column.setEditingSupport(es);
		}
		/**功能名称*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("功能名称");
			column.getColumn().setWidth(120);
			CellLabelProvider provider = new ColumnLabelProvider(){
				@Override
				public String getText(Object element) {
					if(element instanceof FunctionProxy){
						return MenuUtils.getFunctionByName(resource, ((FunctionProxy) element).getFunCode()).getChineseName();
					}else{
						return StringUtils.EMPTY;
					}
				}
				@Override
				public Color getBackground(Object element) {
					return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
				}
			};
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.FUNCTION_PROXY__DESCRIPTION){
				@Override
				public Color getBackground(Object element) {
					return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
				}
				@Override
				public String getText(Object element) {
					if(element instanceof FunctionProxy){
						return MenuUtils.getFunctionByName(resource, ((FunctionProxy) element).getFunCode()).getDescription();
					}else{
						return StringUtils.EMPTY;
					}
				}
				@Override
				public String getToolTipText(Object element) {
					String text = super.getToolTipText(element);
					if(StringUtils.isBlank(text)){
						return getText(element);
					}
					return text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		
		// 增加扩展列支持
		MenuUtils.createFunctionProxyExtensibleModelColumns(getColumnViewer(), getARESResource(), 
		MetadataPackage.Literals.FUNCTION);
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(columnViewer.getControl()));
		getProblemPool().addView(problemView);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		//创建详细表的操作列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_BATCH_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_RECOVER_FUNCTIONS);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
	}
	
	@Override
	protected void addToolTipSupport(TableViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
}
