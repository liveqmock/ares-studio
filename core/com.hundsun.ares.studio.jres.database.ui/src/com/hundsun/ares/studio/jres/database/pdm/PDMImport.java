/**
 * 源程序名称：PDMInport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMStandardField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTable;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMTableField;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMView;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValueList;

/**
 * @author liaogc
 *
 */
public class PDMImport {
	
	

	private PDMImportHelper helper = new PDMImportHelper();
	private ParserPDM parser = new ParserPDM();
	private IARESProject project;
	private String importFilePath = "";
	static final Logger console = ConsoleHelper.getLogger();
	private static Logger logger = Logger.getLogger(PDMImport.class);
	private boolean importMode = false;//导入模式(true:支持非标准字段,false:支持标准字段)
	
	/**
	 * 导入pdm中表相关信息:标准字段,业务数据类型,数据库表,数据库视力
	 * @param file
	 * @param monitor
	 * @param iaresModule
	 * @param genType
	 * @param genstd
	 * @param gendoc
	 */
	public void importPDM(String file,String targetDir , IProgressMonitor monitor, IARESModule iaresModule){
		importMode = PDMHelper.getImportMode(iaresModule.getARESProject());
		if(!importMode){//获得导入模式
			importStdPDM( file, targetDir ,  monitor,  iaresModule);//标准字段方式导入
		}else{
			importNoStdPDM( file, targetDir ,  monitor,  iaresModule);//非标准字段方式导入
		}
	}
	/**
	 * 标准字段方式导入
	 * @param file
	 * @param targetDir
	 * @param monitor
	 * @param iaresModule
	 */
	private void importStdPDM(String file,String targetDir , IProgressMonitor monitor, IARESModule iaresModule){

		InputStream input = null;
		OutputStream output = null;
		project = iaresModule.getARESProject();
		importFilePath = file;
		try {
			final String errorMsg = checkStandardFieldAndBusinessDataType();
			if (StringUtils.isNotBlank(errorMsg)) {
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						MessageDialog.openError(null, "不能为只读",
								errorMsg + "状态为只读,请设置为可写状态");
					}
				});
				return ;
			}
			
		parser.parse(file, iaresModule, monitor);
		Map<String,List<PDMTableField>> allTableFieldMap = parser.getAllTableFieldMap();//解决得到所有的表格中的各字段
		List<PDMTableField> uniqueAllTableField = helper.mergeTableField(allTableFieldMap);//第一次合并重复的字段
		
			IARESResource res = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
			List<StandardField> oldStandardFieldList = res.getInfo(StandardFieldList.class).getItems();
			List<PDMTableField> newUniqueAllTableField = helper.mergeTableField(uniqueAllTableField,oldStandardFieldList,project);//第二次合并标准字段
			importBusinessDataTypeAndStandardField(newUniqueAllTableField,monitor);//导入业务数据类型与标准字段
			importTable(allTableFieldMap,monitor);//导入表
			importView(monitor);
			List<PDMStandardField> exportList = getStandardFieldLsit(uniqueAllTableField);
			input = DatabaseUI.getDefault().getBundle().getEntry("template/标准字段合并评审记录.xls").openStream();
			HSSFWorkbook wb = new HSSFWorkbook(input);
			PDMExcelReaderWriter pdmWriter = new PDMExcelReaderWriter();
			pdmWriter.standardFieldWriter(wb, exportList);
			if (StringUtils.isBlank(targetDir)) {
				targetDir = StringUtils.substringBeforeLast(importFilePath, File.separator);
				targetDir = targetDir + "\\标准字段合并评审记录.xls";
			}
			output = new FileOutputStream(targetDir);
			wb.write(output);
		}catch (ARESModelException e) {
			console.info(e.getMessage());
			throw new RuntimeException("数据处理异常，请检查元数据资源！");
		} catch (IOException e) {
			console.info(e.getMessage());
			throw new RuntimeException("评审记录 生成异常，请检查该文件是否打开或只读！");
		}catch (Exception e) {
			console.info(e.getMessage());
			throw new RuntimeException("PDM导入失败！");
		} finally{
			org.apache.commons.io.IOUtils.closeQuietly(input);
			org.apache.commons.io.IOUtils.closeQuietly(output);
		}
		
	
	}

	/**
	 * 非标准字段方式导入
	 * @param file
	 * @param targetDir
	 * @param monitor
	 * @param iaresModule
	 */
	public void importNoStdPDM(String file, String targetDir,
			IProgressMonitor monitor, IARESModule iaresModule) {


		InputStream input = null;
		OutputStream output = null;
		project = iaresModule.getARESProject();
		importFilePath = file;
		try {
			final String errorMsg = checkStandardFieldAndBusinessDataType();
			if (StringUtils.isNotBlank(errorMsg)) {
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						MessageDialog.openError(null, "不能为只读",
								errorMsg + "状态为只读,请设置为可写状态");
					}
				});
				return ;
			}
			
			parser.parse(file, iaresModule, monitor);
			Map<String, List<PDMTableField>> allTableFieldMap = parser.getAllTableFieldMap();// 解决得到所有的表格中的各字段
			importBusinessDataTypeOfNoStd(allTableFieldMap, monitor);
			importTable(allTableFieldMap, monitor);// 导入表
			importView(monitor);
		}catch (ARESModelException e) {
			console.info(e.getMessage());
			throw new RuntimeException("数据处理异常，请检查元数据资源！");
		} catch (IOException e) {
			console.info(e.getMessage());
			throw new RuntimeException("评审记录 生成异常，请检查该文件是否打开或只读！");
		}catch (Exception e) {
			console.info(e.getMessage());
			throw new RuntimeException("PDM导入失败！");
		} finally{
			org.apache.commons.io.IOUtils.closeQuietly(input);
			org.apache.commons.io.IOUtils.closeQuietly(output);
		}
		
	
	
	}
	
	
	/**
	 * 导入业务数据类型与标准字段
	 * @param uniqueAllTableField
	 * @param monitor
	 * @throws ARESModelException
	 */
	private void importBusinessDataTypeAndStandardField(List<PDMTableField> uniqueAllTableField,IProgressMonitor monitor) throws ARESModelException{
		List<BusinessDataType> businessDataTypeList = new ArrayList<BusinessDataType>();
		List<StandardField> standardFieldList = new ArrayList<StandardField>();
		
		IARESResource res1 = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		BusinessDataTypeList allBusinessDataTypeList = res1.getInfo(BusinessDataTypeList.class);
		List<BusinessDataType> oldBusinessDataTypeList = new ArrayList<BusinessDataType>(allBusinessDataTypeList.getItems());
		
		IARESResource res2 = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
		
		StandardFieldList allStandardFieldList = res2.getInfo(StandardFieldList.class);
		
		IARESResource resTDVType = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
		List<TypeDefaultValue> typeDefaultValueList = new ArrayList<TypeDefaultValue>();
		if(resTDVType!=null){
			typeDefaultValueList = resTDVType.getInfo(TypeDefaultValueList.class).getItems();
		}
		
		List<StandardField> oldStandardFieldList = new ArrayList<StandardField>();
		oldStandardFieldList.addAll(allStandardFieldList.getItems());
		
		List<String> exclusionNames = new ArrayList<String>();
		
		/*for(StandardField standardField:oldStandardFieldList){
			exclusionNames.add(standardField.getName());
		}*/
		String databaseType = "oracle";
		try {
			databaseType =  project.getProjectProperty().getString("tabledir");
			int _index = -1 ;
			int dotIndex = -1;
			if((_index=StringUtils.lastIndexOf(databaseType,"_" ))>-1  && (dotIndex=StringUtils.lastIndexOf(databaseType,"."))>-1 ){
				databaseType = StringUtils.substring(databaseType,_index+1, dotIndex).toLowerCase();
			}else{
				databaseType = "oracle";
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		for(PDMTableField tableField:uniqueAllTableField){
			Map<String,String> fieldInfo =helper.analyseTableFeild(tableField, project,databaseType);
			tableField.setBusType(fieldInfo.get("BusinessDataType_name"));
			if(!exclusionNames.contains(tableField.getNewName())){
				StandardField standardField = helper.createcreateStandardField(fieldInfo);
				BusinessDataType businessDataType = helper.createBusinessDataType(fieldInfo,importMode);
				tableField.setBusType(fieldInfo.get("BusinessDataType_name"));
				int indexB =PDMHelper.indexOfBusinessDataType(oldBusinessDataTypeList, businessDataType);
				if(indexB==-1){//如果不存在相同的业务数据类型则添加
					businessDataTypeList.add(businessDataType);
				}
				int indexS = PDMHelper.indexOfStandardField(oldStandardFieldList, standardField);
				if(indexS==-1){//如果不存在相同的标准字段则添加
					standardFieldList.add(standardField);
				}
				exclusionNames.add(tableField.getNewName());
			}else if(exclusionNames.contains(tableField.getNewName())){
				StandardField standardField = helper.createcreateStandardField(fieldInfo);
				if(PDMHelper.indexOfStandardField(oldStandardFieldList, standardField)>-1){
					continue;
				}else{
					String name = PDMHelper.getUniqueName(tableField.getNewName(), exclusionNames);
					tableField.setNewName(name);
					Map<String,String> fieldInfo1 =helper.analyseTableFeild(tableField, project,databaseType);
					StandardField standardField1 = helper.createcreateStandardField(fieldInfo1);
					BusinessDataType businessDataType = helper.createBusinessDataType(fieldInfo1,importMode);
					tableField.setBusType(fieldInfo.get("BusinessDataType_name"));
					int indexB =PDMHelper.indexOfBusinessDataType(businessDataTypeList, businessDataType);
					if(indexB==-1){
						businessDataTypeList.add(businessDataType);//如果不存在相同的业务数据类型则添加
					}
					standardFieldList.add(standardField1);
					exclusionNames.add(tableField.getNewName());
					tableField.setBusType(fieldInfo.get("BusinessDataType_name"));
				}
			}
				
			
		}
		if (res1 != null) {
			PDMHelper.associateDefaultValueByStdType(businessDataTypeList,typeDefaultValueList);
			allBusinessDataTypeList.getItems().addAll(businessDataTypeList);
			res1.save(allBusinessDataTypeList, true, monitor);
			
		}
		
		if (res2 != null) {
			
			allStandardFieldList.getItems().clear();
			standardFieldList.addAll(oldStandardFieldList);
			Collections.sort(standardFieldList,new NameSorter());
			allStandardFieldList.getItems().addAll(standardFieldList);
			res2.save(allStandardFieldList, true, monitor);
		}
	}
	/**
	 * 生成业务数据类型(支持非标准字段)
	 * @param allTableFieldMap
	 * @param monitor
	 * @throws ARESModelException
	 */
	private void importBusinessDataTypeOfNoStd(Map<String,List<PDMTableField>> allTableFieldMap,IProgressMonitor monitor) throws ARESModelException{

		List<BusinessDataType> businessDataTypeList = new ArrayList<BusinessDataType>();
		
		IARESResource res1 = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		BusinessDataTypeList allBusinessDataTypeList = res1.getInfo(BusinessDataTypeList.class);
		List<BusinessDataType> oldBusinessDataTypeList = new ArrayList<BusinessDataType>(allBusinessDataTypeList.getItems());
		
		
		
		IARESResource resTDVType = project.findResource(IMetadataResType.DefValue, IMetadataResType.DefValue);
		List<TypeDefaultValue> typeDefaultValueList = new ArrayList<TypeDefaultValue>();
		if(resTDVType!=null){
			typeDefaultValueList = resTDVType.getInfo(TypeDefaultValueList.class).getItems();
		}
		
		
		List<String> exclusionNames = new ArrayList<String>(){
			/* (non-Javadoc)
			 * @see java.util.ArrayList#contains(java.lang.Object)
			 */
			@Override
			public boolean contains(Object o) {
				Iterator<String> iter = this.iterator() ;
				if(o instanceof String){
					String comp1 = (String)o;
					while(iter.hasNext()){
						String comp2 = iter.next();
						if(StringUtils.equalsIgnoreCase(comp1, comp2)){
							return true;
						}
					}
				}
					return false;
				
			}
		};
		
		/*for(StandardField standardField:oldStandardFieldList){
			exclusionNames.add(standardField.getName());
		}*/
		String databaseType = "oracle";
		try {
			databaseType =  project.getProjectProperty().getString("tabledir");
			int _index = -1 ;
			int dotIndex = -1;
			if((_index=StringUtils.lastIndexOf(databaseType,"_" ))>-1  && (dotIndex=StringUtils.lastIndexOf(databaseType,"."))>-1 ){
				databaseType = StringUtils.substring(databaseType,_index+1, dotIndex).toLowerCase();
			}else{
				databaseType = "oracle";
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		for(String key:allTableFieldMap.keySet()){
			List<PDMTableField> pdmTableFieldList = allTableFieldMap.get(key);
			for (PDMTableField tableField : pdmTableFieldList) {
					Map<String,String> fieldInfo = helper.analyseTableFeild(tableField, project,databaseType);
					tableField.setBusType(fieldInfo.get("BusinessDataType_no_std_name"));
					if(!exclusionNames.contains(tableField.getBusType())){
						BusinessDataType businessDataType = helper.createBusinessDataType(fieldInfo,importMode);
						tableField.setBusType(fieldInfo.get("BusinessDataType_no_std_name"));
						int indexB =PDMHelper.indexOfBusinessDataType(oldBusinessDataTypeList, businessDataType);
						if(indexB==-1){//如果不存在相同的业务数据类型则添加
							businessDataTypeList.add(businessDataType);
						}
						exclusionNames.add(tableField.getBusType());
				}
			}
		}
		if (res1 != null) {
			PDMHelper.associateDefaultValueByStdType(businessDataTypeList,typeDefaultValueList);//关联默认值
			allBusinessDataTypeList.getItems().addAll(businessDataTypeList);
			res1.save(allBusinessDataTypeList, true, monitor);
		}

	}
	/**
	 * 导入PDM中的数据库表
	 * @param monitor
	 * @throws Exception
	 */
	private void importTable(Map<String,List<PDMTableField>> allTableFieldMap,IProgressMonitor monitor) throws Exception{
		if(!importMode){
			importStdTable(allTableFieldMap, monitor);//标准字段方式导入
		}else{
			importNoStdTable(allTableFieldMap, monitor);//非标准字段方式导入
		}
	}
	
	/**
	 * 导入PDM中的数据库表(标准字段)
	 * @param monitor
	 * @throws Exception
	 */
	private void importStdTable(Map<String,List<PDMTableField>> allTableFieldMap,IProgressMonitor monitor) throws Exception{
		Map<IFolder, List<PDMTable>>  allTable = parser.getTableMap();
		for(Iterator<IFolder> iterator = allTable.keySet().iterator(); iterator.hasNext();){
			IFolder tbFolder = (IFolder) iterator.next();
			List<PDMTable> subPDMTableList = allTable.get(tbFolder);
			List<TableResourceData> subTableList = new ArrayList<TableResourceData>();
			for(PDMTable pdmTable:subPDMTableList){
				TableResourceData tableResourceData = helper.createTableResourceData(pdmTable,allTableFieldMap,importMode);
				subTableList.add(tableResourceData);
			}
			for (TableResourceData table : subTableList) {
				try {
					IFile fTable = tbFolder.getFile(table.getName() + ".table");
					if (fTable.exists()) {
						fTable.delete(true, monitor);
					}
					
					// 2013.11.19 sundl 修改资源创建方式代码
					IARESResource resource = (IARESResource) ARESCore.create(fTable);
					IARESModule module = resource.getModule();
					module.createResource(fTable.getName(), table);
//					ByteArrayOutputStream out = new ByteArrayOutputStream();
//					converter.write(out, table);
//					fTable.create(new ByteArrayInputStream(out.toByteArray()), true, monitor);
				} catch (Exception e) {
					console.info(e.getMessage());
					logger.info(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 导入PDM中的数据库表(非标准字段)
	 * @param monitor
	 * @throws Exception
	 */
	private void importNoStdTable(Map<String,List<PDMTableField>> allTableFieldMap,IProgressMonitor monitor) throws Exception{
		importStdTable(allTableFieldMap,monitor);
	}
	/**
	 * 导入PDM中的视图
	 * @param monitor
	 * @throws Exception
	 */
	private void importView(IProgressMonitor monitor) throws Exception{
		Map<IFolder, List<PDMView>>  allView = parser.getViewMap();
		for(Iterator<IFolder> iterator = allView.keySet().iterator(); iterator.hasNext();){
			IFolder tbFolder = (IFolder) iterator.next();
			List<PDMView> subPDMVieweList = allView.get(tbFolder);
			List<ViewResourceData> subViewList = new ArrayList<ViewResourceData>();
			for(PDMView pdmView:subPDMVieweList){
				ViewResourceData viewResourceData = helper.createVrewResourceData(pdmView);
				subViewList.add(viewResourceData);
			}
			for (ViewResourceData view : subViewList) {
				try {
				IFile fView = tbFolder.getFile(view.getName()+ ".view");
				if (fView.exists()) {
					fView.delete(true, monitor);
				}
				// 2013.11.19 sundl 修改资源创建方式代码
				IARESResource resource = (IARESResource) ARESCore.create(fView);
				IARESModule module = resource.getModule();
				module.createResource(fView.getName(), fView);
				} catch (Exception e) {
					console.info(e.getMessage());
					logger.info(e.getMessage());
				}
			}
		}
	}
	/**
	 * 产生评审报告
	 * @param uniqueAllTableFieldList
	 * @return
	 */
	private List<PDMStandardField>  getStandardFieldLsit(List<PDMTableField> uniqueAllTableFieldList){
		 List<PDMStandardField> exportStandardFieldList= new ArrayList<PDMStandardField>();//产生新的待新的标准字段列表
		 for(PDMTableField pdmTableField:uniqueAllTableFieldList){
			 PDMStandardField pdmStandardField = helper.createPDMStandardField(pdmTableField, new HashMap<String,String>());
			 pdmStandardField.setImportPath(importFilePath);
			 exportStandardFieldList.add(pdmStandardField);
		 }
		 return exportStandardFieldList;
		
	}
	/**
	 * 检查标准字段与业务数据类型文件是否都可写
	 * @param errorMsg
	 * @return
	 * @throws ARESModelException
	 */
	private String checkStandardFieldAndBusinessDataType() throws ARESModelException{
		IARESResource res1 = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		IARESResource res2 = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
		  StringBuffer errorMsg = new StringBuffer();
		if(res1.isReadOnly() || res2.isReadOnly()){
			if (res1.isReadOnly()) {
				errorMsg.append("业务数据类型");
			}
			if (res2.isReadOnly()) {
				if (res1.isReadOnly()) {
					errorMsg.append(",").append("标准字段");
				} else {
					errorMsg.append("标准字段");
				}

			}
			
			
			return errorMsg.toString();
		}
		return StringUtils.EMPTY;
	}
}
