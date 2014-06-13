/**
 * 源程序名称：RevisionHistoryListViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.ui.editor.actions.AddRevisionHistoryAction;
import com.hundsun.ares.studio.ui.editor.actions.AddRevisionHistoryRecordAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.IncreaseVersionAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editable.IEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.LongTextEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public class RevisionHistoryListViewerBlock extends TableViewerBlock {

	private IWorkbenchPartSite site;
	private FormPage page;
	protected ColumnViewerPasteAction pasteAction;
	protected ColumnViewerMoveUpAction actionMoveUp;
	protected ColumnViewerMoveDownAction actionMoveDown;
	protected AddRevisionHistoryAction increaseVersionAction;
	protected AddRevisionHistoryAction addRevisionHistoryAction;
	
	public RevisionHistoryListViewerBlock(FormPage page,EditingDomain editingDomain,IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		this.page = page;
		this.site = site;
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
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

	@Override
	protected void createColumns(TableViewer _viewer) {
		final TableViewer viewer = _viewer;
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(viewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(viewer);
		
		//TODO#界面逻辑#秦元#普通#王彬#已编码#2011-07-25 #36 #25 #加一个表格控件用于操作修订历史，注意添加系统字段扩展支持
		//表格列：修订时间、修订版本、修订单号、申请人、负责人、修改原因、修改内容
		
		/**修订时间*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订时间");
			comlumn.getColumn().setWidth(130);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE);
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_DATE));
		}
		/**修订版本*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订版本");
			comlumn.getColumn().setWidth(160);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__VERSION);
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__VERSION));
			comlumn.getColumn().setMoveable(true);
		}
		/**修订单号*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修订单号");
			comlumn.getColumn().setWidth(160);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER);
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER));
			comlumn.getColumn().setMoveable(true);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTableViewerColumns(
				viewer, getARESResource(), CorePackage.Literals.REVISION_HISTORY, exProblemView);
		
		/**申请人*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("申请人");
			comlumn.getColumn().setWidth(100);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY);
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_BY));
			comlumn.getColumn().setMoveable(true);
		}
		/**负责人 */
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("负责人");
			comlumn.getColumn().setWidth(100);
			comlumn.setLabelProvider(new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__CHARGER));
			comlumn.setEditingSupport(new TextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__CHARGER));
			comlumn.getColumn().setMoveable(true);
		}
		
		/**修改内容*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修改内容");
			comlumn.getColumn().setWidth(200);
			comlumn.setLabelProvider(new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED));
			comlumn.setEditingSupport(new LongTextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED));
			comlumn.getColumn().setMoveable(true);
		}
		
		/**修改原因*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("修改原因");
			comlumn.getColumn().setWidth(200);
			comlumn.setLabelProvider(new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON));
			comlumn.setEditingSupport(new LongTextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__MODIFIED_REASON));
			comlumn.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TableViewerColumn comlumn = new TableViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("备注");
			comlumn.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__COMMENT);
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			comlumn.setEditingSupport(new LongTextEditingSupport(viewer, CorePackage.Literals.REVISION_HISTORY__COMMENT));
			comlumn.getColumn().setMoveable(true);
		}
		
		getProblemPool().addView(problemView);
		getProblemPool().addView(exProblemView);
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());
		
		IAction copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
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
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		createRevisionActions();
		
		//只读控制
		getEditableControl().addEditableUnit(new ActionEditableUnit(actionMoveUp));
		getEditableControl().addEditableUnit(new ActionEditableUnit(actionMoveDown));
	}
	
	protected void createRevisionActions() {
		IARESResource aresResource = getARESResource();
		
		increaseVersionAction = new IncreaseVersionAction(getColumnViewer(), getEditingDomain(), null, CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES, aresResource);
		getActionRegistry().registerAction(increaseVersionAction);
		getSelectionActions().add(IncreaseVersionAction.ID);
		getEditableControl().addEditableUnit(new HistoryActionEditableUnit(increaseVersionAction));
		
		addRevisionHistoryAction = new AddRevisionHistoryRecordAction(getColumnViewer(), getEditingDomain(), null, CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES,aresResource);
		getActionRegistry().registerAction(addRevisionHistoryAction);
		getSelectionActions().add(AddRevisionHistoryRecordAction.ID);
		getEditableControl().addEditableUnit(new HistoryActionEditableUnit(addRevisionHistoryAction));
	}
	
	@Override
	public void setInput(Object input) {
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(getReference());
		actionMoveUp.setOwner((EObject) input);
		actionMoveUp.setReference(getReference());
		actionMoveDown.setOwner((EObject) input);
		actionMoveDown.setReference(getReference());
		
		if (increaseVersionAction != null)
			increaseVersionAction.setInfo((EObject) input);
		
		if (addRevisionHistoryAction != null)
			addRevisionHistoryAction.setInfo((EObject) input);
		
		super.setInput(input);
	}
	
	/**
	 * 为了复用这个block, 可能会用在不同的对象上，所以需要可以重新定义修订记录列表所对应的EMF属性
	 * @return
	 */
	protected EReference getReference() {
		return CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#getHeadColumnFeature()
	 */
	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return CorePackage.Literals.REVISION_HISTORY__ORDER_NUMBER;
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		buttonManager.add(increaseVersionAction);
		buttonManager.add(addRevisionHistoryAction);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
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
		return new ReferenceTreeContentProvider(getReference());
	}
	
	@Override
	protected String getID() {
		return getClass().getName();
	}
	
	@Override
	protected IAction getAddAction() {
		return increaseVersionAction;
	}
	
	public class HistoryActionEditableUnit implements IEditableUnit {

		IAction action;
		public HistoryActionEditableUnit(IAction action){
			this.action = action;
		}
		
		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableUnit#setReadonlyStatus(java.lang.String, java.lang.Object)
		 */
		@Override
		public void setReadonlyStatus(String key, Object status) {
			if(StringUtils.equals(KEY_SYSTEM, key)){
				if(!EDITABLE_TRUE.equals(status)){
					this.action.setEnabled(false);
				}
			}
		}

	}
	
}
