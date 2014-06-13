/**
 * 源程序名称：ITableScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;


/**
 * 数据库试图
 * 
 * @author yanwj06282
 *
 */
public interface IViewScriptWrap  extends IDatabaseResScriptWrap{

	/**
	 * 获取视图名
	 * 
	 * @return
	 */
	public String getViewName();
	
	/**
	 * 是否生成历史表
	 * 
	 * @return
	 */
	public boolean isGenHisTable();
	
	/**
	 * 返回类型
	 * @return
	 */
	public String getTableType();
	
	/**
	 * 	获得表空间
	 * @param prefix
	 * @return
	 */
	public String getTableSpace(String  prefix);
	
	
	/**
	 * 获得sql语句
	 * @param prefix
	 * @return
	 */
	public String getSql();
	
}
