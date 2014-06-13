/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author mawb
 */
public class JresGrid extends Grid {
	
	private boolean isShowToolTip = true;
	private Map<GridColumn, Boolean> isShowToolTipMap = new HashMap<GridColumn, Boolean>();
	
	/**
	 * @param parent
	 * @param style
	 */
	public JresGrid(Composite parent, int style) {
		super(parent, style);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.nebula.widgets.grid.Grid#showToolTip(org.eclipse.nebula.widgets.grid.GridItem, org.eclipse.nebula.widgets.grid.GridColumn, org.eclipse.nebula.widgets.grid.GridColumnGroup, org.eclipse.swt.graphics.Point)
	 */
	@Override
	protected void showToolTip(GridItem item, GridColumn column,
			GridColumnGroup group, Point location) {
		if (isShowToolTip(column)) {
			super.showToolTip(item, column, group, location);
		}
	}

	protected boolean isShowToolTip(GridColumn cloumn) {
		if (isShowToolTipMap.containsKey(cloumn)) {
			return isShowToolTipMap.get(cloumn);
		}
		return isShowToolTip;
	}

	public void setShowToolTip(boolean isShowToolTip) {
		this.isShowToolTip = isShowToolTip;
	}
	
	public void setShowToolTip(GridColumn cloumn, boolean isShowToolTip) {
		isShowToolTipMap.put(cloumn, isShowToolTip);
	}
	
}
