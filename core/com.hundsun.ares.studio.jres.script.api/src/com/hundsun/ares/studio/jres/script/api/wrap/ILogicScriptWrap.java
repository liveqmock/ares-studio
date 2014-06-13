/**
 * 源程序名称：ICRESScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicFunctionWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;

/**
 * @author sundl
 *
 */
public interface ILogicScriptWrap {

	/**
	 * 获取所有的LS
	 * @return
	 */
	ILogicServiceWrap[] getLogicServices();
	
	/**
	 * 获取所有的LF
	 * @return
	 */
	ILogicFunctionWrap[] getLogicFunctions();
	
	/**
	 * 根据中文名查找LS
	 * @return
	 */
	ILogicServiceWrap getLSByCName(String name);
	
	/**
	 * 根据中文名查找LF
	 * @return
	 */
	ILogicFunctionWrap getLFByCName(String name);
}
