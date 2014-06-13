/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.script.api.database.ITableColForergnKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableColPKScriptWrapImpl extends CommonScriptWrap<ForeignKey>
		implements ITableColForergnKeyScriptWrap {
	
	public TableColPKScriptWrapImpl(ForeignKey foreignKey , IARESResource resource) {
		super(foreignKey ,resource);
	}

	@Override
	public String getTableName() {
		return getOriginalInfo().getTableName();
	}

	@Override
	public String getFieldName() {
		return getOriginalInfo().getFieldName();
	}
	
}
