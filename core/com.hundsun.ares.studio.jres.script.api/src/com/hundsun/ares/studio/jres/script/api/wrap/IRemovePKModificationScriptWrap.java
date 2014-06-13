/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 删除表索引
 * 
 * @author yanwj06282
 *
 */
public interface IRemovePKModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取删除表索引的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
