/**
 * 源程序名称：BasicOracleDBService.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleSequence;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTable;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTrigger;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleView;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author gongyf
 *
 */
public class BasicOracleDBService implements IOracleDBService {

	final private IARESProject project;
	private FastFindArrayList<String, IOracleTable> cachedOracleTableList;
	private FastFindArrayList<String, IOracleTrigger> cachedOracleTriggerList;
	private FastFindArrayList<String, IOracleUserPrivilege> cachedOracleUserList;
	private FastFindArrayList<String, IOracleView> cachedOracleViewList;
	private FastFindArrayList<String, IOracleSpace> cachedOracleSpaceList;
	private FastFindArrayList<String, IOracleSequence>cachedOracleSequeneceList;
	
	
	/**
	 * @return the cachedOracleSpaceList
	 */
	public FastFindArrayList<String, IOracleSpace> getCachedOracleSpaceList() {
		if(cachedOracleSpaceList == null){
			cachedOracleSpaceList = new FastFindArrayList<String, IOracleSpace>(new IKeyProvider<String, IOracleSpace>() {

				@Override
				public String getKey(IOracleSpace obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Space, true);
			for(ReferenceInfo referenceInfo : infoList){
				TableSpace ts = (TableSpace)referenceInfo.getObject();
				OracleSpaceResourceData spaceResourceData = null;
				if (ts.eContainer() instanceof OracleSpaceResourceData) {
					spaceResourceData = (OracleSpaceResourceData) ts.eContainer();
				}
				
				if (spaceResourceData == null || StringUtils.isBlank(spaceResourceData.getName()) ) {
					continue;
				}
				else{
					IOracleSpace space = new OracleSpaceAdapter(spaceResourceData);
					cachedOracleSpaceList.add(space);
				}
			}
		}
		return cachedOracleSpaceList;
	}


	/**
	 * @return the cachedOracleSequeneceList
	 */
	public FastFindArrayList<String, IOracleSequence> getCachedOracleSequeneceList() {
		if(cachedOracleSequeneceList == null){
			cachedOracleSequeneceList = new FastFindArrayList<String, IOracleSequence>(new IKeyProvider<String, IOracleSequence>() {

				@Override
				public String getKey(IOracleSequence obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Sequence, true);
			for(ReferenceInfo referenceInfo : infoList){
				SequenceResourceData sequenceResourceData = (SequenceResourceData)referenceInfo.getObject();
				String name = sequenceResourceData.getName();
				IOracleSequence sequence = new OracleSequenceAdapter(sequenceResourceData);
				if (StringUtils.isBlank(name)) {
					continue;
				}
				else{
					cachedOracleSequeneceList.add(sequence);
				}
			}
		}
		return cachedOracleSequeneceList;
	}


	/**
	 * @return the cachedOracleTableList
	 */
	public FastFindArrayList<String, IOracleTable> getCachedOracleTableList() {
		if(cachedOracleTableList == null){
			cachedOracleTableList = new FastFindArrayList<String, IOracleTable>(new IKeyProvider<String, IOracleTable>() {

				@Override
				public String getKey(IOracleTable obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.Table, true);
			for(ReferenceInfo referenceInfo : infoList){
				TableResourceData tableResourceData = (TableResourceData)referenceInfo.getObject();
				String name = tableResourceData.getName();
				IOracleTable table = new OracleTableAdapter(tableResourceData,project);
				if (StringUtils.isBlank(name)) {
					continue;
				}
				else{
					cachedOracleTableList.add(table);
				}
			}
		}
		return cachedOracleTableList;
	}


	/**
	 * @return the cachedOracleTriggerList
	 */
	public FastFindArrayList<String, IOracleTrigger> getCachedOracleTriggerList() {
		if(cachedOracleTriggerList == null){
			cachedOracleTriggerList = new FastFindArrayList<String, IOracleTrigger>(new IKeyProvider<String, IOracleTrigger>() {

				@Override
				public String getKey(IOracleTrigger obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.Trigger, true);
			for(ReferenceInfo referenceInfo : infoList){
				TriggerResourceData triggerResourceData = (TriggerResourceData)referenceInfo.getObject();
				String name = triggerResourceData.getName();
				IOracleTrigger trigger = new OracleTriggerAdapter(triggerResourceData);
				if (StringUtils.isBlank(name)) {
					continue;
				}
				else{
					cachedOracleTriggerList.add(trigger);
				}
			}
		}
		return cachedOracleTriggerList;
	}


	/**
	 * @return the cachedOracleUserList
	 */
	public FastFindArrayList<String, IOracleUserPrivilege> getCachedOracleUserList() {
		if(cachedOracleUserList == null){
			cachedOracleUserList = new FastFindArrayList<String, IOracleUserPrivilege>(new IKeyProvider<String, IOracleUserPrivilege>() {

				@Override
				public String getKey(IOracleUserPrivilege obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IOracleRefType.User, true);
			for(ReferenceInfo referenceInfo : infoList){
				OracleUserResourceData userResourceData = (OracleUserResourceData)referenceInfo.getObject();
				String name = userResourceData.getName();
				IOracleUserPrivilege userPrivilege = new OracleUserPrivilegeAdapter(userResourceData);
				if (StringUtils.isBlank(name)) {
					continue;
				}
				else{
					cachedOracleUserList.add(userPrivilege);
				}
			}
		}
		return cachedOracleUserList;
	}


	/**
	 * @return the cachedOracleViewList
	 */
	public FastFindArrayList<String, IOracleView> getCachedOracleViewList() {
		if(cachedOracleViewList == null){
			cachedOracleViewList = new FastFindArrayList<String, IOracleView>(new IKeyProvider<String, IOracleView>() {

				@Override
				public String getKey(IOracleView obj) {
					return obj.getName();
				}
			});
			List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, IDatabaseRefType.View, true);
			for(ReferenceInfo referenceInfo : infoList){
				ViewResourceData viewResourceData = (ViewResourceData)referenceInfo.getObject();
				String name = viewResourceData.getName();
				IOracleView table = new OracleViewAdapter(viewResourceData);
				if (StringUtils.isBlank(name)) {
					continue;
				}
				else{
					cachedOracleViewList.add(table);
				}
			}
		}
		return cachedOracleViewList;
	}


	/**
	 * 
	 */
	public BasicOracleDBService(IARESProject project) {
		super();
		this.project = project;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getTableList()
	 */
	@Override
	public List<IOracleTable> getTableList() {
		return Collections.unmodifiableList(getCachedOracleTableList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getTable(java.lang.String)
	 */
	@Override
	public IOracleTable getTable(String name) {
		return getCachedOracleTableList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getTriggerList()
	 */
	@Override
	public List<IOracleTrigger> getTriggerList() {
		return Collections.unmodifiableList(getCachedOracleTriggerList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getTrigger(java.lang.String)
	 */
	@Override
	public IOracleTrigger getTrigger(String name) {
		return getCachedOracleTriggerList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getUserList()
	 */
	@Override
	public List<IOracleUserPrivilege> getUserList() {
		return Collections.unmodifiableList(getCachedOracleUserList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getUser(java.lang.String)
	 */
	@Override
	public IOracleUserPrivilege getUser(String name) {
		return getCachedOracleUserList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getView(java.lang.String)
	 */
	@Override
	public IOracleView getView(String name) {
		return getCachedOracleViewList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService#getViewList()
	 */
	@Override
	public List<IOracleView> getViewList() {
		return Collections.unmodifiableList(getCachedOracleViewList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.service.IDataService#refresh()
	 */
	@Override
	public void refresh() {
		cachedOracleTableList = null;
		cachedOracleTriggerList = null;
		cachedOracleUserList = null;
		cachedOracleViewList = null;
		cachedOracleSpaceList = null;
		cachedOracleSequeneceList = null;
	}


	@Override
	public List<IOracleSpace> getSpaceList() {
		return Collections.unmodifiableList(getCachedOracleSpaceList());
	}


	@Override
	public IOracleSpace getSpace(String name) {
		return getCachedOracleSpaceList().find(name);
	}


	@Override
	public List<IOracleSequence> getSequenceList() {
		return Collections.unmodifiableList(getCachedOracleSequeneceList());
	}


	@Override
	public IOracleSequence getSequence(String name) {
		return getCachedOracleSequeneceList().find(name);
	}

}
