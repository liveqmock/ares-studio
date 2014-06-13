/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 菜单与功能功能对象
 * 
 * @author yanwj06282
 *
 */
public interface IFunctionProxyScriptWrap extends IScriptModelWrap{

	/**
	 * 获取功能对象
	 * 
	 * @return
	 */
	public IMenuFunctionScriptWrap getFunctions();
	
	/**
	 * 子交易码(功能编号)
	 * 
	 * @return
	 */
	public String getFunCode();
	
	/**
	 * 交易码(菜单号)
	 * 
	 * @return
	 */
	public String getMenuId();
	
}
