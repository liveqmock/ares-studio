/**
 * 源程序名称：ILogicServiceWrap.java
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
public interface ILogicServiceWrap extends ICRESBizWrap {
	
	/**
	 * 获取超时时间
	 * @return
	 */
	String getTimeOut();

	/**
	 * 根据参数ID，删除内部变量
	 * 
	 * @param id
	 */
	public void deleteInternalVar(String id);
	
}
