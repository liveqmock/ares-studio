/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.ModifyDetail;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IAddPKModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 数据库表修订记录，增加主键
 * 
 * @author yanwj06282
 *
 */
public class AddPKModificationScriptWrapImpl extends CommonScriptWrap<AddTableColumnPKModification> implements IAddPKModificationScriptWrap {

	public AddPKModificationScriptWrapImpl(AddTableColumnPKModification addPKModify ,IARESResource resource) {
		super(addPKModify ,resource);
	}

	public ITableColScriptWrap[] getDetails (){
		List<ITableColScriptWrap> details = new ArrayList<ITableColScriptWrap>();
		for(ModifyDetail detail : getOriginalInfo().getDetails()){
			TableColumn col = DatabaseFactory.eINSTANCE.createTableColumn();
			col.setName(detail.getName());
			col.setMark(detail.getMark());
			details.add(new TableColScriptWrapImpl(col , resource));
		}
		return details.toArray(new ITableColScriptWrap[0]);
	}

}
