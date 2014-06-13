/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 修订记录内明细数据的封装对象，明细数据包括修订记录中的删除列，删除索引，修改表字段类型等
 * 
 * @author yanwj06282
 *
 */
public interface ITableColBaseDetailScriptWrap extends IScriptModelWrap{

	/**
	 * 获取列名
	 * 
	 */
	public String getName();
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
}
