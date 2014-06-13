package com.hundsun.ares.studio.jres.metadata.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.EMFReferenceObjectProvider;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

public class ErrorNoReferenceInfoProvider implements IReferenceInfoProvider {
	
	private static final Logger LOGGER = Logger.getLogger(ErrorNoReferenceInfoProvider.class);

	public ErrorNoReferenceInfoProvider() {
	}

	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource, Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		try {
			ErrorNoList errnoList = resource.getInfo(ErrorNoList.class);
			if(errnoList == null){
				return infoList;
			}
			
			List<ErrorNoItem> items = errnoList.getItems();
			int count = items.size();
			for (int i = 0; i< count; i++) {
				ErrorNoItem item = items.get(i);
				ReferenceInfo info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setResource(resource);
				info.setRefName(item.getName());
				info.setRefType(IMetadataRefType.ErrNo);
				info.setRefNamespace(resource.getBundle().getId());
				info.setObjectProvider(new EMFReferenceObjectProvider(
						MetadataPackage.Literals.METADATA_RESOURCE_DATA, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS ,i));
				
				infoList.add(info);
				
				info = ReferenceFactory.eINSTANCE.createReferenceInfo();
				info.setResource(resource);
				info.setRefName(item.getNo());
				info.setRefType(IMetadataRefType.ErrNo_No);
				info.setRefNamespace(resource.getBundle().getId());
				info.setObjectProvider(new EMFReferenceObjectProvider(
						MetadataPackage.Literals.METADATA_RESOURCE_DATA, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS ,i));
				infoList.add(info);
			}
			
		} catch (ARESModelException e) {
			LOGGER.error(e);
		} 

		return infoList;
	}

}
