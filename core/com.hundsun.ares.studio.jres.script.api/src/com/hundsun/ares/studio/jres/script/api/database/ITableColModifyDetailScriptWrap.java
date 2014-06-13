/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 修改表字段封装对象
 * 
 * @author yanwj06282
 *
 */
public interface ITableColModifyDetailScriptWrap extends IScriptModelWrap {

	/**
	 * 获取新名字
	 * 
	 * @return
	 */
	public String getNewName ();

	/**
	 * 获取老名字
	 * 
	 * @return
	 */
	public String getOldName();
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
}
