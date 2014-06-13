/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.skeleton.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procedure.compiler.mysql.exception.TableNotFoundException;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc
 *
 */
public class TableResourceUtil {
	/**
	 * 根据表名获得表资源
	 * @param tableName
	 * @return
	 */
	public static TableResourceData getTableByName(String tableName,IARESProject aresProject){
		if(!tableName.isEmpty()){
			tableName = tableName.trim();
		}
		String realTableResourceName =tableName;
		if(tableName.startsWith("his_")){
			realTableResourceName = tableName.replaceFirst("his_", StringUtils.EMPTY);
		}else if(tableName.startsWith("fil_")){
			realTableResourceName = tableName.replaceFirst("fil_", StringUtils.EMPTY);
		}else if(tableName.startsWith("r_")){
			realTableResourceName = tableName.replaceFirst("r_", StringUtils.EMPTY);
		}else if(tableName.startsWith("rl_")){
			realTableResourceName = tableName.replaceFirst("rl_", StringUtils.EMPTY);
		}
		
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(aresProject, IDatabaseRefType.Table, realTableResourceName, true);
		if (ref != null) {
			return (TableResourceData) ref.getObject();
		}
			
		return null;
		
	}
	
	/**
	 * 获得表名(短名)
	 * @param tableAllName
	 * @return
	 */
	public static String getTableName(String tableAllName){
		if(StringUtils.isNotBlank(tableAllName)){
			//如果表名前面存在用户名
			if (tableAllName.indexOf(".") != -1) {
				return  tableAllName.substring(tableAllName.indexOf(".") + 1);
			}
		}
		return tableAllName;
	}
	

	/**
	 * 根据标记过滤表格列
	 * @param flag
	 * @param tableName
	 * @param tableColumns
	 * @return
	 */
	public static List<TableColumn> getFieldsWithoutFlag(String flag,String tableName, List<TableColumn> tableColumns) {

		if (tableName.indexOf(".") >= 0) {
			tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
		}
		
		if (!tableName.isEmpty()) {
			tableName = tableName.trim();
			if (tableName.startsWith("his_") || tableName.startsWith("fil_")
					|| tableName.startsWith("r_")
					|| tableName.startsWith("rl_")) {
				return tableColumns;
			}
		}
		if(StringUtils.isBlank(flag)){
			return tableColumns;
		}
		List<TableColumn> filtered = new ArrayList<TableColumn>();
		char[] flags = flag.toCharArray();
		for (TableColumn field : tableColumns) {
			boolean contains = false;

			for (int ch : flags) {
				if (field.getMark() != null) {
					if (field.getMark().indexOf(ch) != -1) {
						contains = true;
						break;
					}
				}
			}
			if (!contains) {
				filtered.add(field);
			}
		}
		return filtered;
	}
	
	/**
	 * 根据标记过滤表格列
	 * @param flag
	 * @param tableName
	 * @param tableColumns
	 * @return
	 * @throws TableNotFoundException 
	 */
	public static List<TableColumn> getFieldsWithoutFlag(String flag,String tableName,IARESProject aresProject) throws TableNotFoundException {

		if (tableName.indexOf(".") >= 0) {
			tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
		}
		List<TableColumn> tableColumns = new ArrayList<TableColumn>(10);
		TableResourceData tableResourceData = getTableByName( tableName, aresProject);
		if(tableResourceData!=null){
			tableColumns.addAll(tableResourceData.getColumns());
		}else{
			throw new TableNotFoundException(tableName);
		}
		
		if (!tableName.isEmpty()) {
			tableName = tableName.trim();
			if (tableName.startsWith("his_") || tableName.startsWith("fil_")
					|| tableName.startsWith("r_")
					|| tableName.startsWith("rl_")) {
				return tableColumns;
			}
		}
		if(StringUtils.isBlank(flag)){
			return tableColumns;
		}

		List<TableColumn> filtered = new ArrayList<TableColumn>();
		char[] flags = flag.toCharArray();
		for (TableColumn field : tableColumns) {
			boolean contains = false;

			for (int ch : flags) {
				if (field.getMark() != null) {
					if (field.getMark().indexOf(ch) != -1) {
						contains = true;
						break;
					}
				}
			}
			if (!contains) {
				filtered.add(field);
			}
		}
		return filtered;
	}
}


