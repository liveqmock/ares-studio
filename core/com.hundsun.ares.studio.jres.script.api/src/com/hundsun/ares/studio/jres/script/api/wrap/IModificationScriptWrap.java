/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 修订记录对象
 * 
 * @author yanwj06282
 *
 */
public interface IModificationScriptWrap extends IScriptModelWrap{

	/**
	 * 获取修订记录的明细记录
	 * 
	 * @return
	 */
	public Object getDetails();
	
}
