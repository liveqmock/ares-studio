/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.RelationInfo;

/**
 * @author gongyf
 *
 */
public class ReferenceUtils {
	
//	/**
//	 * 通过给定的引用类型和引用名，在一定范围内找到这个引用可能的命名空间<BR>
//	 * 因为当引用类型和引用名无重复项时，可以不使用命名空间
//	 * 
//	 * @param project
//	 * @param refType
//	 * @param refName
//	 * @param useRequiredProjects
//	 * @return
//	 */
//	public static String getPossibleNamespace(IARESProject project, String refType, String refName, boolean useRequiredProjects) {
//		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, refType, refName, useRequiredProjects);
//		if (infoList.size() > 0) {
//			return infoList.get(0).getRefNamespace();
//		}
//		return StringUtils.EMPTY;
//	}
	
	/**
	 * 返回一个资源列表，这些资源都引用了参数资源的内容
	 * 
	 * @param resource
	 * @return
	 */
	public static List<IARESResource> getRefResources(IARESResource resource) {
		List<IARESResource> result = new ArrayList<IARESResource>();
		
		// 查找是根据当前环境下进行的，无法得知之前的引用关系
		List<ReferenceInfo> infoList = ViewerUtils.getReferenceInfos(ReferenceManager.getInstance().getRefTableViewer().getTable(), resource);
		for (ReferenceInfo info : infoList) {
			
			List<RelationInfo> rels = ReferenceManager.getInstance().getRelationInfoByTarget(info.getRefType(), info.getRefName(), info.getRefNamespace(), resource.getARESProject());
			for (RelationInfo rel : rels) {
				result.add(rel.getHostResource());
			}
		}
		
		return result;
	}
	
}
