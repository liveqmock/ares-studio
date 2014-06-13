/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 数据库资源对象
 * 
 * @author yanwj06282
 *
 */
public interface IDatabaseResScriptWrap extends IScriptModelWrap{

	/**
	 * 获取中文名
	 * 
	 * @return
	 */
	public String getChineseName();
	
	/**
	 * 获取对象号
	 * 
	 * @return
	 */
	public String getObjectId();
	
	/**
	 * 获得名字（prefix，前缀标志，用于识别当前表、历史表）
	 * 
	 * @param prefix
	 * @return
	 */
	public String getName(String prefix);
	
	/**
	 * 获取数据库用户
	 * 
	 * @param prefix
	 * @return
	 */
	public String getDbuser(String prefix);
	
	/**
	 * 获取表空间对应的逻辑名，按逻辑名存储SQL
	 * @param prefix
	 * @return
	 */
	public String getTableSpaceLogicName(String prefix);
	
	/**
	 * 	获得表空间
	 * @param prefix
	 * @return
	 */
	public String getTableSpace(String  prefix);
	
	/**
	 * 获取用户文件（prefix，前缀标志，用于识别当前表、历史表）
	 * 
	 * @param prefix
	 * @return
	 */
	public String getDbuserFileName(String prefix);
	
	/**
	 * 获取修订记录
	 * 
	 * @return
	 */
	public IRevHistoryScriptWrap[] getHistories();
	
	/**
	 * 设置对象号
	 * 
	 * @param objectId
	 */
	public void setObjectId(String objectId);
	
	/**
	 * 获取说明信息
	 * @return
	 */
	public String getDescription();
	
	/**
	 * 设置中文名
	 * @param cname
	 */
	public void setChineseName(String cname);
	
	/**
	 * 获取版本号，与资源中计算规则保持一致。
	 * @return
	 */
	public String getVesion();
	
}
