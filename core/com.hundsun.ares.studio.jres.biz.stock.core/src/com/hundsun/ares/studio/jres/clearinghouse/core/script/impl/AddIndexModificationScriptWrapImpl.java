/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IAddIndexModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * 数据库表修订记录，增加表索引
 * 
 * @author yanwj06282
 *
 */
public class AddIndexModificationScriptWrapImpl extends CommonScriptWrap<AddIndexModification> implements IAddIndexModificationScriptWrap {

	
	public AddIndexModificationScriptWrapImpl(AddIndexModification addIndexModify ,IARESResource resource) {
		super(addIndexModify ,resource);
	}

	public ITableIndexScriptWrap[] getDetails(){
		List<ITableIndexScriptWrap> indexs = new ArrayList<ITableIndexScriptWrap>();
		for (TableIndex index : getOriginalInfo().getIndexs()){
			indexs.add(new TableIndexScriptWrapImpl(null,index, resource));
		}
		return indexs.toArray(new ITableIndexScriptWrap[0]);
	}

}
