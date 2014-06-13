/**
 * 源程序名称：DatabaseUserScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserPrivilegesScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;

/**
 * @author yanwj06282
 *
 */
public class DatabaseUserScriptWrapImpl extends ResourceWrapBase<OracleUserResourceData> implements
		IDatabaseUserScriptWrap {
	
	public DatabaseUserScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	@Override
	public IDatabaseUserItemScriptWrap[] getUsers() {
		List<IDatabaseUserItemScriptWrap> userItems = new ArrayList<IDatabaseUserItemScriptWrap>();
		for(OracleUser user : getOriginalInfo().getUsers()){
			userItems.add(new DatabaseUserItemScriptWrapImpl(user,resource));
		}
		
		return userItems.toArray(new IDatabaseUserItemScriptWrap[userItems.size()]);
	}

	@Override
	public IDatabaseUserPrivilegesScriptWrap[] getPrivileges() {
		List<IDatabaseUserPrivilegesScriptWrap> userItems = new ArrayList<IDatabaseUserPrivilegesScriptWrap>();
		for(OraclePrivilege privileges : getOriginalInfo().getPrivileges()){
			userItems.add(new DatabaseUserPrivilegesScriptWrapImpl(privileges,resource));
		}
		
		return userItems.toArray(new IDatabaseUserPrivilegesScriptWrap[userItems.size()]);
	}

	@Override
	public Class getInfoClass() {
		return OracleUserResourceData.class;
	}

}
