/**
 * 源程序名称：SubSystemMerge.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMBusinessDataType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMStandardField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableResource;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.model.MergePojo;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author liaogc 
 * 完成各个子系统合并,主要涉及业务数据类型合并,标准字段合并,数据库表字段更新,以及产生一份待评审的标准字段报表
 */
public class PDMSubSystemMerge {
	private IARESProject project;
	private PDMSubSystemMergeHelper helper =null;
	private String outFile = "";
	static final Logger console = ConsoleHelper.getLogger();
	public void subSystemMerge(Map<String ,MergePojo> pojoMap,IARESProject project,String targetDir,IProgressMonitor monitor, StringBuffer errorMsg) throws Exception {
		this.project = project;
		helper  = new PDMSubSystemMergeHelper(this.project);
		this.outFile = targetDir;
		Collection<MergePojo> listInfo =pojoMap.values();
		List<PDMBusinessDataType> allUniquePDMBusinessDataTypeList = mergeBusinessDataType(listInfo,monitor,errorMsg);//合并业务数据类型
		helper.updateStandardFieldBusinessType(listInfo,allUniquePDMBusinessDataTypeList);
		List<PDMStandardField> allUniqueStaneardFieldList = mergeStandardField(listInfo,monitor,errorMsg);//合并标准字段
		//helper.updatePDMSStandardFieldBusinessType(allUniqueStaneardFieldList,allUniquePDMBusinessDataTypeList);//由于业务数据类型修改所以要修改相应的标准字段对应的业务数据类型
		Map<String ,List<PDMTableResource>> tableResourceMap =updateTable(allUniqueStaneardFieldList,monitor,errorMsg);//合并数据库表
		report(allUniqueStaneardFieldList,tableResourceMap,monitor,errorMsg);//产生待评审的合并的标准字段报表
	}

	/**
	 * 合并业务数据类型
	 * @param listInfo
	 * @param monitor
	 * @param errorMsg
	 * @return
	 * @throws ARESModelException
	 */
	private List<PDMBusinessDataType>  mergeBusinessDataType(Collection<MergePojo> listInfo,IProgressMonitor monitor,StringBuffer errorMsg) throws ARESModelException {
		 
		List<PDMBusinessDataType>  allPDMBusinessDataTypeList =  new ArrayList<PDMBusinessDataType>();
		IARESResource businessDataTypeResource = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		IARESResource resTDVType = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
		
		List<BusinessDataType> oldBusinessDataTypeList = new ArrayList<BusinessDataType>();
		BusinessDataTypeList allBusinessDataTypeList=null;
		if (businessDataTypeResource != null) {
			 allBusinessDataTypeList = businessDataTypeResource.getInfo(BusinessDataTypeList.class);
			oldBusinessDataTypeList.addAll(allBusinessDataTypeList.getItems());
		
		}
		List<TypeDefaultValue> typeDefaultValueList = new ArrayList<TypeDefaultValue>();
		if(resTDVType!=null){
			typeDefaultValueList = resTDVType.getInfo(TypeDefaultValueList.class).getItems();
		}
		for(MergePojo mergePojo:listInfo){
			//把各工程传过来的BusinessDataType转换成PDMBusinessDataType
			List<PDMBusinessDataType> susSystemPDMBusinessDataTypeList=helper.analyseBusinessDataType(mergePojo.getBusTypes(),mergePojo.getStdFields(),mergePojo.getSubSyses());
			allPDMBusinessDataTypeList.addAll(susSystemPDMBusinessDataTypeList);
			
		}
		List<PDMBusinessDataType> allUniquePDMBusinessDataTypeList = helper.mergeBusinessDataType(allPDMBusinessDataTypeList);
		List<BusinessDataType> allUniqueBusinessDataTypeList = new ArrayList<BusinessDataType>();
		for(PDMBusinessDataType pmdBusinessDataType:allUniquePDMBusinessDataTypeList){
			BusinessDataType newBusinessDataType =helper.createBusinessDataType(pmdBusinessDataType);//把合并好的PDMBusinessDataType转换成BusinessDataType
			int index = PDMHelper.indexOfBusinessDataType(oldBusinessDataTypeList, newBusinessDataType);
			if(index==-1){
				allUniqueBusinessDataTypeList.add(newBusinessDataType);
			}
		}
		
		if (businessDataTypeResource != null) {
			List<BusinessDataType> totalBusinessDataTypeList = new ArrayList<BusinessDataType>();
			totalBusinessDataTypeList.addAll(allBusinessDataTypeList.getItems());
			totalBusinessDataTypeList.addAll(allUniqueBusinessDataTypeList);
			allBusinessDataTypeList.getItems().clear();
			PDMHelper.associateDefaultValueByStdType(totalBusinessDataTypeList,typeDefaultValueList);
			 Collections.sort(totalBusinessDataTypeList,new NameSorter());
			allBusinessDataTypeList.getItems().addAll(totalBusinessDataTypeList);
			businessDataTypeResource.save(allBusinessDataTypeList, true, monitor);//保存合并好的业务数据类型
		
		}
		return allUniquePDMBusinessDataTypeList;


	}
/**
 * 返回合并好的总的标准字段
 * @param listInfo
 * @param monitor
 * @param errorMsg
 * @return
 * @throws ARESModelException 
 */
	private List<PDMStandardField> mergeStandardField(Collection<MergePojo> listInfo,IProgressMonitor monitor,StringBuffer errorMsg) throws ARESModelException { 
		List<PDMStandardField>  allPDMStaneardFieldList =  new ArrayList<PDMStandardField>();
		for(MergePojo mergePojo:listInfo){
			//把每个项目传过来的标准字段列表转换成PDMStandard列表
			List<PDMStandardField> susSystemPDMStandardFieldList=helper.analyseStandardField(mergePojo.getStdFields(), mergePojo.getSubSyses());
			allPDMStaneardFieldList.addAll(susSystemPDMStandardFieldList);
			
		}
		//合并标准字段
		List<PDMStandardField> allUniquePDMStandardFieldList =helper.mergeStandardField(allPDMStaneardFieldList);
		List<StandardField> allUniqueStandardFieldList = new ArrayList<StandardField>();
		
		for(PDMStandardField pmdStandardField:allUniquePDMStandardFieldList){
			allUniqueStandardFieldList.add(helper.createStandardField(pmdStandardField));//把pmdStandardField转换成相应的StandardField
		}
		IARESResource standardFieldResource = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
		if (standardFieldResource != null) {
			StandardFieldList allStandardFieldList = standardFieldResource.getInfo(StandardFieldList.class);
			allStandardFieldList.getItems().clear();
			Collections.sort(allUniqueStandardFieldList,new NameSorter());
			allStandardFieldList.getItems().addAll(allUniqueStandardFieldList);
			standardFieldResource.save(allStandardFieldList, true, monitor);//保存新的处理过的标准字段
		}
		return allUniquePDMStandardFieldList;

	}
	
	/**
	 * 更新标准字段相关表的相关列
	 * @param allUniquePDMStandardFieldList
	 * @param monitor
	 * @param errorMsg
	 * @throws ARESModelException
	 */
	private Map<String ,List<PDMTableResource>> updateTable(List<PDMStandardField> allUniquePDMStandardFieldList ,IProgressMonitor monitor,StringBuffer errorMsg) throws ARESModelException {

		List<ReferenceInfo> tabInfoList = ReferenceManager.getInstance().getReferenceInfos( project,IDatabaseRefType.Table, true) ;
		Map<String,List<PDMStandardField>> pdmStaneardFieldMap = helper.getStandardFieldCategoryByTable(allUniquePDMStandardFieldList);//表名为单位进行标准字段分类
		Map<String ,List<PDMTableResource>> tableResourceMap  = PDMHelper.getTableResourceMap(tabInfoList);//表名为单位进行分类
		Set<String> tableNames = pdmStaneardFieldMap.keySet();
		for(String tableName:tableNames){
			if(StringUtils.isNotBlank(tableName)){
				List<PDMStandardField> pdmtandardFieldList = pdmStaneardFieldMap.get(tableName);
				List<PDMTableResource> pdmTableResources= tableResourceMap.get(tableName);//一个同名表资源可以存在不同的子系统中
				if(pdmTableResources!=null){
					for(PDMTableResource pdmTableResource:pdmTableResources){//以表资源为单位进行更新
						TableResourceData tableInfo = pdmTableResource.getTableInfo();
						boolean isChanged = false;
						for(PDMStandardField pdmStandardField:pdmtandardFieldList){
							
							if(pdmTableResource!=null 
									&& pdmStandardField.getBolongSubSystemList().size()>0 
									&& StringUtils.isNotBlank(pdmTableResource.getSubSystem())
									&& pdmStandardField.getBolongSubSystemList().contains(pdmTableResource.getSubSystem())){
							    
								if(StringUtils.equals(tableName, tableInfo.getName())){
									
									for(TableColumn tableColumn:tableInfo.getColumns()){
										
										if(StringUtils.equals(pdmStandardField.getOldName(), tableColumn.getName())){
											String name = tableColumn.getName();
											if(!StringUtils.equals(name, pdmStandardField.getGenName()) && StringUtils.isNotBlank(pdmStandardField.getGenName())){
												tableColumn.setName(pdmStandardField.getGenName());
												isChanged = true;
											}
											String changeStr = helper.getModifyInfo(pdmStandardField);
											if(StringUtils.isNotBlank(changeStr)){
												tableColumn.setComments(changeStr+"|"+StringUtils.defaultIfBlank(tableColumn.getComments(), ""));
												isChanged = true;
											}
										    break;	
											
										}
									}
									
								}
								
							}
							
					}
					if(isChanged){
							pdmTableResource.getResource().save(pdmTableResource.getTableInfo(), true, monitor);
						}
					}
						
				}
				
			}
		}
		
		return tableResourceMap;
		
	
	}
	/**
	 * 产生标准字段合并评审记录
	 * @param pmdStandardFieldList
	 * @param monitor
	 * @param errorMsg
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void report(List<PDMStandardField> pmdStandardFieldList,Map<String ,List<PDMTableResource>> tableResourceMap,IProgressMonitor monitor,StringBuffer errorMsg) throws IllegalAccessException, InvocationTargetException  {
		
		List<PDMStandardField> allPDMStandardFieldList = helper.getAllPDMStandardFieldList(pmdStandardFieldList,tableResourceMap);
		InputStream input = null;
		OutputStream output = null;
		try {
			input = DatabaseUI.getDefault().getBundle().getEntry("template/标准字段合并评审记录.xls").openStream();
			HSSFWorkbook wb = new HSSFWorkbook(input);
			PDMExcelReaderWriter pdmWriter = new PDMExcelReaderWriter();
			pdmWriter.standardFieldWriter(wb, allPDMStandardFieldList);
			output = new FileOutputStream(outFile);
			wb.write(output);
		} catch (IOException e) {
			console.info(e.getMessage());
		}finally{
			org.apache.commons.io.IOUtils.closeQuietly(input);
			org.apache.commons.io.IOUtils.closeQuietly(output);
		}
		
	}
	
	
	

}
