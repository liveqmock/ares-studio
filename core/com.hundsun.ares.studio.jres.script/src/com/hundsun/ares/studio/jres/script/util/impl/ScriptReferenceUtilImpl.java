/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.util.IScriptReferenceUtil;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc
 *
 */
public class ScriptReferenceUtilImpl implements IScriptReferenceUtil {
	public static ScriptReferenceUtilImpl instance = new ScriptReferenceUtilImpl();

	/**
	 * 根据引用类型,引用名称以及项目名称获得引用此引用的资源列表
	 * @param refType
	 * @param refName
	 * @param project
	 * @return
	 */
	public Collection<IARESResource> getRefResources(String refType,String refName,IARESProject project) {
		Set<IARESResource> result = new HashSet<IARESResource>();
		// 查找是根据当前环境下进行的，无法得知之前的引用关系
          try{
        	  List<RelationInfo>  rels = ReferenceManager.getInstance().getRelationInfoByTarget(refType, refName, project);
  			for (RelationInfo rel : rels) {
  				result.add(rel.getHostResource());
  			} 
          }catch(Exception e){
        	  
          }
			
		
		return result;
	}

}
