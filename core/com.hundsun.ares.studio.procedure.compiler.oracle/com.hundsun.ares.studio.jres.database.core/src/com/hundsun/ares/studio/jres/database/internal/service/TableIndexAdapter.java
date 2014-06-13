/**
 * 源程序名称：TableIndexAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.internal.service;

import java.util.Collections;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.service.ITableIndex;
import com.hundsun.ares.studio.jres.database.service.ITableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * @author wangxh
 *
 */
public class TableIndexAdapter implements ITableIndex {

	protected final TableIndex index;
	FastFindArrayList<String, ITableIndexColumn> columnList;
	private IARESProject project;
	
	
	public TableIndexAdapter(TableIndex index,IARESProject project) {
		super();
		this.index = index;
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndex#getName()
	 */
	@Override
	public String getName() {
		return index.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndex#isUnique()
	 */
	@Override
	public boolean isUnique() {
		return index.isUnique();
	}

	private FastFindArrayList<String, ITableIndexColumn> getcolumnList(){
		if(columnList == null){
			columnList = new FastFindArrayList<String, ITableIndexColumn>(new IKeyProvider<String, ITableIndexColumn>() {

				@Override
				public String getKey(ITableIndexColumn obj) {
					return obj.getColumnName();
				}
			});
			for(TableIndexColumn col : index.getColumns()){
				columnList.add(new TableIndexColumnAdapter(col));
			}
		}
		return columnList;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndex#getColumnList()
	 */
	@Override
	public List<? extends ITableIndexColumn> getColumnList() {
		return Collections.unmodifiableList(getcolumnList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableIndex#getColum(java.lang.String)
	 */
	@Override
	public ITableIndexColumn getColum(String name) {
		return getcolumnList().find(name);
	}

}
