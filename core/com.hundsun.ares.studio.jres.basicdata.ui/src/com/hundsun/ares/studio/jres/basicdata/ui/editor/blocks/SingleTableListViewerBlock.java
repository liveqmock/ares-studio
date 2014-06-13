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

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BaiscDataCopyAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BasicDataPasteAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.ExportBasicdataAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.ImportBasicdataAction;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProvider;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProviderHelper;
import com.hundsun.ares.studio.jres.basicdata.ui.viewer.BasicDataProblemView;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddChildCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddSiblingCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.InsertItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveDownAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveUpAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.RemoveAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ShowCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataEStructuralFeatureProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.ValidateAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 *
 */
public class SingleTableListViewerBlock extends MetadataListViewerBlock {

	
	EPackage ePackage;
	String className;
	IMetadataService manager;

	public SingleTableListViewerBlock(FormPage page, 
			EditingDomain editingDomain,
			IWorkbenchPartSite site,
			IARESResource resource,
			IProblemPool problemPool,
			EPackage ePackage,
			String className) {
		super(page, editingDomain, site, resource, problemPool);
		this.ePackage = ePackage;
		this.className = className;
		manager = DataServiceManager.getInstance().getService(getARESResource().getARESProject(), IMetadataService.class);
	}

	@Override
	protected boolean getDefaultShowCategory() {
		return true;
	}
	
	/**
	 * 获得字段列
	 * @param resoruce
	 * @param attribute
	 * @return
	 */
	private String getColType(IARESResource resoruce,EAttribute attribute){
		String type = StringUtils.EMPTY;
		if (attribute == null) {
			return type;
		}
		if(attribute instanceof BasicDataEAttribute ){
			BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
			if(basicDataEAttribute.getData() !=null){
				if(basicDataEAttribute.getData() instanceof BasicDataField){
					BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
					if(basicDataField.getColumnType() == 0){//标准字段时
						IStandardField stdField = manager.getStandardField(attribute.getName());
						if (stdField != null) {
							IBusinessDataType bizType = stdField.getDataType();
							if (bizType != null) {
								IStandardDataType stdType = bizType.getStdType();
								if (stdType != null) {
									type = stdType.getName();
								}
							}

						}
					}else{//非标准字段时
						IBusinessDataType bizType = manager.getBusinessDataType(basicDataField.getDataType());
						if (bizType != null) {
							IStandardDataType stdType = bizType.getStdType();
							if (stdType != null) {
								type = stdType.getName();
							}
						}

					
					}
				}
			}
		}
		return type;
	}
	
	/**
	 * 判断是否用大文本框
	 * 
	 * @param type
	 * @return
	 */
	private boolean isLongTextCellEditor(String type){
		if (StringUtils.indexOfIgnoreCase(type, "str") > 0) {
			return true;
		}else if (StringUtils.indexOfIgnoreCase(type, "String") > 0){
			return true;
		}else if (StringUtils.indexOfIgnoreCase(type, "clob") > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否数据字典编辑
	 * @param proposalProvider
	 * @return
	 */
	private boolean isDictCellEditor(BasicDataContentProposalProvider proposalProvider){
		 if(proposalProvider.getAttribute() != null && proposalProvider.getResource() != null){
				if(BasicDataEpackageUtil.isStandardField(proposalProvider.getAttribute())){
					StandardField stdField = JRESResProviderUtil.getMetadataModel(proposalProvider.getResource().getARESProject(), proposalProvider.getAttribute().getName(),IMetadataRefType.StdField , StandardField.class);
					if(null != stdField && StringUtils.isNotBlank(stdField.getDictionaryType())){
						DeDictionaryType dicType = MetadataUtil.decrypt(stdField, proposalProvider.getResource()).getDictionaryType();
						proposalProvider.setInput(dicType.getItems().toArray());
						return true;
					}
				}
		 }
		 return false;
	
	}
	
	private EMFEditingSupport getEditingSupport(TreeViewer treeViewer ,IEStructuralFeatureProvider featureProvider,BasicDataContentProposalProvider proposalProvider ,EAttribute attrArray){
		EMFEditingSupport es = null;
		if(isDictCellEditor(proposalProvider)){
			es = new BasicDataDictTextEditingSupport(treeViewer, featureProvider,proposalProvider);
		}else if (isLongTextCellEditor(getColType(getARESResource(), attrArray))) {
			es = new LongBasicDataTextEditingSupport(treeViewer, featureProvider,proposalProvider);
		}else {
			es = new BasicDataTextEditingSupport(treeViewer, featureProvider,proposalProvider);
		}
		return es;
	}
	
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		
		EClass masterEclass = (EClass)ePackage.getEClassifier(className);
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);
		int attrSize = attrArray.length;
		
		if(0 == attrSize){
			MessageDialog dlg = new MessageDialog(new Shell(), "错误", null, "配置中没有可编辑的项，请确认相关配置的正确性！", MessageDialog.ERROR, new String[]{"确定"}, 0);
			dlg.open();
			return;
		}
		
		EObjectColumnViewerProblemView problemView = new BasicDataProblemView(treeViewer,attrArray);
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			String columnName = BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[0]);
			column.getColumn().setText(columnName);
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			//多feature提供器
			IEStructuralFeatureProvider featureProvider = new MetadataEStructuralFeatureProvider(MetadataPackage.Literals.NAMED_ELEMENT__NAME,attrArray[0]);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicdataHeaderColumnLabelProvider(featureProvider,getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attrArray[0], getARESResource());
			EMFEditingSupport es = getEditingSupport(treeViewer, featureProvider, proposalProvider, attrArray[0]);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
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
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(featureProvider,getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			EAttribute attribute = attrSize > 1 ? attrArray[1] : null;
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			EMFEditingSupport es = getEditingSupport(treeViewer, featureProvider, proposalProvider, attribute);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
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
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(featureProvider,getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持 
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			EAttribute attribute = attrSize > 2 ? attrArray[2] : null;
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			EMFEditingSupport es = getEditingSupport(treeViewer, featureProvider, proposalProvider, attribute);
			es.setDecorator(new BaiscDataEditingSupportDecorator(featureProvider,getARESResource()));
			column.setEditingSupport(es);
		}
		
		for(int i = 3;i < attrSize; i++){
			// 定义主属性
			EAttribute attribute = attrArray[i];
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText(BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attrArray[i]));
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(attribute,getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
			BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, getARESResource());
			EMFEditingSupport es = getEditingSupport(treeViewer, new IEStructuralFeatureProvider.Impl(attribute), proposalProvider, attrArray[i]);
			es.setDecorator(new BaiscDataEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
		}
		
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(viewer, getARESResource(), masterEclass, problemView);
		
		getProblemPool().addView(problemView);
	}

	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		
		return null;
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
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
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
	
	@Override
	protected void createActions() {
		//创建标准字段的所有action
		super.createActions();
		
		List<IAction> actions = new ArrayList<IAction>();
		
		IAction addItemAction = new AddItemAction(getColumnViewer(), 
				getEditingDomain(), 
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem));
		getActionRegistry().registerAction(addItemAction);
		getSelectionActions().add(addItemAction.getId());
		actions.add(addItemAction);
		
		IAction actionInsert = new InsertItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem));
		getActionRegistry().registerAction(actionInsert);
		getSelectionActions().add(actionInsert.getId());
		
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
		
		IAction pasteAction = new BasicDataPasteAction(getColumnViewer(),getEditingDomain(),
				MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem));
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		
		IAction actionValidate = new ValidateAction(getFormPage());
		getActionRegistry().registerAction(actionValidate);
		actions.add(actionValidate);

		ImportBasicdataAction importAction = new ImportBasicdataAction(getARESResource(), getColumnViewer(),getEditingDomain(),ePackage,className);
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
		String dialogTitle = "导出基础数据";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(BasicDataUI.PLUGIN_ID, "icons/full/obj16/BaiscData.png").createImage();
		String dialogMessage = "将项目中的基础数据导出(Excel文件).";
		
		ExportBasicdataAction exportAction = new ExportBasicdataAction(getARESResource(), getSite(),ePackage,className,dialogTitle,dialogImage,dialogMessage);
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

}
