package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.CopyAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ExportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ImportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.InsertItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.RemoveAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ShowCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.editors.dnd.MenuDropAdapter;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.AddChildMenuItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.AddMenuItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.MenuItemMoveDownAction;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.MenuItemMoveUpAction;
import com.hundsun.ares.studio.jres.metadata.ui.menu.actions.MenuItemPasteAction;
import com.hundsun.ares.studio.jres.metadata.ui.refactor.MatadataRenameAction;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MenuColumnViewerProblemView;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MenuExtensibleModelColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MenuHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MenuListTreeContentProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataDescColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.actions.ActionGroup;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.ValidateAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class MenuListViewerBlock extends MetadataListViewerBlock {

	private MetadataTreeContentProvider contentProvider;
	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public MenuListViewerBlock(FormPage page, EditingDomain editingDomain,
			IWorkbenchPartSite site, IARESResource resource,
			IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}

	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		return null;
	}

	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected boolean getDefaultShowCategory() {
		return false;
	}
	
	@Override
	protected void addDragSupport(TreeViewer viewer) {
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		//编辑器列表支持拖拽功能
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new MenuDropAdapter(viewer, getEditingDomain()));
	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		contentProvider = new MenuListTreeContentProvider();
		contentProvider.setShowCategory(false);
		return contentProvider;
	}
	
	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME;
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		/**
		 *	a)	分组（与标准字段分组一致，输入规约也一致）
			b)	ID
			c)	名称
			d)	页面
			e)	备注

		 */
		
		final TreeViewer treeViewer = viewer;
		EObjectColumnViewerProblemView problemView = new MenuColumnViewerProblemView(treeViewer);
		
		/**名称*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("菜单标题");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MenuHeaderColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);
		}
		
		/**ID*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("菜单号");
			column.getColumn().setWidth(120);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
		}

		/**页面*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.MENU_ITEM__URL;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("页面");
			column.getColumn().setWidth(200);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
//			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);

		}
		/**备注*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(200);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataDescColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new LongTextEditingSupport(viewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);

		}
		
		// 增加扩展列支持
		createExtensibleModelTreeViewerColumns(getColumnViewer(), getARESResource(), 
		MetadataPackage.Literals.MENU_ITEM, problemView);

//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		
		getProblemPool().addView(problemView);
	}
	
	private void createExtensibleModelTreeViewerColumns(
			TreeViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider) {
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
				.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());

		try {
			for (IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support
						.getPropertyDescriptors(resource, eClass)) {
					TreeViewerColumn tvColumn = new TreeViewerColumn(viewer,
							SWT.LEFT);

					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();

					Point p = gc.stringExtent(displayName);

					tvColumn.getColumn().setWidth(p.x + 20);
					tvColumn.getColumn().setText(displayName);

					ExtensibleModelColumnLabelProvider provider = new MenuExtensibleModelColumnLabelProvider(
							support, descriptor,getARESResource());
					provider.setDiagnosticProvider(diagnosticProvider);
					tvColumn.setLabelProvider(provider);

					tvColumn.setEditingSupport(new ExtensibleModelEditingSupport(
							viewer, support, descriptor));
					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		List<IAction> actions = new ArrayList<IAction>();
		
		IAction action = new AddMenuItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				MetadataPackage.Literals.MENU_ITEM);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		IAction actionInsert = new InsertItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				MetadataPackage.Literals.MENU_ITEM);
		getActionRegistry().registerAction(actionInsert);
		getSelectionActions().add(actionInsert.getId());
		
		action = new RemoveAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		action = new AddChildMenuItemAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		action = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		action = new MenuItemMoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		actions.add(action);
		
		action = new MenuItemMoveDownAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		actions.add(action);
		
		action = new ShowCategoryAction(getColumnViewer());
		getActionRegistry().registerAction(action);
		actions.add(action);
		
		IAction copyAction = new CopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		IAction pasteAction = new MenuItemPasteAction(getColumnViewer(), getEditingDomain(),
				MetadataPackage.Literals.METADATA_CATEGORY, MetadataPackage.Literals.MENU_ITEM);
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		
		action = new ValidateAction(getFormPage());
		getActionRegistry().registerAction(action);
		actions.add(action);
		
		ImportMetadataAction importAction = new ImportMetadataAction(getARESResource(), getColumnViewer(),getEditingDomain());
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
		String dialogTitle	= "导出菜单与功能";
		String dialogMessage = "将项目中的菜单与功能导出(Excel文件).";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/menu.gif").createImage();
		ExportMetadataAction exportAction = new ExportMetadataAction(getARESResource(), getSite(),dialogTitle,dialogImage,dialogMessage);
		getActionRegistry().registerAction(exportAction);
		getSelectionActions().add(exportAction.getId());
		
		for ( IAction item: actions) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(item));
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		//创建菜单条目列表的操作列表
		List<IAction> newActions = new ArrayList<IAction>();
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		newActions.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_MENU_ITEM);
		newActions.add(action);
		
		ActionGroup newGroup = new ActionGroup(newActions.toArray(new IAction[0]));
		manager.add(newGroup);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_IMPORT_METADATA);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_EXPORT_METADATA);
		manager.add(action);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		//创建菜单条目的右键菜单
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		menuManager.add(action);
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_MENU_ITEM);
		menuManager.add(action);

		menuManager.add(new Separator());

		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_REMOVE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		menuManager.add(new Separator());

		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_DOWN);
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
		
		action = new MatadataRenameAction(getEditingDomain(),"菜单号",getFormPage().getEditor(),getColumnViewer(),getARESResource());
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_VALIDATE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		menuManager.add(action);
	}
	
	@Override
	protected void storeShowCategoryState(IDialogSettings settings) {

	}
	
	@Override
	protected void restoreShowCategoryState(IDialogSettings settings) {
		if (contentProvider != null) {
			// sundl 强制不显示分类
			contentProvider.setShowCategory(false);
		}

	}
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
}
