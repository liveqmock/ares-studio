/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.model.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liaogc
 *
 */
public class RelationRefTypesMapEntry {

	private Map<String, RelationRefNamesMapEntry> relationRefMap = new HashMap<String, RelationRefNamesMapEntry>();
	

	public RelationRefTypesMapEntry() {
	}

	/**
	 * 返回引用关系map
	 * @return
	 */
	public Map<String, RelationRefNamesMapEntry> getRelations(){
		return relationRefMap;
	}
	
	/**
	 * 根据refType以及resName获得关系列表
	 * @param refType
	 * @param refName
	 * @return
	 */
	public List<RelationInfo> getRelationInfosByRefTypeAndRefName(String refType, String refName){
		List<RelationInfo> result = new ArrayList<RelationInfo>();
		if(refName==null){
			return result;
		}
		RelationRefNamesMapEntry refNamesMapEntry = relationRefMap.get(refType);
		if (refNamesMapEntry != null){
			result.addAll(refNamesMapEntry.getRelationInfoByRefName(refName));
		}
		 return result;
	}
	
	/**
	 * 根据refType获得关系列表
	 * @param refType
	 * @return
	 */
	public List<RelationInfo> getRelationInfosByRefType(String refType){
		List<RelationInfo> result = new ArrayList<RelationInfo>();
		RelationRefNamesMapEntry refNamesMapEntry = relationRefMap.get(refType);
		if (refNamesMapEntry != null){
			result.addAll(refNamesMapEntry.values());
		}
		 return result;
	}


}
