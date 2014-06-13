/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColNullableDetailScriptWrap;

/**
 * 
 * 数据库表修订记录，表字段允许为空明细
 * 
 * @author yanwj06282
 *
 */
public class TableColNullableDetailScriptWrapImpl extends
		TableColBaseDetailScriptWrapImpl implements ITableColNullableDetailScriptWrap{

	public TableColNullableDetailScriptWrapImpl(ModifyDetail detail,
			IARESResource resource) {
		super(detail, resource);
	}

	public boolean isNullable(){
		return getOriginalInfo().isNullable();
	}
	
	public String getName(){
		return getOriginalInfo().getName();
	}
	
	@Override
	public CTCNMDetail getOriginalInfo() {
		return (CTCNMDetail) super.getOriginalInfo();
	}
	
}
