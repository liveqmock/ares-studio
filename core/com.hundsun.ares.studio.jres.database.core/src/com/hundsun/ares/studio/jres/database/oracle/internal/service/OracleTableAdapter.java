/**
 * 源程序名称：OracleTableAdapter.java
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

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTable;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTableColumn;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTableIndex;
import com.hundsun.ares.studio.jres.database.oracle.service.ITableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;

/**
 * @author wangxh
 *
 */
public class OracleTableAdapter implements IOracleTable {

	protected final TableResourceData tableResourceData;
	protected FastFindArrayList<String, IOracleTableColumn>tableColumnList;
	protected FastFindArrayList<String, IOracleTableIndex>tableIndexList;
	private IARESProject project;
	private String indexSpace;
	
	public OracleTableAdapter(TableResourceData tableResourceData,IARESProject project) {
		super();
		this.tableResourceData = tableResourceData;
		this.project = project;
	}

	@Override
	public String getName() {
		return tableResourceData.getName();
	}

	@Override
	public String getChineseName() {
		return tableResourceData.getChineseName();
	}

	@Override
	public String getDescription() {
		return tableResourceData.getDescription();
	}

	@Override
	public IOracleTableColumn getColum(String name) {
		return getTableColumnList().find(name);
	}

	@Override
	public List<IOracleTableColumn> getColumnList() {
		return Collections.unmodifiableList(getTableColumnList());
	}

	@Override
	public IOracleTableIndex getIndex(String name) {
		return getTableIndexList().find(name);
	}

	@Override
	public List<IOracleTableIndex> getIndexList() {
		return Collections.unmodifiableList(getTableIndexList());
	}

	/**
	 * @return the tableColumnList
	 */
	public FastFindArrayList<String, IOracleTableColumn> getTableColumnList() {
		if(tableColumnList == null){
			tableColumnList = new FastFindArrayList<String, IOracleTableColumn>(new IKeyProvider<String, IOracleTableColumn>() {

				@Override
				public String getKey(IOracleTableColumn obj) {
					return obj.getName();
				}
			});
			for (TableColumn col : tableResourceData.getColumns()) {
				tableColumnList.add(new OracleTableColumnAdapter(col,project));
			}
		}
		return tableColumnList;
	}

	/**
	 * @return the tableIndexList
	 */
	public FastFindArrayList<String, IOracleTableIndex> getTableIndexList() {
		if(tableIndexList == null){
			tableIndexList = new FastFindArrayList<String, IOracleTableIndex>(new IKeyProvider<String, IOracleTableIndex>() {

				@Override
				public String getKey(IOracleTableIndex obj) {
					return obj.getName();
				}
			});
			for (TableIndex index : tableResourceData.getIndexes()) {
				tableIndexList.add(new OracleTableIndexAdapter(index,project));
			}
		}
		return tableIndexList;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTable#getSpace()
	 */
	@Override
	public String getSpace() {
		return ((OracleTableProperty) tableResourceData).getSpace();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleTable#getIndexSpace()
	 */
	@Override
	public String getIndexSpace() {
	 // FIXME 通过project等查找出，需要进行缓存
		if (indexSpace == null) {
			IOracleDBService service = DataServiceManager.getInstance().getService(project, IOracleDBService.class);
			if(service != null){
				ITableSpaceRelation field = service.getSpaceList().get(0).getTableSpaceRelation(getSpace());
				if (field != null) {
					indexSpace = field.getIndexSpace();
				}
			} else {
				indexSpace = StringUtils.EMPTY;
			}
		}
		return indexSpace;

	}
	
}

