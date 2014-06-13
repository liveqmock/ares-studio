/**
 * 源程序名称：PDMImportExecel.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：导入评审后的标准字段与业务数据类型
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.hundsun.ares.studio.jres.database.utils.DatabaseUtils;
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
 * pdm第二次导入
 *
 */
public class PDMImportExecel {
	
	private IARESProject project;
	private PDMImportExecelHelper helper= new PDMImportExecelHelper();
	static final Logger console = ConsoleHelper.getLogger();
	/**
	 * 导入标准字段合并评审记录
	 * @param file
	 * @param monitor
	 * @param iaresModule
	 */
	public void importPDMExcel(IARESProject pro ,String file,IProgressMonitor monitor){
		project = pro;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(file));
			PDMExcelReaderWriter pmdrw = new PDMExcelReaderWriter();
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			List<PDMStandardField> newStandardFieldList = pmdrw.standardFieldReader(wb);
			List<PDMBusinessDataType>  newBusinessDataTypeList =pmdrw.BusTypeReader(wb);
			StringBuffer errorMsg = new StringBuffer();
		   if(check(newBusinessDataTypeList,newStandardFieldList,errorMsg)){
			  importBusinessDataType(newBusinessDataTypeList,errorMsg,monitor);//业务数据类型
			  List<PDMStandardField> uniquePDMStandardField= helper.mergeStandardField(newStandardFieldList);
			  importStandardField(uniquePDMStandardField,errorMsg,monitor);//导入标准字段
			  updateTables(newStandardFieldList,errorMsg,monitor);//更新表字段
			 
			}
			//生成日志文件
			if(StringUtils.isNotBlank(errorMsg.toString())){
				DatabaseUtils.writeErrorMsgToFile(project, errorMsg.toString(), "评审记录导入异常报告"+ System.currentTimeMillis() + ".txt", monitor);
			}
		} catch (Exception e) {
			console.info(e.getMessage());
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					console.info(e.getMessage());
				}
			}
		}
		
		
		
		
	}
	

	/**
	 * 对评审后的标准字段做简单的检验
	 * @param newBusinessDataTypeList
	 * @param newStandardFieldList
	 * @param errorMsg
	 * @return
	 * @throws ARESModelException 
	 */
	private boolean check(List<PDMBusinessDataType> newBusinessDataTypeList,List<PDMStandardField> newStandardFieldList,StringBuffer errorMsg) throws ARESModelException{
			boolean checkFieldState = helper.checkField(newBusinessDataTypeList, newStandardFieldList, errorMsg);//检验字段
			boolean checkStateTable = helper.checkTable(project,newStandardFieldList,errorMsg);//检验字段所属表
			boolean checkBusinessDataType = helper.checkBusinessDataType(newBusinessDataTypeList, errorMsg);//检验业务数据类型
			return checkFieldState && checkStateTable && checkBusinessDataType;
	}
	
	
	/**
	 * 导入评审后的标准字段与业务类型
	 * @param newStandardFieldList
	 * @throws ARESModelException 
	 */
	private void importStandardField(List<PDMStandardField>newStandardFieldList,StringBuffer errorMsg,IProgressMonitor monitor) throws ARESModelException{
		IARESResource resStdField = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
		List<StandardField>  newAllStandardFieldList  =new ArrayList<StandardField>();
		if (resStdField != null) {
			StandardFieldList allStandardFieldList = resStdField.getInfo(StandardFieldList.class);
			
			
			for(PDMStandardField pdmStandardField:newStandardFieldList){
				StandardField standardField = helper.createStandardField(pdmStandardField);
				newAllStandardFieldList.add(standardField);
			}
			//处理新的业务数据类型
			 allStandardFieldList.getItems().clear();
			 Collections.sort(newAllStandardFieldList,new NameSorter());
			 allStandardFieldList.getItems().addAll(newAllStandardFieldList);
			resStdField.save(allStandardFieldList, true, monitor);
		
		}
		
	}
	
	/**
	 * 导入评审后的标准字段与业务类型
	 * @param newBusinessDataTypeList
	 * @throws ARESModelException 
	 */
	private void importBusinessDataType(List<PDMBusinessDataType> newPDMBusinessDataTypeList,StringBuffer errorMsg,IProgressMonitor monitor) throws ARESModelException{
		IARESResource resBizType = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		IARESResource resTDVType = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
		List<TypeDefaultValue> typeDefaultValueList = new ArrayList<TypeDefaultValue>();
		if(resTDVType!=null){
			typeDefaultValueList = resTDVType.getInfo(TypeDefaultValueList.class).getItems();
		}
		List<BusinessDataType>  newAllBusinessDataTypeList  =new ArrayList<BusinessDataType>();
		if (resBizType != null) {
			BusinessDataTypeList allBusinessDataTypeList = resBizType.getInfo(BusinessDataTypeList.class);
			
			//处理旧的业务数据类型
			for(BusinessDataType businessDataType:allBusinessDataTypeList.getItems()){
				int index = helper.indexOfBusinessDataType(newPDMBusinessDataTypeList, businessDataType);
				if(index!=-1){
					//BusinessDataType newBusinessDataType = helper.createBusinessDataType(newPDMBusinessDataTypeList.get(index),businessDataType);
					newPDMBusinessDataTypeList.remove(index);//相同的的业务数据类型不需要再处理
					//newAllBusinessDataTypeList.add(newBusinessDataType);
				}
			}
			
			for(PDMBusinessDataType pdmBusinessDataType:newPDMBusinessDataTypeList){
				BusinessDataType newBusinessDataType = helper.createBusinessDataType(pdmBusinessDataType);
				newAllBusinessDataTypeList.add(newBusinessDataType);
			}
			newAllBusinessDataTypeList.addAll(new ArrayList<BusinessDataType>(allBusinessDataTypeList.getItems()));//把原来的业务数据类型加上
			allBusinessDataTypeList.getItems().clear();//删除旧的业务数据类型
			//处理新的业务数据类型
			 Collections.sort(newAllBusinessDataTypeList,new NameSorter());
			 PDMHelper.associateDefaultValueByStdType(newAllBusinessDataTypeList,typeDefaultValueList);
			allBusinessDataTypeList.getItems().addAll(newAllBusinessDataTypeList);
			
			resBizType.save(allBusinessDataTypeList, true, monitor);
		
		}
	
		
	}
	
	/**
	 * 更新表格字段
	 * @param newStandardFieldList
	 * @param errorMsg
	 * @throws ARESModelException 
	 */
	private void updateTables(List<PDMStandardField>newStandardFieldList,StringBuffer errorMsg,IProgressMonitor monitor) throws ARESModelException{
		List<ReferenceInfo> tabInfoList = ReferenceManager.getInstance().getReferenceInfos( project,IDatabaseRefType.Table, true) ;
		Map<String ,List<PDMTableResource>> tableResourceMap  = PDMHelper.getTableResourceMap(tabInfoList);//表名为单位进行分类
		Map<String,List<PDMStandardField>> pdmStaneardFieldMap = helper.getStandardFieldCategoryByTable(newStandardFieldList);//表名为单位进行标准字段分类
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
										
										if(StringUtils.equals(pdmStandardField.getGenName(), tableColumn.getName())){
											
											String name = tableColumn.getName();
											if(!StringUtils.equals(name, pdmStandardField.getNewName()) && StringUtils.isNotBlank(pdmStandardField.getNewName())){
												tableColumn.setName(pdmStandardField.getNewName());
												isChanged = true;
											}
											String changeStr = helper.getModifyInfo(pdmStandardField);
											if(StringUtils.isNotBlank(changeStr)){
												StringBuffer newComments = new StringBuffer();
												newComments.append("评审记录导入--");
												newComments.append(changeStr);
												if(StringUtils.isNotBlank(tableColumn.getComments())){
													newComments.append("\r\n");
													newComments.append(tableColumn.getComments());
												}
												
												tableColumn.setComments(newComments.toString());
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
		
		tableResourceMap.clear();
		
		
	}
	
	
	
	
	
}
