/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableColNullableModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * 数据库表修订记录，修改表字段允许为空
 * 
 * @author yanwj06282
 *
 */
public class TableColNullableModificationScriptWrapImpl extends
CommonScriptWrap<ChangeTableColumnNullableModifycation> implements ITableColNullableModificationScriptWrap {

	public TableColNullableModificationScriptWrapImpl(ChangeTableColumnNullableModifycation changeColunmModify ,IARESResource resource) {
		super(changeColunmModify ,resource);
	}

	public ITableColScriptWrap[] getDetails (){
		List<ITableColScriptWrap> details = new ArrayList<ITableColScriptWrap>();
		for(CTCNMDetail detail : getOriginalInfo().getDetails()){
			TableColumn col = DatabaseFactory.eINSTANCE.createTableColumn();
			col.setName(detail.getName());
			col.setMark(detail.getMark());
			col.setNullable(detail.isNullable());
			details.add(new TableColScriptWrapImpl(col , resource));
		}
		return details.toArray(new ITableColScriptWrap[0]);
	}
	
}
