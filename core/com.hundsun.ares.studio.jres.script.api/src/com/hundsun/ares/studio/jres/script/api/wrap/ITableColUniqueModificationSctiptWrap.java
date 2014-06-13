/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 修改唯一约束
 * 
 * @author yanwj06282
 *
 */
public interface ITableColUniqueModificationSctiptWrap extends IModificationScriptWrap{

	/**
	 * 获取修改唯一约束的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
