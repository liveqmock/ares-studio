/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.provider.LongTextEditingSupport;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerDeleteAction;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerInsertAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanyl
 * 
 */
public class GeneralDataConfigDefineBlock extends TreeViewerBlock {
	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;

	private ColumnViewerAddAction addAction;
	private ColumnViewerInsertAction insertAction;

	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public GeneralDataConfigDefineBlock(EditingDomain editingDomain,
			IARESResource resource, IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
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
	public IARESResource getResource() {
		return resource;
	}

	/**
	 * @return the problemPool
	 */
	public IProblemPool getProblemPool() {
		return problemPool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#
	 * getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new ReferenceTreeContentProvider(
				MetadataPackage.Literals.GENERAL_DATA_CONFIG_LIST__ITEMS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createMenus(org
	 * .eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void createMenus(IMenuManager menuManager) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns
	 * (org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		// 用于一般的列
		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(
				treeViewer);
		// 用于扩展列
		EObjectColumnViewerProblemView exProblemView = new ExtensibleModelColumnViewerProblemView(
				treeViewer);

		// 名称
		{
			EAttribute attribute = MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM__ID;

			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer,
					SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("名称");

			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);

			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer,
					attribute));
		}

		// 名称
		{
			EAttribute attribute = MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM__CHINESE_NAME;

			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer,
					SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("中文名");

			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);

			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer,
					attribute));
		}

		// 名称
		{
			EAttribute attribute = MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM__TYPE;

			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer,
					SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("类型");

			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);

			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer,
					attribute));
		}

		// 名称
		{
			EAttribute attribute = MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM__VALUE;

			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer,
					SWT.LEFT);
			tvColumn.getColumn().setWidth(150);
			tvColumn.getColumn().setText("内容");

			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);

			tvColumn.setEditingSupport(new LongTextEditingSupport(treeViewer,
					attribute));
		}
		// 名称
		{
			EAttribute attribute = MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM__DISCRIPTION;

			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer,
					SWT.LEFT);
			tvColumn.getColumn().setWidth(300);
			tvColumn.getColumn().setText("描述");

			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			provider.setDiagnosticProvider(problemView);
			tvColumn.setLabelProvider(provider);

			tvColumn.setEditingSupport(new LongTextEditingSupport(treeViewer,
					attribute));
		}

		getProblemPool().addView(problemView);
		getProblemPool().addView(exProblemView);
//		getEditableControl().addEditableUnit(
//				new JresDefaultEditableUnit(viewer.getControl()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();

		addAction = new ColumnViewerAddAction(getColumnViewer(),
				getEditingDomain(), null,
				MetadataPackage.Literals.GENERAL_DATA_CONFIG_LIST__ITEMS,
				MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM);
		getActionRegistry().registerAction(addAction);
		getSelectionActions().add(addAction.getId());

		insertAction = new ColumnViewerInsertAction(getColumnViewer(),
				getEditingDomain(), null,
				MetadataPackage.Literals.GENERAL_DATA_CONFIG_LIST__ITEMS,
				MetadataPackage.Literals.GENERAL_DATA_CONFIG_ITEM);
		getActionRegistry().registerAction(insertAction);
		getSelectionActions().add(insertAction.getId());

		IAction delAction = new ColumnViewerDeleteAction(getColumnViewer(),
				getEditingDomain());
		getActionRegistry().registerAction(delAction);
		getSelectionActions().add(delAction.getId());

		// 只读控制
		getEditableControl().addEditableUnit(new ActionEditableUnit(addAction));
		getEditableControl().addEditableUnit(new ActionEditableUnit(delAction));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#setInput(java
	 * .lang.Object)
	 */
	@Override
	public void setInput(Object input) {
		addAction.setOwner((EObject) input);
		insertAction.setOwner((EObject) input);
		super.setInput(input);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createButtons
	 * (com.hundsun.ares.studio.jres.ui.actions.ButtonGroupManager)
	 */
	@Override
	protected void createToolbarItems(ToolBarManager manager) {
		// 创建按钮列表
		IAction action = getActionRegistry()
				.getAction(IActionIDConstant.CV_ADD);
		manager.add(action);

		action = getActionRegistry().getAction(IActionIDConstant.CV_DELETE);
		manager.add(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}
}