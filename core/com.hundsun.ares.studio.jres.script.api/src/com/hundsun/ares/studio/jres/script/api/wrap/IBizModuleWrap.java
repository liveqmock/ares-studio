/**
 * 源程序名称：IBizModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;

/**
 * @author sundl
 *
 */
public interface IBizModuleWrap {
	
	/**
	 * 获取所有的服务资源; 可以用参数指定是否递归查找子模块下的资源，如果参数为false则只返回直接包含
	 * 在当前模块下的资源；如果为true，则递归查找子模块，返回所有资源
	 * @return
	 */
	IBizServiceWrap[] getServices(boolean recursive);
	
}
