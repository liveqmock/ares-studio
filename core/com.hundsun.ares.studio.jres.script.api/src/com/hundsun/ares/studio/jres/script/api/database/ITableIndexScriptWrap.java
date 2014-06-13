/**
 * 源程序名称：ITableIndexScriptWrap.java
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
 * 数据库表索引
 * 
 * @author yanwj06282
 *
 */
public interface ITableIndexScriptWrap extends IScriptModelWrap{

	/**
	 * 获取表索引sql
	 * 
	 * @param type 数据类型
	 * @param prefix
	 * @param isUser 是否带上数据库用户前缀
	 * @return
	 */
	public String getSql(String type ,String prefix , boolean isUser);
	
	/**
	 * 获取标记
	 * 
	 * @return
	 */
	public String getMark();
	
	/**
	 * 是否唯一
	 * 
	 * @return
	 */
	public boolean isUnique();
	
	/**
	 * 是否聚簇
	 * 
	 * @return
	 */
	public boolean isCluster();
	
	/**
	 * 是否反转
	 * 
	 * @return
	 */
	public boolean isReverse();
	
	/**
	 * 获取索引中的数据列
	 * 
	 * @return
	 */
	public ITableColScriptWrap[] getTableIndexColumns();
	
	public void trim();

	
}
