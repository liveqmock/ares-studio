/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 修改表字段类型的明细数据的封装对象
 * 
 * @author yanwj06282
 *
 */
public interface ITableColTypeModifyDetailScriptWrap extends IScriptModelWrap{

	/**
	 * 获取新类型
	 * 
	 * @return
	 */
	public String getNewType();
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
}
