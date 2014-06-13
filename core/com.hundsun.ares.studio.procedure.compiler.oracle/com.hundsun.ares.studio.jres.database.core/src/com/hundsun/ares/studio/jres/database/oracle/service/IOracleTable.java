/**
 * 源程序名称：IOracleTable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.oracle.service;

import java.util.List;

import com.hundsun.ares.studio.jres.database.service.ITable;

/**
 * @author gongyf
 *
 */
public interface IOracleTable extends ITable {
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getColum(java.lang.String)
	 */
	@Override
	public IOracleTableColumn getColum(String name);
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getColumnList()
	 */
	@Override
	public List<? extends IOracleTableColumn> getColumnList();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getIndex(java.lang.String)
	 */
	@Override
	public IOracleTableIndex getIndex(String name);
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getIndexList()
	 */
	@Override
	public List<? extends IOracleTableIndex> getIndexList();
	
	String getSpace();
	
	String getIndexSpace();
	
//	public IOracleTableProperty getProperty();
}
