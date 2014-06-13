/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.script.api.database.ITableColModifyDetailScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRenameTableColModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * 数据库表修订记录，重命名表字段
 * 
 * @author yanwj06282
 *
 */
public class RenameTableColModificationScriptWrapImpl extends
CommonScriptWrap<RenameTableColumnModification> implements IRenameTableColModificationScriptWrap {

	public RenameTableColModificationScriptWrapImpl(RenameTableColumnModification renameColumn ,IARESResource resource) {
		super(renameColumn ,resource);
	}

	public ITableColModifyDetailScriptWrap[] getDetails(){
		List<ITableColModifyDetailScriptWrap> details = new ArrayList<ITableColModifyDetailScriptWrap>();
		for(RTCMDetail detail : getOriginalInfo().getDetails()){
			details.add(new TableColModifyDetailScriptWrapImpl(detail ,resource));
		}
		return details.toArray(new ITableColModifyDetailScriptWrap[0]);
	}
	
}
