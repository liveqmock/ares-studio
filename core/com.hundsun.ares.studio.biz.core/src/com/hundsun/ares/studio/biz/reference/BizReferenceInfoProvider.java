package com.hundsun.ares.studio.biz.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ARESResourceObjectProvider;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

/**
 * @author gongyf
 *
 */
public class BizReferenceInfoProvider implements IReferenceInfoProvider {

	public BizReferenceInfoProvider() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IReferenceInfoProvider#getReferenceInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource, Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
		info.setRefName(resource.getFullyQualifiedName());
		info.setRefType(ResourceTypeMapping.getInstance().getReferenceType(resource.getType()));
		info.setRefNamespace(StringUtils.EMPTY);
		info.setResource(resource);
		info.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
		infoList.add(info);
		return infoList; 
	}

}
