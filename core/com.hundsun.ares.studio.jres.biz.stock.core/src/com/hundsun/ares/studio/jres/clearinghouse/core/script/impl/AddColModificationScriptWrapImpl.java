/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.HisTableColumn;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IAddColModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 数据库表修订记录，增加表字段
 * 
 * @author yanwj06282
 *
 */
public class AddColModificationScriptWrapImpl extends CommonScriptWrap<AddTableColumnModification> implements IAddColModificationScriptWrap {

	public AddColModificationScriptWrapImpl(AddTableColumnModification addTableModify ,IARESResource resource) {
		super(addTableModify ,resource);
	}

	public ITableColScriptWrap[] getDetails(){
		List<ITableColScriptWrap> columns = new ArrayList<ITableColScriptWrap>();
		for (HisTableColumn column : getOriginalInfo().getColumns()){
			columns.add(new TableColScriptWrapImpl(column, resource));
		}
		return columns.toArray(new ITableColScriptWrap[0]);
	}

}
