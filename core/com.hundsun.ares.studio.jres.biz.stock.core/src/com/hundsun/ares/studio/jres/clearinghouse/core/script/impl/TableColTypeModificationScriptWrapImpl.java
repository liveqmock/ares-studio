/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.script.api.database.ITableColTypeModifyDetailScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableColTypeModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * 数据库表修订记录，修改表类型
 * 
 * @author yanwj06282
 *
 */
public class TableColTypeModificationScriptWrapImpl extends
CommonScriptWrap<ChangeTableColumnTypeModification> implements ITableColTypeModificationScriptWrap {

	public TableColTypeModificationScriptWrapImpl(ChangeTableColumnTypeModification changeColunmModify ,IARESResource resource) {
		super(changeColunmModify ,resource);
	}

	public ITableColTypeModifyDetailScriptWrap[] getDetails (){
		List<ITableColTypeModifyDetailScriptWrap> details = new ArrayList<ITableColTypeModifyDetailScriptWrap>();
		for(CTCTMDetail detail : getOriginalInfo().getDetails()){
			details.add(new TableColTypeDetailScriptWrapImpl(detail , resource));
		}
		return details.toArray(new ITableColTypeModifyDetailScriptWrap[0]);
	}
	
}
