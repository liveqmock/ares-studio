/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 数据字典对象
 * 
 * @author yanwj06282
 *
 */
public interface IDictEntryScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取数据字典条目明细
	 * 
	 * @return
	 */
	public IDictSubEntryScriptWrap[] getSubEntries();
	
}
