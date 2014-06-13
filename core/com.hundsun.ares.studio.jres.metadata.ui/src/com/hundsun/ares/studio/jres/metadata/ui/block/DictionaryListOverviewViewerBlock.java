/**
 * 源程序名称：DictionaryListViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import java.util.EventObject;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.jres.metadata.ui.utils.MetadataOverviewExtensibleModelUtils;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewContentProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewProjectColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.ColumnSelectSorterListener;
import com.hundsun.ares.studio.ui.editor.actions.ButtonGroupManager;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ColumnViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public class DictionaryListOverviewViewerBlock extends MetadataListOverviewViewerBlock{

	private ColumnViewer detailColumnViewer;
	
//	protected int[] cachedWidthDetail = new int[]{};
//	
//	protected int[] cachedColumnDetail = new int[]{};
	
	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public DictionaryListOverviewViewerBlock(FormPage page,
			EditingDomain editingDomain, IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);

//		int[] savedDetailColumnWidths = getSavedDetailColumnWidthes();
//		if (savedDetailColumnWidths.length > 0) {
//			cachedWidthDetail = savedDetailColumnWidths;
//		}
//		int[] saveDetailColumns = getSavedDetailColumns();
//		if (saveDetailColumns.length > 0) {
//			cachedColumnDetail = saveDetailColumns;
//		}
	}
	
//	/**
//	 * 保存明细列宽
//	 */
//	private void saveColumnWidthDetail(IDialogSettings mySettings){
//		// 保存宽度
//		String[] widthes = new String[cachedWidthDetail.length];
//		for (int i = 0; i < cachedWidthDetail.length; i++) {
//			widthes[i] = String.valueOf(cachedWidthDetail[i]);
//		}
//		mySettings.put(DictionaryListViewerBlock.SAVED_WIDTHE_DETAIL, widthes);
//	
//	}
//	
//	/**
//	 * 保存明细列
//	 */
//	private void saveColumnDetail(IDialogSettings mySettings) {
//		// 保存列
//		String[] widthes = new String[cachedColumnDetail.length];
//		for (int i = 0; i < cachedColumnDetail.length; i++) {
//			widthes[i] = String.valueOf(cachedColumnDetail[i]);
//		}
//		mySettings.put(DictionaryListViewerBlock.SAVED_COLUMN_DETAIL, widthes);
//	}
//	
//	/**
//	 * 取得上次保存的明细列宽。
//	 * @return 上次保存的明细列宽数组。
//	 */
//	protected int[] getSavedDetailColumnWidthes() {
//		String[] savedWidthes = null;
//		IDialogSettings settings = JRESUI.getDefault().getDialogSettings().getSection(getID());
//		if (settings != null) {
//			savedWidthes = settings.getArray(DictionaryListViewerBlock.SAVED_WIDTHE_DETAIL);
//			if (savedWidthes != null) {
//				int[] saved = new int[savedWidthes.length];
//				for (int i = 0; i < savedWidthes.length; i++) {
//					saved[i] = Integer.parseInt(savedWidthes[i]);
//				}
//				return saved;
//			}
//		}
//		return new int[0];
//	}
//	
//	/**
//	 * 取得上次保存的明细列。
//	 * @return 上次保存的明细列数组。
//	 */
//	protected int[] getSavedDetailColumns() {
//		String[] savedWidthes = null;
//		IDialogSettings settings = JRESUI.getDefault().getDialogSettings().getSection(getID());
//		if (settings != null) {
//			savedWidthes = settings.getArray(DictionaryListViewerBlock.SAVED_COLUMN_DETAIL);
//			if (savedWidthes != null) {
//				int[] saved = new int[savedWidthes.length];
//				for (int i = 0; i < savedWidthes.length; i++) {
//					saved[i] = Integer.parseInt(savedWidthes[i]);
//				}
//				return saved;
//			}
//		}
//		return new int[0];
//	}
	
	/**
	 * @return the detailColumnViewer
	 */
	public ColumnViewer getDetailColumnViewer() {
		return detailColumnViewer;
	}

	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new MetadataOverviewContentProvider(getARESResource(),IMetadataRefType.Dict);
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		RefreshViewerJob.refresh(getDetailColumnViewer(), null, false);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		SashForm sash = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
		super.createControl(sash, toolkit);
		
		Composite downPane = toolkit.createComposite(sash);
		Control viewer = createDetailColumnViewer(downPane, toolkit);
		
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(downPane);
		GridDataFactory.fillDefaults().hint(100, 100).grab(true, true).applyTo(viewer);
		
		sash.setWeights(new int[] { 70, 30 });
	}
	
	/**
	 * 创建详细表
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Control createDetailColumnViewer(Composite parent,
			FormToolkit toolkit) {
		FilteredTree tree = new FilteredTree(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, new ColumnViewerPatternFilter(), true);
		
		toolkit.adapt(tree);
		tree.getViewer().getTree().setHeaderVisible(true);
		tree.getViewer().getTree().setLinesVisible(true);
		detailColumnViewer = tree.getViewer();
		
		configureDetailColumnViewer(detailColumnViewer);
		return tree;
	}
	
	protected  EReference getDetailReference(){
		return MetadataPackage.Literals.DICTIONARY_TYPE__ITEMS;
		
	}
	
	@Override
	protected String getReferenceType() {
		return IMetadataRefType.Dict;
	}
	
	/**
	 * @param columnViewer
	 */
	protected void configureDetailColumnViewer(ColumnViewer columnViewer) {
		createDetailColumns(columnViewer);
		columnViewer.setContentProvider(new ReferenceTreeContentProvider(getDetailReference()));
		
		MenuManager menuManager = new MenuManager("#popup.items");
		menuManager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.removeAll();
				createDetailMenus(manager);
			}
		});

		Menu menu = menuManager.createContextMenu(columnViewer.getControl());
		columnViewer.getControl().setMenu(menu);
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(columnViewer, ToolTip.RECREATE);
		
	}
	
	/**
	 * 创建详细表的按键
	 * @param manager
	 */
	protected  void createDetailButtons(ButtonGroupManager manager){
	}
	

	/**
	 * 创建详细表的右键菜单
	 * @param manager
	 */
	protected  void createDetailMenus(IMenuManager manager){
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelectionProvider() == getColumnViewer()) {
			super.selectionChanged(event);
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if (!selection.isEmpty()) {
				notifyDetailViewer((MetadataOverviewElement) selection.getFirstElement());
			} else {
				notifyDetailViewer(null);
			}
			
		}
	}
	
	protected void notifyDetailViewer(MetadataOverviewElement input) {
		// 如果是引用的话，需要只读
		if(input != null){
			if (MetadataUtil.isReferencingItem(input.getItem())) {
				DictionaryType type = null;
				try {
					IContentProvider contentProvider = getColumnViewer().getContentProvider();
					if(contentProvider instanceof MetadataOverviewContentProvider){
						Pair<MetadataItem, IARESResource> result = MetadataUtil.defaultResolve(input.getItem(), input.getResource());
						input = new MetadataOverviewElement(result.second, result.first);
					}
				} catch (CircularReferenceException e) {
				}
			}
			getDetailColumnViewer().setInput(input.getItem());
		}
	}
	
	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		
		/**字典条目*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典条目");
			column.getColumn().setWidth(120);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewHeaderColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
		}
		/**条目名称*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("条目名称");
			column.getColumn().setWidth(120);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		
		// 2013-05-15 sundl 只有无ref nature才添加“所属项目"列
		// 注意这里的逻辑，为保持老工程的兼容性，有ref nature的不显示引用节点；无ref nature的才显示引用节点
		IARESResource res = getARESResource();
		if (res == null) 
			return;
		IARESProject proj = res.getARESProject();
		if (proj != null && !ARESProject.hasRefNature(proj.getProject())) {
			/**所属项目*/
			{
				// 创建表格列
				TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
				column.getColumn().setText("所属项目");
				column.getColumn().setWidth(200);
				// 设置标签提供器
				ColumnLabelProvider provider = new MetadataOverviewProjectColumnLabelProvider();
				column.setLabelProvider(provider);
				column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
				column.getColumn().setMoveable(true);
			}
		}

		/**说明*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("说明");
			column.getColumn().setWidth(200);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		
		MetadataOverviewExtensibleModelUtils.createExtensibleModelTreeViewerColumns(getColumnViewer(), getARESResource(), 
				MetadataPackage.Literals.DICTIONARY_TYPE, null);
		
	}
	
	/**
	 * 创建详细表的列信息
	 * @param columnViewer
	 */
	protected  void createDetailColumns(ColumnViewer columnViewer){
		final TreeViewer treeViewer = (TreeViewer) columnViewer;
		
		/**ID*/
		/*{
			
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("ID");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__NAME);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
		}*/
		/**字典项*/
		{
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典项");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__VALUE);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**字典项说明*/
		{
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典项说明");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**常量ID*/
		{
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典常量");
			column.getColumn().setWidth(120);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__CONSTANT_NAME);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**备注*/
		{
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("备注");
			column.getColumn().setWidth(200);
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(MetadataPackage.Literals.DICTIONARY_ITEM__DESCRIPTION);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		
		MetadataOverviewExtensibleModelUtils.createExtensibleModelTreeViewerColumns(treeViewer, getARESResource(), 
				MetadataPackage.Literals.DICTIONARY_ITEM, null);
		
//		//STEP1:调整列顺序
//		if (cachedColumnDetail.length > 0) {
//			treeViewer.getTree().setColumnOrder(cachedColumnDetail);
//		}
//		//STEP2:给列赋宽度
//		for (int i=0;i<cachedWidthDetail.length;i++) {
//			treeViewer.getTree().getColumns()[i].setWidth(cachedWidthDetail[i]);
//		}
//		
//		treeViewer.getControl().addDisposeListener(new DisposeListener() {
//			
//			@Override
//			public void widgetDisposed(DisposeEvent e) {
//				cachedColumnDetail = treeViewer.getTree().getColumnOrder();
//				TreeColumn[] treeColumns = treeViewer.getTree().getColumns();
//				cachedWidthDetail = new int[treeColumns.length];
//				for (int i = 0; i < treeColumns.length; i++) {
//					cachedWidthDetail[i] = treeColumns[i].getWidth();
//				}
//				saveColumnWidthDetail(mySettings);
//				saveColumnDetail(mySettings);
//			}
//		});
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.link.ICellLinkProvider#getLinkedObject(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		BaseEObjectColumnLabelProvider labelProvider = (BaseEObjectColumnLabelProvider) getColumnViewer().getLabelProvider(cell.getColumnIndex());
		
		EStructuralFeature feature = labelProvider.getEStructuralFeature(cell.getElement());
		Pair<MetadataItem, IARESResource> pair = (Pair<MetadataItem, IARESResource>) cell.getElement();
		DeDictionaryType d = MetadataUtil.decrypt((DictionaryType) pair.first, pair.second);

		if (MetadataPackage.Literals.METADATA_ITEM__REF_ID.equals(feature)) {
			if(((Pair)d.getResolvedItem()).equals(pair)){
				return null;
			}
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

}
