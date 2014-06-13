package com.hundsun.ares.studio.ui;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

/**
 * @author gongyf
 *
 */
public class TableViewerPatternFilter extends PatternFilter {
	
	public TableViewerPatternFilter(){
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
