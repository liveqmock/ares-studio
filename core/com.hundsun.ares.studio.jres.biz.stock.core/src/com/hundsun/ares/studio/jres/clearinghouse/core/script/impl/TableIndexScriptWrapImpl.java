/**
 * 源程序名称：TableIndexScriptWrapImpl.java
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

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;

/**
 * @author yanwj06282
 *
 */
public class TableIndexScriptWrapImpl extends CommonScriptWrap<TableIndex> implements ITableIndexScriptWrap {

	private ITableScriptWrap tableWrap;
	private List<ITableColScriptWrap> wraps = new ArrayList<ITableColScriptWrap>();
	
	public TableIndexScriptWrapImpl(ITableScriptWrap tableWrap , TableIndex index , IARESResource resource) {
		super(index ,resource);
		if (tableWrap == null) {
			this.tableWrap = new TableScriptWrapImpl(resource);
		}else {
			this.tableWrap = tableWrap;
		}
		for (TableIndexColumn indexColumn : index.getColumns()){
			
			for(ITableColScriptWrap tc :this.tableWrap.getTableColumns()){
				if (StringUtils.equals(tc.getName(), indexColumn.getColumnName())) {
					wraps.add(tc);
				}
			}
		}
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}
	
	@Override
	public String getType() {
		return null;
	}

	public String getSql(String type , String prefix , boolean isUser){
		StringBuffer sqlBuffer = new StringBuffer();
		// 索引名
		String indexName = getOriginalInfo().getName();
		// 是否唯一
		boolean unique = getOriginalInfo().isUnique();
		
		String tableName = tableWrap.getName(prefix);
		String user = tableWrap.getDbuser(prefix);
		if (isUser && StringUtils.isNotBlank(user)) {
			tableName = user + "." +tableName;
		}
		sqlBuffer.append("CREATE ");
		sqlBuffer.append(unique ? "UNIQUE " : "");
//		sqlBuffer.append(cluster ? "CLUSTERED " : "");
		sqlBuffer.append("INDEX " + prefix + indexName + " ON " + tableName);
		// 索引字段
		EList<TableIndexColumn> indexCols = getOriginalInfo().getColumns();
		for(int i = 0;i< indexCols.size() ; i++){
			TableIndexColumn ic = indexCols.get(i);
			String indexColName = ic.getColumnName();
			indexColName = getTableName(indexColName);
			if(i == 0){
				sqlBuffer.append("(" + indexColName);
			}else{
				sqlBuffer.append("," + indexColName);
			}
			if (ic.isAscending()) {
				sqlBuffer.append(" ASC ");
			} else {
				sqlBuffer.append(" DESC ");
			}
			if (i == indexCols.size()-1) {
				sqlBuffer.append(")");
			}
		}
		
		return sqlBuffer.toString();
	}
	
	public ITableColScriptWrap[] getTableIndexColumns(){
		return wraps.toArray(new ITableColScriptWrap[0]);
	}

	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}

	@Override
	public boolean isUnique() {
		return getOriginalInfo().isUnique();
	}

	@Override
	public boolean isCluster() {
		return getOriginalInfo().isCluster();
	}

	public boolean isReverse(){
		String reverse = IJSONUtil.instance.getStringFromJSON(toJSON(),
			"Oracle_reverse");
		if (StringUtils.isNotBlank(reverse)) {
			return Boolean.parseBoolean(reverse);
		}
	return false;
	}
	
	private String getTableName(String indexColName){
		EObject obj = getTableResourceData(getOriginalInfo());
		if (obj instanceof TableResourceData) {
			for (TableColumn tableColumn : ((TableResourceData)obj).getColumns()){
				if (StringUtils.equals(tableColumn.getFieldName(), indexColName)) {
					return tableColumn.getName();
				}
			}
		}
		return indexColName;
	}
	
	private EObject getTableResourceData (EObject obj){
		if (obj == null) {
			return null;
		}
		if (obj instanceof TableResourceData) {
			return (TableResourceData) obj;
		}else {
			return getTableResourceData(obj.eContainer());
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap#trim()
	 */
	@Override
	public void trim() {
		// TODO Auto-generated method stub
		for(TableIndexColumn indexcol : getOriginalInfo().getColumns()){
			indexcol.setColumnName(indexcol.getColumnName().trim().toLowerCase());
		}
	}

}
