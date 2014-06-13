/**
 * 源程序名称：ISequenceScriptWrap.java
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
 * 序列封装对象
 * 
 * @author yanwj06282
 *
 */
public interface ISequenceScriptWrap extends IDatabaseResScriptWrap {

	/**
	 * 序列名
	 * 
	 * @return
	 */
	public String getSeqName();
	
	/**
	 * 是否存在历史表
	 * 
	 * @return
	 */
	public boolean isGenHisTable();
	
	/**
	 * 获得表空间
	 * 
	 * @return
	 */
	public String getTableSpace();
	
	/**
	 * 是否循环
	 * 
	 * @return
	 */
	public boolean isCycle();
	
	/**
	 * 是否用户缓存
	 * 
	 * @return
	 */
	public boolean isUseCache();
	
	/**
	 * 缓存
	 * 
	 * @return
	 */
	public String getCache();
	
	/**
	 * 开始
	 * @return
	 */
	public String getStart();
	
	/**
	 * 表名
	 * @return
	 */
	public String getTableName();
	
	/**
	 * 增量
	 * @return
	 */
	public String getIncrement();
	
	/**
	 * 最大值
	 * @return
	 */
	public String getMaxValue();
	
	/**
	 * 起始值
	 * @return
	 */
	public String getMinValue();
	
}
