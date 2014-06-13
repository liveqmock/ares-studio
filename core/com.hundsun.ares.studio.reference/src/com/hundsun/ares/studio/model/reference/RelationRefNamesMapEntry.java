/**
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.model.reference;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;


/**
 * @author liaogc
 * 
 */
public class RelationRefNamesMapEntry {
	
	private ListMultimap<String,RelationInfo> relationRefNameMap =  ArrayListMultimap.create() ;
	public RelationRefNamesMapEntry() {
	}
	/**
	 * 获得一个分类下的所有相同名称引用名
	 * @return
	 */
	public List<String> getRefNames(){
		return new ArrayList<String>(this.relationRefNameMap.keySet());
	}
	/**
	 * 根据引用名称返回相同引用换的所胡关系列表
	 * @param refName
	 * @return
	 */
	public List<RelationInfo> getRelationInfoByRefName(String refName){
		return relationRefNameMap.get(refName);
	}
	/**
	 * 加添关系
	 * @param relationInfo
	 */
	public void addRelation(RelationInfo relationInfo){
		relationRefNameMap.put(relationInfo.getUsedRefName(), relationInfo);
	}
	
	/**
	 * 返回同一refType所有的关系
	 * @return
	 */
	public List<RelationInfo> values(){
		List<RelationInfo> values = new ArrayList<RelationInfo>();
		for(String refName:this.relationRefNameMap.keySet()){
			values.addAll(relationRefNameMap.get(refName));
		}
		return values;
	}
	

}
