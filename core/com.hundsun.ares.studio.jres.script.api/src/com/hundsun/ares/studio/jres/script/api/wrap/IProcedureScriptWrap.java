/**
 * 源程序名称：IProcedureScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：qinyuan
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureWrap;

/**
 * @author qinyuan
 *
 */
public interface IProcedureScriptWrap {
	
	/**
	 * 获取所有存储过程
	 * @return
	 */
	IProcedureWrap[] getProcedures();
	
	
	/**
	 * 根据中文名获取存储过程
	 * @return
	 */
	IProcedureWrap getProcedureByCName(String name);
}
