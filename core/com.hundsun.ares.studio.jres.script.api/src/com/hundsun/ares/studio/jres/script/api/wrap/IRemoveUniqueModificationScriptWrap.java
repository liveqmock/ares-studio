/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 删除唯一约束
 * 
 * @author yanwj06282
 *
 */
public interface IRemoveUniqueModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取删除唯一约束的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
