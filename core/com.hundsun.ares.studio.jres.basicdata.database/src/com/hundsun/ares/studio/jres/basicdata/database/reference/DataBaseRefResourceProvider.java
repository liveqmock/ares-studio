/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.database.reference;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResourceDelta;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IRefResourceProvider;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 *
 */
public class DataBaseRefResourceProvider implements IRefResourceProvider {

	@Override
	public Collection<IARESResource> getRefResources(IARESResource res,
			IResourceDelta delta, Map<String, IAresContext> contexts) {
		Set<IARESResource> result = new HashSet<IARESResource>();

		List<RelationInfo> rels = ReferenceManager.getInstance().getRelationInfoByTarget(IDatabaseRefType.Table, res.getName(), res.getARESProject());
		for (RelationInfo rel : rels) {
			IARESResource host = rel.getHostResource();
			String type = host.getType();
			//过滤，只检查基础数据和表关联信息资源
			if(StringUtils.equals(type, IBasicDataRestypes.singleTable) 
					|| StringUtils.equals(type, IBasicDataRestypes.MasterSlaveTable) 
					|| StringUtils.equals(type, IBasicDataRestypes.MasterSlaveLinkTable)
					|| StringUtils.equals(type, IBasicDataRestypes.PackageDefine)){
				result.add(rel.getHostResource());
			}
		}
		return result;
	}

}
