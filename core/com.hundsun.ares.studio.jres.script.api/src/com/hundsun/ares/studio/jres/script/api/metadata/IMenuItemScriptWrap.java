/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.metadata;

import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * 菜单与功能明细对象
 * 
 * @author yanwj06282
 *
 */
public interface IMenuItemScriptWrap extends IMetadataItemScriptWrap{

	/**
	 * 获取菜单标题
	 * 
	 * @return
	 */
	public String getMenuTitle();
	
	/**
	 * 获取菜单号
	 * 
	 * @return
	 */
	public String getMenuId();
	
	/**
	 * 获取页面
	 * 
	 * @return
	 */
	public String getUrl();
	
	/**
	 * 获得该明细下所有的子交易码对象
	 * 
	 * @return
	 */
	public IFunctionProxyScriptWrap[] getFunctionProxys();
	
	/**
	 * 获得所有子条目对象
	 * 
	 * @return
	 */
	public IMenuItemScriptWrap[] getSubItems();
	
	/**
	 * 设置中文名
	 * 
	 * @param chineseName
	 */
	public void setMenuTitle(String menuTitle);
	
	/**
	 * 设置name
	 * 
	 * @param name
	 */
	public void setMenuId(String menuId);
	
}
