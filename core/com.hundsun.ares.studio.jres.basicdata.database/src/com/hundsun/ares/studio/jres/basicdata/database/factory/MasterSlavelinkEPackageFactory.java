package com.hundsun.ares.studio.jres.basicdata.database.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BaseEPackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

public class MasterSlavelinkEPackageFactory extends BaseEPackageFactory{
	
	public static MasterSlavelinkEPackageFactory eInstance = new MasterSlavelinkEPackageFactory();

	public EPackage create(IARESProject project,PackageDefine define)throws Exception{
		if(define instanceof MasterSlaveLinkTable){
			MasterSlaveLinkTable defineData = (MasterSlaveLinkTable)define;
			
			//主表资源
			String masterTableName = defineData.getMaster();
			TableResourceData masterTable = JRESResProviderUtil.getResourceModel(project, 
					masterTableName,
					IDatabaseRefType.Table,
					TableResourceData.class);
			if(null == masterTable){
				throw new Exception(String.format("表资源[%s]不存在。", masterTableName));
			}
			
			//主表字段
			Map<String,BasicDataField> masterBasicDataFields = new LinkedHashMap<String,BasicDataField>();
			for(TableColumn item:masterTable.getColumns()){
				BasicDataField basicDataField = BasicdataFactory.eINSTANCE.createBasicDataField();
				basicDataField.setName(item.getFieldName());
				basicDataField.setDataType(item.getDataType());
				basicDataField.setColumnType(item.getColumnType().getValue());
				basicDataField.setChineseName(item.getChineseName());
				masterBasicDataFields.put(item.getFieldName(),basicDataField);
			}
			
			//信息表资源
			String infoTableName = defineData.getLink();
			TableResourceData infoTable = JRESResProviderUtil.getResourceModel(project, 
					infoTableName,
					IDatabaseRefType.Table,
					TableResourceData.class);
			if(null == infoTable){
				throw new Exception(String.format("表资源[%s]不存在。", infoTableName));
			}
			
			//信息表字段
			Map<String,BasicDataField> infoBasicDataFields = new LinkedHashMap<String,BasicDataField>();
			for(TableColumn item:infoTable.getColumns()){
				BasicDataField basicDataField = BasicdataFactory.eINSTANCE.createBasicDataField();
				basicDataField.setName(item.getFieldName());
				basicDataField.setDataType(item.getDataType());
				basicDataField.setColumnType(item.getColumnType().getValue());
				basicDataField.setChineseName(item.getChineseName());
				infoBasicDataFields.put(item.getFieldName(),basicDataField);
			}
			
			
			//从表资源
			String slaveTableName = defineData.getSlave();
			TableResourceData slaveTable = JRESResProviderUtil.getResourceModel(project, 
					slaveTableName,
					IDatabaseRefType.Table,
					TableResourceData.class);
			if(null == slaveTable){
				throw new Exception(String.format("表资源[%s]不存在。", slaveTableName));
			}
			
			//从表子字段
			Map<String,BasicDataField> slaveBasicDataFields = new LinkedHashMap<String,BasicDataField>();
			for(TableColumn item:slaveTable.getColumns()){
				BasicDataField basicDataField = BasicdataFactory.eINSTANCE.createBasicDataField();
				basicDataField.setName(item.getFieldName());
				basicDataField.setDataType(item.getDataType());
				basicDataField.setColumnType(item.getColumnType().getValue());
				basicDataField.setChineseName(item.getChineseName());
				slaveBasicDataFields.put(item.getFieldName(),basicDataField);
			}
			
			String[] masterLinkFields = DataBaseEPackageUtil.getLinkFields(masterTable,
					slaveTable ,"主表[%s]中的主键字段[%s]在关联表[%s]中不存在。");
			String[] infoLinkLinkFields = DataBaseEPackageUtil.getLinkFields(infoTable,
					slaveTable ,"信息表[%s]中的主键字段[%s]在关联表[%s]中不存在。");
			
			for(String masterItem:masterLinkFields){
				for(String infoItem:infoLinkLinkFields){
					if(StringUtils.equals(masterItem, infoItem)){
						throw new Exception(String.format("主表和信息表包含相同主键字段[%s]", masterItem));
					}
				}
			}
			
			//关联表中去掉主表主键
			for(String item:masterLinkFields){
				slaveBasicDataFields.remove(item);
			}
			
//			if(infoList.isEmpty()){
//				throw new Exception(String.format("信息表[%s]字段数为0。", infoTableName));
//			}
			
			EPackage ePackage = theCoreFactory.createEPackage();
			ePackage.setName("masterslabelinktablePackage");
			ePackage.setNsPrefix("masterslabelinktable");
			ePackage.setNsURI("http://www.hundsun.com/masterslabelinktable");
			
			EClass rootClass = addResourceRoot(ePackage);
			
			EClass masterClass = addMasterItem(ePackage,
						IBasicDataEpacakgeConstant.MasterItem,
						masterBasicDataFields);
			
			EClass infoClass = addEclass(ePackage, 
					IBasicDataEpacakgeConstant.InfoItem, 
					infoBasicDataFields);
			infoClass.getESuperTypes().add(BasicdataPackage.Literals.BASICDATA_ITEM);//继承BasicdataItem
			
			EClass linkClass = addEclass(ePackage, 
					IBasicDataEpacakgeConstant.SlaveItem, 
					slaveBasicDataFields);
			linkClass.getESuperTypes().add(BasicdataPackage.Literals.BASICDATA_ITEM);//继承BasicdataItem
			
			//添加注释
			BasicDataEpackageUtil.addMasterKeyAnnotation(masterClass, masterLinkFields);
			BasicDataEpackageUtil.addMasterKeyAnnotation(infoClass, infoLinkLinkFields);
			
			//添加引用
			addReference( rootClass, infoClass, IBasicDataEpacakgeConstant.Attr_Info_Items);
			
			addReference( masterClass, linkClass, IBasicDataEpacakgeConstant.Attr_Slave_Items);
			
			addContentReference(linkClass,infoClass,IBasicDataEpacakgeConstant.Attr_Info_Content_Reference);
			
			return ePackage;
		}
		return null;
	}
	
	protected void addContentReference(EClass master,
			EClass slave,
			String referenceName){
		//定义引用
		EReference itemReference = theCoreFactory.createEReference();
		itemReference.setName(referenceName);
		itemReference.setEType(slave);
		itemReference.setUpperBound(1);
		itemReference.setContainment(false);
		itemReference.setTransient(true);
		
		//添加引用
		master.getEStructuralFeatures().add(itemReference);
	}
	
}
