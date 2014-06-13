/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.decorators;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.TreeItem;

import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlockDecorator;

/**
 * 
 * 回车后移动到下一行，如果是最末行则调用指定命名的Action
 * @author gongyf
 *
 */
public class EnterForNewLineTreeViewerBlockDecorator extends
		ColumnViewerBlockDecorator<TreeViewer> {
	
	public final static String ID = EnterForNewLineTreeViewerBlockDecorator.class.getName();
	
	private int editIndex = 0;
	
	/**
	 * @return the editIndex
	 */
	public int getEditIndex() {
		return editIndex;
	}
	
	/**
	 * @param editIndex the editIndex to set
	 */
	public void setEditIndex(int editIndex) {
		this.editIndex = editIndex;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlockDecorator#decorateViewer(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock, org.eclipse.jface.viewers.ColumnViewer)
	 */
	@Override
	public void decorateViewer(final ColumnViewerBlock<TreeViewer> block,
			final TreeViewer viewer) {
		viewer.getTree().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == '\r' && viewer.getTree().getSelectionCount() == 1) {
					TreeItem[] items = viewer.getTree().getSelection();
					if (ArrayUtils.isNotEmpty(items)) {
						// 查找下一行
						TreeItem nextItem = null;
						TreeItem[] subItems = items[0].getItems();
						if (items[0].getExpanded() && ArrayUtils.isNotEmpty(subItems) ) {
							nextItem = subItems[0];
						} else if (items[0].getParentItem() == null) {
							int index = viewer.getTree().indexOf(items[0]);
							if (index < viewer.getTree().getItemCount() - 1) {
								nextItem = viewer.getTree().getItem(index + 1);
							}
						} else {
							int index = items[0].getParentItem().indexOf(items[0]);
							if (index < items[0].getParentItem().getItemCount() - 1) {
								nextItem = items[0].getParentItem().getItem(index + 1);
							}
						}
						
						
						if (nextItem == null) {
							// 最后一行
							IAction action = block.getActionRegistry().getAction(IActionIDConstant.CV_ADD);
							if (action != null && action.isEnabled()) {
								action.run();
								
//								viewer.refresh();
//								viewer.editElement(viewer.getTree().getItem(viewer.getTree().getItemCount() - 1).getData(), getEditIndex());
							}
						} else {
							viewer.getTree().setSelection(nextItem);
							// 需要viewr和control同步
							viewer.setSelection(viewer.getSelection());
						}
					}
				}
			}
		});
	}
}
