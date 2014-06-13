package com.hundsun.ares.studio.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESResourceObjectProvider;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;

public class CNameReferenceInfoProvicer implements IReferenceInfoProvider{

	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource, Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		BasicResourceInfo basicInfo = null;
		try {
			basicInfo = resource.getInfo(BasicResourceInfo.class);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		if (basicInfo != null) {
			ReferenceInfo cnameRefInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
			cnameRefInfo.setResource(resource);
			cnameRefInfo.setRefName(basicInfo.getChineseName());
			cnameRefInfo.setRefType(ResourceTypeMapping.getInstance().getReferenceType(resource.getType()));
			cnameRefInfo.setRefNamespace(resource.getBundle().getId());
			cnameRefInfo.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
			infoList.add(cnameRefInfo);
		}
		return infoList;
	}

}
