/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 元数据资源对象
 * 
 * @author yanwj06282
 *
 */
public interface IMetadataResScriptWrap extends IScriptModelWrap,IResourceModifyHistory {

	/**
	 * 获取元数据条目
	 * 
	 * @return
	 */
	public IMetadataItemScriptWrap[] getItems();
	
	/**
	 * 获取分组信息,可获取该分组下的所有条目信息
	 * 
	 * @return
	 */
	public IMetadataCategoryScriptWrap[] getCategories (); 
	
	/**
	 * 获取未分组的条目
	 * 
	 * @return
	 */
	public IMetadataItemScriptWrap[] getNotCateItems ();
	
	/**
	 * 根据name查找元数据条目信息
	 * 
	 * @param name 条目名
	 * @param ignoreCase 是否忽略大小写
	 * @return
	 */
	public IMetadataItemScriptWrap findItemByName(String name , boolean ignoreCase);
	
	/**
	 * 增加元数据条目
	 * 
	 * @return
	 */
	public IMetadataItemScriptWrap addItem();
	
	/**
	 * 根据ID删除对应条目，如果没有对应ID，则不操作
	 * 
	 * @param id
	 */
	public boolean removeItemById(String id);
	
}
