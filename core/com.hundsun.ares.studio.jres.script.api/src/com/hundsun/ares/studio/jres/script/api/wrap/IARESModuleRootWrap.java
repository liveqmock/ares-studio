/**
 * 源程序名称：IARESModuleRootWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

/**
 * 模块根
 * @author sundl
 *
 */
public interface IARESModuleRootWrap {

	/**
	 * 获取这个模块根下的所有子系统（第一级模块）
	 */
	IARESModuleWrap[] getSubSystems();
	
	/**
	 * 根据模块长名，获取模块根下面的模块
	 * 
	 * @param moduleName
	 * @return
	 */
	public IARESModuleWrap getSubModuleByName(String moduleName);
	
}
