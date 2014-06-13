/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColModifyDetailScriptWrap;

/**
 * 重命名表字段
 * 
 * @author yanwj06282
 *
 */
public interface IRenameTableColModificationScriptWrap extends IModificationScriptWrap{

	/**
	 * 获取重命名表字段的明细记录
	 * 
	 * @return
	 */
	public ITableColModifyDetailScriptWrap[] getDetails();
	
}
