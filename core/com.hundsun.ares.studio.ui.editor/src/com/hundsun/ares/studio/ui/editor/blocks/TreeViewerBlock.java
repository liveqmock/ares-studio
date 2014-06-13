/**
 * 源程序名称：TreeViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.Map;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.editor.decorators.EnterForNewLineTreeViewerBlockDecorator;
import com.hundsun.ares.studio.ui.editor.viewers.ColumnViewerPatternFilter;

/**
 * @author gongyf
 *
 */
public abstract class TreeViewerBlock extends ColumnViewerBlock<TreeViewer> {

	
	//创建一个带过滤框的表格
	protected TreeViewer doCreateColumnViewer(Composite parent, FormToolkit toolkit) {
		FilteredTree tree = new FilteredTree(parent, FormWidgetUtils.getDefaultTreeStyles(), new ColumnViewerPatternFilter(), true) {
			/* (non-Javadoc)
			 * @see org.eclipse.ui.dialogs.FilteredTree#getRefreshJobDelay()
			 */
			@Override
			protected long getRefreshJobDelay() {
				return getColumnViewer().getTree().getItemCount() / 40 + 100;
			}
		};
		
		// TASK #8541 服务接口中输入对象参数类型信息时，无法输入定位的问题
		// 2013-10-15 sundl 如果设置了双击编辑, 默认的会触发树的展开事件，导致不能编辑。
		// 这一段代码可以禁用双击展开的特性。参考http://www.eclipse.org/forums/index.php/t/257325/
		tree.getViewer().getControl().addListener(SWT.MeasureItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
			}
		});
		// 下面这段代码可以达到类似的效果，不同的是，这个并不禁止展开的特性，也就是双击可以激活CellEditor，同时也会展开树
//		tree.getViewer().addOpenListener(new IOpenListener() {
//			@Override
//			public void open(OpenEvent event) {
//			}
//		});
		
		toolkit.adapt(tree);
		tree.getViewer().getTree().setHeaderVisible(true);
		tree.getViewer().getTree().setLinesVisible(true);
		tree.setFont(JFaceResources.getTextFont());
		
		return tree.getViewer();
	}

	
	@Override
	protected void restoreState(IDialogSettings settings) {
		super.restoreState(settings);
		
		TreeViewer viewer = getColumnViewer();
		
		{
			// 恢复列宽设置
			String[] savedWidthes = settings.getArray(SAVED_WIDTHES);
			if (savedWidthes != null) {
				int[] saved = new int[savedWidthes.length];
				for (int i = 0; i < savedWidthes.length; i++) {
					saved[i] = Integer.parseInt(savedWidthes[i]);
				}
				
				if (viewer.getTree().getColumns().length == saved.length) {
					for (int i=0;i<saved.length;i++) {
						viewer.getTree().getColumns()[i].setWidth(saved[i]);
					}
				}
				
			} else {
				// 没有保存的自动使用最佳设置
				for (TreeColumn column : viewer.getTree().getColumns()) {
					//2013年5月14日14:06:33 如果宽度有初始自定义值，则使用初始值
					if(column.getWidth() <= 0){
						column.pack();
					}
				}
			}
		}

		{
			// 恢复列顺序
			String[] savedOrder = settings.getArray(SAVED_COLUMNS);
			if (savedOrder != null) {
				int[] saved = new int[savedOrder.length];
				for (int i = 0; i < savedOrder.length; i++) {
					saved[i] = Integer.parseInt(savedOrder[i]);
				}
				if (saved.length > 0 && saved.length == viewer.getTree().getColumns().length) {
					viewer.getTree().setColumnOrder(saved);
				}
			}
		}
		

	}
	
	@Override
	protected void storeState(IDialogSettings settings) {
		super.storeState(settings);
		
		{
			// 保存宽度
			TreeColumn[] columns = getColumnViewer().getTree().getColumns();
			String[] widthes = new String[columns.length];
			for (int i = 0; i < columns.length; i++) {
				widthes[i] = String.valueOf(columns[i].getWidth());
			}
			settings.put(SAVED_WIDTHES, widthes);
		}

		
		{
			// 保存列
			int[] order = getColumnViewer().getTree().getColumnOrder();
			String[] strOrder = new String[order.length];
			for (int i = 0; i < order.length; i++) {
				strOrder[i] = String.valueOf(order[i]);
			}
			settings.put(SAVED_COLUMNS, strOrder);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#configureColumnViewer(org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	protected void configureColumnViewer(final TreeViewer viewer) {
		
		TreeViewerEditor.create(viewer, createColumnViewerEditorActivationStrategy(viewer), 
				ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR);
		
		super.configureColumnViewer(viewer);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock#configureDecorators(java.util.Map)
	 */
	@Override
	protected void configureDecorators(
			Map<String, IColumnViewerBlockDecorator<TreeViewer>> decorators) {
		super.configureDecorators(decorators);
		decorators.put(EnterForNewLineTreeViewerBlockDecorator.ID, new EnterForNewLineTreeViewerBlockDecorator());
	}
}
