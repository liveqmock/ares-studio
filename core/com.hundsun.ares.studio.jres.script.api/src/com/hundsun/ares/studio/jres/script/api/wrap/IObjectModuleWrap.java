/**
 * 源程序名称：IObjectModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap;

/**
 * @author sundl
 *
 */
public interface IObjectModuleWrap {

	
	/**
	 * 获取模块下所有的对象资源，可以用参数指定是否递归查找子模块下的资源,
	 * @return
	 */
	IBizObjectWrap[] getObjects(boolean recursive);
}
