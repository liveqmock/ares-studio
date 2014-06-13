/**
 * 源程序名称：IOracleDBService.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.oracle.service;

import java.util.List;

import com.hundsun.ares.studio.jres.database.service.IDatabaseService;

/**
 * @author gongyf
 *
 */
public interface IOracleDBService extends IDatabaseService {
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.IDatabaseService#getTableList()
	 */
	@Override
	List<? extends IOracleTable> getTableList();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.IDatabaseService#getTable(java.lang.String)
	 */
	@Override
	IOracleTable getTable(String name);
	
	IOracleView getView(String name);
	
	List<? extends IOracleView> getViewList();
	
	
	List<? extends IOracleTrigger> getTriggerList();
	
	IOracleTrigger getTrigger(String name);
	
	List<? extends IOracleUserPrivilege> getUserList();
	
	IOracleUserPrivilege getUser(String name);
	
	List<? extends IOracleSpace> getSpaceList();
	
	IOracleSpace getSpace(String name);
	
	List<? extends IOracleSequence> getSequenceList();
	
	IOracleSequence getSequence(String name);
	
}
