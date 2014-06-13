/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BaiscDataCopyAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BaiscDataDeleteAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.actions.BasicDataPasteAction;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.action.AddLinkAction;
import com.hundsun.ares.studio.jres.basicdata.ui.extend.BasicdataExtensibleModelUtil;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProvider;
import com.hundsun.ares.studio.jres.basicdata.ui.proposal.BasicDataContentProposalProviderHelper;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardDataType;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TableViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;


public class LinkDetailViewerBlock extends TableViewerBlock {
	
	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;
	
	
	private AddLinkAction itemAddAction;
	
	private ColumnViewerInsertAction insertAction;
	private BaiscDataDeleteAction itemDeleteAction;
	private ColumnViewerMoveUpAction itemMoveUpAction;
	private ColumnViewerMoveDownAction itemMoveDownAction;
//	private ColumnViewerPasteAction pasteAction;
	

	private EPackage ePackage;
	private EObject resoruceInfo;
	
	private IKeyTableLocator locator;
	private EStructuralFeature infoReference;
	IMetadataService manager;
	
	private String[] keys; //主键
	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public LinkDetailViewerBlock(
			EditingDomain editingDomain,
			IARESResource resource, 
			EObject resoruceInfo,
			IProblemPool problemPool,
			EPackage ePackage,
			IKeyTableLocator locator) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
		this.ePackage = ePackage;
		this.resoruceInfo = resoruceInfo;
		this.locator = locator;
		manager = DataServiceManager.getInstance().getService(getARESResource().getARESProject(), IMetadataService.class);
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
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceContentProvider(BasicDataEpackageUtil.getSlaveItemReference(ePackage));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		IAction action = itemAddAction = new AddLinkAction(editingDomain, getARESResource(),resoruceInfo, null);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				BasicDataEpackageUtil.getSlaveItemReference(ePackage),
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem));
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		action = itemDeleteAction = new BaiscDataDeleteAction(getColumnViewer(), 
				getEditingDomain(),
				null,
				BasicDataEpackageUtil.getSlaveItemReference(ePackage));
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = itemMoveUpAction = new ColumnViewerMoveUpAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				BasicDataEpackageUtil.getSlaveItemReference(ePackage));
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		action = itemMoveDownAction = new ColumnViewerMoveDownAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				BasicDataEpackageUtil.getSlaveItemReference(ePackage));
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		IAction copyAction = new BaiscDataCopyAction(getColumnViewer(),getEditingDomain());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
		
		IAction copyColumnAction = new CopyColumnAction(getColumnViewer());
		getActionRegistry().registerAction(copyColumnAction);
		
		IAction pasteAction =  new BasicDataPasteAction(getColumnViewer(), 
				getEditingDomain(), 
				BasicDataEpackageUtil.getSlaveItemReference(ePackage),
				(EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem));
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemAddAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemDeleteAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveUpAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveDownAction));
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
		itemDeleteAction.setOwner((EObject) input);
//		pasteAction.setOwner((EObject) input);
//		pasteAction.setReference(MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
		
		//初始化引用
		EReference reference =  BasicDataEpackageUtil.getSlaveItemReference(ePackage);
		List<?>  tlist =  (List<?>)((EObject)input).eGet(reference);
		for(Object item:tlist){
			EObject eItem = (EObject)item;
			try {
				EObject refer =  locator.getLinkObject(eItem);
				eItem.eSet(infoReference, refer);
			} catch (Exception e) {
			}
		}
		
		super.setInput(input);
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
	protected void createColumns(TableViewer columnViewer) {
		final TableViewer treeViewer = (TableViewer) columnViewer;
		
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		EClass slaveEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
		infoReference = slaveEclass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Content_Reference);
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(slaveEclass);//slaveEclass.getEAllAttributes().toArray(new EAttribute[0]);
		
		
		//添加信息表信息展示
		EClass infoEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
		
		EAttribute[] infoAttrArray = BasicDataEpackageUtil.filterAttr(infoEclass);//infoEclass.getEAllAttributes().toArray(new EAttribute[0]);
		keys = BasicDataEpackageUtil.getMasterKeyAnnotation(infoEclass);  //主键
		
		//得出没有主键的所有属性
		List<String> keyList = new ArrayList<String>();
		for(String item:keys){
			keyList.add(item);
		}
		
		
		for(int i = 0;i < attrArray.length; i++){
			// 定义主属性
			EAttribute attribute = attrArray[i];
			
			// 创建表格列
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText(BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attribute));
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new BasicDataColumnLabelProvider(attribute,resource);
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			EMFEditingSupport es = null;
			if(keyList.contains(attribute.getName())){
				//主键属性
				es = new BasicDataTextEditingSupportEX(treeViewer, new IEStructuralFeatureProvider.Impl(attribute),locator,infoReference);
			}else{
				//非主键属性
				BasicDataContentProposalProviderHelper helper = new BasicDataContentProposalProviderHelper();
				BasicDataContentProposalProvider proposalProvider = new BasicDataContentProposalProvider(helper, attribute, resource);
				es = getEditingSupport(treeViewer, new IEStructuralFeatureProvider.Impl(attribute), proposalProvider, attribute);
					//new BasicDataTextEditingSupport(treeViewer, new IEStructuralFeatureProvider.Impl(attribute),proposalProvider);
			}
			es.setDecorator(new BaiscDataEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
		}
		//扩展属性
		ExtensibleModelUtils.createExtensibleModelTableViewerColumns(columnViewer, resource, slaveEclass, problemView);
		
		//过滤掉主键属性
		List<EAttribute> infoAttrList = new ArrayList<EAttribute>();
		for(EAttribute item:infoAttrArray){
			if(!keyList.contains(item.getName())){
				infoAttrList.add(item);
			}
		}
		
		//添加表格列
		for(int i = 0;i < infoAttrList.size(); i++){
			// 定义主属性
			EAttribute attribute = infoAttrList.get(i);
			
			// 创建表格列
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText(BasicDataEpackageUtil.getAttrColumnName(getARESResource(),attribute));
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			BaiscDataReferInfoColumnLabelProvider provider = new BaiscDataReferInfoColumnLabelProvider(infoReference,attribute);
			column.setLabelProvider(provider);
		}
		//创建信息表的扩展列
		BasicdataExtensibleModelUtil.createRefExtensibleModelTableViewerColumns(columnViewer, resource, infoEclass, problemView, infoReference);
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(columnViewer.getControl()));
		getProblemPool().addView(problemView);

	}
	
	private EMFEditingSupport getEditingSupport(ColumnViewer treeViewer ,IEStructuralFeatureProvider featureProvider,BasicDataContentProposalProvider proposalProvider ,EAttribute attrArray){
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

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		//创建详细表的操作列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
	}


}
