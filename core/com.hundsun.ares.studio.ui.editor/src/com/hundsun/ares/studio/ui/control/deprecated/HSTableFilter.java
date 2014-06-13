/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.deprecated;

/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/**
 */



import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.hundsun.ares.studio.ui.util.StringMatcher;


/** 
 * 过滤器。只能用于Table
 * <p>CreatedDate: 2007-12-20</p>
 * @author sundl
 */
public class HSTableFilter extends ViewerFilter {

	private StringMatcher matcher;
	
	private String id;
	private boolean leadingWildCards = false;
	
	
    /**
     * The pattern string for which this filter should select 
     * elements in the viewer.
     * 
     * @param patternString
     */
    public void setPattern(String patternString) {
        if (patternString == null || patternString.equals("")) { //$NON-NLS-1$
			matcher = null;
		} else {
			String pattern = patternString + "*"; //$NON-NLS-1$
			if (leadingWildCards) {
				pattern = "*" + pattern; //$NON-NLS-1$
			}
			matcher = new StringMatcher(pattern, true, false);
		}
    }
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the leadingWildCards
	 */
	public boolean isLeadingWildCards() {
		return leadingWildCards;
	}

	/**
	 * @param leadingWildCards the leadingWildCards to set
	 */
	public void setLeadingWildCards(boolean leadingWildCards) {
		this.leadingWildCards = leadingWildCards;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the matcher
	 */
	public StringMatcher getMatcher() {
		return matcher;
	}

	/**
	 * @param matcher the matcher to set
	 */
	public void setMatcher(StringMatcher matcher) {
		this.matcher = matcher;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(matcher == null) return true;
		
		TableViewer tViewer = (TableViewer)viewer;
		
		int columnNum = tViewer.getTable().getColumnCount();
		IBaseLabelProvider bp = tViewer.getLabelProvider();
		if (bp != null && bp instanceof ITableLabelProvider) {
			ITableLabelProvider tp = (ITableLabelProvider)bp;
			for(int i = 0; i < columnNum; i++) {
				String columnText = tp.getColumnText(element, i);
				if(columnText != null && matcher.match(columnText)) {
					return true;
				}
			}
		} else {
			for(int i = 0; i < columnNum; i++) {
				String columnText = ((ColumnLabelProvider)tViewer.getLabelProvider(i)).getText(element);
				if(columnText != null && matcher.match(columnText)) {
					return true;
				}
			}
		}
		

		
		return false;
	}
//
//	public boolean isItemVisable(Viewer viewer, TableItem item) {
//		Table table = item.getParent();		
//		int columnNum = table.getColumnCount();
//		
//		for(int i = 0; i < columnNum; i++) {
//			String columnString = item.getText(i);
//			if(columnString != null && matcher.match(columnString)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
