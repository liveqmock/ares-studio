/**
 * 源程序名称：MetadataTreeListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.editors.dnd.MetadataDropAdapter;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.viewers.ColumnViewerPatternFilter;
import com.hundsun.ares.studio.ui.viewers.link.CellLinkColumnViewerEditorActivationStrategy;
import com.hundsun.ares.studio.ui.viewers.link.CellLinkMouseListener;
import com.hundsun.ares.studio.ui.viewers.link.ICellLinkProvider;

/**
 * 将元数据的表格使用带过滤框的{@link FilteredTree}类实现<br>
 * 将内容提供器统一使用{@link MetadataTreeContentProvider}<br>
 * 增加了表格上的拖拽支持
 * @author gongyf
 *
 */
public abstract class MetadataTreeListPage extends MetadataListPage implements ICellLinkProvider {

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MetadataTreeListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	//创建一个带过滤框的表格
	protected Control createColumnViewer(Composite parent, FormToolkit toolkit) {
		FilteredTree tree = new FilteredTree(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI, new ColumnViewerPatternFilter(), true);
		
		toolkit.adapt(tree);
		tree.getViewer().getTree().setHeaderVisible(true);
		tree.getViewer().getTree().setLinesVisible(true);
		setColumnViewer(tree.getViewer());
		
		configureColumnViewer(getColumnViewer());
		

		
		return tree;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#getColumnViewerContentProvider()
	 */
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new MetadataTreeContentProvider();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureColumnViewer(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void configureColumnViewer(ColumnViewer viewer) {
		super.configureColumnViewer(viewer);
		
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		//编辑器列表支持拖拽功能
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new MetadataDropAdapter((TreeViewer)viewer, getEditingDomain()));
		
		TreeViewer treeViewer = (TreeViewer) viewer;
		
		// 防止按Ctrl出现编辑框
		TreeViewerEditor.create(treeViewer, 
				new CellLinkColumnViewerEditorActivationStrategy(treeViewer), ColumnViewerEditor.DEFAULT);
		
		treeViewer.getTree().addMouseListener(new CellLinkMouseListener(viewer, this));
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#getColumnViewer()
	 */
	@Override
	public TreeViewer getColumnViewer() {
		return (TreeViewer) super.getColumnViewer();
	}
	
}
