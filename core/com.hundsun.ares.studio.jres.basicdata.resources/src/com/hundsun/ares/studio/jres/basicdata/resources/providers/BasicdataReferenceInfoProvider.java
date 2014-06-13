package com.hundsun.ares.studio.jres.basicdata.resources.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

public class BasicdataReferenceInfoProvider implements IReferenceInfoProvider {

	private static final Logger logger = Logger.getLogger(BasicdataReferenceInfoProvider.class);
	
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		
		String restype = resource.getType();
		ReferenceInfo refInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
		refInfo.setResource(resource);
		refInfo.setRefName(resource.getName());
		refInfo.setRefType(restype);
		refInfo.setRefNamespace(resource.getBundle().getId());
		refInfo.setObjectProvider(new IObjectProvider() {
				
				@Override
				public Object getObject(IARESResource resource) {
					try {
						return resource.getInfo(Object.class);
					} catch (ARESModelException e) {
						e.printStackTrace();
						logger.error(String.format("引用信息读取资源%s具体info的时候出错...", resource.getElementName()), e);
						return null;
					}
				}
			});
		infoList.add(refInfo);
		
		return infoList;
	}

}
