/**
 * 源程序名称：ICRESBizWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.biz.cres;

import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;

/**
 * 
 * @author sundl
 *
 */
public interface ICRESBizWrap extends IBizServiceWrap {

	/**
	 * 获取内部变量列表
	 * @return
	 */
	IInternalVarWrap[] getInternalVars();
	
	/**
	 * 获取源码字符串
	 * @return
	 */
	String getCode();
}
