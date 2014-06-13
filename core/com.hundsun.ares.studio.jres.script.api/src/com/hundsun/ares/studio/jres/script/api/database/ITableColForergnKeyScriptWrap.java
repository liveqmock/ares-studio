/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 
 * 数据库表字段外键
 * 
 * @author yanwj06282
 *
 */
public interface ITableColForergnKeyScriptWrap extends IScriptModelWrap{

	/**
	 * 表字段名，对应编辑器的“重命名”
	 * 
	 * @return
	 */
	public String getTableName();
	
	/**
	 * 字段属性名,对应编辑器的“字段名”
	 * 
	 * @return
	 */
	public String getFieldName();
	
}
