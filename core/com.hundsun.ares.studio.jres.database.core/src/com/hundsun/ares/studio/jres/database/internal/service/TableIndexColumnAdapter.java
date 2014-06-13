/**
 * 
 */
package com.hundsun.ares.studio.jres.database.internal.service;

import com.hundsun.ares.studio.jres.database.service.ITableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * @author gongyf
 *
 */
public class TableIndexColumnAdapter implements ITableIndexColumn {

	private TableIndexColumn column;

	/**
	 * @param column
	 */
	public TableIndexColumnAdapter(TableIndexColumn column) {
		super();
		this.column = column;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndexColumn#getColumnName()
	 */
	@Override
	public String getColumnName() {
		return column.getColumnName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndexColumn#isAscending()
	 */
	@Override
	public boolean isAscending() {
		return column.isAscending();
	}

}
