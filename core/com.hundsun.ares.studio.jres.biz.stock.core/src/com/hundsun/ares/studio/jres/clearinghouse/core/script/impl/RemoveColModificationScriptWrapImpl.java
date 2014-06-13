package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRemoveColModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * Êý¾Ý¿â±íÐÞ¶©¼ÇÂ¼£¬É¾³ý±í×Ö¶Î
 * 
 * @author yanwj06282
 *
 */
public class RemoveColModificationScriptWrapImpl extends CommonScriptWrap<RemoveTableColumnModification> implements IRemoveColModificationScriptWrap{

	public RemoveColModificationScriptWrapImpl(RemoveTableColumnModification removeColumn ,IARESResource resource) {
		super(removeColumn , resource);
	}

	public ITableColScriptWrap[] getDetails(){
		List<ITableColScriptWrap> removeColumns = new ArrayList<ITableColScriptWrap>();
		for(RemovedTableColumn column : getOriginalInfo().getColumns()){
			TableColumn col = DatabaseFactory.eINSTANCE.createTableColumn();
			col.setName(column.getName());
			col.setMark(column.getMark());
			removeColumns.add(new TableColScriptWrapImpl(col, resource));
		}
		return removeColumns.toArray(new ITableColScriptWrap[0]);
	}
	
}
