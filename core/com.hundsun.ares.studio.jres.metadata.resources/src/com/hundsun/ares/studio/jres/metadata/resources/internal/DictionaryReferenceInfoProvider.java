/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.resources.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.EMFReferenceObjectProvider;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

/**
 * @author gongyf
 *
 */
public class DictionaryReferenceInfoProvider implements IReferenceInfoProvider {
	
	/**
	 * 
	 */
	public DictionaryReferenceInfoProvider() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IReferenceInfoProvider#getReferenceInfos(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		try {
			MetadataResourceData<MetadataItem> info = resource.getInfo(MetadataResourceData.class);
			if(info==null){
				return infoList;
			}
			for (int i = 0; i < info.getItems().size(); i++) {
				MetadataItem item = info.getItems().get(i);
				ReferenceInfo refInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
				
				refInfo.setResource(resource);
				refInfo.setRefName(item.getName());
				refInfo.setRefType(ResourceTypeMapping.getInstance().getReferenceType(resource.getType()));
				refInfo.setRefNamespace(resource.getBundle().getId());
				refInfo.setObjectProvider(new EMFReferenceObjectProvider(
						MetadataPackage.Literals.METADATA_RESOURCE_DATA, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS ,i));
				
				for(DictionaryItem dicitem:((DictionaryType)item).getItems()){
					if(!StringUtils.isEmpty(dicitem.getConstantName())){
						ReferenceInfo dicItemReferInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
						
						dicItemReferInfo.setResource(resource);
						dicItemReferInfo.setRefName(dicitem.getConstantName());
						dicItemReferInfo.setRefType(IMetadataRefType.Dictionary_Const);
						dicItemReferInfo.setRefNamespace(resource.getBundle().getId());
						dicItemReferInfo.setObjectProvider(new EMFURIObjectProvider(item.eResource().getURIFragment(dicitem)));
						infoList.add(dicItemReferInfo);
					}
				}
				
				infoList.add(refInfo);
			}
			
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return infoList;
	}

}
