package com.hundsun.ares.studio.jres.basicdata.resources.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.IRelationInfoProvider;

public class BasicDataRelationInfoProvider implements IRelationInfoProvider{

	private static final Logger logger = Logger.getLogger(BasicDataRelationInfoProvider.class);
	
	private String basicDataType = "";
	
	
	private void addReferenceInfo(IARESResource resource,List<RelationInfo> infoList,EAttribute[] attrArray){
		for(EAttribute attr:attrArray){
			RelationInfo info = ReferenceFactory.eINSTANCE.createRelationInfo();
			
			info.setHostResource(resource);
			info.setUsedRefName(attr.getName());
			info.setUsedRefType(IMetadataRefType.StdField);
//			info.setUsedRefNamespace(value)
			infoList.add(info);
		}
	}


	@Override
	public List<RelationInfo> getRelationInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<RelationInfo> infoList = new ArrayList<RelationInfo>();
		basicDataType = EPackageUtil.getBasicDataType(resource.getARESProject());
		
		EPackage ePackage = null;
		PackageDefine define = null;
		try {
			ePackage = BasicDataEpackageFactory.eINSTANCE.createEPackage(resource);
			define = BasicDataEpackageFactory.eINSTANCE.getDefine(resource);		
		
			//增加引用表
			if(define instanceof SingleTable){
				addTableReferenceInfo(resource,infoList,((SingleTable) define).getMaster());
			}else if(define instanceof MasterSlaveTable){
				addTableReferenceInfo(resource,infoList,((MasterSlaveTable) define).getMaster());
				addTableReferenceInfo(resource,infoList,((MasterSlaveTable) define).getSlave());
			}else if(define instanceof MasterSlaveLinkTable){
				addTableReferenceInfo(resource,infoList,((MasterSlaveLinkTable) define).getMaster());
				addTableReferenceInfo(resource,infoList,((MasterSlaveLinkTable) define).getSlave());
				addTableReferenceInfo(resource,infoList,((MasterSlaveLinkTable) define).getLink());
			}
			
			if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.MasterItem)){
				EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
				EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
				addReferenceInfo(resource, infoList, attrArray);
			}
			
			if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.SlaveItem)){
				EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
				EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
				addReferenceInfo(resource, infoList, attrArray);
			}
			
			if(BasicDataEpackageUtil.contains(ePackage, IBasicDataEpacakgeConstant.InfoItem)){
				EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
				EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(master);
				addReferenceInfo(resource, infoList, attrArray);
			}
		} catch (Exception e) {
			logger.error("读取基础数据时，创建EPackage失败", e);
		}
		return infoList;
	}


	/**
	 * @param resource
	 * @param infoList
	 * @param name
	 */
	private void addTableReferenceInfo(IARESResource resource,
			List<RelationInfo> infoList, String name) {
		RelationInfo info = ReferenceFactory.eINSTANCE.createRelationInfo();
		
		info.setHostResource(resource);
		info.setUsedRefName(name);
		info.setUsedRefType(basicDataType);
		infoList.add(info);
	}

}
