/**
 * 源程序名称：StandardFieldViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddChildCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.AddSiblingCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.CopyAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ExportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ImportMetadataAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.InsertItemAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveDownAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MoveUpAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.PasteAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.RemoveAction;
import com.hundsun.ares.studio.jres.metadata.ui.actions.ShowCategoryAction;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalHelper;
import com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport.MetadataContentProposalProvider;
import com.hundsun.ares.studio.jres.metadata.ui.refactor.MatadataRenameAction;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnViewerProblemView;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.StdFieldDictDescColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ActionGroup;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.ValidateAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public class StandardFieldListViewerBlock extends MetadataListViewerBlock {

	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public StandardFieldListViewerBlock(FormPage page, EditingDomain editingDomain,
			IWorkbenchPartSite site, IARESResource resource,
			IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}

	@Override
	protected boolean getDefaultShowCategory() {
		return false;
	}
	
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		// TODO#界面逻辑#秦元#简单#王彬#已编码 #2011-07-26 #88 #40 #建一个表格用于编辑标准字段，支持树状层级
		//表格列：英文代码、引用、中文名称、字段类型、长度、精度、字典条目、字段说明、显示默认值、显示控件、控件属性、控件事件
		/*TODO#界面逻辑#龚叶峰#简单#王小恒#已编码 #2011-8-9 #5 #3 #LabelProvider修正
		 * 
		 *
		 * 树视图有分组显示模式，这个时候必须保证能够在一列显示多种对象的不同属性，所以需要使用MetadataEStructuralFeatureProvider
		 * 例子参考默认值编辑器，对于名字和描述2列有特殊处理。
		 */
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
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(treeViewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);
		}
		/**标准字段引用*/
		if (MetadataUtil.isUseRefFeature(getARESResource())) {
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.METADATA_ITEM__REF_ID;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("标准字段引用");
			comlumn.getColumn().setWidth(100);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			
			//TODO#界面逻辑#龚叶峰#简单#王小恒#已编码 #2011-8-9 #44 #20#引用提示,引用标准字段后相关字段不可编辑
			// 1. 获取aresProject
			final IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();
			
			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(project);
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.StdField, project);
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**业务数据类型*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("字段类型");
			comlumn.getColumn().setWidth(130);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			// 1. 获取aresProject
			final IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();
			
			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(project);
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.BizType, project);
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**字典条目*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("字典条目");
			comlumn.getColumn().setWidth(100);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			// 1. 获取aresProject
			final IARESProject project = getARESResource() == null ? null : getARESResource().getARESProject();
			
			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(project);
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.Dict, project);
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**字典条目说明*/
		{
//			// 定义主属性
//			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			comlumn.getColumn().setText("字典条目说明");
			comlumn.getColumn().setWidth(200);
			// 设置标签提供器
			StdFieldDictDescColumnLabelProvider provider = new StdFieldDictDescColumnLabelProvider(getARESResource()){
				@Override
				public String getToolTipText(Object element) {
					String text = getText(element);
					return StringUtils.isBlank(text) ? super.getToolTipText(element) : text;
				}
			};//MetadataColumnLabelProvider(attribute, getARESResource());
			comlumn.setLabelProvider(provider);
			// 设置编辑支持
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
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource()){
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
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		
		// 增加扩展列支持
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(getColumnViewer(), getARESResource(), 
		MetadataPackage.Literals.STANDARD_FIELD, problemView);
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(treeViewer.getControl()));
		getProblemPool().addView(problemView);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.link.ICellLinkProvider#getLinkedObject(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		BaseEObjectColumnLabelProvider labelProvider = (BaseEObjectColumnLabelProvider) getColumnViewer().getLabelProvider(cell.getColumnIndex());
		
		EStructuralFeature feature = labelProvider.getEStructuralFeature(cell.getElement());
		
		DeStandardField d = MetadataUtil.decrypt((StandardField)cell.getElement(), getARESResource());
		if (MetadataPackage.Literals.METADATA_ITEM__REF_ID.equals(feature)) {
			return (Pair)d.getResolvedItem();
		} else if (MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE.equals(feature)) {
			return (Pair)d.getDataType().getResolvedItem();
		} else if (MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE.equals(feature)) {
			return (Pair)d.getDictionaryType().getResolvedItem();
		}
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
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_VALIDATE);
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
		
		IAction addItemAction = new AddItemAction(getColumnViewer(), getEditingDomain(), MetadataPackage.Literals.STANDARD_FIELD);
		getActionRegistry().registerAction(addItemAction);
		getSelectionActions().add(addItemAction.getId());
		actions.add(addItemAction);
		
		IAction actionInsert = new InsertItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				MetadataPackage.Literals.STANDARD_FIELD);
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
				MetadataPackage.Literals.METADATA_CATEGORY, MetadataPackage.Literals.STANDARD_FIELD);
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		categoryWareActions.add(pasteAction.getId());
		
		IAction actionValidate = new ValidateAction(getFormPage());
		getActionRegistry().registerAction(actionValidate);
		actions.add(actionValidate);

		ImportMetadataAction importAction = new ImportMetadataAction(getARESResource(), getColumnViewer(),getEditingDomain());
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
		String dialogTitle	= "导出标准字段";
		String dialogMessage = "将项目中的标准字段导出(Excel文件).";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/stdFieldFile.png").createImage();
		ExportMetadataAction exportAction = new ExportMetadataAction(getARESResource(), getSite(),dialogTitle,dialogImage,dialogMessage);
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
