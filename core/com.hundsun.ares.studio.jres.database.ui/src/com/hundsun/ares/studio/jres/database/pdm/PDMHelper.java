/**
 * 源程序名称：PDMHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：pdm导入公用代码
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableResource;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;

/**
 * pdm公共的帮助类
 * @author liaogc
 *
 */
public class PDMHelper {
	/**
	 * 创建模块属性
	 * @param root 模块根
	 * @param moduleName 模块名
	 * @param moduleChineseName 模块中文名
	 * @param path //路径
	 * @throws CoreException
	 */
	public static void createModuleProperty(IARESModuleRoot root,String moduleName,String moduleChineseName,String path) throws CoreException{

		IARESModule createdModule = root.findModule(path);
		if (createdModule == null) {
			createdModule = root.createModule(path);
		}
		IARESResource resource = createdModule
				.findResource(IARESModule.MODULE_PROPERTY_FILE);
		if (resource == null) {
			ModuleProperty property = new ModuleProperty();
			property.setValue(ICommonModel.CNAME, moduleChineseName);
			property.setValue(ICommonModel.NAME, moduleName);
			createdModule.createResource(IARESModule.MODULE_PROPERTY_FILE, property);
		}
		
	}
	
	/**
	 * 产生一个唯一的字段名
	 * @param preffix 前缀
	 * @param exclusionNames 除外的name
	 * @return
	 */
	public static String getUniqueName(String preffix,List<String> exclusionNames){
		for(int i=0;i<Integer.MAX_VALUE;i++){
			String name = preffix+i;
			if(!exclusionNames.contains(name)){
				return name;
			}
		}
		return "";
	}
	/**
	 * 返回新的业务数据类型是否在旧的业务数据类型的位置-1:类型史,标准数据类型,长度,精度相同则两者相同
	 * @param businessDataTypeList 业务数据类型列表
	 * @param businessDataType 业务数据类型
	 * @return 返回业务数据类型在业务数据类型列表中的位置,如果不存在于业务数据列表中则返回-1
	 */
	public static int indexOfBusinessDataType(List<BusinessDataType> businessDataTypeList,BusinessDataType businessDataType){
		if(businessDataTypeList==null || businessDataTypeList.size()==0){
			return -1;
		}
		for(int i =0;i<businessDataTypeList.size();i++){
			BusinessDataType oldBusinessDataType = businessDataTypeList.get(i);
			if(StringUtils.equals(businessDataType.getName(), oldBusinessDataType.getName())
					//&& StringUtils.equals(businessDataType.getChineseName(), oldBusinessDataType.getChineseName())
					&& StringUtils.equals(businessDataType.getStdType(), oldBusinessDataType.getStdType())
					&& StringUtils.equals(businessDataType.getLength(), oldBusinessDataType.getLength())
					&& StringUtils.equals(businessDataType.getPrecision(), oldBusinessDataType.getPrecision())){
				return i;
			}
		}
		return -1;
		
	}
	
	/**
	 * 查找是否有相同的标准字段:字段名,中文名,类型上相同则相同
	 * @param standardFieldList 标准字段列表
	 * @param srcStandardField 字段
	 * @return 返回标准字段在标准字段在列表中的位置,如果不存在于标准字段在列表中则返回-1
	 */
	public static int indexOfStandardField(List<StandardField> standardFieldList,StandardField srcStandardField){
		if(standardFieldList==null || standardFieldList.size()==0)return -1;
		for(int i=0;i<standardFieldList.size();i++){
			StandardField destStandardField = standardFieldList.get(i);
			if(StringUtils.equalsIgnoreCase(srcStandardField.getName(), destStandardField.getName()) && StringUtils.equalsIgnoreCase(srcStandardField.getDataType(), destStandardField.getDataType()) &&StringUtils.equalsIgnoreCase(srcStandardField.getChineseName(), destStandardField.getChineseName() )){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 查询当前表格资源的模块根
	 * @param tableResource 表资源
	 * @return  subSystem 子系统名称
	 */
	 private static String getSubSystem(IARESResource tableResource){
		if(tableResource==null){
			return "";
		}
		IARESModule module =tableResource.getModule();
		IARESModule parentModule = module;
		
		while(parentModule!=null){
			module = parentModule;
			parentModule =parentModule.getParentModule();
			
		}
		 return module.getElementName();	
			
	}
	 
	 /**
		 * 查询模块根
		 * @param IARESModule 模块
		 * @return  subSystem 子系统名称
		 */
		 public static String getSubSystem(IARESModule module){
			if(module==null){
				return "";
			}
			IARESModule parentModule = module;
			
			while(parentModule!=null){
				module = parentModule;
				parentModule =parentModule.getParentModule();
				
			}
			 return module.getElementName();	
				
		}

	/**
	 * 把table资源缓存起来,不同子系统可能存在同名表格
	 * 
	 * @param tabInfoList
	 * @return List<PDMTableResource>一个map,以表名为key
	 * @throws ARESModelException
	 */
		public static Map<String ,List<PDMTableResource>> getTableResourceMap(List<ReferenceInfo> tabInfoList) throws ARESModelException{
			Map<String,List<PDMTableResource>>tableResourceMap = new HashMap<String,List<PDMTableResource>>();
			for(ReferenceInfo referenceInfo:tabInfoList){
				IARESResource tableResource =referenceInfo.getResource();
				TableResourceData tableInfo = tableResource.getInfo(TableResourceData.class);
				String subSystem = getSubSystem(tableResource);//获取表资源对应的子系统
				String tableName =tableInfo.getName();
				PDMTableResource pdmTableResource = new PDMTableResource(tableResource,tableInfo,subSystem);
					if(tableResourceMap.get(tableName)!=null){
						tableResourceMap.get(tableName).add(pdmTableResource);
					}else{
						List<PDMTableResource> standardFieldCategory = new ArrayList<PDMTableResource>();
						standardFieldCategory.add(pdmTableResource);
						tableResourceMap.put(tableName, standardFieldCategory);
					}
				
			}
			return tableResourceMap;
		}
		
		/**
		 * 根据类型数据类型关联默认值
		 * @param businessDataTypeList //默认值类型列表
		 * @param typeDefaultValueList //默认值列表
		 */
		public static void associateDefaultValueByStdType(List<BusinessDataType>  businessDataTypeList,List<TypeDefaultValue> typeDefaultValueList){
			for(BusinessDataType businessDataType:businessDataTypeList){
				String bStdType = businessDataType.getStdType();
				for(TypeDefaultValue typeDefaultValue:typeDefaultValueList){
					if(StringUtils.isBlank(businessDataType.getDefaultValue())){
						String typeDefaultValueName  = typeDefaultValue.getName();//取得默认值名
						if(StringUtils.startsWithIgnoreCase(bStdType, "std")){//如果业务数据类型是标准的
							String prefix = StringUtils.substring(bStdType,0 ,3);
							String suffix = StringUtils.substring(bStdType, 3) ;//取得业务数据类型的3位以后的信息
							if(StringUtils.startsWithIgnoreCase(typeDefaultValueName, suffix)){//如果默认值以suffix为前前缀
								businessDataType.setDefaultValue(typeDefaultValueName);//设置业务数据类型的默认值
								break;
							}
						}
					}
				}
			}
		}
		
		/**
		 * 根据 标准字段名获得真实类型
		 * @param name
		 * @return 标准字段对应的真实类型
		 */
		public static String getRealDataType(IARESProject project ,String name,String databaseType){
				String dataType =StringUtils.EMPTY;
				StandardDataType stdType = null;
				if(StringUtils.isBlank(databaseType)){
					databaseType = "oracle";
				}
				try {
					stdType = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			
				BusinessDataType busType = null;
				try {
					busType = MetadataServiceProvider.getBusinessDataTypeOfStdFieldByName(project, name);
				} catch (Exception e) {
					//e.printStackTrace();
				}
				
				if((stdType != null)  && ( busType!= null))//标准字段
				{
					dataType = stdType.getValue(databaseType);
					String  length = StringUtils.defaultIfBlank(busType.getLength(), "0");
					String precision =StringUtils.defaultIfBlank(busType.getPrecision(),"0");
					
					if(StringUtils.indexOf(dataType, "$L")>-1){
						dataType = dataType.replace("$L", length);
					}
					if(StringUtils.indexOf(dataType, "$P")>-1){
						dataType = dataType.replace("$P", precision);
					}
					
				}
			
			
			return dataType;
		}
		
		
		/**
		 * 返回导入模式(从项目属性中获得)
		 * @return
		 */
		public static boolean getImportMode(IARESProject project){
			return (new DatabaseUtil()).isNonStdFiledAllowed(project);
		}
		
		
}
