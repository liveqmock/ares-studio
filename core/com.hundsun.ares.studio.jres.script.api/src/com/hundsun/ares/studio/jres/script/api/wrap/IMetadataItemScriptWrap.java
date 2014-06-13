/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 元数据明细条目对象
 * 
 * @author yanwj06282
 *
 */
public interface IMetadataItemScriptWrap extends IScriptModelWrap{

	/**
	 * 获取中文名
	 * 
	 * @return
	 */
	public String getChineseName();
	
	/**
	 * 获取描述信息
	 * 
	 * @return
	 */
	public String getDescription();
	
}
