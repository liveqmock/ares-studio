/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 
 * 元数据分组信息包装类
 * 
 * @author yanwj06282
 *
 */
public interface IMetadataCategoryScriptWrap extends IScriptModelWrap{

	/**
	 * 获取分组下的条目
	 * 
	 * @return
	 */
	public IMetadataItemScriptWrap[] getItems();
	
	/**
	 * 获取子分组
	 * 
	 * @return
	 */
	public IMetadataCategoryScriptWrap[] getCategories ();
}
