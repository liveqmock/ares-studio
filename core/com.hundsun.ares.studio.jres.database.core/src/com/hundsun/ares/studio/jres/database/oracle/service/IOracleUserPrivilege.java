/**
 * 源程序名称：IOracleUserPrivilege.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.service;

import java.util.List;

/**
 * @author wangxh
 *
 */
public interface IOracleUserPrivilege {
	String getName();
	String getChineseName();
	String getDescription();
	
	public IOracleUser getUser(String name);
	public List<IOracleUser> getUserList();
	
	public IOraclePrivilege getPrivilege(String name);
	public List<IOraclePrivilege> getPrivilegeList();
}
