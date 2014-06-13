/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 标准字段对象
 * 
 * @author lvgao
 *
 */
public interface IStandardFieldScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取业务数据类型
	 * 
	 */
	public IBizDataTypeItemScriptWrap getBizDataType();
	
	/**
	 * 获取业务数据类型
	 * 
	 */
	public String getStrBizDataType();
	
	/**
	 * 获取业务类型长度
	 * <p>如果该值不是整数类型，返回0</p>
	 * 
	 * @return
	 */
	public int getLength();

	/**
	 * 获取业务类型精度
	 * <p>如果该值不是整数类型，返回0</p>
	 * 
	 * @return
	 */
	public int getPrecision();
	
	/**
	 * 获取真实数据类型
	 * @param type  输入:oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#等（注意大小写）
	 * @return
	 */
	public String  getRealType(String type);
	
	/**
	 * 获取数据字典info
	 * 
	 * @return
	 */
	public IDictEntryScriptWrap getDictInfo();
	
	/**
	 * 获取数据字典
	 * 
	 */
	public String getStrDictInfo();
	
	/**
	 * 设置标准字段名
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * 设置标准字段中文名
	 * 
	 * @param chinaeseName
	 */
	public void setChineseName(String chinaeseName);
	
	/**
	 * 设置描述信息
	 * 
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * 设置数据字典
	 * 
	 * @param dictId 数据字典条目名
	 */
	public void setDictInfo(String dictId);
	
	/**
	 * 设置业务数据类型
	 * 
	 * @param dataType 业务数据类型条目名
	 */
	public void setDataType(String dataType);
	
	/**
	 * 获取标准字段对应的业务类型的默认值
	 * @param type  输入:oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#等（注意大小写）
	 * @return
	 */
	public String getTrueDefaultValue(String type);
	
}
