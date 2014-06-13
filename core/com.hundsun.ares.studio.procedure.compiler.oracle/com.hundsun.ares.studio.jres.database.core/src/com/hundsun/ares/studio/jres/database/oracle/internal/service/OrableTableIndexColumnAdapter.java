/**
 * 
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.jres.database.internal.service.TableIndexColumnAdapter;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * @author gongyf
 *
 */
public class OrableTableIndexColumnAdapter extends TableIndexColumnAdapter implements IOracleTableIndexColumn {

	public OrableTableIndexColumnAdapter(TableIndexColumn column) {
		super(column);
	}


}
