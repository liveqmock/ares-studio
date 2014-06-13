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
public interface ITypeDefaultValueItemScriptWrap extends IMetadataItemScriptWrap{
	
	/**
	 * 获取指定类型的默认值
	 * @param type  输入:oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#等（注意大小写）
	 * @return
	 */
	public String getValue(String type);
	
}
