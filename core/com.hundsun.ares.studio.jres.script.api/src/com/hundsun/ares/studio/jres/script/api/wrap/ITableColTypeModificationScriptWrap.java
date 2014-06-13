/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColTypeModifyDetailScriptWrap;

/**
 * 修订记录字段类型
 * 
 * @author yanwj06282
 *
 */
public interface ITableColTypeModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取修改表字段类型的明细记录
	 * 
	 * @return
	 */
	public ITableColTypeModifyDetailScriptWrap[] getDetails();
	
}
