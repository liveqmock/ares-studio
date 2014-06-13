/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.script.api.database.ITableColModifyDetailScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableColModifyDetailScriptWrapImpl extends CommonScriptWrap<RTCMDetail> implements
		ITableColModifyDetailScriptWrap {

	public TableColModifyDetailScriptWrapImpl(RTCMDetail detail ,IARESResource resource) {
		super(detail ,resource);
	}
	
	public String getNewName (){
		return getOriginalInfo().getNewName();
	}

	public String getOldName(){
		return getOriginalInfo().getOldName();
	}

	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}
	
}
