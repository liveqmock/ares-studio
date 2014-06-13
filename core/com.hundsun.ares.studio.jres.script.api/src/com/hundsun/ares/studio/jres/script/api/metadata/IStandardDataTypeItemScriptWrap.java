/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 业务数据类型明细对象
 * 
 * @author yanwj06282
 *
 */
public interface IStandardDataTypeItemScriptWrap extends IMetadataItemScriptWrap{
	
	/**
	 * 获取标准数据类型真实值
	 * 
	 * @return
	 */
	public String getRealType(String type);
	
}
