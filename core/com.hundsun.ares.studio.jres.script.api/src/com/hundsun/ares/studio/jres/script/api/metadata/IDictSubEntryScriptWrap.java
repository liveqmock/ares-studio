/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 数据字典明细对象
 * 
 * @author yanwj06282
 *
 */
public interface IDictSubEntryScriptWrap extends IMetadataItemScriptWrap{
	
	/**
	 * 获取字典子项
	 * 
	 * @return
	 */
	public String getSubEntry();
	
	/**
	 * 获取子项名称
	 * 
	 * @return
	 */
	public String getSubEntryName();
	
	/**
	 * 字典常量
	 * 
	 * @return
	 */
	public String getCnstName();
	
	/**
	 * 获取父条目
	 * 
	 * @return
	 */
	public IDictEntryScriptWrap getParent();
	
}
