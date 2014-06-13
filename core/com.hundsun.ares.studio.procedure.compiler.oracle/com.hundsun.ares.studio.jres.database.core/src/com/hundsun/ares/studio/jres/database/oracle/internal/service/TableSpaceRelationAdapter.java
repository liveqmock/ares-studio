/**
 * 源程序名称：TableSpaceRelationAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.jres.database.oracle.service.ITableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;

/**
 * @author wangxh
 *
 */
public class TableSpaceRelationAdapter implements ITableSpaceRelation {

	protected final TableSpaceRelation tableSpaceRelation;
	
	
	public TableSpaceRelationAdapter(TableSpaceRelation tableSpaceRelation) {
		super();
		this.tableSpaceRelation = tableSpaceRelation;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.ITableSpaceRelation#getMainSpace()
	 */
	@Override
	public String getMainSpace() {
		return tableSpaceRelation.getMainSpace();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.ITableSpaceRelation#getIndexSpace()
	 */
	@Override
	public String getIndexSpace() {
		return tableSpaceRelation.getIndexSpace();
	}

}
