/**
 * 源程序名称：DatabaseUserItemScriptWrapImpl.java
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
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserPrivilegesScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class DatabaseUserItemScriptWrapImpl extends CommonScriptWrap<OracleUser> implements
		IDatabaseUserItemScriptWrap {

	public DatabaseUserItemScriptWrapImpl(OracleUser user ,IARESResource resource) {
		super(user ,resource);
	}

	@Override
	public boolean isEnable() {
		return getOriginalInfo().isEnable();
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
	public String getAttributes() {
		return getOriginalInfo().getAttributes();
	}

	@Override
	public String getDescription() {
		return getOriginalInfo().getDecription();
	}

	@Override
	public String getPassword() {
		return getOriginalInfo().getPassword();
	}
	
	@Override
	public String getDefaultTableSpace() {
		return getOriginalInfo().getDefaultTableSpace();
	}
	
}
