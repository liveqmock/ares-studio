/**
 * 源程序名称：OracleUserAdapter.java
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
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;

/**
 * @author wangxh
 *
 */
public class OracleUserAdapter implements IOracleUser {

	protected final OracleUser user;
	
	private FastFindArrayList<String, IOraclePrivilege>privilegelist;
	
	
	public OracleUserAdapter(OracleUser user) {
		super();
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser#getName()
	 */
	@Override
	public String getName() {
		return user.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser#getDecription()
	 */
	@Override
	public String getDecription() {
		return user.getDecription();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser#getAttributes()
	 */
	@Override
	public String getAttributes() {
		return user.getAttributes();
	}

	
	/**
	 * @return the privilegelist
	 */
	public FastFindArrayList<String, IOraclePrivilege> getPrivilegelist() {
		if(privilegelist == null){
			privilegelist = new FastFindArrayList<String, IOraclePrivilege>(new IKeyProvider<String, IOraclePrivilege>() {

				@Override
				public String getKey(IOraclePrivilege obj) {
					return obj.getName();
				}
			});
			for( OraclePrivilege privilege: user.getPrivileges()){
				OraclePrivilegeAdapter oraclePrivilege = new OraclePrivilegeAdapter(privilege);
				privilegelist.add(oraclePrivilege);
			}
		}
		return privilegelist;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser#getPrivilegeList()
	 */
	@Override
	public List<? extends IOraclePrivilege> getPrivilegeList() {
		return Collections.unmodifiableList(getPrivilegelist());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleUser#getPrivilege(java.lang.String)
	 */
	@Override
	public IOraclePrivilege getPrivilege(String name) {
		return getPrivilegelist().find(name);
	}

	@Override
	public boolean isEnable() {
		return user.isEnable();
	}

}
