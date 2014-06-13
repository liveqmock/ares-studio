/**
 * 源程序名称：MetadataListViewerBlock.java
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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.editors.OperationButtonGroupControl;
import com.hundsun.ares.studio.jres.metadata.ui.editors.dnd.MetadataDropAdapter;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider.CategoryListener;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.editor.blocks.TreeViewerBlock;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.viewers.link.CellLinkColumnViewerEditorActivationStrategy;
import com.hundsun.ares.studio.ui.viewers.link.CellLinkMouseListener;
import com.hundsun.ares.studio.ui.viewers.link.ICellLinkProvider;

/**
 * @author yanwj06282
 *
 */
public abstract class MetadataListViewerBlock extends TreeViewerBlock implements ICellLinkProvider{

	protected static final String SHOW_CATEGORY = "show_category";
	
	private EditingDomain editingDomain;
	private IARESResource resource;
	private IProblemPool problemPool;
	private IWorkbenchPartSite site;
	private FormPage page;
	private OperationButtonGroupControl obgc;
	
	private MetadataTreeContentProvider contentProvider;
	
	// 对是否显示分类状态变化敏感的AcitonIDs，在这个list的action会在显示分类状态切换的时候刷新。
	protected List<String> categoryWareActions = new ArrayList<String>();
	
	public MetadataListViewerBlock(FormPage page,EditingDomain editingDomain,IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		this.page = page;
		this.site = site;
		this.editingDomain = editingDomain;
		this.resource = resource;
		this.problemPool = problemPool;
	}
	
	@Override
	protected EStructuralFeature getHeadColumnFeature() {
		return MetadataPackage.Literals.NAMED_ELEMENT__NAME;
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
	 * @return the site
	 */
	public IWorkbenchPartSite getSite() {
		return site;
	}

	public FormPage getFormPage (){
		return page;
	}
	

	/**
	 * 获取扩展列容器
	 * @return 
	 */
	public OperationButtonGroupControl getOperationControl() {
		return obgc;
	}
	
	@Override
	protected abstract String getID();
	
	@Override
	protected Control createViewerButtons(Composite parent, FormToolkit toolkit) {
		Composite client = toolkit.createComposite(parent);
		
		ToolBar toolbar = new ToolBar(client, SWT.VERTICAL);
		ToolBarManager toolbarManager = new ToolBarManager(toolbar);
		createToolbarItems(toolbarManager);
		
		toolbarManager.add(new Separator(OperationButtonGroupControl.GROUP_ID));
		obgc = new OperationButtonGroupControl(toolbarManager);
		
		GridLayoutFactory.fillDefaults().applyTo(client);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(toolbar);
		
		toolbarManager.update(true);
		toolkit.adapt(toolbar, false, false);
		return client;
	}
	
	@Override
	public void setInput(Object input) {
		super.setInput(input);
		obgc.setData((MetadataResourceData) input);
		obgc.setARESResource(resource);
		obgc.refresh();
	}
	
	@Override
	protected void configureColumnViewer(TreeViewer viewer) {
		super.configureColumnViewer(viewer);
		
		addDragSupport(viewer);
		TreeViewer treeViewer = viewer;
		
		treeViewer.getTree().addMouseListener(new CellLinkMouseListener(viewer, this));
	}
	
	protected void addDragSupport(TreeViewer viewer){
		
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		//编辑器列表支持拖拽功能
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new MetadataDropAdapter(viewer, getEditingDomain()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#createColumnViewerEditorActivationStrategy(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected ColumnViewerEditorActivationStrategy createColumnViewerEditorActivationStrategy(
			TreeViewer viewer) {
		// 防止按Ctrl出现编辑框
		return new CellLinkColumnViewerEditorActivationStrategy(viewer);
	}
	
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		//初始化时为分组显示
		contentProvider = new MetadataTreeContentProvider();
		contentProvider.addShowCategoryStateChangeListener(new CategoryListener() {
			@Override
			public void showCategoryStateChanged() {
				for (String id : categoryWareActions) {
					IAction action = getActionRegistry().getAction(id);
					if (action != null) {
						if (action instanceof IUpdateAction) {
							((IUpdateAction) action).update();
						}
					}
				}
			}
		});
		return contentProvider;
	}

	@Override
	protected void storeState(IDialogSettings settings) {
		super.storeState(settings);
		storeShowCategoryState(settings);
	}
	
	protected void storeShowCategoryState(IDialogSettings settings) {
		// 保存分组
		if (contentProvider != null) {
			boolean showCategory = contentProvider.isShowCategory();
			settings.put(SHOW_CATEGORY, showCategory);
		}

	}
	
	@Override
	protected void restoreState(IDialogSettings settings) {
		super.restoreState(settings);
		restoreShowCategoryState(settings);
	}
	
	protected void restoreShowCategoryState(IDialogSettings settings) {
		if (contentProvider != null) {
			boolean showCategory = getDefaultShowCategory();
			if (settings.get(SHOW_CATEGORY) != null) {
				showCategory = settings.getBoolean(SHOW_CATEGORY);
			}
			contentProvider.setShowCategory(showCategory);
		}

	}
	
	/**
	 * 默认分组状态
	 * @return
	 */
	protected abstract boolean getDefaultShowCategory();

	@Override
	protected void createMenus(IMenuManager menuManager) {}

	@Override
	protected void createActions() {
		// FIXME 复制的时候，会不会因为Reference都是MetadataItem出现问题
	}
	
	@Override
	protected void createColumns(TreeViewer viewer){
		
	}
	
	/**
	 * 获取第一列标题
	 * 默认返回“ID”
	 * @return
	 */
	protected String getTreeViewerFirstColumnName() {
		TreeViewer viewer = getColumnViewer();
		if(null != viewer) {
			Tree tree = viewer.getTree();
			if(null != tree) {
				TreeColumn firstCol = tree.getColumn(0);
				if(null != firstCol){
					return firstCol.getText();
				}
			}
		}
		return "ID";
	}

}
