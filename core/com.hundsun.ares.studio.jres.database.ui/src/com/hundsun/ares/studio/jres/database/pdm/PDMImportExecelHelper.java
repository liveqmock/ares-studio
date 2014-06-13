/**
 * 源程序名称：PDMImportExecelHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMBusinessDataType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMStandardField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableResource;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc
 * 第一次导入excel帮助类
 */
public class PDMImportExecelHelper {

	/**
	 * 返回业务类型在评审后的业务类型列表中的位置,如果不在返回-1.如果类型名,标准类型名,长度,精度相同则相同
	 * @param pdmBusinessDataTypeList
	 * @param businessDataType
	 * @return
	 */
	public int indexOfBusinessDataType(List<PDMBusinessDataType> pdmBusinessDataTypeList,BusinessDataType businessDataType){
		if(pdmBusinessDataTypeList==null || pdmBusinessDataTypeList.size()==0){
			return -1;
		}
		for(int i =0;i<pdmBusinessDataTypeList.size();i++){
			PDMBusinessDataType pdmBusinessDataType = pdmBusinessDataTypeList.get(i);
			if(StringUtils.equals(businessDataType.getName(), pdmBusinessDataType.getTypeName())
					//&& StringUtils.equals(businessDataType.getChineseName(), pdmBusinessDataType.getTypeChineseName())
					&& StringUtils.equals(businessDataType.getStdType(), pdmBusinessDataType.getStandardTypeName())
					&& StringUtils.equals(businessDataType.getLength(), pdmBusinessDataType.getLength())
					&& StringUtils.equals(businessDataType.getPrecision(), pdmBusinessDataType.getPrecision())){
				return i;
			}
		}
		return -1;
		
	}
	/**
	 * 返回标准字段在评审后的标准字段列表中的位置,如果不在评审后的标准字段列表,返回-1.如果字段名,中文名,业务数据类型名相同则相同
	 * @param pdmStandardFieldList
	 * @param standardField
	 * @return
	 */
	public int indexOfStandardField(List<PDMStandardField> pdmStandardFieldList,StandardField standardField){
		if(pdmStandardFieldList==null || pdmStandardFieldList.size()==0){
			return -1;
		}
		for(int i=0;i<pdmStandardFieldList.size();i++){
			PDMStandardField pdmstandardField = pdmStandardFieldList.get(i);
			String fieldName = StringUtils.isBlank(pdmstandardField.getNewName())?pdmstandardField.getGenName():pdmstandardField.getNewName();
			if(StringUtils.equals(standardField.getName(), fieldName)){
				return i;
			}
		}
		return -1;
		
	}
	/**
	 * 根据PDMStandardField以及standardField构建一个新的standardField
	 * @param pdmStandardField
	 * @param standardField
	 * @return
	 */
	public StandardField createStandardField(PDMStandardField pdmStandardField,StandardField standardField){
		StandardField newStandardField = MetadataFactory.eINSTANCE.createStandardField();
		String fieldName = StringUtils.isBlank(pdmStandardField.getNewName())?pdmStandardField.getGenName():pdmStandardField.getNewName();
		newStandardField.setName(fieldName);
		String chineseName =  StringUtils.isBlank(pdmStandardField.getNewChineseName())?pdmStandardField.getOldChineseName():pdmStandardField.getNewChineseName();
		newStandardField.setChineseName(chineseName);
		String busType = StringUtils.isBlank(pdmStandardField.getNewBusType())?pdmStandardField.getGenBusType():pdmStandardField.getNewBusType();
		
		newStandardField.setDataType(busType);
		String comment =StringUtils.isBlank(pdmStandardField.getNewComment())?pdmStandardField.getNewComment():pdmStandardField.getOldName();
		newStandardField.setDescription(comment);
		if(StringUtils.isNotBlank(pdmStandardField.getDictId())){
			newStandardField.setRefId(pdmStandardField.getDictId());	
		}
		return newStandardField;
		
	}
	/**
	 * 根据PDMStandardField构建一个新的standardField
	 * @param pdmStandardField
	 * @return
	 */
	public StandardField createStandardField(PDMStandardField pdmStandardField){
		StandardField standardField = MetadataFactory.eINSTANCE.createStandardField();
		String fieldName = StringUtils.isBlank(pdmStandardField.getNewName())?pdmStandardField.getGenName():pdmStandardField.getNewName();
		standardField.setName(fieldName);
		String chineseName =  StringUtils.isBlank(pdmStandardField.getNewChineseName())?pdmStandardField.getOldChineseName():pdmStandardField.getNewChineseName();
		standardField.setChineseName(chineseName);
		String busType = StringUtils.isBlank(pdmStandardField.getNewBusType())?pdmStandardField.getGenBusType():pdmStandardField.getNewBusType();
		standardField.setDataType(busType);
		String comment =StringUtils.isBlank(pdmStandardField.getNewComment())?pdmStandardField.getNewComment():pdmStandardField.getOldName();
		standardField.setDescription(comment);
		if(StringUtils.isNotBlank(pdmStandardField.getDictId())){
			standardField.setRefId(pdmStandardField.getDictId());	
		}
		return standardField;
		
	}
	
	/**
	 * 根据PDMBusinessDataType构建一个新的BusinessDataType
	 * @param pdmBusinessDataType
	 * @return
	 */
	public BusinessDataType createBusinessDataType(PDMBusinessDataType pdmBusinessDataType){
		BusinessDataType newBusinessDataType = MetadataFactory.eINSTANCE.createBusinessDataType();
		newBusinessDataType.setName(pdmBusinessDataType.getTypeName());
		newBusinessDataType.setChineseName(pdmBusinessDataType.getTypeChineseName());
		newBusinessDataType.setStdType(pdmBusinessDataType.getStandardTypeName());
		if(StringUtils.isNotBlank(pdmBusinessDataType.getDefaultValue())){
			newBusinessDataType.setDefaultValue(pdmBusinessDataType.getDefaultValue());
		}
		if(StringUtils.isNotBlank(pdmBusinessDataType.getLength())){
			newBusinessDataType.setLength(pdmBusinessDataType.getLength());
		}
		if(StringUtils.isNotBlank(pdmBusinessDataType.getPrecision())){
			newBusinessDataType.setPrecision(pdmBusinessDataType.getPrecision());
		}
		newBusinessDataType.setDescription(pdmBusinessDataType.getComment());
		return newBusinessDataType;
		
	}
	
	/**
	 * 根据PDMBusinessDataType以及旧的businessDataType构建一个新的BusinessDataType
	 * @param pdmBusinessDataType
	 * @return
	 */
	public BusinessDataType createBusinessDataType(PDMBusinessDataType pdmBusinessDataType,BusinessDataType businessDataType){
		BusinessDataType newBusinessDataType = MetadataFactory.eINSTANCE.createBusinessDataType();
		newBusinessDataType.setName(pdmBusinessDataType.getTypeName());
		newBusinessDataType.setChineseName(pdmBusinessDataType.getTypeChineseName());
		newBusinessDataType.setStdType(pdmBusinessDataType.getStandardTypeName());
		if(StringUtils.isNotBlank(pdmBusinessDataType.getDefaultValue())){
			newBusinessDataType.setDefaultValue(pdmBusinessDataType.getDefaultValue());
		}
		if(StringUtils.isNotBlank(pdmBusinessDataType.getLength())){
			newBusinessDataType.setLength(pdmBusinessDataType.getLength());
		}
		if(StringUtils.isNotBlank(pdmBusinessDataType.getPrecision())){
			newBusinessDataType.setPrecision(pdmBusinessDataType.getPrecision());
		}
		newBusinessDataType.setDescription(pdmBusinessDataType.getComment());
		return newBusinessDataType;
		
	}
	/**
	 * 合并相同的标准字段
	 * @param pmdStandardFieldList
	 * @return
	 */
	public List<PDMStandardField> mergeStandardField(List<PDMStandardField> pmdStandardFieldList){
		Map<String,PDMStandardField>pdmStaneardFieldMap = getStandardFieldCategory(pmdStandardFieldList);//此时value值是各字段都不相同(相同的被覆盖了)
		 List<PDMStandardField> mergeList = new  ArrayList<PDMStandardField>();
		 mergeList.addAll(pdmStaneardFieldMap.values());
		return mergeList;
		
	
		
	}
	
	
	
	
	
	/**
	 * 产生修改信息
	 * @param pdmFeild
	 * @return
	 */
	 public String getModifyInfo(PDMStandardField pdmFeild){
		StringBuffer modifyStr = new StringBuffer();
		if(StringUtils.isNotBlank(pdmFeild.getNewName()) && !StringUtils.equals(pdmFeild.getGenName(), pdmFeild.getNewName())){
			modifyStr.append("字段名:").append("\""+pdmFeild.getGenName()+"\"").append("修改为:").append("\""+pdmFeild.getNewName()+"\"");
		}
		if(StringUtils.isNotBlank(pdmFeild.getNewChineseName())){
			if(StringUtils.isNotBlank(modifyStr.toString())){
				modifyStr.append("||");
			}
			modifyStr.append("中文名:").append("\""+pdmFeild.getOldChineseName()+"\"").append("修改为:").append("\""+pdmFeild.getNewChineseName()+"\"");
		}
		if(StringUtils.isNotBlank(pdmFeild.getNewBusType())){
			if(StringUtils.isNotBlank(modifyStr.toString())){
				modifyStr.append("||");
			}
			modifyStr.append("类型:").append("\""+pdmFeild.getGenBusType()+"\"").append("修改为:").append("\""+pdmFeild.getNewBusType()+"\"");
		}
		if(StringUtils.isNotBlank(pdmFeild.getNewComment())){
			if(StringUtils.isNotBlank(modifyStr.toString())){
				modifyStr.append("||");
			}
			modifyStr.append("备注:").append("\""+pdmFeild.getOldComment()+"\"").append("修改为:").append("\""+pdmFeild.getNewComment()+"\"");
		}
		return modifyStr.toString();
	}
	
	/**
	 * 以字段名为key对PDMStandardField进行分类
	 * @param pmdStandardFieldList
	 * @return
	 */
	private Map<String,PDMStandardField>getStandardFieldCategory(List<PDMStandardField> pmdStandardFieldList){
		Map<String,PDMStandardField>pdmStaneardFieldMap = new HashMap<String,PDMStandardField>();
		
		for(PDMStandardField pmdStandardField:pmdStandardFieldList){
			String name = StringUtils.isNotBlank(pmdStandardField.getNewName())?pmdStandardField.getNewName():pmdStandardField.getGenName();
			pdmStaneardFieldMap.put(name, pmdStandardField);
		}
		return pdmStaneardFieldMap;
	}
	/**
	 * 以tableName对PDMStandardField进行分类
	 * @param pmdStandardFieldList
	 * @return
	 */
	public  Map<String,List<PDMStandardField>>getStandardFieldCategoryByTable(List<PDMStandardField> pmdStandardFieldList){
		Map<String,List<PDMStandardField>>pdmStaneardFieldMap = new HashMap<String,List<PDMStandardField>>();
		
		for(PDMStandardField pmdStandardField:pmdStandardFieldList){
			for(String tableName:pmdStandardField.getBelongTableList()){
				if(StringUtils.isNotBlank(tableName)){
					if(pdmStaneardFieldMap.get(tableName)!=null){
						pdmStaneardFieldMap.get(tableName).add(pmdStandardField);
					}else{
						List<PDMStandardField> standardFieldCategory = new ArrayList<PDMStandardField>();
						standardFieldCategory.add(pmdStandardField);
						pdmStaneardFieldMap.put(tableName, standardFieldCategory);
					}
				}
			}
			
		}
		return pdmStaneardFieldMap;
	}
	/**
	 * 检查表是否存在
	 * @param project
	 * @param newStandardFieldList
	 * @param errorMsg
	 * @return
	 * @throws ARESModelException
	 */
	public boolean checkTable(IARESProject project,List<PDMStandardField> newStandardFieldList,StringBuffer errorMsg) throws ARESModelException{
		StringBuffer tableError = new StringBuffer();
		List<ReferenceInfo> tabInfoList = ReferenceManager.getInstance().getReferenceInfos( project,IDatabaseRefType.Table, true) ;
		Map<String ,List<PDMTableResource>> tableResourceMap  = PDMHelper.getTableResourceMap(tabInfoList);//获得所有的数据库表,并以表名为key
			for(int i =0;i< newStandardFieldList.size();i++ ){
				PDMStandardField std = newStandardFieldList.get(i);
				String name = StringUtils.isNotBlank(std.getNewName())?std.getNewName():std.getGenName();
				String subSystem = std.getBolongSubSystemList().size()==0?"":std.getBolongSubSystemList().get(0);
				if( StringUtils.isBlank(subSystem)){//如果所属字系统不存在,不做此字段的所属表检验
					break;
				}
				for(String tableName:std.getBelongTableList()){
					List<PDMTableResource> tableList = tableResourceMap.get(tableName);
					
					if(tableList==null  || tableList.size()==0){//如果整个工程中不存在表
						tableError.append("字段:").append(name).append("所属表:").append(tableName).append("不存在").append("(第"+(i+3)+"行)").append("\r\n");
					}else{//如果工程中存在字段是所属的表，则检查此表是不是在所属的子系统中
						boolean find = false;
						for(PDMTableResource pdmTableResource:tableList){
							//如果表名相同,子系统也相同则存在
							if(StringUtils.equals(pdmTableResource.getSubSystem(),subSystem) && StringUtils.isNotBlank(subSystem)){
								find = true;
								break;
							}
						}
						if(!find){//如果不在所属表不存在
							tableError.append("字段:").append(name).append("所属表:").append(tableName).append("不存在").append("(第"+(i+3)+"行)").append("\r\n");
						}
					}
				}
				
			}
			if (StringUtils.isNotBlank(tableError.toString())) {
				errorMsg.append(tableError.toString());
				return false;
			}
			return true;
		}

	/**
	 * 检查标准字段存在存在歧义:字段名相同,类型类不相同则存在歧义
	 * @param newBusinessDataTypeList
	 * @param newStandardFieldList
	 * @param errorMsg
	 * @return
	 */
	public boolean checkField(List<PDMBusinessDataType> newBusinessDataTypeList,List<PDMStandardField> newStandardFieldList,StringBuffer errorMsg){
		for(int i =0;i< newStandardFieldList.size();i++ ){
			PDMStandardField std = newStandardFieldList.get(i);
			String stdName = StringUtils.isNotBlank(std.getNewName())?std.getNewName():std.getGenName();
			String stdType = StringUtils.isNotBlank(std.getNewBusType())?std.getNewBusType():std.getGenBusType();
			for(int j =0;j< newStandardFieldList.size();j++ ){
				if(j>i){
					PDMStandardField subStd = newStandardFieldList.get(j);
					String subStdName = StringUtils.isNotBlank(subStd.getNewName())?subStd.getNewName():subStd.getGenName();
					String subBusType = StringUtils.isNotBlank(subStd.getNewBusType())?subStd.getNewBusType():subStd.getGenBusType();
					if (StringUtils.equals(stdName, subStdName) && !StringUtils.equals(stdType, subBusType)) {
						errorMsg.append("字段名："+stdName+" ，资源类型存在歧义: "+"第"+(i+3)+"行:"+stdType +","+"第"+(j+3)+"行:"+subBusType+"\r\n");
					}
				
				}
			
			}
		}
		if (StringUtils.isNotBlank(errorMsg.toString())) {
			return false;
		}
		return true;
	}
	/**
	 * 检验业务数据类型是否存在相同的类型名
	 * @param newBusinessDataTypeList
	 * @param errorMsg
	 * @return
	 */
	public boolean checkBusinessDataType(List<PDMBusinessDataType> newBusinessDataTypeList,StringBuffer errorMsg){
		
		 class LinePDMBusinessDataType {
			public LinePDMBusinessDataType(PDMBusinessDataType pdmBusinessDataType,int line ) {
				 this.line = line;
				 this.pdmBusinessDataType = pdmBusinessDataType;
			 }
			 private PDMBusinessDataType pdmBusinessDataType;
			 /**
			 * @return the pdmBusinessDataType
			 */
			public PDMBusinessDataType getPdmBusinessDataType() {
				return pdmBusinessDataType;
			}
			
			public int getLine() {
				return line;
			}
		
			private int line;
			
		}
		
		Map<String,List<LinePDMBusinessDataType>> sameNameBusinessDataTypeMap= new HashMap<String,List<LinePDMBusinessDataType>>();
		StringBuffer businessDataTypeError = new StringBuffer();
		//以类型为key对业务数据类型分类
		for(int i =0;i< newBusinessDataTypeList.size();i++ ){
			PDMBusinessDataType pdmBusinessDataType = newBusinessDataTypeList.get(i);
			String name = StringUtils.isNotBlank(pdmBusinessDataType.getTypeName())?pdmBusinessDataType.getTypeName():pdmBusinessDataType.getOldTypeName();
			if(sameNameBusinessDataTypeMap.get(name)!=null){
				sameNameBusinessDataTypeMap.get(name).add(new LinePDMBusinessDataType(pdmBusinessDataType,(i+3)));
			}else{
				List<LinePDMBusinessDataType> LinePDMBusinessDataTypeList = new ArrayList<LinePDMBusinessDataType>();
				LinePDMBusinessDataTypeList.add(new LinePDMBusinessDataType(pdmBusinessDataType,(i+3)));
				sameNameBusinessDataTypeMap.put(name, LinePDMBusinessDataTypeList);
			}
			
		}
		for(String name:sameNameBusinessDataTypeMap.keySet()){
			List<LinePDMBusinessDataType> linePDMBusinessDataTypeList = sameNameBusinessDataTypeMap.get(name);
			if(linePDMBusinessDataTypeList !=null &&linePDMBusinessDataTypeList.size()>1){
				for(LinePDMBusinessDataType linePDMBusinessDataType :linePDMBusinessDataTypeList){
					businessDataTypeError.append("业务数据类型:").append(name).append("存在冲突(存在同名的业务数据类型)").append("第").append(linePDMBusinessDataType.getLine()).append("行").append("\r\n");
				}
			}
		}
		if (StringUtils.isNotBlank(businessDataTypeError.toString())) {
			errorMsg.append(businessDataTypeError.toString());
			return false;
		}
		return true;
	}

}
