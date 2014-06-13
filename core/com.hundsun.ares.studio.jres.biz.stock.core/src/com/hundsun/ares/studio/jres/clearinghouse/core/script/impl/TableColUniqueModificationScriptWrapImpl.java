/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.CTCUMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableColUniqueModificationSctiptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * 数据库表修订记录，修改表字段唯一约束
 * 
 * @author yanwj06282
 *
 */
public class TableColUniqueModificationScriptWrapImpl extends
CommonScriptWrap<ChangeTableColumnUniqueModifycation> implements ITableColUniqueModificationSctiptWrap {

	
	public TableColUniqueModificationScriptWrapImpl(ChangeTableColumnUniqueModifycation changeColunmModify ,IARESResource resource) {
		super(changeColunmModify ,resource);
	}

	public ITableColScriptWrap[] getDetails (){
		List<ITableColScriptWrap> details = new ArrayList<ITableColScriptWrap>();
		for(CTCUMDetail detail : getOriginalInfo().getDetails()){
			TableColumn col = DatabaseFactory.eINSTANCE.createTableColumn();
			col.setName(detail.getName());
			col.setMark(detail.getMark());
			col.setUnique(detail.isUnique());
			details.add(new TableColScriptWrapImpl(col , resource));
		}
		return details.toArray(new ITableColScriptWrap[0]);
	}
	
}
