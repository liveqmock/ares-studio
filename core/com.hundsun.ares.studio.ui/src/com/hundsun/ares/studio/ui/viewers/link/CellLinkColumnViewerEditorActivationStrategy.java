/**
 * 源程序名称：CellLinkColumnViewerEditorActivationStrategy.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.viewers.link;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

/**
 * 用于包含单元格链接的编辑器激活策略，当用户按下Ctrl的时候，不要激活CellEditor
 * @author gongyf
 *
 */
public class CellLinkColumnViewerEditorActivationStrategy extends
		JRESColumnViewerEditorActivationStrategy {

	/**
	 * @param viewer
	 */
	public CellLinkColumnViewerEditorActivationStrategy(ColumnViewer viewer) {
		super(viewer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy#isEditorActivationEvent(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
	 */
	@Override
	protected boolean isEditorActivationEvent(
			ColumnViewerEditorActivationEvent event) {
		if (event.sourceEvent instanceof MouseEvent) {
			// 如果是鼠标点击事件，则判断是否被按下Ctrl或者Shift
			MouseEvent mEvent = (MouseEvent) event.sourceEvent;
			if ((mEvent.stateMask & SWT.CTRL) != 0 
					|| (mEvent.stateMask & SWT.SHIFT) != 0) {
				return false;
			}
		}
		return super.isEditorActivationEvent(event);
	}
}
