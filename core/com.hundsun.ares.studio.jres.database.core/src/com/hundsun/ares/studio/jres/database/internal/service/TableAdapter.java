/**
 * 源程序名称：TableAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.internal.service;

import java.util.Collections;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.service.ITable;
import com.hundsun.ares.studio.jres.database.service.ITableColumn;
import com.hundsun.ares.studio.jres.database.service.ITableIndex;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author gongyf
 *
 */
public class TableAdapter implements ITable {
	
	final protected TableResourceData table;
	protected FastFindArrayList<String, ITableColumn>tableColumnList;
	protected FastFindArrayList<String, ITableIndex>tableIndexList;
	private IARESProject project;
	
	/**
	 * @param table
	 */
	public TableAdapter(TableResourceData table,IARESProject project) {
		super();
		this.table = table;
		this.project = project;
	}

	/**
	 * @return the tableColumnList
	 */
	private FastFindArrayList<String, ITableColumn> getTableColumnList() {
		if(tableColumnList == null){
			tableColumnList = new FastFindArrayList<String, ITableColumn>(new IKeyProvider<String, ITableColumn>() {

				@Override
				public String getKey(ITableColumn obj) {
					return obj.getName();
				}
			});
			for (TableColumn col : table.getColumns()) {
				tableColumnList.add(new TableColumnAdapter(col,project));
			}
		}
		return tableColumnList;
	}

	/**
	 * @return the tableIndexList
	 */
	private FastFindArrayList<String, ITableIndex> getTableIndexList() {
		if(tableIndexList == null){
			tableIndexList = new FastFindArrayList<String, ITableIndex>(new IKeyProvider<String, ITableIndex>() {

				@Override
				public String getKey(ITableIndex obj) {
					return obj.getName();
				}
			});
			for (TableIndex index : table.getIndexes()) {
				tableIndexList.add(new TableIndexAdapter(index,project));
			}
		}
		return tableIndexList;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getName()
	 */
	@Override
	public String getName() {
		return table.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getChineseName()
	 */
	@Override
	public String getChineseName() {
		return table.getChineseName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getDescription()
	 */
	@Override
	public String getDescription() {
		return table.getDescription();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getColumnList()
	 */
	@Override
	public List<ITableColumn> getColumnList() {
		return Collections.unmodifiableList(getTableColumnList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getColum(java.lang.String)
	 */
	@Override
	public ITableColumn getColum(String name) {
		return getTableColumnList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getIndexList()
	 */
	@Override
	public List<ITableIndex> getIndexList() {
		return Collections.unmodifiableList(getTableIndexList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITable#getIndex(java.lang.String)
	 */
	@Override
	public ITableIndex getIndex(String name) {
		return getTableIndexList().find(name);
	}
	
}
