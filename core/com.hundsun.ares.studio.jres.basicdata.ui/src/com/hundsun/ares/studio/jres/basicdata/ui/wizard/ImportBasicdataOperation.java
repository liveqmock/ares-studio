/**
 * 
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.excel.BizExcelLog;
import com.hundsun.ares.studio.core.excel.ExcelHandlerException;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.core.util.log.Log.Location;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicdataConstants;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataFactory;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.IBaiscDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.extensionpoint.EpackageFactoryManager;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.EPackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.basicdata.manage.BasicdataDefineManageUtil;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * @author yanwj06282
 *
 */
public class ImportBasicdataOperation implements IWorkspaceRunnable {

	private IARESProject project;
	private File[] files;
	public  BizExcelLog log  = new BizExcelLog();
	
	public ImportBasicdataOperation(IARESProject project ,File[] files) {
		this.project = project;
		this.files = files;
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("开始导入。。。", files.length);
		for (File file : files) {
			monitor.subTask(file.getName());
			InputStream input = null;
			try {
				if (file.exists()) {
					
					IARESModuleRoot root = getModuleRoot();
					if(root == null){
						throw new ExcelHandlerException("未找到基础数据模块根，请检查工程的完整性");
					}
					HSSFWorkbook wb = null;
					try{
						input = new FileInputStream(file);
						wb = new HSSFWorkbook(input);
					}catch (IOException e) {
						e.printStackTrace();
						Resource re = new Resource();
						re.name = file.getName();
						re.type = "文件";
						re.startLoc = new Location();
						re.startLoc.file = file.getPath();
						log.logResFailed(re, "文件读取失败["+file.getPath()+"]");
						continue;
					}
					Map< String, SubModule> menu = getExcelStringMenu(wb, "基础数据目录", 1, 1);
					Map<SubModule, List< List<String>>> detail = getExcelStringForCate(wb);
					
					log.totle += detail.size();
					Set<String> dupFileNames = new HashSet<String>();
					for (Iterator<SubModule> iterator = detail.keySet().iterator(); iterator
							.hasNext();) {
						SubModule key = iterator.next();
						List<List<String>> value = detail.get(key);
						SubModule sb = menu.get(key.getResCName()+key.getResEName());
						if (sb == null) {
							sb = key;
						}
						try {
							String ename = StringUtils.replace(sb.getModuleEName(),"-", "/");
							ename = StringUtils.replace(sb.getModuleEName(),".", "/");
							
							String cname = StringUtils.replace(sb.getModuleCName(),"-", "/");
							cname = StringUtils.replace(sb.getModuleCName(),".", "/");
							IARESModule module = createModules(root, StringUtils.lowerCase(ename), cname);
							if (module == null) {
								continue;
							}
							IFolder folder = (IFolder)module.getResource();
							IFile f = folder.getFile(sb.getResEName()+"."+IBasicDataRestypes.singleTable);
							String url = f.getProjectRelativePath().toOSString();
							//判断同名资源，如果遇到同名资源，需要对文件名做下标处理，"_1"开始依次递增
							String dir = StringUtils.substringBeforeLast(url, File.separator);
							String fileName = StringUtils.substringAfterLast(url, File.separator);
							//重名列表
							
							while (dupFileNames.contains(fileName)) {
								String ex = StringUtils.substringAfterLast(fileName, ".");
								fileName = StringUtils.substringBeforeLast(fileName, ".");
								String index = StringUtils.substringAfterLast(fileName, "_");
								fileName = StringUtils.substringBeforeLast(fileName, "_");
								if (StringUtils.isNotBlank(index)) {
									try {
										int i = Integer.parseInt(index);
										i++;
										fileName += "_"+i; 
									} catch (Exception e) {
									}
								}else {
									fileName += "_1"; 
								}
								fileName = fileName + "." + ex;
								url = dir + File.separator + fileName;
							}
							Object info = createRes(f,url, sb);
							if (info == null) {
								continue;
							}
							EPackage ePackage = createEPackage(project, f.getProjectRelativePath().toOSString() , sb.getRelationTable());
							EClass masterEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
							
							EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);//内容
							
							//masterKeyTitles是基础数据字段对应的为英文名，但value.get(0)是excel中有使用的标题
							//此处应该直接使用excel中的标题
							List<String> titles = value.get(0);
							List<Element> elements = getProjectExsibleModelConfigList(project, masterEclass);
							List<String> titles2 = new ArrayList<String>();
							List<String> datakeys2 = new ArrayList<String>();
							for(Element element : elements){
								titles2.add(element.attributeValue("name"));
								datakeys2.add(element.attributeValue("id"));
							}
							List<String> curTitle = new ArrayList<String>();
							if (titles.size() >= attrArray.length) {
								curTitle = titles.subList(0, attrArray.length);
							}else {
								curTitle.addAll(titles);
								for(int i = 0;i< attrArray.length - titles.size() ;i++){
									curTitle.add("");
								}
							}
							POIUtils.importExcelStringTableForSHClear(value, masterEclass,
									curTitle.toArray(new String[curTitle.size()]),
									attrArray,true, 
									//附加属性
									titles2.toArray(new String[]{}),
									datakeys2.toArray(new String[]{}),
									(MetadataResourceData)info, project);
							
							
							//write(f, info);
							// 2013.11.19 sundl 去除emf adapter插件，修改资源创建方式
							
							module.createResource(fileName, info);
							
							Resource re = new Resource();
							re.name = sb.getResEName();
							re.type = IBasicDataRestypes.singleTable;
							log.logResCreted(re);
							dupFileNames.add(fileName);
						} catch (Exception e) {
							e.printStackTrace();
							Resource re = new Resource();
							re.name = sb.getResEName();
							re.type = IBasicDataRestypes.singleTable;
							re.startLoc = new Location();
							re.startLoc.file = file.getPath();
							log.logResFailed(re, "数据库表不存在，或者表字段不匹配["+sb.getRelationTable()+"]");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				IOUtils.closeQuietly(input);
			}
			monitor.worked(1);
		}
		monitor.done();
	}
	
	private EPackage createEPackage(IARESProject project ,String resourctUrl , String master) throws Exception{
		
		SingleTable tableDefine = BasicdataFactory.eINSTANCE.createSingleTable();
		tableDefine.setType("jres.db.table");

		tableDefine.setMaster(master);
		
		IBaiscDataEpackageFactory factory = EpackageFactoryManager.getInstance().getFactory(tableDefine.getType());
		return factory.createEPackage(project, tableDefine);
	}
	
	private IARESModule createModules(IARESModuleRoot root ,String moduleENams ,String moduleCName) throws ARESModelException{
		if (StringUtils.isBlank(moduleENams)) {
			if (StringUtils.isBlank(moduleCName)) {
				throw new RuntimeException("模块名字和中文名，未设置！");
			}
			//先查找是否含有相同中文名的模块
			IARESModule module = getModuleByChName(root , moduleCName);
			if (module != null) {
				moduleENams = StringUtils.replace(module.getElementName(), ".", "/");
			}
		}
		String[] engPacks = StringUtils.split(moduleENams , "/");
		String[] chPacks = StringUtils.split(moduleCName , "/");
		//创建模块
		IARESModule createdModule = null;
		if (root == null) {
			throw new RuntimeException("未找到数据库的模块根，请检查工程完整性！");
		}
		String packageName = "";
		for (int i=0;i<chPacks.length;i++){
			String pn = StringUtils.EMPTY;
			String ch = chPacks[i];
			if (engPacks.length > i) {
				pn = engPacks[i];
			}else {
				pn = "m"+StringUtils.replace(getEnglishName(ch), "-", "_");
			}
			if (StringUtils.isBlank(packageName)) {
				packageName = pn;
			}else {
				packageName += "." + pn;
			}
			createdModule = root.findModule(packageName);
			if (createdModule==null) {
				try {
					createdModule = root.createModule(packageName);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			
			IARESResource resource = createdModule.findResource(IARESModule.MODULE_PROPERTY_FILE);
			ModuleProperty property = null;
			if (resource == null) {
				property = new ModuleProperty();
				property.setValue(ICommonModel.CNAME, ch);
				property.setValue(ICommonModel.NAME, pn);
				try {
					resource = createdModule.createResource(IARESModule.MODULE_PROPERTY_FILE, property);
					resource.save(property, true, new NullProgressMonitor());
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}else {
				try {
					property = resource.getInfo(ModuleProperty.class);
					String cMName = (String) property.getValue(ICommonModel.CNAME);
					if(StringUtils.isNotBlank(moduleCName) && !StringUtils.equals(cMName, moduleCName)){
						property.setValue(ICommonModel.CNAME, ch);
							
						}
				} catch (ARESModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return createdModule;
	}
	
	private IARESModule getModuleByChName(IARESModuleRoot root , String moduleCName ) throws ARESModelException {
		for(IARESModule module : root.getModules()){
			if (StringUtils.isBlank(module.getElementName())) {
				continue;
			}
			IARESResource resource = module.findResource(IARESModule.MODULE_PROPERTY_FILE);
			ModuleProperty property = null;
			try {
				property = resource.getInfo(ModuleProperty.class);
				String cMName = (String) property.getValue(ICommonModel.CNAME);
				if(StringUtils.equals(cMName, moduleCName)){
					return module;
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.indexOf(moduleCName , "/") > -1) {
			return getModuleByChName(root, StringUtils.substringBeforeLast(moduleCName, "/"));
		}else {
			return null;
		}
	}
	
	/**
	 * 用于获取英文名，主要用于资源名和包名
	 * 如果包含有中文字符，则通过hashCode生成
	 * 
	 * @param key
	 * @return
	 */
	public static String getEnglishName(String name){
		if(name.matches("(^[a-z_][a-z0-9_]{0,49}$)")){
			return name;
		}else {
			return String.valueOf(StringUtils.replace(String.valueOf(name.hashCode()), "-", "_"));
		}
	}
	
	private static Map<SubModule, List< List<String>>> getExcelStringForCate(HSSFWorkbook workBook) throws Exception {
		Map< SubModule, List< List<String> > > sheetFieldMap = new HashMap<SubModule, List<List<String>>>();
		
			try {
				HSSFFormulaEvaluator evaluator =  workBook.getCreationHelper().createFormulaEvaluator();
				
				for (int i=0;i<Integer.MAX_VALUE;i++) {
					List<List<String>> fieldLists = new ArrayList<List<String>>();
					HSSFSheet sheet = null;
					try {
						sheet = workBook.getSheetAt(i);
					} catch (IllegalArgumentException e) {
					}
					if (sheet == null) {
						break;
					}
					//中文分隔符—也支持
					String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
					if (sheet == null || StringUtils.equals(sheetName, "基础数据目录") || !StringUtils.startsWith(sheetName, "通用数据-")) {
						continue;
					}
					List<String> moduleInfo = getColumns(sheet, 1,1, evaluator);
					if (moduleInfo.size() == 6) {
						sheetFieldMap.put(new SubModule("", "", moduleInfo.get(1), moduleInfo.get(3), moduleInfo.get(5), "jres.db.table" ,"") ,fieldLists);
					}else {
						continue;
					}
					
					List<String> titleField = POIUtils.getColumns(sheet, 1,3, evaluator);
					fieldLists.add(titleField);
					Label: for (int j = 4; j<sheet.getLastRowNum()+1;j++){
						HSSFRow row = sheet.getRow(j);
						if (row == null) {
							break;
						}
						int cellNum = 1;
						List<String> fields = new ArrayList<String>();
						int isCate = 0;
						for (int k = 0; k < titleField.size(); k++) {
							HSSFCell cell = row.getCell(k + cellNum);//addMergedRegion
							String cellValue = POIUtils.getCellStringValue(cell, evaluator).trim();
							if (StringUtils.equals(cellValue, "修改记录")) {
								break Label;
							}
							fields.add(cellValue);						
						}
						String titile = "";
						for (int k = 0; k < fields.size(); k++) {
							if (StringUtils.isNotBlank(fields.get(k))) {
								titile = fields.get(k);
								isCate ++;
							}
						}
						
						if (isCate == 1) {
							fields.set(0, titile);
							for (int k = 1; k < fields.size(); k++) {
								fields.set(k, null);
							}
						}
						
						boolean isLast = true;
						for (String field : fields) {
							if (StringUtils.isNotBlank(field)) {
								isLast = false;
								break;
							}
						}
						if (!isLast) {
							fieldLists.add(fields);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return sheetFieldMap;
	}
	
	public static List<String> getColumns (HSSFSheet sheet , int cellNum , int rowNum, HSSFFormulaEvaluator evaluator){
		List<String> fields = new ArrayList<String>();
		boolean nextCell = true;
		while(nextCell){
			HSSFRow row = sheet.getRow(rowNum);
			if (row != null) {
				HSSFCell cell = row.getCell(cellNum);
				if (cell != null) {
					String text = POIUtils.getCellStringValue(cell, evaluator);
					
					if (text != null) {
						fields.add(text);
						cellNum ++;
					}else {
						nextCell = false;
					}
				}else {
					nextCell = false;
				}
			}else {
				nextCell = false;
			}
		}
		return fields;
	}
	
	private static Map< String, SubModule> getExcelStringMenu(HSSFWorkbook workBook, String sheetName, int startCol, int startRow) throws Exception {
		Map< String, SubModule> sheetFieldMap = new HashMap<String, SubModule>();
		
		try {
			
			HSSFFormulaEvaluator evaluator =  workBook.getCreationHelper().createFormulaEvaluator();
			
				HSSFSheet sheet = workBook.getSheet(sheetName);
				if (sheet == null) {
					return sheetFieldMap;
				}
				for (int j = startRow+1; j<sheet.getLastRowNum()+1;j++){
					HSSFRow row = sheet.getRow(j);
					if (row == null) {
						break;
					}
					int cellNum = startCol;
					
					HSSFCell cell = row.getCell(cellNum);
					String moduleEName = POIUtils.getCellStringValue(cell, evaluator);
					cell = row.getCell(cellNum+1);
					String moduleCName = POIUtils.getCellStringValue(cell, evaluator);
					cell = row.getCell(cellNum+3);
					String resCName = POIUtils.getCellStringValue(cell, evaluator);
					cell = row.getCell(cellNum+4);
					String resEName = POIUtils.getCellStringValue(cell, evaluator);
					cell = row.getCell(cellNum+5);
					String relationTable = POIUtils.getCellStringValue(cell, evaluator);
					cell = row.getCell(cellNum+8);
					String fileName = POIUtils.getCellStringValue(cell, evaluator);
					SubModule mod = new SubModule(moduleEName, moduleCName, resEName, resCName, relationTable, "jres.db.table" ,fileName);
					
					//用中文名英文名作为key，防止中文名重复的情况
					if (StringUtils.isBlank(resEName)) {
						resEName = "r"+getEnglishName(resCName);
					}
					sheetFieldMap.put(resCName+resEName, mod);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("文件不存在或文件格式不正确");
		}
		return sheetFieldMap;
	}
	
	private IARESModuleRoot getModuleRoot (){
		IFolder bizFolder = ARESElementUtil.getModuleRootFolder(this.project, IBasicdataConstants.BASICDATA_ROOT_TYPE);
		return (IARESModuleRoot) ARESCore.create(bizFolder);
	}
	
	private Object createRes (IFile file ,String url ,SubModule sb ){
		IResDescriptor resDescriptor = ARESResRegistry.getInstance()
			.getResDescriptor(IBasicDataRestypes.singleTable);
		String resName = sb.getResEName();
		String cName = sb.getResCName();
		String masterTable = sb.getRelationTable();
		String fileName = sb.getFileName();
		Object info = null;
		if (resDescriptor != null) {
			IModelConverter converter = resDescriptor.getConverter();
			//生成基础数据定义
			PackageDefine tableDefine = null;
			//资源模型
			try {
				tableDefine = createPackageDefine(masterTable ,url);
				info = BasicDataEpackageFactory.eINSTANCE.createInstance(project, tableDefine);
				((BasicDataBaseModel)info).setName(resName);
				if (StringUtils.isNotBlank(fileName)) {
					((BasicDataBaseModel)info).setFile(fileName);
				}
				//获取基础数据的模型定义数据源的类型
				String type = EPackageUtil.getBasicDataType(project);
				if(info instanceof BasicDataBaseModel){
					try {
						String tableName = "";
						if (tableDefine instanceof SingleTable) {
							tableName = ((SingleTable) tableDefine).getMaster();
						}
						JRESResourceInfo table = JRESResProviderUtil.getResourceModel(project, 
								tableName,
								type,//"jres.db.table",
								JRESResourceInfo.class);
						if (StringUtils.isNotBlank(cName)) {
							((BasicDataBaseModel)info).setChineseName(cName);
						}else if (StringUtils.isNotBlank(table.getChineseName())) {
							((BasicDataBaseModel)info).setChineseName(table.getChineseName());
						}else {
							((BasicDataBaseModel)info).setChineseName(resName);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				BasicdataDefineManageUtil.savePackageDefine(project, tableDefine);
			} catch (Exception e) {
				e.printStackTrace();
				Resource re = new Resource();
				re.name = masterTable;
				re.type = IBasicDataRestypes.singleTable;
				re.startLoc = new Location();
				re.startLoc.file = file.getFullPath().toOSString();
				log.logResFailed(re, e.getMessage());
			}
			//保存模型
//			write(converter, file, info);
		}
		return info;
	}
	
	private void write(IModelConverter converter ,IFile file , Object info){
		if (converter instanceof IModelConverter2) {
			try {

				IARESResource resource = (IARESResource) ARESCore
						.create(file);
				file.create(
						new ByteArrayInputStream(
								((IModelConverter2) converter).write(
										resource, info)), true, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				converter.write(bos, info);
				file.create(
						new ByteArrayInputStream(bos.toByteArray()),
						true, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private PackageDefine createPackageDefine(String tableName ,String path){
		SingleTable table = BasicdataFactory.eINSTANCE.createSingleTable();
		table.setMaster(tableName);
		
		table.setType(EPackageUtil.getBasicDataType(project));
		table.setUrl(path);
		
		return table;
	}
	
	//获取当前项目属性中的所有扩展属性定义
	private List<Element> getProjectExsibleModelConfigList(IARESProject project,EClass eClass) {
		List<Element> elements = new ArrayList<Element>();
		try {
			//解析项目属性中的扩展
			ExtensibleModelConfigProperty config = (ExtensibleModelConfigProperty) project.getInfo().getMap().get("ExtensibleModelConfigProperty");
			if (config == null) {
				config = CoreFactory.eINSTANCE.createExtensibleModelConfigProperty();
			}
			
			String xml = config.getXml();
			
			if (StringUtils.isNotBlank(xml)) {
				Document doc = config.getXmlCache();
				if (doc != null) {
					for(Object obj : doc.getRootElement().elements("ExtensibleModel")){
						if(obj instanceof Element){
							Element element = (Element)obj;
							EClass cls = (EClass) eClass.getEPackage().getEClassifier(element.attributeValue("class"));
							if(cls != null && cls.isSuperTypeOf(eClass) && StringUtils.equalsIgnoreCase(eClass.getEPackage().getNsURI(), element.attributeValue("uri"))){
								for(Element attributeElement : (List<Element>)element.elements("Attribute")){
									elements.add(attributeElement);
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			
		}
		return elements;
	}
	
}
