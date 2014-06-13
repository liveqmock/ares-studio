/**
 * 源程序名称：ColumnViewerPatternFilter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author gongyf
 *
 */
public class ColumnViewerPatternFilter extends PatternFilter {
	
	public ColumnViewerPatternFilter(){
		//TASK #9491 所有表格筛选框中，默认支持通配符*
		setIncludeLeadingWildcard(true);
	}
	
    protected boolean isLeafMatch(Viewer viewer, Object element){
    	int columnCount = 0;
    	Control control = viewer.getControl();
    	if (control instanceof Tree) {
    		columnCount = ((Tree) control).getColumnCount();
		} else if (control instanceof Table) {
			columnCount = ((Table) control).getColumnCount();
		}
    	
    	for (int i = 0; i < columnCount; i++) {
    		CellLabelProvider labelProvider = ((ColumnViewer) viewer).getLabelProvider(i);
			if (labelProvider != null && labelProvider instanceof ColumnLabelProvider) {
				if (wordMatches( ((ColumnLabelProvider)labelProvider).getText(element))) {
					return true;
				}
			}
		}
    	
        return false;
    }
}
