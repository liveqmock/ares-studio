/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColPKDetailScriptWrap;

/**
 * 
 * 数据库表修订记录，表字段主键明细
 * 
 * @author yanwj06282
 *
 */
public class TableColPKDetailScriptWrapImpl extends
		TableColBaseDetailScriptWrapImpl implements ITableColPKDetailScriptWrap{

	public TableColPKDetailScriptWrapImpl(ModifyDetail detail,
			IARESResource resource) {
		super(detail, resource);
	}

	public boolean isPrimaryKey(){
		return getOriginalInfo().isPrimarkKey();
	}
	
	@Override
	public CTCPMDetail getOriginalInfo() {
		return (CTCPMDetail) super.getOriginalInfo();
	}
	
}
