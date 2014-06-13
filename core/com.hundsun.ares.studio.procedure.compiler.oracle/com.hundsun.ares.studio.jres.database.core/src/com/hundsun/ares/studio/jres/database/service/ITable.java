/**
 * 源程序名称：ITable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.service;

import java.util.List;

/**
 * @author gongyf
 *
 */
public interface ITable {
	
	String getName();
	String getChineseName();
	String getDescription();
	
	List<? extends ITableColumn> getColumnList();
	ITableColumn getColum(String name);
	
	List<? extends ITableIndex> getIndexList();
	ITableIndex getIndex(String name);
}
