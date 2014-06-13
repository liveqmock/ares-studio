/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

/**
 * 基础数据对象
 * 
 * @author lvgao
 *
 */
public interface IBasicdataScriptWrap {

	public static final String COMMONDATA_TYPE = "commondata";
	
	/**
	 * 获取所有二维表基础数据
	 * @return
	 */
	public ISingleTableScriptWrap[] getAllTableBasicData();
	
	/**
//	 * 获取所有主从表基础数据
	 * @return
	 */
	public IMasterSlaveTableScriptWrap[] getAllMasterSlaveTableBasicData();
	
	/**
	 * 获取所有主从关联表基础数据
	 * @return
	 */
	public IMasterSlaveLinkTableScriptWrap[] getAllMasterSlaveLinkTableBasicData();
	
	/**
	 * 根据子系统名获取二维表基础数据
	 * @param subsysName
	 * @return
	 */
	public ISingleTableScriptWrap[] getTableBasicDataBySubsys(String subsysName);
	
	/**
	 * 根据模块名获取二维表基础数据
	 * @param moduleName 模块名字，如果存在多级，用“.”分隔
	 * @return
	 */
	public ISingleTableScriptWrap[] getTableBasicDataByModule(String moduleName);
	
	/**
	 * 根据子系统名获取主从表基础数据
	 * @param subsysName
	 * @return
	 */
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataBySubsys(String subsysName);
	
	/**
	 * 根据模块名获取主从表基础数据
	 * @param moduleName 模块名字，如果存在多级，用“.”分隔
	 * @return
	 */
	public IMasterSlaveTableScriptWrap[] getMasterSlaveTableBasicDataByModule(String moduleName);
	
	/**
	 * 根据子系统名获取主从信息表基础数据
	 * @param subsysName
	 * @return
	 */
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataBySubsys(String subsysName);
	
	/**
	 * 根据模块名获取主从信息表基础数据
	 * @param moduleName 模块名字，如果存在多级，用“.”分隔
	 * @return
	 */
	public IMasterSlaveLinkTableScriptWrap[] getMasterSlaveLinkTableBasicDataByModule(String moduleName);
	
	/**
	 * 通过名称查找二维表
	 * @param name
	 * @return
	 */
	public ISingleTableScriptWrap getTableBasicDataByName(String name);
	
	/**
	 * 通过名称查找主从表
	 * @param name
	 * @return
	 */
	public IMasterSlaveTableScriptWrap getMasterSlaveTableBasicDataByName(String name);
	
	/**
	 * 通过名称查找主从关联表
	 * @param name
	 * @return
	 */
	public IMasterSlaveLinkTableScriptWrap getMasterSlaveLinkTableBasicDataByName(String name);
	
	/**
	 * 获取所有元数据基础数据
	 * @return
	 */
	public ISingleTableScriptWrap[] getAllMetaDataBasicData();
	
	/**
	 * 通过名称查找元数据基础数据
	 * @param name
	 * @return
	 */
	public ISingleTableScriptWrap getMetaDataBasicDataByName(String name);
	
}
