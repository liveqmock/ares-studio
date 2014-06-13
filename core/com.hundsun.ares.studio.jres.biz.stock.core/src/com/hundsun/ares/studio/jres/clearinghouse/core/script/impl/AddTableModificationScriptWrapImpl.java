/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IAddTableModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 
 * @author yanwj06282
 *
 */
public class AddTableModificationScriptWrapImpl extends CommonScriptWrap<AddTableModification>
		implements IAddTableModificationScriptWrap {

	public AddTableModificationScriptWrapImpl(AddTableModification addTableModify , IARESResource resource) {
		super(addTableModify ,resource);
	}

	public ITableColScriptWrap[] getTableColumns(){
		List<ITableColScriptWrap> columns = new ArrayList<ITableColScriptWrap>();
		for (TableColumn column : getOriginalInfo().getColumns()){
			columns.add(new TableColScriptWrapImpl(column, resource));
		}
		return columns.toArray(new ITableColScriptWrap[0]);
	}
	
	public boolean isGenHisTable(){
		return getOriginalInfo().isNewHistoryTable();
	}
	
	public boolean isGenTable(){
		return getOriginalInfo().isNewSelfTable();
	}
	
	public ITableIndexScriptWrap[] getTableIndexes(){
		List<ITableIndexScriptWrap> indexs = new ArrayList<ITableIndexScriptWrap>();
		for (TableIndex index : getOriginalInfo().getIndexes()){
			indexs.add(new TableIndexScriptWrapImpl(null,index, resource));
		}
		return indexs.toArray(new ITableIndexScriptWrap[0]);
	}

	@Override
	public Object getDetails() {
		return null;
	}
	
}
