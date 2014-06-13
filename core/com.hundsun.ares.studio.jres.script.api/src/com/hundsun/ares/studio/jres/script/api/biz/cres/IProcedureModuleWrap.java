/**
 * 源程序名称：IProcedureModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：qinyuan
 */
package com.hundsun.ares.studio.jres.script.api.biz.cres;

/**
 * @author qinyuan
 *
 */
public interface IProcedureModuleWrap {
	
	/**
	 * 获取模块下的所有的存储过程，可以指定是否递归获取子模块下的资源；如果传入否，则仅获取当前层级下的资源
	 * @param recursive
	 * @return
	 */
	IProcedureWrap[] getProcedures(boolean recursive);

}
