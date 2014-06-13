/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.model.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.ReferenceWithNamespace;
import com.hundsun.ares.studio.core.model.util.ReferenceUtil;
import com.hundsun.ares.studio.reference.IRelationInfoProvider;

/**
 * @author gongyf
 *
 */
public class JRESRelationInfoProvider implements IRelationInfoProvider {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IRelationInfoProvider#getRelationInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	@Override
	public List<RelationInfo> getRelationInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<RelationInfo> infoList = new ArrayList<RelationInfo>();
		
		try {
			IReferenceProvider object = resource.getInfo(IReferenceProvider.class);
			if (object != null) {
				for (Reference r : ReferenceUtil.INSTANCE.getReferences(object, resource.getARESProject())) {
					RelationInfo info = ReferenceFactory.eINSTANCE.createRelationInfo();
					info.setHostResource(resource);
					info.setUsedRefType(r.getType());
					// 主要问题在于引用信息没有命名空间信息的时候，不一定是引用的当前工程的信息，也可能是引用包或者引用工程的信息
					
					if (r instanceof ReferenceWithNamespace) {
						info.setUsedRefName(((ReferenceWithNamespace) r).getId());
						info.setUsedRefNamespace(((ReferenceWithNamespace) r).getNamespace());
					} else {
						info.setUsedRefName(r.getValue());
						info.setUsedRefNamespace(StringUtils.EMPTY);
					}
					
					infoList.add(info);
				}
			}

		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return infoList;
	}

}
