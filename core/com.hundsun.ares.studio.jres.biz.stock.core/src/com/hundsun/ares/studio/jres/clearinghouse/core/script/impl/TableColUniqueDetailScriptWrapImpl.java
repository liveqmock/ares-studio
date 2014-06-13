/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColUniqueDetailScriptWrap;

/**
 * 
 * 数据库表修订记录，表字段唯一约束明细
 * 
 * @author yanwj06282
 *
 */
public class TableColUniqueDetailScriptWrapImpl extends
		TableColBaseDetailScriptWrapImpl implements ITableColUniqueDetailScriptWrap{

	public TableColUniqueDetailScriptWrapImpl(ModifyDetail detail,
			IARESResource resource) {
		super(detail, resource);
	}

	public boolean isUnique(){
		return getOriginalInfo().isUnique();
	}
	
	@Override
	public CTCUMDetail getOriginalInfo() {
		return (CTCUMDetail) super.getOriginalInfo();
	}
	
}
