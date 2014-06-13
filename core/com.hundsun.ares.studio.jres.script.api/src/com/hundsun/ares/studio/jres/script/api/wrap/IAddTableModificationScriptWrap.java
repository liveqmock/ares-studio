/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;

/**
 * 修订记录封装对象，增加表
 * 
 * @author yanwj06282
 *
 */
public interface IAddTableModificationScriptWrap extends
		IModificationScriptWrap {

	/**
	 * 生成历史表
	 * 
	 * @return
	 */
	public boolean isGenHisTable();
	
	/**
	 * 生成原表
	 * 
	 * @return
	 */
	public boolean isGenTable();
	
	/**
	 * 获取表格列
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getTableColumns();
	
	/**
	 * 获取表索引
	 * 
	 * @return
	 */
	public ITableIndexScriptWrap[] getTableIndexes();
	
}
