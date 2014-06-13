/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 用户常量明细对象
 * 
 * @author yanwj06282
 *
 */
public interface IConstantItemScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取业务数据类型
	 * 
	 * @return
	 */
	public String getDataType(String type);
	
	/**
	 * 获取常量值
	 * 
	 * @return
	 */
	public String getContantValue();
	
}
