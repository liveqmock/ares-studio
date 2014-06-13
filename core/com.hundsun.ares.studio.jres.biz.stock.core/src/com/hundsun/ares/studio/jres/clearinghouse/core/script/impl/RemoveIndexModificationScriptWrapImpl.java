/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemovedIndex;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRemoveIndexModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 数据库表修订记录，删除表索引
 * 
 * @author yanwj06282
 *
 */
public class RemoveIndexModificationScriptWrapImpl extends CommonScriptWrap<RemoveIndexModification>
		implements IRemoveIndexModificationScriptWrap {

	public RemoveIndexModificationScriptWrapImpl(RemoveIndexModification removeIndex ,IARESResource resource) {
		super(removeIndex ,resource);
	}

	public ITableIndexScriptWrap[] getDetails(){
		List<ITableIndexScriptWrap> removeIndexes = new ArrayList<ITableIndexScriptWrap>();
		for(RemovedIndex index : getOriginalInfo().getIndexs()){
			TableIndex tableIndex = DatabaseFactory.eINSTANCE.createTableIndex();
			tableIndex.setName(index.getName());
			tableIndex.setMark(index.getMark());
			removeIndexes.add(new TableIndexScriptWrapImpl(null ,tableIndex, resource));
		}
		return removeIndexes.toArray(new ITableIndexScriptWrap[0]);
	} 
	
}
