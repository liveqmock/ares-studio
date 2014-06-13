/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.impl.ReferenceForBuilderTable;
import com.hundsun.ares.studio.model.reference.impl.ReferencesForBuilderCollection;

/**
 * @author liaogc
 * 资源更新时影响的引用集合(目前只对标准字段做引用管理)builer
 */
public class ReferenceForBuilderManager {
	
	private ReferenceForBuilderManager() {
	}
	private static ReferenceForBuilderManager instance;
	private ReferenceForBuilderTable referenceForBuilderTable = new ReferenceForBuilderTable();
	
	/**
	 * 获取引用管理对象单例
	 * @return
	 */
	public synchronized static ReferenceForBuilderManager getInstance() {
		if (instance == null) {
			instance = new ReferenceForBuilderManager();
		}
		return instance;
	}
	
	public Map<IARESProject, ReferencesForBuilderCollection> getProjects(){
		return referenceForBuilderTable.getProjects();
	}
	
	/**
	 * 返回更新resource的影响的引用集合
	 * @return
	 */
	public List<ReferenceInfo> getReferencesByResource(IARESResource resource){
		if( referenceForBuilderTable.getProjects().get(resource.getARESProject())==null){
			 referenceForBuilderTable.getProjects().put(resource.getARESProject(), new ReferencesForBuilderCollection());
		}
		return referenceForBuilderTable.getProjects().get(resource.getARESProject()).getReferencesByResource(resource);
	}
	
	/**
	 * 
	 * 增加更新resource的影响的引用集合
	 */
	public void addReferences(IARESResource resource,List<ReferenceInfo> references){
		if( referenceForBuilderTable.getProjects().get(resource.getARESProject())==null){
			 referenceForBuilderTable.getProjects().put(resource.getARESProject(), new ReferencesForBuilderCollection());
		}
		 referenceForBuilderTable.getProjects().get(resource.getARESProject()).getReferences().remove(resource);
		 referenceForBuilderTable.getProjects().get(resource.getARESProject()).getReferences().put(resource, references);
	}
	
	/**
	 * 
	 * 删除resource的影响的引用集合
	 */
	public void clearReferences(IARESResource resource){
		if( referenceForBuilderTable.getProjects().get(resource.getARESProject())==null){
			 referenceForBuilderTable.getProjects().put(resource.getARESProject(), new ReferencesForBuilderCollection());
		}
		 referenceForBuilderTable.getProjects().get(resource.getARESProject()).getReferences().remove(resource);
	}
	
	
}
