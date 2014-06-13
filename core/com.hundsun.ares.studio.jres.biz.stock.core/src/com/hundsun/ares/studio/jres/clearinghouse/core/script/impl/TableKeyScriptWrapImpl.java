package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.script.api.database.ITableColForergnKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

public class TableKeyScriptWrapImpl extends CommonScriptWrap<TableKey> implements ITableKeyScriptWrap {

	/**
	 * @param t
	 * @param resource
	 */
	public TableKeyScriptWrapImpl(TableKey t, IARESResource resource) {
		super(t, resource);
	}

	@Override
	public ITableColScriptWrap[] getColumns() {
		List<ITableColScriptWrap> cols = new ArrayList<ITableColScriptWrap>();
		for (TableColumn fk : getOriginalInfo().getColumns()){
			cols.add(new TableColScriptWrapImpl(fk, resource));
		}
		return cols.toArray(new ITableColScriptWrap[0]);
	}

	@Override
	public ITableColForergnKeyScriptWrap[] getForeignKey() {
		List<ITableColForergnKeyScriptWrap> fks = new ArrayList<ITableColForergnKeyScriptWrap>();
		for (ForeignKey fk : getOriginalInfo().getForeignKey()){
			fks.add(new TableColPKScriptWrapImpl(fk, resource));
		}
		return fks.toArray(new ITableColForergnKeyScriptWrap[0]);
	}
	
	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	@Override
	public String getType() {
		return getOriginalInfo().getType().getLiteral();
	}
	
	
	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}

}
