/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RemovedIndex;
import com.hundsun.ares.studio.jres.script.api.database.IRemovedTableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class RemoveTableIndexScriptWrapImpl extends CommonScriptWrap<RemovedIndex> implements
		IRemovedTableIndexScriptWrap {

	public RemoveTableIndexScriptWrapImpl(RemovedIndex index , IARESResource resource) {
		super(index ,resource);
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
