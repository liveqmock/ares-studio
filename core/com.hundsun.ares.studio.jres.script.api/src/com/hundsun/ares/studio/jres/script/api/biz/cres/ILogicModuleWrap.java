/**
 * 源程序名称：ILogicModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.biz.cres;


/**
 * @author sundl
 *
 */
public interface ILogicModuleWrap {

	/**
	 * 获取模块下的所有的逻辑服务资源，可以指定是否递归获取子模块下的资源；如果传入否，则仅获取当前层级下的资源
	 * @param recursive
	 * @return
	 */
	ILogicServiceWrap[] getLogicServices(boolean recursive);
	
	/**
	 * 获取模块下的所有的逻辑函数资源，可以指定是否递归获取子模块下的资源；如果传入否，则仅获取当前层级下的资源
	 * @param recursive
	 * @return
	 */
	ILogicFunctionWrap[] getLogicFunctions(boolean recursive);
}
