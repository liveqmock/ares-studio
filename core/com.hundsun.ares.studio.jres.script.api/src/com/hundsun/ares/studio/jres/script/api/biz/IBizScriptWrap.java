package com.hundsun.ares.studio.jres.script.api.biz;

/**
 * 业务逻辑相关的API接口
 * @author sundl
 *
 */
public interface IBizScriptWrap {

	/**
	 * 获取所有的服务资源
	 * @return
	 */
	IBizServiceWrap[] getServices();
	
	/**
	 * 根据<b>全名</b>查找资源
	 * @param name  带模块前缀的全名
	 * @return
	 */
	IBizServiceWrap getServiceByName(String name);
	
	/**
	 * 根据<b>中文</b>名查找资源，这个方法返回找到的第一个符合给定的中文名的资源（如果有的话）
	 * @param name 中文名
	 * @return
	 */
	IBizServiceWrap getServiceByCName(String name);
	
	/**
	 * 获取子系统下的所有的服务资源
	 * @param subsysName
	 * @return
	 */
	IBizServiceWrap[] getServicesBySubsys(String subsysName);
	
	/**
	 * 获取模块下的所有的服务资源
	 * @param moduleName
	 * @return
	 */
	IBizServiceWrap[] getServicesByModule(String moduleName);
	
}
