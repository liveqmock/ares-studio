/**
 * 源程序名称：ErrorNoListViewerBlock.java
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

import org.apache.commons.lang.ObjectUtils;
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
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataDescColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeErrorNoItem;
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
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public class ErrorNoListViewerBlock extends MetadataListViewerBlock{

	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public ErrorNoListViewerBlock(FormPage page, EditingDomain editingDomain,
			IWorkbenchPartSite site, IARESResource resource,
			IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}

	@Override
	protected void createColumns(TreeViewer _viewer) {
		// TODO#界面逻辑#秦元#简单#王彬#已编码 #2011-07-26#48 #20 #建一个表格用于错误号，支持树状层级
		//表格列：错误号、引用、错误信息、错误常量名、解决方式说明、错误级别
		
		/**
		 *	a)	分组（与标准字段分组一致，输入规约也一致）
			b)	错误号编号
			c)	ID
			d)	名称
			e)	错误号引用（引用自本工程、其它模块工程或引用包）
			f)	常量ID（字符串类型）
			g)	错误信息
			h)	错误级别
			i)	备注
		 */
		/*TODO#界面逻辑#龚叶峰#简单#王小恒#已编码 #2011-8-9#5#5 #LabelProvider修正
		 * 
		 *
		 * 树视图有分组显示模式，这个时候必须保证能够在一列显示多种对象的不同属性，所以需要使用MetadataEStructuralFeatureProvider
		 * 例子参考默认值编辑器，对于名字和描述2列有特殊处理。
		 */
		final TreeViewer viewer = _viewer;
		EObjectColumnViewerProblemView problemView = new MetadataColumnViewerProblemView(viewer);
		/**错误号*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.ERROR_NO_ITEM__NO;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("错误号");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataHeaderColumnLabelProvider(attribute , getARESResource()){
				@Override
				public String getText(Object element) {
					EAttribute attribute = MetadataPackage.Literals.ERROR_NO_ITEM__NO;
					if (element instanceof MetadataCategory) {
						attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
					}
					EObject owner = getOwner(element);
					if (attribute != null && owner != null && owner.eClass().getEAllAttributes().contains(attribute)) {
						Object value = owner.eGet(attribute);
						if (value == null) {
							value = "";
						}
						return String.valueOf(value );
					}
					return "";
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute){
				@Override
				protected Object getValue(Object element) {
					EAttribute attribute = MetadataPackage.Literals.ERROR_NO_ITEM__NO;
					if (element instanceof MetadataCategory) {
						attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
					}
					featureProvider = new IEStructuralFeatureProvider.Impl(attribute);
					Object obj = getOwner(element).eGet(featureProvider.getFeature(element));
					return ObjectUtils.defaultIfNull(obj, "");
				}
			};
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource())
			{
				@Override
				public boolean decorateCanEdit(boolean canEdit, Object element) {
					if (super.decorateCanEdit(canEdit, element)) {
						return true;
					}
					if (element instanceof MetadataCategory && !StringUtils.equals("未分组", ((MetadataCategory) element).getName())) {
						return true;
					}
					return false;
				}
			});
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);
		}
		/**错误提示信息*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.ERROR_NO_ITEM__MESSAGE;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("错误提示信息");
			comlumn.getColumn().setWidth(200);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**常量定义*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
			column.getColumn().setText("常量定义");
			column.getColumn().setWidth(250);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource()){
				@Override
				public String getText(Object element) {
					EStructuralFeature attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
					if (element instanceof MetadataCategory) {
						attribute = MetadataPackage.Literals.ERROR_NO_ITEM__NO;
					}
					EObject owner = getOwner(element);
					if (attribute != null && owner != null && owner.eClass().getEAllAttributes().contains(attribute)) {
						Object value = owner.eGet(attribute);
						if (value == null) {
							value = "";
						}
						return String.valueOf(value );
					}
					return "";
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute){
				@Override
				protected boolean doCanEdit(Object element) {
					if (element instanceof MetadataCategory) {
						return false;
					}
					return super.doCanEdit(element);
				}
			};
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			column.setEditingSupport(es);
			column.getColumn().setMoveable(true);
		}
		/**错误号引用*/
		if (MetadataUtil.isUseRefFeature(getARESResource())) {
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.METADATA_ITEM__REF_ID;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("错误号引用");
			comlumn.getColumn().setWidth(100);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			
			//TODO#界面逻辑#龚叶峰#简单#王小恒#已编码 #2011-8-9#23 #10 #引用提示,引用错误号后相关字段不可编辑
			// 1. 获取aresProject
			IARESResource res = getARESResource();
			final IARESProject project = res == null ? null : res.getARESProject();
			
			// 2. proposal provider
			MetadataContentProposalHelper helper = new MetadataContentProposalHelper(project);
			MetadataContentProposalProvider proposalProvider = new MetadataContentProposalProvider(helper, IMetadataRefType.ErrNo, project);
			
			// 3. 创建EditingSupport, 
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					viewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**错误级别*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.ERROR_NO_ITEM__LEVEL;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("错误级别");
			comlumn.getColumn().setWidth(100);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new TextEditingSupport(viewer, attribute);
			//es.setDecorator(new MetadataItemEditingSupportDecorator(attribute));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION;
			
			// 创建表格列
			TreeViewerColumn comlumn = new TreeViewerColumn(viewer, SWT.LEFT);
			comlumn.getColumn().setText("说明");
			comlumn.getColumn().setWidth(200);
			// 设置标签提供器
			EObjectColumnLabelProvider provider = new MetadataDescColumnLabelProvider(attribute, getARESResource());
			provider.setDiagnosticProvider(problemView);
			comlumn.setLabelProvider(provider);
			
			// 设置编辑支持
			TextEditingSupport es = new LongTextEditingSupport(viewer, attribute);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,getARESResource()));
			comlumn.setEditingSupport(es);
			comlumn.getColumn().setMoveable(true);
		}
		
		// 增加扩展列支持
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(getColumnViewer(), getARESResource(), 
				MetadataPackage.Literals.ERROR_NO_ITEM, problemView);
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		getProblemPool().addView(problemView);
	}

	@Override
	protected void createActions() {
		//注册所有的action
		super.createActions();
		List<IAction> actions = new ArrayList<IAction>();
		
		IAction action = new AddItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				MetadataPackage.Literals.ERROR_NO_ITEM);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		IAction actionInsert = new InsertItemAction(
				getColumnViewer(), 
				getEditingDomain(), 
				MetadataPackage.Literals.ERROR_NO_ITEM);
		getActionRegistry().registerAction(actionInsert);
		getSelectionActions().add(actionInsert.getId());
		
		action = new RemoveAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		action = new AddChildCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		categoryWareActions.add(action.getId());
		actions.add(action);
		
		action = new AddSiblingCategoryAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		categoryWareActions.add(action.getId());
		actions.add(action);
		
		action = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		actions.add(action);
		
		action = new MoveUpAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		actions.add(action);
		
		action = new MoveDownAction(getColumnViewer(), getEditingDomain());
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
		
		IAction pasteAction = new PasteAction(getColumnViewer(), getEditingDomain(),
				MetadataPackage.Literals.METADATA_CATEGORY, MetadataPackage.Literals.ERROR_NO_ITEM);
		getActionRegistry().registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());
		getClipboardActions().add(pasteAction.getId());
		categoryWareActions.add(pasteAction.getId());
		
		action = new ValidateAction(getFormPage());
		getActionRegistry().registerAction(action);
		actions.add(action);
		
		ImportMetadataAction importAction = new ImportMetadataAction(getARESResource(), getColumnViewer(),getEditingDomain());
		getActionRegistry().registerAction(importAction);
		getSelectionActions().add(importAction.getId());
	
		String dialogTitle	= "导出标准错误号";
		String dialogMessage = "将项目中的标准错误号导出(Excel文件).";
		Image dialogImage = AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/errornoFile.png").createImage();
		ExportMetadataAction exportAction = new ExportMetadataAction(getARESResource(), getSite(),dialogTitle,dialogImage,dialogMessage);
		getActionRegistry().registerAction(exportAction);
		getSelectionActions().add(exportAction.getId());
		
		for (IAction item : actions ) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(item));
		}
		
	}
	
	@Override
	protected boolean getDefaultShowCategory() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		//创建错误号编辑器的操作列表
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
	protected void createMenus(IMenuManager menuManager) {
		//创建错误号编辑器的右键菜单
		IAction action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_ITEM);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY);
		menuManager.add(action);
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_ADD_SLIBING_CATEGORY);
		menuManager.add(action);
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		menuManager.add(action);
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_REMOVE);
		menuManager.add(action);
		menuManager.add(new Separator());
		
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
		
		menuManager.add(new Separator());

		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_SHOW_CATEGORY);
		menuManager.add(action);
		
		action = new MatadataRenameAction(getEditingDomain(),"常量定义",getFormPage().getEditor(),getColumnViewer(),getARESResource());
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_VALIDATE);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		menuManager.add(action);
	}
	
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		BaseEObjectColumnLabelProvider labelProvider = (BaseEObjectColumnLabelProvider) getColumnViewer().getLabelProvider(cell.getColumnIndex());
		
		EStructuralFeature feature = labelProvider.getEStructuralFeature(cell.getElement());
		
		DeErrorNoItem d = MetadataUtil.decrypt((ErrorNoItem)cell.getElement(), getARESResource());
		if (MetadataPackage.Literals.METADATA_ITEM__REF_ID.equals(feature)) {
			return (Pair)d.getResolvedItem();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
}
