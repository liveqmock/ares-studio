/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.clearinghouse;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlockDecorator;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 删除默认的修改内容这一列，这一列会由扩展的来显示
 * @author gongyf
 *
 */
public class TableHistoryColumnViewerBlockDecorator extends
		ColumnViewerBlockDecorator<TreeViewer> {
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlockDecorator#inputChanged(com.hundsun.ares.studio.jres.ui.pages.ColumnViewerBlock)
	 */
	@Override
	public void inputChanged(ColumnViewerBlock<TreeViewer> block) {
		super.inputChanged(block);
		if (block.getColumnViewer().getInput() instanceof TableResourceData) {
			// 如果是数据库表编辑器则修改内容这一列是扩展的，原始的列需要屏蔽
			Tree tree = block.getColumnViewer().getTree();
			for (int i = 0; i < tree.getColumnCount(); i++) {
				CellLabelProvider lp = block.getColumnViewer().getLabelProvider(i);
				if (lp instanceof EObjectColumnLabelProvider) {
					if (CorePackage.Literals.REVISION_HISTORY__MODIFIED.equals(((EObjectColumnLabelProvider) lp).getEStructuralFeature(null))   ) {
						tree.getColumn(i).dispose();
						break;
					}
				}
			}
			
		}
	}
}
