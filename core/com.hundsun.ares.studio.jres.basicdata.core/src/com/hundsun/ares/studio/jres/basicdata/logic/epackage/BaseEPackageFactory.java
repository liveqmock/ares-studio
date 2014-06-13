package com.hundsun.ares.studio.jres.basicdata.logic.epackage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEcoreFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

public class BaseEPackageFactory {
	

	protected EcoreFactory theCoreFactory = BasicDataEcoreFactory.eINSTANCE;

	protected EcorePackage theCorePackage = EcorePackage.eINSTANCE;
	
	Pattern p1 = Pattern.compile("[A-Za-z][A-Za-z_0-9]*");
	/**
	 * 添加属性到EClass中
	 * @param targetEClass
	 * @param fields
	 */
	protected void addAttrToEclass(EClass targetEClass, Map<String,BasicDataField> basicDataFields){
		for(String item:basicDataFields.keySet()){
			if(!StringUtils.isBlank(item)){
				Matcher m1 = p1.matcher(item);
				if(m1.matches()){
					BasicDataField basicDataField = basicDataFields.get(item) ; ; 
					BasicDataEAttribute attr = (BasicDataEAttribute) theCoreFactory.createEAttribute();
					attr.setName(item);
					attr.setEType(theCorePackage.getEString());
					attr.setData(basicDataField);
					
					targetEClass.getEStructuralFeatures().add(attr);
				}
			}
		}
	}
	
	/**
	 * 添加主类
	 * @param ePackage
	 * @param fields
	 * @return
	 */
	protected EClass addMasterItem(EPackage ePackage,String className, Map<String,BasicDataField> basicDataFields){

		EClass masterItemEClass = theCoreFactory.createEClass();
		masterItemEClass.setName(className);
		masterItemEClass.getESuperTypes().add(BasicdataPackage.Literals.BASICDATA_ITEM);//继承BasicdataItem
		//添加属性
		addAttrToEclass(masterItemEClass,basicDataFields);
		
		ePackage.getEClassifiers().add(masterItemEClass);
		return masterItemEClass;
	}
	

	
	/**
	 * 添加资源模型根
	 * @param ePackage
	 * @return
	 */
	protected EClass addResourceRoot(EPackage ePackage){
		EClass resourceRootEClass = theCoreFactory.createEClass();
		resourceRootEClass.setName(IBasicDataEpacakgeConstant.ResourceRoot);
		resourceRootEClass.getESuperTypes().add(BasicdataPackage.Literals.BASIC_DATA_BASE_MODEL);
		
		ePackage.getEClassifiers().add(resourceRootEClass);
		return resourceRootEClass;
	}
	
	
	

	/**
	 * 添加类
	 * @param ePackage
	 * @param className
	 * @param fields
	 * @return
	 */
	protected EClass addEclass(EPackage ePackage,String className,Map<String,BasicDataField> basicDataFields){

		EClass itemEClass = theCoreFactory.createEClass();
		itemEClass.setName(className);
		//添加属性
		addAttrToEclass(itemEClass,basicDataFields);
		//添加到Epackage
		ePackage.getEClassifiers().add(itemEClass);
		return itemEClass;
	}
	
	/**
	 * 添加引用
	 * @param master
	 * @param slave
	 * @param referenceName
	 */
	protected void addReference(EClass master,
			EClass slave,
			String referenceName){
		//定义引用
		EReference itemReference = theCoreFactory.createEReference();
		itemReference.setName(referenceName);
		itemReference.setEType(slave);
		itemReference.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
		itemReference.setContainment(true);
		
		//添加引用
		master.getEStructuralFeatures().add(itemReference);
		
	}
	
	
	
	
	
//	protected EClass addSlaveItem(EPackage ePackage,
//			EClass master,
//			String attrName,
//			String 
//			String[] fields){
//		EClass slaveItemEClass = theCoreFactory.createEClass();
//		slaveItemEClass.setName(IBasicDataEpacakgeConstant.SlaveItem);
//		//添加属性
//		addAttrToEclass(slaveItemEClass,fields);
//		
//		//定义引用
//		EReference itemReference = theCoreFactory.createEReference();
//		itemReference.setName(IBasicDataEpacakgeConstant.Attr_Slave_Items);
//		itemReference.setEType(slaveItemEClass);
//		itemReference.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
//		itemReference.setContainment(true);
//		
//		//添加引用
//		master.getEStructuralFeatures().add(itemReference);
//		
//		ePackage.getEClassifiers().add(slaveItemEClass);
//		
//		return slaveItemEClass;
//	}
	
	
}
