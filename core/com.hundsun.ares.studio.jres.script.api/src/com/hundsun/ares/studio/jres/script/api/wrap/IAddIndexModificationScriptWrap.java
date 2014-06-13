/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;

/**
 * 增加表索引
 * 
 * @author yanwj06282
 *
 */
public interface IAddIndexModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取增加表索引的明细记录
	 * 
	 * @return
	 */
	public ITableIndexScriptWrap[] getDetails();
	
}
