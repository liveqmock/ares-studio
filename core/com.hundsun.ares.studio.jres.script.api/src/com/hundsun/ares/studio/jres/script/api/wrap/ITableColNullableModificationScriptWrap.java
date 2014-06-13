/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 修改表字段允许为空
 * 
 * @author yanwj06282
 *
 */
public interface ITableColNullableModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取修改表字段允许为空的明细记录
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
