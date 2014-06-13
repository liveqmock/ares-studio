/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.DictionaryItemListContentProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.blocks.TableViewerBlock;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author liaogc
 * 
 */
public class DictionaryItemListBlock extends TableViewerBlock {

	/** 字典条目是否属于字典项的引用字典条目 */
	private boolean detailColumnIsRef = false;

	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;
    private DictionaryList dictionaryList;

	/**
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public DictionaryItemListBlock(EditingDomain editingDomain,
			IARESResource resource, DictionaryList dictionaryList,IProblemPool problemPool) {
		super();
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
		this.dictionaryList = dictionaryList;
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

	/**
	 * @param detailColumnIsRef
	 *            the detailColumnIsRef to set
	 */
	public void setDetailColumnIsRef(boolean detailColumnIsRef) {
		this.detailColumnIsRef = detailColumnIsRef;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#
	 * getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new DictionaryItemListContentProvider(dictionaryList);
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
		menuManager.removeAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumns
	 * (org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void createColumns(TableViewer columnViewer) {
		final TableViewer treeViewer = (TableViewer) columnViewer;

		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(treeViewer);
		DictionaryItemEditingSupportDecorator decorator = new DictionaryItemEditingSupportDecorator();

		/** ID */
		/*
		 * {
		 * 
		 * TableViewerColumn column = new TableViewerColumn(treeViewer,
		 * SWT.LEFT); column.getColumn().setText("ID");
		 * column.getColumn().setWidth(120); EObjectColumnLabelProvider provider
		 * = new
		 * EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__NAME
		 * ){
		 * 
		 * @Override public Color getBackground(Object element) {
		 * if(detailColumnIsRef){ return
		 * Display.getDefault().getSystemColor(SWT.COLOR_GRAY); } return
		 * super.getBackground(element); } };
		 * provider.setDiagnosticProvider(problemView);
		 * column.setLabelProvider(provider); TextEditingSupport tes = new
		 * TextEditingSupport(treeViewer,
		 * MetadataPackage.Literals.DICTIONARY_ITEM__NAME);
		 * tes.setDecorator(decorator); column.setEditingSupport(tes); }
		 * 
		 * 
		 */
		
		/** 字典条目 */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("字典条目");
			column.getColumn().setWidth(150);
			EObjectColumnLabelProvider provider = new MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider(MetadataPackage.Literals.NAMED_ELEMENT__NAME)
			{
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			column.getColumn().setMoveable(true);
		}
		
		/** 条目名称 */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("条目名称");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new MetadataDictionaryItimOfDictionaryTypeAttributeColumnLabelProvider(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME)
			{
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			
			column.getColumn().setMoveable(true);
		}
		/** 字典项 */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("字典子项");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					MetadataPackage.Literals.DICTIONARY_ITEM__VALUE) {
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			TextEditingSupport tes = new TextEditingSupport(treeViewer,
					MetadataPackage.Literals.DICTIONARY_ITEM__VALUE);
			tes.setDecorator(decorator);
			column.setEditingSupport(tes);
			column.getColumn().setMoveable(true);
		}
		/** 字典项说明 */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("子项名称");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME) {
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		/** 常量ID */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("字典常量");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME) {
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}
		/** 备注 */
		{
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					MetadataPackage.Literals.DICTIONARY_ITEM__DESCRIPTION) {
				@Override
				public Color getBackground(Object element) {
					if (detailColumnIsRef) {
						return Display.getDefault().getSystemColor(
								SWT.COLOR_GRAY);
					}
					return super.getBackground(element);
				}
			};
			provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);
			column.getColumn().setMoveable(true);
		}

		// 增加扩展列支持
		ExtensibleModelUtils.createExtensibleModelTableViewerColumns(
				getColumnViewer(), getARESResource(),
				MetadataPackage.Literals.DICTIONARY_ITEM, problemView,false);

//		getEditableControl().addEditableUnit(
//				new JresDefaultEditableUnit(columnViewer.getControl()));
		getProblemPool().addView(problemView);

	}
	
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#doShareGlobalActions(org.eclipse.ui.IActionBars)
	 */
	@Override
	protected void doShareGlobalActions(IActionBars actionBars) {
		// TODO Auto-generated method stub
		//super.doShareGlobalActions(actionBars);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#doClearGlobalActions(org.eclipse.ui.IActionBars)
	 */
	@Override
	protected void doClearGlobalActions(IActionBars actionBars) {
		// TODO Auto-generated method stub
		//super.doClearGlobalActions(actionBars);
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
	}

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

			return false;
		}

	}
}
