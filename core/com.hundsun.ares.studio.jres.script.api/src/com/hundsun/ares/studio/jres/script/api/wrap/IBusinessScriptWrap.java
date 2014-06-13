/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

/**
 * 业务逻辑，对应工程结构中的业务逻辑模块根，可以直接或者间接的获取业务逻辑下的所有信息
 * 
 * @author yanwj06282
 *
 */
public interface IBusinessScriptWrap {

	/**
	 * 获取所有的服务资源
	 * 
	 * @return
	 */
	public IBusinessResScriptWrap[] getAllService();
	
}
