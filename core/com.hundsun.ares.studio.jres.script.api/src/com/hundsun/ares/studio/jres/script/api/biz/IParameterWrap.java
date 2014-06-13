package com.hundsun.ares.studio.jres.script.api.biz;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;


public interface IParameterWrap extends IScriptModelWrap{
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getFlag();
	
	/**
	 * 参数名，英文名
	 * @return
	 */
	String getName();
	
	/**
	 * 中文名
	 * @return
	 */
	String getCName();
	
	/**
	 * 说明
	 * @return
	 */
	String getDesc();
	
	/**
	 * 参数类型, 即标准字段，对象，或非标准字段
	 * @return
	 */
	String getParamType();
	
	/**
	 * 业务数据类型
	 * @return
	 */
	String getBizType();
	
	/**
	 * 真实数据类型，即Java类型还是C类型等...  具体返回哪种是根据项目属性中的配置，服务接口中使用哪种语言决定的。
	 * ##暂未实现
	 * @return
	 */
	String getRealType();
	
	/**
	 * 获取对应的Java类型, 如果找不到则返回null
	 * @return
	 */
	String getJavaType();
	
	/**
	 * 获取C#数据类型
	 * @return
	 */
	String getCSharpType();
	
	/**
	 * 数量关系。返回字符串形式： [0..1], [1..1], [0..n], [1..n]
	 * 
	 * @return
	 */
	String getMultiplicity();
	
	/**
	 * 默认值
	  * @param type  输入:oracle\db2\sqlserver\c\java\mysql\informix\sybase\C#等（注意大小写）
	 * @return
	 */
	String getDefaultValue(String type);
	
	/**
	 * 备注
	 * @return
	 */
	String getComments();
	
	/**
	 * 设置标记
	 * 
	 * @return
	 */
	void setFlag(String flag);
}
