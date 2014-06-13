/**
 * 源程序名称：OraclePrivilegeAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.jres.database.oracle.service.IOraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;

/**
 * @author wangxh
 *
 */
public class OraclePrivilegeAdapter implements IOraclePrivilege {

	protected final OraclePrivilege privilege;
	
	
	public OraclePrivilegeAdapter(OraclePrivilege privilege) {
		super();
		this.privilege = privilege;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOraclePrivilege#getName()
	 */
	@Override
	public String getName() {
		return privilege.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOraclePrivilege#getType()
	 */
	@Override
	public String getType() {
		return privilege.getType();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOraclePrivilege#getDescription()
	 */
	@Override
	public String getDescription() {
		return privilege.getDecription();
	}

}
