/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;
import com.hundsun.ares.studio.jres.script.api.database.IRemovedTableColumnScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class RemovedTableColScriptWrapImpl extends CommonScriptWrap<RemovedTableColumn>
		implements IRemovedTableColumnScriptWrap {

	public RemovedTableColScriptWrapImpl(RemovedTableColumn column ,IARESResource resource) {
		super(column ,resource);
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
