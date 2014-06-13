/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColBaseDetailScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableColBaseDetailScriptWrapImpl extends CommonScriptWrap<ModifyDetail> implements
		ITableColBaseDetailScriptWrap {

	public TableColBaseDetailScriptWrapImpl(ModifyDetail detail , IARESResource resource) {
		super(detail ,resource);
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}
	
}
