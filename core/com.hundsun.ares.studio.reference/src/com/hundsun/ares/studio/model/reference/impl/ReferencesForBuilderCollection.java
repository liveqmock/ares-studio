/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.model.reference.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;

/**
 * @author liaogc
 * 资源更新时影响的引用的集合
 */
public class ReferencesForBuilderCollection {
	private Map<IARESResource, List> references = new HashMap<IARESResource,List>();
	
	public ReferencesForBuilderCollection(){
	}
	/**
	 * 返回所有的资源的更新资源时的资源变化的所有引用
	 */
	public Map<IARESResource, List> getReferences(){
		return this.references;
	}
	
	/**
	 * 返回更新resource的影响的引用集合
	 * @return
	 */
	public List<ReferenceInfo> getReferencesByResource(IARESResource resource){
		return references.get(resource);
	}

}
