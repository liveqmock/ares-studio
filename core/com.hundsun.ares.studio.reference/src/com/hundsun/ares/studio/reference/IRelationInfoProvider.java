/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference;

import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.RelationInfo;

/**
 * @author gongyf
 *
 */
public interface IRelationInfoProvider {
	
	/**
	 * 提取一个ARES资源中对外部引用的信息列表
	 * @param resource
	 * @return
	 */
	List<RelationInfo> getRelationInfos(IARESResource resource, Map<Object, Object> context);
}
