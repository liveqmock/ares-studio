/**
 * 源程序名称：DatabaseUserPrivilegesScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.script.api.database.IDatabaseUserPrivilegesScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class DatabaseUserPrivilegesScriptWrapImpl extends CommonScriptWrap<OraclePrivilege>
		implements IDatabaseUserPrivilegesScriptWrap {

	public DatabaseUserPrivilegesScriptWrapImpl(OraclePrivilege privileges ,IARESResource resource) {
		super(privileges ,resource);
	}

}
