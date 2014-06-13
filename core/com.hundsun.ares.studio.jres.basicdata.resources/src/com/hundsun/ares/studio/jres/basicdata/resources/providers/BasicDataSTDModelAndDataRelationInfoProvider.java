package com.hundsun.ares.studio.jres.basicdata.resources.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.IRelationInfoProvider;

public class BasicDataSTDModelAndDataRelationInfoProvider implements IRelationInfoProvider{

	private static final Logger logger = Logger.getLogger(BasicDataSTDModelAndDataRelationInfoProvider.class);	


	@Override
	public List<RelationInfo> getRelationInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<RelationInfo> infoList = new ArrayList<RelationInfo>();
	
		try {
			StandardFieldModelAndData info = resource.getInfo(StandardFieldModelAndData.class);
			
			for(StandardFieldColumn  column:info.getModel().getFields()){
				RelationInfo relationInfo = ReferenceFactory.eINSTANCE.createRelationInfo();
				
				relationInfo.setHostResource(resource);
				relationInfo.setUsedRefName(column.getStandardField());
				relationInfo.setUsedRefType(IMetadataRefType.StdField);
				
				infoList.add(relationInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("创建[%s]引用模型失败。", resource.getResource().getFullPath().toOSString()));
		}
		
		return infoList;
	}

}
