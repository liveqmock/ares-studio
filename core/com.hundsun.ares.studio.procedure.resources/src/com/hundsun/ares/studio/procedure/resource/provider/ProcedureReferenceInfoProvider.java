package com.hundsun.ares.studio.procedure.resource.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.constants.ProcedureResourceMapping;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

public class ProcedureReferenceInfoProvider implements IReferenceInfoProvider {

	private static Logger logger = Logger.getLogger(ProcedureReferenceInfoProvider.class);
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IReferenceInfoProvider#getReferenceInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
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
		refInfo.setObjectProvider(new IObjectProvider() {
				
				@Override
				public Object getObject(IARESResource resource) {
					try {
						return resource.getInfo(JRESResourceInfo.class);
					} catch (ARESModelException e) {
						e.printStackTrace();
						return null;
					}
				}
			});
		infoList.add(refInfo);
		
		String cnameType = ProcedureResourceMapping.getCNameType(restype);
		if(null != cnameType){
			try {
				JRESResourceInfo info = resource.getInfo(JRESResourceInfo.class);
				if(info==null){
					return infoList;
				}
				ReferenceInfo cnameRefInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
				cnameRefInfo.setResource(resource);
				cnameRefInfo.setRefName(info.getChineseName());
				cnameRefInfo.setRefType(cnameType);
				cnameRefInfo.setRefNamespace(resource.getBundle().getId());
				cnameRefInfo.setObjectProvider(new IObjectProvider() {
					
					@Override
					public Object getObject(IARESResource resource) {
						try {
							return resource.getInfo(JRESResourceInfo.class);
						} catch (ARESModelException e) {
							e.printStackTrace();
							logger.error(String.format("引用信息读取资源%s具体info的时候出错...", resource.getElementName()), e);
							return null;
						}
					}
				});
				infoList.add(cnameRefInfo);
			} catch (Exception e) {
			}
		}
		
		return infoList;
	}


}
