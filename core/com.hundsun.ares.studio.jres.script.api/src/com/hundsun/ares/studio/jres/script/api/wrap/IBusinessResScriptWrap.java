/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 业务逻辑资源对象
 * 
 * @author yanwj06282
 *
 */
public interface IBusinessResScriptWrap extends IScriptModelWrap{
	
	/**
	 * 获取资源中文名
	 * 
	 * @return
	 */
	public String getChineseName();
	
	/**
	 * 获取资源长名，从模块根下开始，以“.”做分隔符
	 * <p>例如：sys.ser.AddUser</p>
	 * 
	 * @return
	 */
	public String getFullyQualifiedName();
	
	/**
	 * 获取功能号
	 * 
	 * @return
	 */
	public String getFunctionId();
	
	/**
	 * 资源描述
	 * 
	 * @return
	 */
	public String getDescription();
	
}
