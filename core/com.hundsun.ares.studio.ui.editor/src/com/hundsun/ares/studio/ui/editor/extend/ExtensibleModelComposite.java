/**
 * 源程序名称：ExtensibleModelComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.viewers.ColumnViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

/**
 * @author gongyf
 *
 */
public class ExtensibleModelComposite extends Composite {

	private TreeViewer viewer;
	private IProblemPool problemPool;
	private EObjectColumnViewerProblemView problemView;
	private ExtensibleModelProblemView formProblemView ;
	private ExtensibleModelTreeContentProvider contentProvider;
	
	/**
	 * @param parent
	 * @param style
	 */
	public ExtensibleModelComposite(Composite parent, FormToolkit toolkit) {
		super(parent, SWT.NONE);
		
		create(toolkit);
	}
	
	public void setInput(IARESElement resource, ExtensibleModel model) {
		if (this.problemPool != null && formProblemView != null) {
			this.problemPool.removeView(formProblemView);
		}
		formProblemView = new ExtensibleModelProblemView(model);
		viewer.setInput(new ExtensibleModelEditingRoot(resource, model));
		
		if (this.problemPool != null ) {
			this.problemPool.addView(formProblemView);
		}
	}

	/**
	 * 刷新内容
	 */
	public void refresh() {
		RefreshViewerJob.refresh(viewer);
	}

	
	private void create(FormToolkit toolkit) {
		FilteredTree ft = new FilteredTree(this, FormWidgetUtils.getDefaultTreeStyles(), new ColumnViewerPatternFilter(), true);
		toolkit.adapt(ft);
		
		viewer = ft.getViewer();
		
		viewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);
		
		// 开启表格tooltip显示
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.RECREATE);
		
		contentProvider = new ExtensibleModelTreeContentProvider();
		viewer.setContentProvider(contentProvider);
		
		TreeViewerColumn keyColumn = new TreeViewerColumn(viewer, SWT.LEFT);
		keyColumn.setLabelProvider(new PropertyNameColumnLabelProvider());
		keyColumn.getColumn().setWidth(200);
		keyColumn.getColumn().setText("属性名");
		
		TreeViewerColumn valueColumn = new TreeViewerColumn(viewer, SWT.LEFT);
		valueColumn.getColumn().setWidth(200);
		valueColumn.getColumn().setText("值");
		PropertyValueColumnLabelProvider lableProvider = new PropertyValueColumnLabelProvider();
		
		problemView = new EObjectColumnViewerProblemView(viewer) {
			protected void doRefresh(ProblemPoolChangeEvent event) {
				RefreshViewerJob.refresh(getViewer());
			};
		};
		lableProvider.setDiagnosticProvider(problemView);
		valueColumn.setLabelProvider(lableProvider);
		valueColumn.setEditingSupport(new PropertyValueEditingSupport(viewer));
		
		
		GridLayoutFactory.fillDefaults().applyTo(this);
		GridDataFactory.fillDefaults().grab(true, true).hint(100, 300).applyTo(ft);
		
	}
	
	/**
	 * @param problemPool the problemPool to set
	 */
	public void setProblemPool(IProblemPool problemPool) {
		if (this.problemPool != null) {
			this.problemPool.removeView(problemView);
			if (formProblemView != null) {
				this.problemPool.removeView(formProblemView);
			}
			
		}
		this.problemPool = problemPool;
		if (this.problemPool != null) {
			this.problemPool.addView(problemView);
			if (formProblemView != null) {
				this.problemPool.addView(formProblemView);
			}
		}
	}
	
	
}
