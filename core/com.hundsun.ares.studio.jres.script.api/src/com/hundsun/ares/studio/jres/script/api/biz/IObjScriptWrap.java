/**
 * 源程序名称：IObjScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.biz;

/**
 * 提供查询对象接口，为了独立和复用对象资源，对象的API接口实现独立实现
 * @author sundl
 *
 */
public interface IObjScriptWrap {
	/**
	 * 获取所有的对象资源
	 * @return 当前项目下所有的对象资源
	 */
	IBizObjectWrap[] getObjects();
	
	/**
	 * 查找对象资源
	 * @param name 带模块前缀的全名
	 * @return
	 */
	IBizObjectWrap getObjectByName(String name);
	
	/**
	 * 根据子系统名获取下面的所有对象资源
	 * @param subsys
	 * @return
	 */
	IBizObjectWrap[] getObjectsBySubsys(String subsysName);
	
	/**
	 * 根据包名获取下面的所有对象资源
	 * @param moduleName  模块名字，如果存在多级，用“.”分隔
	 * @return
	 */
	public IBizObjectWrap[] getObjectsByModule(String moduleName);
}
