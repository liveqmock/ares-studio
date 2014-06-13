/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;

/**
 * @author yanwj06282
 *
 */
public abstract class DatabaseResScriptWrapImpl extends ResourceWrapBase<DatabaseResourceData> implements
		IDatabaseResScriptWrap {

	public DatabaseResScriptWrapImpl(IARESResource resource) {
		super(resource);
	}
	
	@Override
	public String getName(String prefix) {
		String returnName = resource.getName();
		if (StringUtils.isNotBlank(prefix)) {
			returnName = prefix + returnName;
		}
		return returnName;
	}
	
	public String getDbuser(String prefix) {
		TableSpace ts = getTableSpaceInfo(prefix);
		if (ts != null) {
			return ts.getUser();
		}
		return StringUtils.EMPTY;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap#getTableSpaceLogicName(java.lang.String)
	 */
	@Override
	public String getTableSpaceLogicName(String prefix) {
		TableSpace ts = getTableSpaceInfo(prefix);
		if (ts != null) {
			return ts.getLogicName();
		}
		return StringUtils.EMPTY;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap#getTableSpace(java.lang.String)
	 */
	@Override
	public String getTableSpace(String prefix) {
		TableSpace ts = getTableSpaceInfo(prefix);
		if (ts != null) {
			return ts.getName();
		}
		return StringUtils.EMPTY;
	}

	public String getDbuserFileName(String prefix) {
		String userName = getDbuser(prefix);

		try {
			IARESResource dbuser = resource.getARESProject().findResource(
					"dbuser", "dbuser");
			if (dbuser != null) {
				OracleUserResourceData userInfo = dbuser.getInfo(OracleUserResourceData.class);
				
				for(OracleUser user : userInfo.getUsers()){
					if (StringUtils.equals(userName, user.getName())) {
						return user.getAttributes();
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	
	}
	
	@Override
	public Class getInfoClass() {
		return DatabaseResourceData.class;
	}
	
	protected TableSpace getTableSpaceInfo(String prefix){

		int tableType = transTableType(prefix);
		String space = IJSONUtil.instance.getStringFromJSON(toJSON(),
				"Oracle_space");
		// oracle 表空间
		try {
			OracleSpaceResourceData dbobjectinfo = getDBObjectInfo();
			if (dbobjectinfo == null) {
				return null;
			}
			if (tableType != 0) {
				EList<TableSpaceRelation> relations = dbobjectinfo.getRelations();
				for (TableSpaceRelation relation : relations) {
					if (StringUtils.isNotBlank(relation.getMainSpace())
							&& StringUtils.equals(space, relation.getMainSpace())) {
						if (tableType == 1) {
							space = IJSONUtil.instance.getStringFromJSON(
									relation.toJSON(), "tschouse_hisSpace");
							break;
						}
						if (tableType == 2) {
							space = IJSONUtil.instance.getStringFromJSON(
									relation.toJSON(), "tschouse_fileSpace");
							break;
						}
					}
				}
			}
			EList<TableSpace> spaces = dbobjectinfo.getSpaces();
			for (TableSpace tableSpace : spaces) {
				/*if (StringUtils.isNotBlank(tableSpace.getUser())
						&& StringUtils.equals(space, tableSpace.getName())) {*/
				//2014-05-05 modified by zhuyf 获取所需表空间不需要对用户名进行非空判断
				if (StringUtils.equals(space, tableSpace.getName())) {
					return tableSpace;
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected OracleSpaceResourceData getDBObjectInfo () throws ARESModelException{
		IARESResource dbobjectres = resource.getARESProject().findResource(
				"dbobject", "dbobject");
		if (dbobjectres != null) {
			return dbobjectres
			.getInfo(OracleSpaceResourceData.class);
		}
		return null;
	}
	
	/**
	 * 普通表 = 0 历史表 = 1 归档表 = 2
	 * 
	 * @param prefix
	 * @return
	 */
	private int transTableType(String prefix) {
		if (StringUtils.equals(prefix, "his_")) {
			return 1;
		} else if (StringUtils.equals(prefix, "fil_")) {
			return 2;
		}
		return 0;
	}
	
	/**
	 * 获取说明
	 */
	public String getDescription(){
		return StringUtils.defaultString(getOriginalInfo().getDescription());
	}
	
	/**
	 * 设置中文名
	 */
	public void setChineseName(String cname){
		getOriginalInfo().setChineseName(cname);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IDatabaseResScriptWrap#getVesion()
	 */
	@Override
	public String getVesion() {
		//找出最新的版本号

		List<RevisionHistory> hises = (getOriginalInfo()).getHistories();
		List<RevisionHistory> tempHis = (List<RevisionHistory>) EcoreUtil.copyAll(hises);
		Collections.sort(tempHis, new Comparator<RevisionHistory>(){
			@Override
			public int compare(RevisionHistory o1, RevisionHistory o2) {
				String d1 = ((RevisionHistory)o1).getVersion();
				String d2 = ((RevisionHistory)o2).getVersion();
				
				if (compareDate(d1, d2)){
					return -1;
				}else {
					return 1;
				}
			}
			
			private boolean compareDate(String v1 , String v2){
				if (StringUtils.equals(v1, v2)) {
					return true;
				}
				String[] v1s = StringUtils.split(v1, ".");
				String[] v2s = StringUtils.split(v2, ".");
				if (v1 == null || v2 == null) {
					return false;
				}
				try {
					String[] tempVs = null;
					if (v1s.length > v2s.length) {
						tempVs = new String[v1s.length];
						System.arraycopy(v2s, 0, tempVs, 0, v2s.length);
						for (int i = 0; i < tempVs.length - v2s.length; i++) {
							tempVs[v2s.length + i] = "0";
						}
						return compareInt(v1s, tempVs);
					} else {
						tempVs = new String[v2s.length];
						System.arraycopy(v1s, 0, tempVs, 0, v1s.length);
						for (int i = 0; i < tempVs.length - v1s.length; i++) {
							tempVs[v1s.length + i] = "0";
						}
						return compareInt(tempVs, v2s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
			
			private boolean compareInt(String[] v1, String[] v2) {
				for (int i = 0; i < v1.length; i++) {
					int v1i = Integer.parseInt(v1[i]);
					int v2i = Integer.parseInt(v2[i]);
					if (v1i > v2i) {
						return true;
					} else if (v1i < v2i) {
						return false;
					}
				}
				return false;
			}
			
		});
		if (hises.size() > 0) {
			return tempHis.get(0).getVersion();
		}else {
			//2013年5月24日14:43:41 如果没有修改记录信息，则取所在子系统当前版本号+1
			
			IARESModule topModule = null; 
			if (resource == null) {
				topModule = null; 
			} else {
				String rootType = resource.getRoot().getType(); 
				if (ARESElementUtil.isDatabaseRoot(rootType)) {
					topModule = ARESElementUtil.getTopModule(resource);
				} else if (ARESElementUtil.isMetadataRoot(rootType)) {
					// topModule为null的效果就是不计算模块
					topModule = null;
				} else {
					topModule = resource.getModule();
				}
			}
			
			// 当前已经保存的资源中的最大版本
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(topModule);
			if (his != null) {
				String currentVersion = his.getVersion();
				// 项目属性
				String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(resource.getARESProject());
				
				// 找上述3者最大值
				String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, projectVersion));
				// 第一次找不到任何记录的时候
				if (StringUtils.isEmpty(versionStr)) {
					versionStr = "1.0.0.0";
				} 
				try{
					return RevisionHistoryUtil.increase(versionStr);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "1.0.0.0";
	}
	
	
	
}
