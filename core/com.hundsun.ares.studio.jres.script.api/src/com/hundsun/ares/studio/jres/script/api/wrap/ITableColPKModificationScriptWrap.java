/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;

/**
 * 修改表字段主键
 * 
 * @author yanwj06282
 *
 */
public interface ITableColPKModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取修改表字段主键明细
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getDetails();
	
}
