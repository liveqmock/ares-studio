package com.hundsun.ares.studio.jres.basicdata.logic.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataField;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

public class BasicDataEpackageUtil {

	/**
	 * 获取从表引用属性
	 * @param epackage
	 * @return
	 */
	public static EReference getSlaveItemReference(EPackage ePackage){
		if(!contains(ePackage, IBasicDataEpacakgeConstant.MasterItem)){
			return null;
		}
		EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
		return (EReference)master.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Slave_Items);
	}
	
	
	/**
	 * 获取信息表引用
	 * @param ePackage
	 * @return
	 */
	public static EReference getInfoItemReference(EPackage ePackage){
		if(!contains(ePackage, IBasicDataEpacakgeConstant.ResourceRoot)){
			return null;
		}
		EClass master = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.ResourceRoot);
		return (EReference)master.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
	}
	
	
	
	/**
	 * 是否包含类
	 * @param ePackage
	 * @param className
	 * @return
	 */
	public static boolean contains(EPackage ePackage,String className){
		if(null != ePackage.getEClassifier(className)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getAttrColumnName(IARESResource resoruce,EAttribute attribute){
		String colName = attribute.getName();
		if(isStandardField(attribute)){
			StandardField field = JRESResProviderUtil.getMetadataModel(resoruce.getARESProject(), attribute.getName(),IMetadataRefType.StdField , StandardField.class);
			if(null != field){//标准字段时
				 colName = field.getChineseName();
				if (StringUtils.isBlank(colName) || StringUtils.equalsIgnoreCase(colName, "NULL")) {
					colName = field.getName();
				}
			}
		}
		else{
			if(!isStandardField(attribute)){
				colName = getChineseName(attribute); 
				if(StringUtils.isBlank(colName) ){
					colName = attribute.getName();
				}
			}
		}
		
		return colName;
	}
	
	/**
	 * 过滤一些属性
	 * @param eClass
	 * @return
	 */
	public static EAttribute[] filterAttr(EClass eClass){
		List<EAttribute> tList = new ArrayList<EAttribute>();
		for(EAttribute attr : eClass.getEAllAttributes()){
			if(attr != MetadataPackage.Literals.NAMED_ELEMENT__NAME &&
					attr != MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME &&
					attr != MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION &&
					attr != MetadataPackage.Literals.METADATA_ITEM__REF_ID){
				tList.add(attr);
			}
		}
		return tList.toArray(new EAttribute[0]);
	}
	
	/**
	 * 添加主键注释
	 * @param eClass
	 * @param keys
	 */
	public static void addMasterKeyAnnotation(EClass eClass,String[] keys){
		StringBuffer content = new StringBuffer();
		for(int i = 0; i < keys.length ; i++){
			if(i == keys.length - 1){
				content.append(keys[i]);
			}else{
				content.append(keys[i] + IBasicDataEpacakgeConstant.Annotation_Key_Seprator);
			}
		}
		
		EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
		annotation.setSource(IBasicDataEpacakgeConstant.Annotation_Source);
		annotation.getDetails().put(IBasicDataEpacakgeConstant.Annotation_Key_MasterKey, 
				content.toString());
		eClass.getEAnnotations().add(annotation);
	}
	
	
	/**
	 * 获取主键注释
	 * @param eClass
	 * @return
	 */
	public static String[] getMasterKeyAnnotation(EClass eClass){
		EAnnotation annotation =  eClass.getEAnnotation(IBasicDataEpacakgeConstant.Annotation_Source);
		if(null == annotation){
			return new String[0];
		}
		if(!annotation.getDetails().containsKey(IBasicDataEpacakgeConstant.Annotation_Key_MasterKey)){
			return new String[0];
		}
		String content = annotation.getDetails().get(IBasicDataEpacakgeConstant.Annotation_Key_MasterKey).toString();
		return StringUtils.split(content, IBasicDataEpacakgeConstant.Annotation_Key_Seprator);
	}
	
	public static EAttribute getEAttribute(EClass eClass,String attrName){
		return (EAttribute)eClass.getEStructuralFeature(attrName);
	}
	
	/**
	 * 判断EAttribute是否是标准字段
	 * @param attribute
	 * @return
	 */
	public static boolean  isStandardField(EAttribute attribute){
		
		if(attribute instanceof BasicDataEAttribute ){
			BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
			if(basicDataEAttribute.getData() !=null){
				if(basicDataEAttribute.getData() instanceof BasicDataField){
					BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
					return basicDataField.getColumnType()==0;
				}
			}
		}
		return true;
	}
	
	/**
	 * 取出data中的业务数据类型
	 * @param attribute
	 * @return
	 */
	public static String  getDataType(EAttribute attribute){
		if(attribute instanceof BasicDataEAttribute ){
			BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
			if(basicDataEAttribute.getData() !=null){
				if(basicDataEAttribute.getData() instanceof BasicDataField){
					BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
					return basicDataField.getDataType();
				}
			}
		}
		
		return "";
		
	}
	
	/**
	 * 取出data中的中文名
	 * @param attribute
	 * @return
	 */
	public static String  getChineseName(EAttribute attribute){
		
		if(attribute instanceof BasicDataEAttribute ){
			BasicDataEAttribute basicDataEAttribute = (BasicDataEAttribute) attribute;
			if(basicDataEAttribute.getData() !=null){
				if(basicDataEAttribute.getData() instanceof BasicDataField){
					BasicDataField basicDataField = (BasicDataField) basicDataEAttribute.getData();
					return basicDataField.getChineseName();
				}
			}
		}
		return "";
		
	}
	
}
