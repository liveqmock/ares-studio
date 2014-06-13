/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.metadata.ui.utils.DictoniaryUtils;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.DictionaryColumnViewerProblemView;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveBottomAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveTopAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyColumnAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.editor.blocks.ActionRegistry;
import com.hundsun.ares.studio.ui.editor.blocks.TableViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author gongyf
 *
 */
public class DictionaryDetailViewerBlock extends TableViewerBlock {

	/**字典条目是否属于字典项的引用字典条目*/
	private boolean detailColumnIsRef = false;
	private TreeViewer parentViewer;
	
	private ColumnViewerAddAction itemAddAction;
	private ColumnViewerInsertAction insertAction;
	private ColumnViewerDeleteAction itemDeleteAction;
	private ColumnViewerMoveUpAction itemMoveUpAction;
	private ColumnViewerMoveDownAction itemMoveDownAction;
	private ColumnViewerMoveTopAction itemMoveTopAction;
	private ColumnViewerMoveBottomAction itemMoveBottomAction;
	private ColumnViewerPasteAction pasteAction;
	

	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 * @param parentViewer 
	 */
	public DictionaryDetailViewerBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool, TreeViewer parentViewer) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
		this.parentViewer = parentViewer;
	}

	/**
	 * @param detailColumnIsRef the detailColumnIsRef to set
	 */
	public void setDetailColumnIsRef(boolean detailColumnIsRef) {
		this.detailColumnIsRef = detailColumnIsRef;
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
		return new ReferenceContentProvider(MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
	}

	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return MetadataPackage.Literals.DICTIONARY_ITEM__VALUE;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		IAction action = itemAddAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(), 
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS,
				MetadataPackage.Literals.DICTIONARY_ITEM);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS,
				MetadataPackage.Literals.DICTIONARY_ITEM);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
		action = itemDeleteAction = new ColumnViewerDeleteAction(getColumnViewer(), getEditingDomain());
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = itemMoveTopAction = new ColumnViewerMoveTopAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		action = itemMoveBottomAction = new ColumnViewerMoveBottomAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		action = itemMoveUpAction = new ColumnViewerMoveUpAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());
		getStackActions().add(action.getId());
		
		action = itemMoveDownAction = new ColumnViewerMoveDownAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
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
		
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemAddAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemDeleteAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveUpAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveDownAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveTopAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(itemMoveBottomAction));
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
		itemMoveTopAction.setOwner((EObject) input);
		itemMoveBottomAction.setOwner((EObject) input);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS);
		super.setInput(input);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
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
		
		DictionaryColumnViewerProblemView problemView = new DictionaryColumnViewerProblemView(treeViewer,parentViewer);
		DictionaryItemEditingSupportDecorator decorator = new DictionaryItemEditingSupportDecorator();
		
		/**字典项*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典子项");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__VALUE ,getARESResource()){
				@Override
				public Color getBackground(Object element) {
					if(detailColumnIsRef){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			TextEditingSupport tes = new TextEditingSupport(treeViewer, MetadataPackage.Literals.DICTIONARY_ITEM__VALUE); 
			tes.setDecorator(decorator);
			column.setEditingSupport(tes);
			column.getColumn().setMoveable(true);
		}
		/**字典项说明*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("子项名称");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME , getARESResource()){
				@Override
				public Color getBackground(Object element) {
					if(detailColumnIsRef){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			TextEditingSupport tes = new TextEditingSupport(treeViewer, MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME); 
			tes.setDecorator(decorator);
			column.setEditingSupport(tes);
			column.getColumn().setMoveable(true);
		}
		/**常量ID*/
		if(!DictoniaryUtils.isHideDictionaryConstant(resource.getARESProject())){
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典常量");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME ,getARESResource()){
				@Override
				public Color getBackground(Object element) {
					if(detailColumnIsRef){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			TextEditingSupport tes = new TextEditingSupport(treeViewer, MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME); 
			tes.setDecorator(decorator);
			column.setEditingSupport(tes);
			column.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new MetadataColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__DESCRIPTION , getARESResource()){
				@Override
				public Color getBackground(Object element) {
					if(detailColumnIsRef){
						return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			TextEditingSupport tes = new TextEditingSupport(treeViewer, MetadataPackage.Literals.DICTIONARY_ITEM__DESCRIPTION); 
			tes.setDecorator(decorator);
			column.setEditingSupport(tes);
			column.getColumn().setMoveable(true);
		}
		
		// 增加扩展列支持
		ExtensibleModelUtils.createExtensibleModelTableViewerColumns(getColumnViewer(), getARESResource(), 
		MetadataPackage.Literals.DICTIONARY_ITEM, problemView);
		
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(columnViewer.getControl()));
		getProblemPool().addView(problemView);

	}

	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		//创建详细表的操作列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		manager.add(action);
		
		action = getActionRegistry().getAction(IMetadataActionIDConstant.CV_INSERT_ITEM);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_TOP);
		manager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_BOTTOM);
		manager.add(action);
		
	}
	
	@Override
	protected void updateActions(final List<String> actionIds) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				ActionRegistry registry = getActionRegistry();
				Iterator<String> iter = actionIds.iterator();
				while (iter.hasNext()) {
					IAction action = registry.getAction(iter.next());
					if (action instanceof IUpdateAction)
						if(detailColumnIsRef){
							action.setEnabled(false);
						}else{
							((IUpdateAction) action).update();
						}
				}
			}
		});
	}
	//TODO#界面逻辑#  #简单#王小恒#已编码 #2011-8-11#24 #10 #数据字典设置【字典条目引用】后，字典项列表中的属性应为只读
	class DictionaryItemEditingSupportDecorator implements
			IEditingSupportDecorator {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator
		 * #decorateGetCellEditor(org.eclipse.jface.viewers.CellEditor,
		 * java.lang.Object)
		 */
		@Override
		public CellEditor decorateGetCellEditor(CellEditor cellEditor,
				Object element) {
			return cellEditor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator
		 * #decorateCanEdit(boolean, java.lang.Object)
		 */
		@Override
		public boolean decorateCanEdit(boolean canEdit, Object element) {
			if (detailColumnIsRef) {
				return false;
			}
			return canEdit;
		}

	}
	
}
