/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 增加表字段
 * 
 * @author yanwj06282
 *
 */
public interface IAddColModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取增加表字段的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
