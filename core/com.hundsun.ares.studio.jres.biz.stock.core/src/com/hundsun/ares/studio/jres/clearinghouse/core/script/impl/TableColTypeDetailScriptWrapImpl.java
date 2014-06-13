/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColTypeModifyDetailScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableColTypeDetailScriptWrapImpl extends CommonScriptWrap<CTCTMDetail> implements
		ITableColTypeModifyDetailScriptWrap {
	
	public TableColTypeDetailScriptWrapImpl(CTCTMDetail detail ,IARESResource resource) {
		super(detail ,resource);
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	public String getNewType(){
		return getOriginalInfo().getNewType();
	}

	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}
	
}
