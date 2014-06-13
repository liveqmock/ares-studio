/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.jface.util.Policy;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author gongyf
 *
 */
public class BlockUtils {
	static String COLUMN_VIEWER_KEY = Policy.JFACE + ".columnViewer";
	
	public static TableViewerColumn getViewerColumn(TableColumn column) {
		return (TableViewerColumn) column.getData(COLUMN_VIEWER_KEY);
	}
	
	public static TreeViewerColumn getViewerColumn(TreeColumn column) {
		return (TreeViewerColumn) column.getData(COLUMN_VIEWER_KEY);
	}
	
}
