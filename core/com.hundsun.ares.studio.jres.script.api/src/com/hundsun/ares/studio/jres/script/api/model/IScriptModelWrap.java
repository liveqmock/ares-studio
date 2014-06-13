/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.model;



/**
 * @author lvgao
 *
 */
public interface IScriptModelWrap {

	/**
	 * 获取类型
	 * @return
	 */
	public String getType();
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 获取扩展信息
	 * 
	 * @param key
	 * @return
	 */
	public String getExtendsValue(String key);
	
	/**
	 * 设置用户扩展
	 * 
	 * @param key
	 * @param value
	 */
	public void setExtendsValue(String key , String value);
	
}
