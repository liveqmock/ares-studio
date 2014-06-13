/**
 * 源程序名称：ITableSpaceItemScriptWrap.java
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
 * 数据库资源--用户资源--用户明细
 * 
 * @author yanwj06282
 *
 */
public interface IDatabaseUserItemScriptWrap extends IScriptModelWrap{

	/**
	 * 获取用户权限
	 * 
	 * @return
	 */
	public IDatabaseUserPrivilegesScriptWrap[] getPrivileges();
	
	/**
	 * 是否启用
	 * 
	 * @return
	 */
	public boolean isEnable();
	
	/**
	 * 获取文件属性
	 * 
	 * @return
	 */
	public String getAttributes();
	
	/**
	 * 获取说明信息
	 * 
	 * @return
	 */
	public String getDescription();
	
	/**
	 * 获取密码
	 * 
	 * @return
	 */
	public String getPassword();
	
	/**
	 * 获取默认表空间
	 * 
	 */
	public String getDefaultTableSpace();
}
