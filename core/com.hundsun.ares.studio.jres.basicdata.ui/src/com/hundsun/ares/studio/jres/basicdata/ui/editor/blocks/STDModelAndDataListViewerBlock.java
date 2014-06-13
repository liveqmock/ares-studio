/**
 * 源程序名称：StandardFieldViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BaiscDataCopyAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BasicDataPasteAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.ExportBasicdataAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.ImportBasicdataAction;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProvider;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProviderHelper;
import com.hundsun.ares.studio.jres.basicdata.ui.viewer.BasicDataProblemView;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddChildCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddSiblingCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveDownAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveUpAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.RemoveAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ShowCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataEStructuralFeatureProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.ValidateAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 *
 */
public class STDModelAndDataListViewerBlock extends MetadataListViewerBlock {

	
	EPackage ePackage;
	String className;
	TreeViewer viewer;
	EObjectColumnViewerProblemView problemView;
	
	AddItemAction addItemAction;
//	InsertItemAction insertAction;
//	ImportBasicdataAction importAction;
//	ExportBasicdataAction exportAction;
	
	List<TreeViewerColumn> columns = new ArrayList<TreeViewerColumn>();

	public STDModelAndDataListViewerBlock(FormPage page, 
			EditingDomain editingDomain,
			IWorkbenchPartSite site,
			IARESResource resource,
			IProblemPool problemPool,
			String className) {
		super(page, editingDomain, site, resource, problemPool);
		this.className = className;
	}
	
	/**
	 * 每次ePackage都在不停的变化，所以要一致重新设定
	 * @param ePackage
	 */
	public void setEpackage(EPackage ePackage){
		this.ePackage = ePackage;
		
		//ePackage变动后，有的ACTION需要重新设定
		if(null != addItemAction ){
			addItemAction.setItemClass((EClass)ePackage.getEClassifier(className));
//			insertAction.setItemClass((EClass)ePackage.getEClassifier(className));
			
//			importAction.setePackage(ePackage);
//			exportAction.setePackage(ePackage);
		}
	}
	

	@Override
	protected boolean getDefaultShowCategory() {
		return true;
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		this.viewer = viewer;
		buildColumns();
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		getProblemPool().addView(problemView);
	}
	
	public void recreateColumns(){
		
		//删除列控件
		for(TreeViewerColumn item:columns){
			item.getColumn().dispose();
		}
		//清理所有列
		columns.clear();
		
		//去除问题视图
		getProblemPool().removeView(problemView);
		//重新创建列
		buildColumns();
		//刷新界面
		viewer.refresh();
	}
	
	protected void buildColumns() {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		
		EClass masterEclass = (EClass)ePackage.getEClassifier(className);
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);
		int attrSize = attrArray.length;
		
		if(0 == attrSize){
//			MessageDialog dlg = new MessageDialog(new Shell(), "错误", null, "配置中没有可编辑的项，请确认相关配置的正确性！", MessageDialog.ERROR, new String[]{"确定"}, 0);
//			dlg.open();
//			return;
		}
		
		problemView = new BasicDataProblemView(treeViewer,attrArray);
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			columns.add(column);
			String columnName = "名称";
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			//多feature提供器
			IEStructuralFeatureProvider featureProvider = null;
			if(attrSize > 0){
				columnName = BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[0]);
				featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__NAME,attrArray[0]);
			}else{
				featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__NAME,null);
			}
			column.getColumn().setText(columnName);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicdataHeaderColumnLabelProvider(featureProvider,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			EAttribute attribute = attrSize > 0 ? attrArray[0] : null;
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			BasicDataTextEditingSupport es = new BasicDataTextEditingSupport(treeViewer, featureProvider,proposalProvider);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			columns.add(column);
			String columnName = "中文名";
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			//多feature提供器
			IEStructuralFeatureProvider featureProvider = null;
			if(attrSize > 1){
				columnName = BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[1]);
				featureProvider =new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,attrArray[1]);
			}else{
				featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,null);
			}
			column.getColumn().setText(columnName);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(featureProvider,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			EAttribute attribute = attrSize > 1 ? attrArray[1] : null;
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			BasicDataTextEditingSupport es = new BasicDataTextEditingSupport(treeViewer, featureProvider,proposalProvider);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			columns.add(column);
			String columnName = "备注";
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			//多feature提供器
			IEStructuralFeatureProvider featureProvider = null;
			if(attrSize > 2){
				columnName = BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[2]);
				featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION,attrArray[2]);
			}else{
				featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION,null);
			}
			column.getColumn().setText(columnName);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(featureProvider,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			EAttribute attribute = attrSize > 2 ? attrArray[2] : null;
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			BasicDataTextEditingSupport es = new BasicDataTextEditingSupport(treeViewer, featureProvider,proposalProvider);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		for(int i = 3;i < attrSize; i++){
			// 定义主属性
			EAttribute attribute = attrArray[i];
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			columns.add(column);
			column.getColumn().setText(BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[i]));
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(attribute,getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			BasicDataTextEditingSupport es = new BasicDataTextEditingSupport(treeViewer, new IEStructuralFeatureProvider.Impl(attribute),proposalProvider);
			es.setDecorator(new BaiscDataEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
		}
		

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.link.ICellLinkProvider#getLinkedObject(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {

		//创建标准字段编辑器的右键菜单
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_SLIBING_CATEGORY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_REMOVE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_DOWN);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_COPY);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyCellAction.ID);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(CopyColumnAction.ID);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_SHOW_CATEGORY);
		menuManager.add(action);
		
//		action = new MatadataRenameAction(getEditingDomain(),getTreeViewerFirstColumnName(),getFormPage().getEditor(),getColumnViewer(),getARESResource());
//		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_VALIDATE);
		menuManager.add(action);
	
	}
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		super.createToolbarItems(manager);

		//创建标准字段编辑器的操作列表
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_SLIBING_CATEGORY);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);

		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_IMPORT_METADATA);
		manager.add(action);
//		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_EXPORT_METADATA);
		manager.add(action);
	}
	

	/**
	 * 创建需要变动的Action
	 */
	private void createNeedChangedAction(){
		addItemAction = new AddItemAction(getColumnViewer(), 
				getEditingDomain(), 
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem)){
			
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#calculateEnabled()
			 */
			@Override
			protected boolean calculateEnabled() {
				EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(getItemClass());
				if(attrArray.length == 0){
					return false;
				}
				return super.calculateEnabled();
			}
		};
		
//		insertAction = new InsertItemAction(
//				getColumnViewer(), 
//				getEditingDomain(), 
//				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem));
//		
//		importAction = new ImportBasicdataAction(getARESResource(), getColumnViewer(),getEditingDomain(),ePackage,className);
//	
//		exportAction = new ExportBasicdataAction(getARESResource(), getSite(),ePackage,className);
		
	}
	
	
	@Override
	protected void createActions() {
		//创建标准字段的所有action
		super.createActions();
		
		List<IAction> actions = new ArrayList<IAction>();
		
		createNeedChangedAction();
		
		getActionRegistry().registerAction(addItemAction);
		getSelectionActions().add(addItemAction.getId());
		actions.add(addItemAction);
		
		
//		getActionRegistry().registerAction(insertAction);
//		getSelectionActions().add(insertAction.getId());
		
		IAction addChildCategoryAction = new AddChildCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(addChildCategoryAction);
		getSelectionActions().add(addChildCategoryAction.getId());
		actions.add(addChildCategoryAction);
		
		IAction addSiblingCategoryAction = new AddSiblingCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(addSiblingCategoryAction);
		getSelectionActions().add(addSiblingCategoryAction.getId());
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
		
		IAction copyAction = new BaiscDataCopyAction(getColumnViewer(),getEditingDomain());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		IAction pasteAction = new BasicDataPasteAction(getColumnViewer(),getEditingDomain(),MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,(EClass)ePackage.getEClassifier(className));
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		
		IAction actionValidate = new ValidateAction(getFormPage());
		getActionRegistry().registerAction(actionValidate);
		actions.add(actionValidate);

		ImportBasicdataAction importAction = new ImportBasicdataAction(getARESResource(), viewer, getEditingDomain(), ePackage, className);
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
//	
		String dialogTitle = "导出基础数据";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(BasicDataUI.PLUGIN_ID, "icons/full/obj16/BaiscData.png").createImage();
		String dialogMessage = "将项目中的基础数据导出(Excel文件).";
		ExportBasicdataAction exportAction = new ExportBasicdataAction(getARESResource(), getSite(), ePackage, className,dialogTitle,dialogImage,dialogMessage);
		getActionRegistry().registerAction(exportAction);
		getSelectionActions().add(exportAction.getId());
		
		for(IAction action: actions) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(action));
		}
	}
	
	
	@Override
	protected String getID() {
		return getClass().getName();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		super.setInput(input);
	}
	

}
