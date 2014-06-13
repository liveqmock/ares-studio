/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.decorators;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlockDecorator;

/**
 * 
 * 回车后移动到下一行，如果是最末行则调用指定命名的Action
 * @author gongyf
 *
 */
public class EnterForNewLineTableViewerBlockDecorator extends
		ColumnViewerBlockDecorator<TableViewer> {
	
	public final static String ID = EnterForNewLineTableViewerBlockDecorator.class.getName();
	
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
	public void decorateViewer(final ColumnViewerBlock<TableViewer> block,
			final TableViewer viewer) {
		
		viewer.getTable().addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == '\r' && viewer.getTable().getSelectionCount() == 1) {
					int index = viewer.getTable().getSelectionIndex();
					if (index >= 0) {
						if (index == viewer.getTable().getItemCount() - 1) {
							// 最后一行
							IAction action = block.getActionRegistry().getAction(IActionIDConstant.CV_ADD);
							if (action != null && action.isEnabled()) {
								action.run();
								
								// 必须要等新添加的行出现后才能激活编辑
//								viewer.refresh();
//								viewer.editElement(viewer.getTable().getItem(viewer.getTable().getItemCount() - 1).getData(), getEditIndex());
							}
						} else {
							viewer.getTable().setSelection(index + 1);
							// 需要viewr和control同步
							viewer.setSelection(viewer.getSelection());
						}
					}
				}
			}
		});
	}
}
