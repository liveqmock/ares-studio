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
public interface IBizDataTypeItemScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取标准类型
	 * 
	 * @return
	 */
	public IStandardDataTypeItemScriptWrap getStdType();
	
	/**
	 * 获取标准数据类型真实值
	 * @param type  输入:oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#等（注意大小写）
	 * @return
	 */
	public String getRealType(String type);
	
	/**
	 * 获取长度
	 * @return
	 */
	public int getLength();
	
	/**
	 * 获取精度
	 * @return
	 */
	public int getPrecision();
	
	/**
	 * 获取默认值ID
	 * 
	 * @return
	 */
	public String getDefaultValueID();
	
	/**
	 * 获取默认值
	 * 
	 * @return
	 */
	public ITypeDefaultValueItemScriptWrap getDefaultValue();
	
}
