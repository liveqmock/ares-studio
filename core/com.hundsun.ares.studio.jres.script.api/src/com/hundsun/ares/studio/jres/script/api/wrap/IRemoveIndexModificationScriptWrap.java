/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;

/**
 * 删除表索引
 * 
 * @author yanwj06282
 *
 */
public interface IRemoveIndexModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取删除表索引的明细记录
	 * 
	 * @return
	 */
	public ITableIndexScriptWrap[] getDetails();
	
}
