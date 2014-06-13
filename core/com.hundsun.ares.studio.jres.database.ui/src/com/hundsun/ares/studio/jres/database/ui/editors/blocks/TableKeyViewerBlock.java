package com.hundsun.ares.studio.jres.database.ui.editors.blocks;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.viewer.ForeignKeyColumnEditingSupport;
import com.hundsun.ares.studio.jres.database.ui.viewer.ForeignKeyColumnLabelProvider;
import com.hundsun.ares.studio.jres.database.ui.viewer.KeyColumnEditingSupport;
import com.hundsun.ares.studio.jres.database.ui.viewer.TableColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveDownAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveUpAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.editor.actions.CopyCellAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.EnumEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

public class TableKeyViewerBlock extends TreeViewerBlock {
	
	private ColumnViewerAddAction addAction;
	private ColumnViewerInsertAction insertAction;
	private ColumnViewerMoveUpAction moveUpAction;
	private ColumnViewerMoveDownAction moveDownAction;
	private ColumnViewerPasteAction pasteAction;
	
	
	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public TableKeyViewerBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock#getHeadColumnFeature()
	 */
	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return DatabasePackage.Literals.TABLE_KEY__NAME;
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
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_PASTE);
		menuManager.add(action);
		
		
		menuManager.add(new Separator());
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		menuManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
		menuManager.add(action);
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		// 用于一般的列
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(treeViewer);
		
		//标记列
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		//名称
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__NAME;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("名称");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 类型
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_KEY__TYPE;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(130);
			tvColumn.getColumn().setText("类型");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(attribute , getARESResource());
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new EnumEditingSupport(treeViewer, attribute));
		}
 		// 字段列表
		{
			EStructuralFeature feature = DatabasePackage.Literals.TABLE_KEY__COLUMNS;
					
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("字段列表");
			
			EObjectColumnLabelProvider provider = new TableColumnLabelProvider(feature,getARESResource()){
				@Override
				public String getText(Object element) {
					StringBuffer buffer = new StringBuffer();
					if(element instanceof TableKey){
						TableKey tableKey = (TableKey)element;
						EList<TableColumn> colunms = tableKey.getColumns();
						for(int index=0; index<colunms.size(); index++){
							if(index > 0){
								buffer.append(",");
							}
							buffer.append(colunms.get(index).getName());
						}
					}
					return buffer.toString();
				}
			};
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(getKeyColEditingSupper(treeViewer, feature));
			tvColumn.getColumn().setMoveable(true);
		}
		// 外键
		{
			EStructuralFeature feature = DatabasePackage.Literals.TABLE_KEY__FOREIGN_KEY;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("外键");
			
			ForeignKeyColumnLabelProvider provider = new ForeignKeyColumnLabelProvider(getARESResource(),feature);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);
			
			ForeignKeyColumnEditingSupport editingSupport = new ForeignKeyColumnEditingSupport(treeViewer,feature,resource);
			editingSupport.setDecorator(new IEditingSupportDecorator() {
				
				@Override
				public CellEditor decorateGetCellEditor(CellEditor cellEditor,
						Object element) {
					return cellEditor;
				};
				
				@Override
				public boolean decorateCanEdit(boolean canEdit, Object element) {
					TableKey key = (TableKey)element;
					return key.getType().equals(key_type.FOREIGN);
				}
			});
			tvColumn.setEditingSupport(editingSupport);
			tvColumn.getColumn().setMoveable(true);
		}
		
		// 扩展信息
		ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
				treeViewer, getARESResource(), DatabasePackage.Literals.TABLE_KEY, exProblemView);
		
		// 2012-05-15 sundl 在非编辑器中使用的时候，problemPool可能是null
		if (getProblemPool() != null) {
			getProblemPool().addView(problemView);
			getProblemPool().addView(exProblemView);
//			getEditableControl().addEditableUnit(new JresDefaultEditableUnit(viewer.getControl()));
		}
	}
	
	/**
	 * 获取约束的编辑支持
	 * 
	 * @param treeViewer
	 * @param feature
	 * @return
	 */
	protected EMFEditingSupport getKeyColEditingSupper(TreeViewer treeViewer ,EStructuralFeature feature){
		return new KeyColumnEditingSupport(treeViewer,feature,resource);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		addAction = new ColumnViewerAddAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS,
				DatabasePackage.Literals.TABLE_KEY);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());
		
		insertAction = new ColumnViewerInsertAction(
				getColumnViewer(), 
				getEditingDomain(),
				null,
				DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS,
				DatabasePackage.Literals.TABLE_KEY);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());
		
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
		
		IAction copyAction = new ColumnViewerCopyAction(getColumnViewer());
		getActionRegistry().registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		
		pasteAction =  new ColumnViewerPasteAction(getColumnViewer(), getEditingDomain(), null, null);
		getActionRegistry().registerAction(pasteAction);
		getClipboardActions().add(pasteAction.getId());
		
		createCellAction();
		
		
		//只读控制
		// 2012-05-15 sundl 编辑器外使用的时候，可能是null
		if (getEditableControl() != null) {
			getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveUpAction));
			getEditableControl().addEditableUnit(new ActionEditableUnit(moveDownAction));
		}
	}
	
	/**
	 * 复制文本Acation
	 */
	private void createCellAction(){
		IAction copyCellAction = new CopyCellAction(getColumnViewer());
		getActionRegistry().registerAction(copyCellAction);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		moveDownAction.setOwner((EObject) input);
		moveDownAction.setReference(DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS);
		moveUpAction.setOwner((EObject) input);
		moveUpAction.setReference(DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS);
		pasteAction.setOwner((EObject) input);
		pasteAction.setReference(DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS);
		super.setInput(input);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons(com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager buttonManager) {
		// 创建按钮列表
		IAction action = getActionRegistry().getAction(IActionIDConstant.CV_ADD);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_INSERT);
		if(null != action)
			buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_UP);
		buttonManager.add(action);
		
		action = getActionRegistry().getAction(IActionIDConstant.CV_MOVE_DOWN);
		buttonManager.add(action);
	}

	@Override
	protected String getID() {
		return getClass().getName();
	}

}
