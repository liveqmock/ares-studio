package com.hundsun.ares.studio.jres.obj.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ui.ObjContentProposalHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddChildCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddSiblingCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.CopyAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.InsertItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveDownAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveUpAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.PasteAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.RemoveAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ShowCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.block.MetadataNewActionGroup;
import com.hundsun.ares.studio.jres.metadata.ui.refactor.MatadataRenameAction;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnViewerProblemView;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.jres.obj.ui.StdObjFieldColumnLabelProvider;
import com.hundsun.ares.studio.jres.obj.ui.StdObjFieldEditingSupportDecorator;
import com.hundsun.ares.studio.jres.obj.ui.StdObjFieldTypeContentProposalProvider;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ActionGroup;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class StdObjListViewerBlock extends MetadataListViewerBlock{

	public StdObjListViewerBlock(FormPage page, EditingDomain editingDomain,
			IWorkbenchPartSite site, IARESResource resource,
			IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}
	
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = viewer;
		EObjectColumnViewerProblemView problemView = new MetadataColumnViewerProblemView(treeViewer);
		/**ID*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字段名");
			column.getColumn().setWidth(160);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataHeaderColumnLabelProvider(attribute,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(treeViewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
		}
		
		/**名称*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字段名称");
			column.getColumn().setWidth(150);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new StdObjFieldColumnLabelProvider(attribute, getARESResource().getARESProject());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(treeViewer, attribute);
			es.setDecorator(new StdObjFieldEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);
		}
		
		/**业务数据类型*/
		{
			// 定义主属性
			EAttribute attribute = BizPackage.Literals.STANDARD_OBJ_FIELD__TYPE;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("字段类型");
			comlumn.getColumn().setWidth(130);
			// 设置标签提供器
			StdObjFieldColumnLabelProvider provider = new StdObjFieldColumnLabelProvider(attribute, getARESResource().getARESProject());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			// 1. 获取aresProject
			final IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();
			
			// 2. proposal provider
			ObjContentProposalHelper helper = new ObjContentProposalHelper();
			StdObjFieldTypeContentProposalProvider proposalProvider = new StdObjFieldTypeContentProposalProvider(helper, project);
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		
		/**备注*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("说明");
			comlumn.getColumn().setWidth(200);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new StdObjFieldColumnLabelProvider(attribute, getARESResource().getARESProject()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			// 设置编辑支持
			TextEditingSupport es = new LongTextEditingSupport(treeViewer, attribute);
			es.setDecorator(new StdObjFieldEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		
		// 增加扩展列支持
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(getColumnViewer(), getARESResource(), 
		BizPackage.Literals.STANDARD_OBJ_FIELD, problemView);
		
		getProblemPool().addView(problemView);
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
	protected void createActions() {
		super.createActions();
		
		List<IAction> actions = new ArrayList<IAction>();
		
		IAction addItemAction = new AddItemAction(getColumnViewer(), getEditingDomain(), BizPackage.Literals.STANDARD_OBJ_FIELD);
		getActionRegistry().registerAction(addItemAction);
		getSelectionActions().add(addItemAction.getId());
		actions.add(addItemAction);
		
		IAction actionInsert = new InsertItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				BizPackage.Literals.STANDARD_OBJ_FIELD);
		getActionRegistry().registerAction(actionInsert);
		getSelectionActions().add(actionInsert.getId());
		
		IAction addChildCategoryAction = new AddChildCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(addChildCategoryAction);
		getSelectionActions().add(addChildCategoryAction.getId());
		categoryWareActions.add(addChildCategoryAction.getId());
		actions.add(addChildCategoryAction);
		
		IAction addSiblingCategoryAction = new AddSiblingCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(addSiblingCategoryAction);
		getSelectionActions().add(addSiblingCategoryAction.getId());
		categoryWareActions.add(addSiblingCategoryAction.getId());
		actions.add(addSiblingCategoryAction);
		
		IAction columnViewerDelAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(columnViewerDelAction);
		getSelectionActions().add(columnViewerDelAction.getId());
		actions.add(columnViewerDelAction);
		
		IAction removeAction = new RemoveAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(removeAction);
		getSelectionActions().add(removeAction.getId());
		actions.add(removeAction);
		
		IAction moveUpAction = new MoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveUpAction);
		getSelectionActions().add(moveUpAction.getId());
		getStackActions().add(moveUpAction.getId());
		actions.add(moveUpAction);
		
		IAction moveDownAction = new MoveDownAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(moveDownAction);
		getSelectionActions().add(moveDownAction.getId());
		getStackActions().add(moveDownAction.getId());
		actions.add(moveDownAction);
		
		IAction showCategoryAction = new ShowCategoryAction(getColumnViewer());
		getActionRegistry().registerAction(showCategoryAction);
		actions.add(showCategoryAction);
		
		IAction copyAction = new CopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		IAction pasteAction = new PasteAction(getColumnViewer(), getEditingDomain(),
				MetadataPackage.Literals.METADATA_CATEGORY, BizPackage.Literals.STANDARD_OBJ_FIELD);
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		categoryWareActions.add(pasteAction.getId());
		
		for(IAction action: actions) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(action));
		}
	}
	
	@Override
	protected void createMenus(IMenuManager menuManager) {

		//创建标准字段编辑器的右键菜单
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_SLIBING_CATEGORY);
		menuManager.add(action);

		menuManager.add(new Separator());
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_REMOVE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);

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

		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_SHOW_CATEGORY);
		menuManager.add(action);
		
		action = new MatadataRenameAction(getEditingDomain(),getTreeViewerFirstColumnName(),getFormPage().getEditor(),getColumnViewer(),getARESResource());
		
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		menuManager.add(action);
	}
	
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		super.createToolbarItems(manager);

		//创建标准字段编辑器的操作列表
		ActionGroup newGroup = new MetadataNewActionGroup(getActionRegistry());
		manager.add(newGroup);
		
		IAction
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
	}

}
