/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 封装对象，修订记录--删除表字段,表字段明细
 * 
 * @author yanwj06282
 *
 */
public interface IRemovedTableColumnScriptWrap extends IScriptModelWrap {

	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
}
