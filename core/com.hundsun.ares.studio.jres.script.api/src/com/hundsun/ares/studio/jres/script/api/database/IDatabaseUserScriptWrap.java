/**
 * 源程序名称：ITableSpaceScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 数据库资源--用户资源
 * 
 * @author yanwj06282
 *
 */
public interface IDatabaseUserScriptWrap extends IScriptModelWrap{

	/**
	 * 数据库用户条目
	 * 
	 * @return
	 */
	public IDatabaseUserItemScriptWrap[] getUsers();
	
	/**
	 * 数据库用户权限条目
	 * 
	 * @return
	 */
	public IDatabaseUserPrivilegesScriptWrap[] getPrivileges();
	
}
