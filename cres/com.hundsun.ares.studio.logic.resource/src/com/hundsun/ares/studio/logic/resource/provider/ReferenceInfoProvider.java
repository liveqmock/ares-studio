package com.hundsun.ares.studio.logic.resource.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESResourceObjectProvider;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.logic.constants.LogicResourceMapping;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

public class ReferenceInfoProvider implements IReferenceInfoProvider {

	private Logger logger = Logger.getLogger(ReferenceInfoProvider.class);
	public ReferenceInfoProvider() {
	}

	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		//JRESResourceInfo info = resource.getInfo(JRESResourceInfo.class);
		
		String restype = resource.getType();
		ReferenceInfo refInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
		refInfo.setResource(resource);
		refInfo.setRefName(resource.getName());
		refInfo.setRefType(restype);
		refInfo.setRefNamespace(resource.getBundle().getId());
		refInfo.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
		infoList.add(refInfo);
		
		String cnameType = LogicResourceMapping.getCNameType(restype);
		if(null != cnameType){
			try {
				JRESResourceInfo info = resource.getInfo(JRESResourceInfo.class);
				if(info == null){
					return infoList;
				}
				ReferenceInfo cnameRefInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
				cnameRefInfo.setResource(resource);
				cnameRefInfo.setRefName(info.getChineseName());
				cnameRefInfo.setRefType(cnameType);
				cnameRefInfo.setRefNamespace(resource.getBundle().getId());
				cnameRefInfo.setObjectProvider(ARESResourceObjectProvider.INSTANCE);
				infoList.add(cnameRefInfo);
			} catch (Exception e) {
			}
		}
		
		return infoList;
	}
}
