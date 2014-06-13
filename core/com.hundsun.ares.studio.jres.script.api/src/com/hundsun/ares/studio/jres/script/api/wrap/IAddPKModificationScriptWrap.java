/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 增加表主键
 * 
 * @author yanwj06282
 *
 */
public interface IAddPKModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取增加表主键的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
