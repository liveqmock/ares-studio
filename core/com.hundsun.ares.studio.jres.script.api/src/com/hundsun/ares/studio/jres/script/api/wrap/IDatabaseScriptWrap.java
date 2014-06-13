/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ISequenceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableSpaceScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITriggerScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IViewScriptWrap;

/**
 * 数据库，对应工程结构中的数据库模块根，可以直接或者间接的获取数据库下的所有信息
 * 
 * @author lvgao
 *
 */
public interface IDatabaseScriptWrap {

	/**
	 * 获取所有的数据库资源
	 * 
	 * @return
	 */
	public IDatabaseResScriptWrap[] getAllDatabaseResources();
	
	/**
	 * 获取指定子系统下的所有资源
	 * 
	 * @param moduleName
	 * @return
	 */
	public IDatabaseResScriptWrap[] getAllDatabaseResourcesBySubsys(String subsysName);
	
	/**
	 * 获取指定模块下的数据库资源
	 * 
	 * @param moduleName 模块名字，如果存在多级，用“.”分隔
	 * @return
	 */
	public IDatabaseResScriptWrap[] getAllDatabaseResourcesByModule(String moduleName);
	
	/**
	 * 获取指定子系统下的修订记录
	 * 
	 * @param subsysName
	 * @return
	 */
	public String getAllHistoriesCommentBySubsys(String subsysName , String content);
	
	/**
	 * 获取指定模块下的修订记录
	 * 
	 * @param moduleName
	 * @return
	 */
	public String getAllHistoriesCommentByModule(String moduleName , String content);
	
	/**
	 * 获取指定子系统下的修订记录对象
	 * 
	 * @param subsysName
	 * @return
	 */
	public ITableRevHistoryScriptWrap[] getAllHistoriesBySubsys(String subsysName);
	
	/**
	 * 获取指定模块下的修订记录对象
	 * 
	 * @param moduleName
	 * @return
	 */
	public ITableRevHistoryScriptWrap[] getAllHistoriesByModule(String moduleName);
	
	/**
	 * 获取所有表
	 * @return
	 */
	public ITableScriptWrap[] getAllTable();
	
	/**
	 * 获取所有视图
	 * @return
	 */
	public IViewScriptWrap[] getAllView();
	
	/**
	 * 获取所有序列
	 * 
	 * @return
	 */
	public ISequenceScriptWrap[] getAllSequence();
	
	/**
	 * 获取所有触发器
	 * 
	 * @return
	 */
	public ITriggerScriptWrap[] getAllTrigger();
	
	/**
	 * 通过名字获取表
	 * @param name
	 * @return
	 */
	public ITableScriptWrap[] getTableByName(String name);
	
	/**
	 * 通过名字获取视图
	 * @param name
	 * @return
	 */
	public IViewScriptWrap[] getViewByName(String name);
	
	/**
	 * 获取表空间对象
	 * 
	 * @return
	 */
	public ITableSpaceScriptWrap getTableSpace();
	
	/**
	 * 获取用户和权限对象
	 * 
	 * @return
	 */
	public IDatabaseUserScriptWrap getDBUser();
	
}
