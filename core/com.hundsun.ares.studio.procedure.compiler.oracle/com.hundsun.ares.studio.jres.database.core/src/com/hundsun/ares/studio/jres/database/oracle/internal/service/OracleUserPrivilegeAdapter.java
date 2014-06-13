/**
 * 源程序名称：OracleUserPrivilegeAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import java.util.Collections;
import java.util.List;

import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.oracle.service.IOraclePrivilege;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;

/**
 * @author wangxh
 *
 */
public class OracleUserPrivilegeAdapter implements IOracleUserPrivilege {

	protected final OracleUserResourceData userResourceData;
	FastFindArrayList<String, IOracleUser> OracleUserList;
	FastFindArrayList<String, IOraclePrivilege>OraclePrivilegeList;
	
	
	public OracleUserPrivilegeAdapter(OracleUserResourceData userResourceData) {
		super();
		this.userResourceData = userResourceData;
	}

	
	/**
	 * @return the oracleUserList
	 */
	public FastFindArrayList<String, IOracleUser> getOracleUserList() {
		if(OracleUserList == null){
			OracleUserList = new FastFindArrayList<String, IOracleUser>(new IKeyProvider<String, IOracleUser>() {

				@Override
				public String getKey(IOracleUser obj) {
					return obj.getName();
				}
			});
			for( OracleUser user : userResourceData.getUsers()){
				IOracleUser oracleUser = new OracleUserAdapter(user);
				OracleUserList.add(oracleUser);
			}
		}
		return OracleUserList;
	}


	/**
	 * @return the oraclePrivilegeList
	 */
	public FastFindArrayList<String, IOraclePrivilege> getOraclePrivilegeList() {
		if(OraclePrivilegeList == null){
			OraclePrivilegeList = new FastFindArrayList<String, IOraclePrivilege>(new IKeyProvider<String, IOraclePrivilege>() {

				@Override
				public String getKey(IOraclePrivilege obj) {
					return obj.getName();
				}
			});
			for( OraclePrivilege privilege : userResourceData.getPrivileges()){
				IOraclePrivilege oraclePrivilege = new OraclePrivilegeAdapter(privilege);
				OraclePrivilegeList.add(oraclePrivilege);
			}
		}
		return OraclePrivilegeList;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege#getUser(java.lang.String)
	 */
	@Override
	public IOracleUser getUser(String name) {
		return getOracleUserList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege#getUserList()
	 */
	@Override
	public List<IOracleUser> getUserList() {
		return Collections.unmodifiableList(getOracleUserList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege#getPrivilege(java.lang.String)
	 */
	@Override
	public IOraclePrivilege getPrivilege(String name) {
		return getOraclePrivilegeList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUserPrivilege#getPrivilegeList()
	 */
	@Override
	public List<IOraclePrivilege> getPrivilegeList() {
		return Collections.unmodifiableList(getOraclePrivilegeList());
	}


	@Override
	public String getName() {
		return userResourceData.getName();
	}


	@Override
	public String getChineseName() {
		return userResourceData.getChineseName();
	}


	@Override
	public String getDescription() {
		return userResourceData.getDescription();
	}

}
