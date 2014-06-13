/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 删除表字段
 * 
 * @author yanwj06282
 *
 */
public interface IRemoveColModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取删除表字段的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
