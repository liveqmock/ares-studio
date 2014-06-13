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

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.nebula.jface.gridviewer.GridTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataTreeContentProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * 将元数据的表格使用带过滤框的{@link FilteredTree}类实现<br>
 * 将内容提供器统一使用{@link MetadataTreeContentProvider}<br>
 * 增加了表格上的拖拽支持
 * @author gongyf
 *
 */
public abstract class MetadataGridTreeListPage extends MetadataListPage {

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MetadataGridTreeListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	protected Control createColumnViewer(Composite parent, FormToolkit toolkit) {
		GridTreeViewer viewer = new GridTreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI| SWT.V_SCROLL | SWT.H_SCROLL);
		
//		viewer.getGrid().set
		
		toolkit.adapt(viewer.getGrid());
		viewer.getGrid().setHeaderVisible(true);
		viewer.getGrid().setLinesVisible(true);
		viewer.getGrid().setTreeLinesVisible(false);
		setColumnViewer(viewer);
		
		configureColumnViewer(getColumnViewer());
		
		viewer.getGrid().setRowHeaderVisible(true);
		
		return viewer.getGrid();
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
		
//		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE;
//		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
//		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
//		viewer.addDropSupport(dndOperations, transfers, new MetadataDropAdapter((TreeViewer)viewer, getEditDomain()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#getColumnViewer()
	 */
	@Override
	public GridTreeViewer getColumnViewer() {
		return (GridTreeViewer) super.getColumnViewer();
	}
}
