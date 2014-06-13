/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model;

/**
 * 统一模型接口
 * @author sundl
 */
public interface ICommonModel {
	
	/** 英文名字属性ID	 */
	String NAME = "name";
	/** 中文名字属性ID	 */
	String CNAME= "cname";
	/** 对象号　*/
	String ID = "id";
	
	/**
	 * 获取指定的属性值，如果没有指定的属性，可以返回null
	 * @param key 属性key
	 * @return 值
	 */
	Object getValue(String key);
	
	/**
	 * 设置指定的属性的值
	 * @param key
	 * @param value
	 */
	void setValue(String key, Object value);
	
	String getString(String key);
}
