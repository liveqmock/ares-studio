/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.usermacro.ui.editors.pages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IEMFFormPage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.JresBasicMasterDetailsBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.ColumnViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroPackage;
import com.hundsun.ares.studio.usermacro.impl.UserMacroItemImpl;

/**
 * 用户自定义宏msterdetails
 * @author qinyuan
 *
 */
public class UserMacroMDBlock extends JresBasicMasterDetailsBlock {
	
	private EObject info;
	private TreeViewer viewer;

	private ColumnViewerAddAction addAction;
	private ColumnViewerMoveUpAction moveUpAction;
	private ColumnViewerMoveDownAction moveDownAction;
	
	/**
	 * @param page
	 * @param iEditableControl 
	 * @param btnGroupManager 
	 */
	public UserMacroMDBlock(EMFFormPage page, IEditableControl iEditableControl) {
		super(page,iEditableControl);
	}

	public TreeViewer getViewer() {
		return this.viewer;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#getPageHeadName()
	 */
	@Override
	protected String getPageHeadName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#getSectionName()
	 */
	@Override
	protected String getSectionName() {
		return "宏列表";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#getDescription()
	 */
	@Override
	protected String getDescription() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#getElementTypes()
	 */
	@Override
	protected Object[] getElementTypes() {
		return new Object[]{UserMacroItemImpl.class};
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#getDetailPage(java.lang.Object)
	 */
	@Override
	protected IDetailsPage getDetailPage(Object type) {
		return new UserMacroDetailsPage();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager manager) {
		super.createMenus(manager);

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
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#createViewer(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm, com.hundsun.ares.studio.jres.ui.form.IEMFFormPage)
	 */
	@Override
	protected ColumnViewer createViewer(Composite client, IManagedForm managedForm,
			IEMFFormPage page) {
		//初始化模型
		info = page.getEditor().getInfo();
		
		FilteredTree table = new FilteredTree(client, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, new ColumnViewerPatternFilter(), true);
		viewer = table.getViewer();//new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);
		createTableColumns();
		
		fillTableContents();
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		
		return viewer;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.page.masterdetail.JresBasicMasterDetailsBlock#setMasterLayout(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setMasterLayout(Composite client) {
		// 设置布局
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().hint(50, 50).grab(true, true).applyTo(viewer.getControl());
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		addAction = new ColumnViewerAddAction(viewer, 
				page.getEditingDomain(), 
				info,
				UserMacroPackage.Literals.USER_MACRO__MACRO_ITEMS,
				UserMacroPackage.Literals.USER_MACRO_ITEM);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
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
		moveDownAction.setOwner((EObject) input);
		moveDownAction.setReference(UserMacroPackage.Literals.USER_MACRO__MACRO_ITEMS);
		moveUpAction.setOwner((EObject) input);
		moveUpAction.setReference(UserMacroPackage.Literals.USER_MACRO__MACRO_ITEMS);
		super.setInput(input);
	}
	
	/**
	 * @param buttonManager
	 */
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
	}
	
	private void createTableColumns() {
		//TODO#界面逻辑#秦元#普通#王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #加一个表格控件用于用户操作，注意添加系统字段扩展支持
		{
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("宏名称");
			column.getColumn().setWidth(250);
			column.setLabelProvider(new EObjectColumnLabelProvider(UserMacroPackage.Literals.USER_MACRO_ITEM__NAME));
			column.setEditingSupport(new TextEditingSupport(viewer, UserMacroPackage.Literals.USER_MACRO_ITEM__NAME));
		}
		
		{
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("范围");
			column.getColumn().setWidth(100);
			column.setLabelProvider(new EObjectColumnLabelProvider(UserMacroPackage.Literals.USER_MACRO_ITEM__TYPE));
			column.setEditingSupport(new TextEditingSupport(viewer, UserMacroPackage.Literals.USER_MACRO_ITEM__TYPE));
		}
		
	}
	private void fillTableContents() {
		
		EReference reference = UserMacroPackage.Literals.USER_MACRO__MACRO_ITEMS;
		
		viewer.setContentProvider(new ReferenceTreeContentProvider(reference));

		if(info instanceof UserMacro) {
			viewer.setInput(info);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.MasterDetailsBlock#createToolBarActions(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		
	}


}
