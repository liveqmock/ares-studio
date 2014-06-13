package com.hundsun.ares.studio.jres.basicdata.database.factory;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;

public class DataBaseEPackageUtil {

	/**
	 * 获取关联字段
	 * @param masterTable
	 * @param slaveTable
	 * @return
	 * @throws Exception
	 */
	public static String[] getLinkFields(TableResourceData masterTable,
			TableResourceData slaveTable ,String msg)throws Exception {
		List<String> masterKeys = getMasterKeys(masterTable);
//			new ArrayList<String>();
//		for(TableColumn item:masterTable.getColumns()){
//			if(item.isPrimaryKey()){
//				masterKeys.add(item.getFieldName());
//			}
//		}
		if(masterKeys.isEmpty()){
			throw new Exception(String.format("表[%s]中没有主键或唯一索引。", masterTable.getName()));
		}
		
		//主键
		String[] keys = masterKeys.toArray(new String[0]);
		for(TableColumn item:slaveTable.getColumns()){
			if(masterKeys.contains(item.getFieldName())){
				masterKeys.remove(item.getFieldName());
			}
		}
		if(!masterKeys.isEmpty()){
			StringBuffer unmatchKey = new StringBuffer();
			for(String item:masterKeys){
				unmatchKey.append(" "+ item);
			}
			throw new Exception(String.format(msg, 
					masterTable.getName(),
					unmatchKey.toString(),
					slaveTable.getName()));
		}
		return keys;
	}
	
	public static List<String> getMasterKeys(TableResourceData table){
		List<String> masterKeys = new ArrayList<String>();
		
		//主键
//		for(TableColumn item:table.getColumns()){
//			if(item.isPrimaryKey()){
//				masterKeys.add(item.getFieldName());
//			}
//		}
		for(TableKey key : table.getKeys()){
			if(key.getType().equals(key_type.PRIMARY)){
				for(TableColumn item : key.getColumns()){
					masterKeys.add(item.getFieldName());
				}
			}
		}
		
		//没有主键添加唯一索引字段
		if(masterKeys.isEmpty()){
			for(TableIndex index:table.getIndexes()){
				if(index.isUnique()){
					//添加唯一索引字段
					for(TableIndexColumn item:index.getColumns()){
						masterKeys.add(item.getColumnName());
					}
				}
			}
		}
		
		return masterKeys;
	}
	
}
