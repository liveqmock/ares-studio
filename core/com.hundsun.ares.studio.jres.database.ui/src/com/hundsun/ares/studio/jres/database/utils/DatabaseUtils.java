/**
 * 源程序名称：DatabaeUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.excel.ExcelHandlerException;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

/**
 * @author yanwj06282
 *
 */
public class DatabaseUtils {
	
	private static Logger logger = LogManager.getLogger(DatabaseUtils.class);
	
	private static List<String> tableType = new ArrayList<String>();
	
	private static final String MODULE_NAME = "模块名";
	private static final String SPLIT_FIELD = "分区字段";
	private static final String SPLIT_NUM = "分区个数";
	private static final String SPLIT_DATA = "分区开始年月";
	private static final String TABLE_TYPE = "表类型";
	private static final String TABLE_SPACE = "表空间";
	
	//开发工具格式兼容状态
	private static boolean hstool = false;
	
	static{
		tableType.add("U");
		tableType.add("T");
		tableType.add("M");
	}
	
	
	/**
	 * 数据库表Excel文件的读取
	 * 
	 * @param is
	 * @param startRow
	 * @param startCol
	 * @return 返回的Map ， 以包长名作为Key
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,List<Map<String,Object>>> readTableExcel(InputStream is,int startRow,int startCol) throws IOException{
		Map<String,List<Map<String,Object>>> exPMAPs = new HashMap<String,List<Map<String,Object>>>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFFormulaEvaluator evaluator =  wb.getCreationHelper().createFormulaEvaluator();
			//处理目录菜单
			boolean compb = false;
			BiMap<String, Object>  biMap = readMenuSheet(wb, evaluator, 0, 0);
			List<Map<String, Object>>  exMap = getMoudleSheet(wb, evaluator, 0, 0);
			if (exMap != null) {
				exPMAPs.put(DBTableDefine.DATABASE_MODULE_INFO, exMap);
			}
			List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
			if (biMap != null) {
				menuList.add(biMap);
				exPMAPs.put(DBTableDefine.DATABASE_MENU, menuList);
			}else {
				//恒生开发工具格式
				compb = true;
				BiMap<String, Object> menu = HashBiMap.create();
				menuList.add(menu);
				exPMAPs.put(DBTableDefine.DATABASE_MENU, menuList);
			}
			
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				HSSFSheet sheet = null;
				try {
					sheet = wb.getSheetAt(i);
				} catch (Exception e) {
					break;
				}
				//中文分隔符—也支持
				String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
				if (compb && !StringUtils.equals(sheetName, DBTableDefine.DATABASE_MODULE_INFO)) {
					if (StringUtils.startsWith(sheetName, "表结构-")) {
						sheetName = StringUtils.replace(sheetName, "表结构-", "");
					}else if (StringUtils.startsWith(sheetName, "表结构")) {
						sheetName = StringUtils.replace(sheetName, "表结构", "");
					}
//					String key = HanziToPingtin.getFullSpell(sheetName);
					String key = StringUtils.replace(sheetName, "-", "/");
					exPMAPs.get(DBTableDefine.DATABASE_MENU).get(0).put(key, sheetName);
				}
				HSSFRow row = sheet.getRow(startRow);
				if (row == null) {
					continue;
				}
				HSSFCell cell = row.getCell(startCol);
				if (!StringUtils.equals(POIUtils.getCellStringValue(cell, evaluator).trim(), DBTableDefine.BASE_OBJECT_NUM)) {
					continue;
				}
				List<Map<String,Object>> exLists = new ArrayList<Map<String,Object>>();
				exPMAPs.put(sheetName, exLists);
				boolean param = true;
				boolean field = false;
				boolean index = false;
				boolean isKey = false;
				Map<String,Object> exMaps = new HashMap<String, Object>();
				exLists.add(exMaps);
				for (int j = 0; j <= sheet.getLastRowNum(); j++) {
					//读取基本信息,包括扩展信息
					row = sheet.getRow(startRow+j);
					if(row == null){
						continue;//可能中间存在空行
					}
					//结束
					cell = row.getCell(startCol);
					String modify = POIUtils.getCellStringValue(cell, evaluator);
					if (StringUtils.equals(DBTableDefine.MODIFY_DEFINE, modify)) {
						//修订记录的值，这个值目前只有恒生开发工具的试图用，其他没用
						cell = row.getCell(startCol+1);
						exMaps.put(DBTableDefine.MODIFY_DEFINE, POIUtils.getCellStringValue(cell, evaluator));
						boolean modifyStatus = true;
						boolean space = false;
						int totle = 0;
						while (modifyStatus) {
							j++;
							row = sheet.getRow(startRow+j);
							if (row != null) {
								for (int k = 1; k < row.getLastCellNum(); k++) {
									cell = row.getCell(startCol+k);
									if (StringUtils.isNotBlank(POIUtils.getCellStringValue(cell, evaluator))) {
										break;
									}
									if (k == row.getLastCellNum()-1) {
										space = true;
									}
								}
								if(row.getLastCellNum()==-1){
									space = true;
								}
							}else {
								if (totle >= 10) {
									modifyStatus = false;
								}
								space = true;
								totle++;
							}
							int t = 0;
							if (space) {
								boolean x = true;
								while(x){
									j++;
									HSSFRow r = sheet.getRow(startRow+j);
									if (r != null) {
										x = false;
										j--;
									}else {
										t++;
										if (t >= 10) {
											x = false;
										}
									}
								}
								modifyStatus = false;
								break;
							}
						}
						param = true;
						index = false;
						field =false;
						isKey = false;
						exMaps = new HashMap<String, Object>();
						exLists.add(exMaps);
						continue;
					}
					
					if (param) {
						String key = "";
						for (int k = 0; k < row.getLastCellNum(); k++) {
							cell = row.getCell(startCol+k);
							String value = POIUtils.getCellStringValue(cell, evaluator);
							if (StringUtils.isNotBlank(key)) {
								exMaps.put(key, value);
								key = "";
							}else {
								key = value;
							}
						}
					}
					//表字段
					cell = row.getCell(startCol);
					List<String> strs = null;
					if (field) {
						strs = new ArrayList<String>();
						List<List<String>> strList = ((List<List<String>>)(exMaps.get(DBTableDefine.FIELD_DEFINE)));
						strList.add(strs);
						strs.add(POIUtils.getCellStringValue(cell, evaluator));
						int max = strList.get(0).size();
						for (int k = 1; k < max + 1; k++) {
							cell = row.getCell(startCol + k);
							String value = POIUtils.getCellStringValue(cell, evaluator);
							strs.add(value);
						}
					}else {
						HSSFRow nextRow = sheet.getRow(startRow+j+1);
						if (nextRow == null) {
							break;
						}
						cell = nextRow.getCell(startCol);
						String value = POIUtils.getCellStringValue(cell, evaluator);
						if (StringUtils.equals(DBTableDefine.FIELD_DEFINE, value)) {
							j++;
							List<List<String>> strList = new ArrayList<List<String>>();
							strs = new ArrayList<String>();
							strs.add(value);
							for (int k = 1; k < nextRow.getLastCellNum(); k++) {
								cell = nextRow.getCell(startCol+k);
								strs.add(POIUtils.getCellStringValue(cell, evaluator));
							}
							strList.add(strs);
							exMaps.put(DBTableDefine.FIELD_DEFINE, strList);
							param = false;
							field = true;
							index = false;
							isKey = false;
						}
					}
					
					//表索引
					cell = row.getCell(startCol);
					if (index) {
						cell = row.getCell(startCol);
						strs = new ArrayList<String>();
						List<List<String>> strList = ((List<List<String>>)(exMaps.get(DBTableDefine.INDEX_DEFINE)));
						strList.add(strs);
						strs.add(POIUtils.getCellStringValue(cell, evaluator));
						int max = strList.get(0).size();
						for (int k = 1; k < max + 1; k++) {
							cell = row.getCell(startCol + k);
							String value = POIUtils.getCellStringValue(cell, evaluator);
							strs.add(value);
						}
					}else {
						HSSFRow nextRow = sheet.getRow(startRow+j+1);
						if(nextRow == null){
							continue;//有可能中间存在空行，导致Null
						}
						cell = nextRow.getCell(startCol);
						String value = POIUtils.getCellStringValue(cell, evaluator);
						if (StringUtils.equals(DBTableDefine.INDEX_DEFINE, value) || StringUtils.equals(DBTableDefine.INDEX_DEFINE_UTIL, value)) {
							j++;
							List<List<String>> strList = new ArrayList<List<String>>();
							strs = new ArrayList<String>();
							strs.add(value);
							for (int k = 1; k < nextRow.getLastCellNum(); k++) {
								cell = nextRow.getCell(startCol+k);
								strs.add(POIUtils.getCellStringValue(cell, evaluator));
							}
							strList.add(strs);
							exMaps.put(DBTableDefine.INDEX_DEFINE, strList);
							param = false;
							index = true;
							field =false;
							isKey = false;
						}
					}
					
					//表索引
					cell = row.getCell(startCol);
					if (isKey) {
						cell = row.getCell(startCol);
						strs = new ArrayList<String>();
						List<List<String>> strList = ((List<List<String>>)(exMaps.get(DBTableDefine.KEY_DEFINE)));
						strList.add(strs);
						strs.add(POIUtils.getCellStringValue(cell, evaluator));
						int max = strList.get(0).size();
						for (int k = 1; k < max + 1; k++) {
							cell = row.getCell(startCol + k);
							String value = POIUtils.getCellStringValue(cell, evaluator);
							strs.add(value);
						}
					}else {
						HSSFRow nextRow = sheet.getRow(startRow+j+1);
						cell = nextRow.getCell(startCol);
						String value = POIUtils.getCellStringValue(cell, evaluator);
						if (StringUtils.equals(DBTableDefine.KEY_DEFINE, value)) {
							j++;
							List<List<String>> strList = new ArrayList<List<String>>();
							strs = new ArrayList<String>();
							strs.add(value);
							for (int k = 1; k < nextRow.getLastCellNum(); k++) {
								cell = nextRow.getCell(startCol+k);
								strs.add(POIUtils.getCellStringValue(cell, evaluator));
							}
							strList.add(strs);
							exMaps.put(DBTableDefine.KEY_DEFINE, strList);
							param = false;
							index = false;
							field =false;
							isKey = true;
							continue;
						}
					}
				}
			}
		} catch (IOException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		}
		return exPMAPs;
	}
	
	/**
	 * 读取目录菜单sheet
	 * 
	 * @param wb
	 * @param evaluator
	 * @return 中文名为value ， 英文名为key
	 */
	private static BiMap<String, Object> readMenuSheet (HSSFWorkbook wb , HSSFFormulaEvaluator evaluator ,int startRow , int startCol){
		BiMap<String, Object> menu = HashBiMap.create();
		HSSFSheet sheet = wb.getSheet(DBTableDefine.DATABASE_MENU);
		int tempStartCol = startCol;
		if (sheet == null) {
			return null;
		}
		boolean findStartRow = false;
		HSSFRow row = sheet.getRow(startRow);
		HSSFCell cell = null;
		//确定需要处理的行号以及开始的单元格号
		while (!findStartRow && startRow < 10) {
			if(row!=null){
				boolean findStarCol = false;
				 cell = row.getCell(startCol);
				 startCol = tempStartCol;
				while (!findStarCol && startCol < 10) {
					if(cell!=null){
						String  value =  POIUtils.getCellStringValue(cell, evaluator);
						if(StringUtils.isNotBlank(value)){
							findStarCol = true;
							findStartRow = true;
							break;
						}
					}
					startCol ++;
					cell = row.getCell(startCol);
					
				}	
			}
			if(findStartRow){
				break;
			}
			startRow++;
			row = sheet.getRow(startRow);
		}
		
		startRow++;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(startRow + i);
			if (row == null) {
				continue;
			}
			cell = row.getCell(startCol);
			String key = POIUtils.getCellStringValue(cell, evaluator).trim();
			cell = row.getCell(startCol + 1);
			String value = POIUtils.getCellStringValue(cell, evaluator);
			if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
				menu.put(key, value);
			}
		}
		return menu;
	}
	
	/**
	 * 数据库表的导入
	 * 
	 * @param exMaps
	 * @param project
	 */
	public static List<String> importDatabaseTableData (Map<String,List<Map<String,Object>>> exMaps, IARESProject project ,boolean isClearModule, IProgressMonitor monitor) throws Exception{
		List<String> result = new ArrayList<String>();
		//先处理目录菜单
		BiMap<String, Object>  biMap = null;
		monitor.beginTask("数据库表导入", exMaps.keySet().size()-1);
		List<Map<String,Object>> menuList = exMaps.get(DBTableDefine.DATABASE_MENU);
		if (menuList != null) {
			biMap = (BiMap<String, Object>) menuList.get(0);
		}
		if (biMap == null) {
			throw new RuntimeException("目录菜单未找到，导入终止！");
		}
		exMaps.remove(DBTableDefine.DATABASE_MENU);
		for (Iterator<String> it = exMaps.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			monitor.subTask(key);
			monitor.worked(1);
			String englistName = biMap.inverse().get(key);
			String chineseName = key;
			if (StringUtils.equals(key, DBTableDefine.DATABASE_MODULE_INFO)) {
				continue;
			}
			//英文名为空，就跳过，不处理
			if (StringUtils.isBlank(englistName)) {
				result.add(key);
				continue;
			}
			String[] engPacks = StringUtils.split(englistName , "/");
			String[] chPacks = StringUtils.split(chineseName , "-");
			//创建模块
			IFolder dbFolder = ARESElementUtil.getModuleRootFolder(project, IDBConstant.ROOT_TYPE);
			IARESModuleRoot root = (IARESModuleRoot) ARESCore.create(dbFolder);//project.getModuleRoot("database");
			IARESModule createdModule = null;
			if (root == null) {
				throw new ExcelHandlerException("未找到数据库的模块根，请检查工程完整性！");
			}
			String packageName = "";
			for (int i=0;i<engPacks.length;i++){
				String pn = engPacks[i];
				if (!isCH(pn)) {
					pn = "m" + StringUtils.replace(String.valueOf(pn.hashCode()), "-", "_");
				}
				String ch = StringUtils.EMPTY;
				try {
					ch = chPacks[i];
				} catch (Exception e) {
					ch = StringUtils.EMPTY;
				}
				if (StringUtils.isBlank(packageName)) {
					packageName = pn;
				}else {
					packageName += "." + pn;
				}
				createdModule = root.findModule(packageName);
				if (createdModule==null) {
					createdModule = root.createModule(packageName);
				}
				IARESResource resource = createdModule.findResource(IARESModule.MODULE_PROPERTY_FILE);
				ModuleProperty property = null;
				if (resource == null) {
					property = new ModuleProperty();
					property.setValue(ICommonModel.CNAME, ch);
					property.setValue(ICommonModel.NAME, pn);
					resource = createdModule.createResource(IARESModule.MODULE_PROPERTY_FILE, property);
				}
				if (property == null) {
					property = resource.getInfo(ModuleProperty.class);
				}
				List<Map<String, Object>> objMap = exMaps.get(DBTableDefine.DATABASE_MODULE_INFO);
				String moduleName = "/数据库/"+GenDbExportServiceUtils.getChineseFileName("/" , createdModule);
				String moduleSpace = "";
				table_type tableType = null;
				String splitField = "";
				String splitNum = "";
				String splitData = "";
				for(Map<String, Object> vm : objMap){
					if (StringUtils.equals(vm.get(MODULE_NAME)!=null?vm.get(MODULE_NAME).toString():StringUtils.EMPTY, moduleName)) {
						moduleSpace = vm.get(TABLE_SPACE)!=null?vm.get(TABLE_SPACE).toString():StringUtils.EMPTY;
						splitField = vm.get(SPLIT_FIELD)!=null?vm.get(SPLIT_FIELD).toString():StringUtils.EMPTY;
						tableType = GenDbExportServiceUtils.getTableTypeEnum(vm.get(TABLE_TYPE)!=null?vm.get(TABLE_TYPE).toString():StringUtils.EMPTY);
						splitNum = vm.get(SPLIT_NUM)!=null?vm.get(SPLIT_NUM).toString():StringUtils.EMPTY;
						splitData = vm.get(SPLIT_DATA)!=null?vm.get(SPLIT_DATA).toString():StringUtils.EMPTY;
						ModuleExtensibleModel mem = CoreFactory.eINSTANCE.createModuleExtensibleModel();
						property.getMap().put("ModuleExtensibleModel", mem);
						ExtensibleModelUtils.extend(resource, mem, false);
						if (mem != null) {
							OracleTableProperty moduleOP = (OracleTableProperty) mem.getData2().get(IOracleConstant.TABLE_DATA2_KEY);
							if (moduleOP != null) {
								moduleOP.setSpace(moduleSpace);
								moduleOP.setTabletype(tableType);
							}
							TableBaseProperty chouse = (TableBaseProperty) mem.getData2().get("chouse");
							if (chouse != null) {
								chouse.setSplitField(splitField);
								chouse.setSplitNum(splitNum);
								chouse.setStartData(splitData);
							}
							resource.save(property, true, new NullProgressMonitor());
						}
						break;
					}
				}
			}
			IFolder folder = (IFolder)createdModule.getResource();
			List<Map<String,Object>> exLists = exMaps.get(key);
			if (isClearModule && exLists.size() > 0) {
				Stack<IARESResource> resStack = new Stack<IARESResource>();
				resStack.addAll(Arrays.asList(createdModule.getARESResources()));
				while (!resStack.isEmpty()) {
					IARESResource res = resStack.pop();
					//清空模块资源，保留模块属性文件
					if (!StringUtils.equals(res.getElementName(), IARESModule.MODULE_PROPERTY_FILE)) {
						res.delete(true, null);
					}
				}
			}
			//每个Map都是一个数据库资源
			for (Map<String,Object> maps : exLists) {
				
				String tableName = maps.get(DBTableDefine.BASE_NAME)==null ? "":maps.get(DBTableDefine.BASE_NAME).toString();
				
				String type = maps.get(DBTableDefine.BASE_TYPE)==null ? "":maps.get(DBTableDefine.BASE_TYPE).toString();
				if (StringUtils.isNotBlank(tableName) && StringUtils.equalsIgnoreCase(type, "V")) {
					importView(maps,folder);
				}
				if (StringUtils.isNotBlank(tableName) && StringUtils.equalsIgnoreCase(type, "S")) {
					importSequence(maps,folder);
				}
				if (StringUtils.isBlank(tableName) || !tableType.contains(type)) {
					continue;
				}
				//基本信息
				TableResourceData table = DatabaseFactory.eINSTANCE.createTableResourceData();
				table.setName(tableName);
				table.setObjectId(maps.get(DBTableDefine.BASE_OBJECT_NUM)==null ? "":maps.get(DBTableDefine.BASE_OBJECT_NUM).toString());
				table.setChineseName(maps.get(DBTableDefine.BASE_CHINESE_NAME)==null ? "":maps.get(DBTableDefine.BASE_CHINESE_NAME).toString());
				table.setDescription(maps.get(DBTableDefine.BASE_DESCRIPTION)==null ? "":maps.get(DBTableDefine.BASE_DESCRIPTION).toString());
				IFile file = folder.getFile(tableName + "." +IDatabaseResType.Table);
				IARESResource res = (IARESResource) ARESCore.create(file);
				//基本信息的扩展信息
				Map<String, IAttributeHelper> helperMapMain = new HashMap<String, POIUtils.IAttributeHelper>();
				
				IExtensibleModelEditingSupport[] supportsMain = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.TABLE_RESOURCE_DATA);
				for (IExtensibleModelEditingSupport support : supportsMain) {
					for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.TABLE_RESOURCE_DATA)) {
						if (!desc.isDerived()) {
							if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
								helperMapMain.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
							} else {
								helperMapMain.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
							}
							
						}
					}
				}
				ExtensibleModelUtils.extend(res, table, false);
				
				for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
					String text = iterator.next();
					if (DBTableDefine.isExtendsText(text)) {
						IAttributeHelper helper = helperMapMain.get(text);
						if (helper != null && maps.get(text) instanceof String && StringUtils.isNotBlank(maps.get(text).toString())) {
							String value = (String) maps.get(text);
							if (StringUtils.equals("表类型", text)) {
								value = String.valueOf(DBTableDefine.getTableType(value));
							}
							helper.setValue(table, value);
						}
					}
				}
				
				List<TableColumn> primaryColumns = new ArrayList<TableColumn>();
				//表字段
				Object obj = maps.get(DBTableDefine.FIELD_DEFINE);
				if (obj instanceof List && ((List) obj).size() > 0) {
					List<List<String>> cols = (List<List<String>>) obj;
					Map<Integer,String> titleSort = new HashMap<Integer ,String>();
					// 标题名和属性助手的映射
					Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
					for (int i = 0; i < cols.size(); i++) {
						List<String> colStr = cols.get(i);
						if (i == 0) {
							if (!colStr.contains(DBTableDefine.COLUMN_CHINESE_NAME)) {
								hstool = true;
							}else {
								hstool = false;
							}
							
							IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.TABLE_COLUMN);
							for (IExtensibleModelEditingSupport support : supports) {
								for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.TABLE_COLUMN)) {
									if (!desc.isDerived()) {
										if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
										} else {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
										}
										
									}
								}
							}
							
							for (int j = 0; j < colStr.size(); j++) {
								titleSort.put(j , colStr.get(j));
//								if (!DBTableDefine.isColumnMainTitle(colStr.get(j))) {
//									String title = colStr.get(j);
//									if (helperMap.get(title) != null) {
//										helperList.put(title ,helperMap.get(title));
//									}
//								}
							}
							continue;
						}
						TableColumn column = DatabaseFactory.eINSTANCE.createTableColumn();
						table.getColumns().add(column);
						int ii = 0;
						for (int j = 0; j < colStr.size(); j++) {
							String value = colStr.get(j);
							if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_STATUS)) {
								if (StringUtils.isNotBlank(value)) {
									if (!maps.containsKey(DBTableDefine.KEY_DEFINE) && StringUtils.endsWith(value, DBTableDefine.COLUMN_PRIMARY_KEY)) {
										//加键约束之前的导出版本，将主键对应的表字段保存到临时列表中
										column.setMark(StringUtils.substringBeforeLast(value, DBTableDefine.COLUMN_PRIMARY_KEY));
//										column.setPrimaryKey(true);
										primaryColumns.add(column);
									}else {
										column.setMark(value);
									}
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_NAME)) {
								if (StringUtils.isNotBlank(value)) {
									column.setFieldName(value);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_NULL)) {
								//兼容恒生开发工具，如果是空，也表示不可为空
								if (StringUtils.isBlank(value) || StringUtils.equals(DBTableDefine.COLUMN_NULL_ABLE_N, value)) {
									column.setNullable(false);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_DEFAULT_VALUE)) {
								//默认值
								if (StringUtils.isNotBlank(value)) {
									column.setDefaultValue(value);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_CHINESE_NAME)) {
								//中文名
								if (StringUtils.isNotBlank(value)) {
									column.setChineseName(value);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_TYPE)) {
								//数据类型
								if (StringUtils.isNotBlank(value)) {
									column.setDataType(value);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_DESCRIPTION)) {
								//字段说明
								if (StringUtils.isNotBlank(value)) {
									column.setDescription(value);
								}
								continue;
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_COMMENTS) || StringUtils.equals(titleSort.get(j), DBTableDefine.COLUMN_REMARK)) {
								//备注
								if (StringUtils.isNotBlank(value)) {
									column.setComments(value);
								}
								continue;
							}else {
								ExtensibleModelUtils.extend(res, column, false);
								IAttributeHelper helper = helperMap.get(titleSort.get(j));
								if (helper != null) {
									helper.setValue(column, value);
								}
							}
						}
						//设置表字段类型，先默认设置为非标准字段
						column.setColumnType(ColumnType.NON_STD_FIELD);
						//然后通过与标准字段对比，确定最终类型
						setTableFieldColumnType(project,column);
					}
				}
				
				//表索引
				obj = maps.get(DBTableDefine.INDEX_DEFINE);
				if (obj instanceof List && ((List) obj).size() > 0) {
					List<List<String>> indexs = (List<List<String>>) obj;
					Map<Integer,String> titleSort = new HashMap<Integer ,String>();
					List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
					for (int i = 0; i < indexs.size(); i++) {
						List<String> colStr = indexs.get(i);
						if (i == 0) {
							// 标题名和属性助手的映射
							Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
							
							IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.TABLE_INDEX);
							for (IExtensibleModelEditingSupport support : supports) {
								for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.TABLE_INDEX)) {
									if (!desc.isDerived()) {
										if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
										} else {
											helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
										}
										
									}
								}
							}
							
							for (int j = 0; j < colStr.size(); j++) {
								String title = colStr.get(j);
								if (StringUtils.isBlank(title)) {
									continue;
								}
								titleSort.put(j , title);
								if (StringUtils.equals(title, DBTableDefine.INDEX_STATUS)) {
									title = DBTableDefine.INDEX_MARK;
								}
								if (!DBTableDefine.isIndexMainTitle(title)) {
									if (helperMap.get(title) != null && !helperList.contains(helperMap.get(title))) {
										helperList.add(helperMap.get(title));
									}
								}
							}
							continue;
						}
						TableIndex index = DatabaseFactory.eINSTANCE.createTableIndex();
						table.getIndexes().add(index);
						ExtensibleModelUtils.extend(res, index, false);
						int ii = 0;
						for (int j = 0; j < colStr.size(); j++) {
							String value = colStr.get(j);
							if (StringUtils.isBlank(value)) {
								continue;
							}
							if (StringUtils.equals(titleSort.get(j), "")) {
								continue;
							}else if ((StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_STATUS) || StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_DEFINE_UTIL)) && (j == 0)) {
								if (StringUtils.isNotBlank(value)) {
									index.setMark(value);
								}
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_NAME)) {
								if (StringUtils.isNotBlank(value)) {
									index.setName(value);
								}
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_UNIQUE)) {
								if (StringUtils.equals(DBTableDefine.INDEX_UNIQUE_Y, value)) {
									index.setUnique(true);
								}
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_CLUST)) {
								if (StringUtils.equals(DBTableDefine.INDEX_CLUST_Y, value)) {
									index.setCluster(true);
								}
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_MARK)) {
								if (StringUtils.isNotBlank(value)) {
									index.setMark(value);
								}
							}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.INDEX_COLUMNS) && (j > 0)) {
								if (StringUtils.isNotBlank(value)) {
									String[] ics = StringUtils.split(value, ",");
									for (String ic : ics) {
										TableIndexColumn tic = DatabaseFactory.eINSTANCE.createTableIndexColumn();
										tic.setColumnName(ic);
										index.getColumns().add(tic);
									}
								}
							}else {
								if (helperList.size() > ii) {
									helperList.get(ii).setValue(index, value);
									ii ++;
								}
							}
						}
					}
				}
				
				//键约束
				if(!maps.containsKey(DBTableDefine.KEY_DEFINE)){
					//excel是键约束存在之前的版本
					if(primaryColumns.size()>0){
						TableKey tableKey = DatabaseFactory.eINSTANCE.createTableKey();
						tableKey.setName("pk_" + tableName);
						tableKey.setType(key_type.PRIMARY);
						for(TableColumn primaryCol : primaryColumns){
							tableKey.getColumns().add(primaryCol);
						}
						table.getKeys().add(tableKey);
					}
				}else{
					//excel是键约束存在之后的版本
					obj = maps.get(DBTableDefine.KEY_DEFINE);
					if (obj instanceof List && ((List) obj).size() > 0) {
						List<List<String>> indexs = (List<List<String>>) obj;
						Map<Integer,String> titleSort = new HashMap<Integer ,String>();
						List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
						for (int i = 0; i < indexs.size(); i++) {
							List<String> colStr = indexs.get(i);
							if (i == 0) {
								// 标题名和属性助手的映射
								Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
								
								IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.TABLE_KEY);
								for (IExtensibleModelEditingSupport support : supports) {
									for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.TABLE_KEY)) {
										if (!desc.isDerived()) {
											if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
												helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
											} else {
												helperMap.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
											}
											
										}
									}
								}
								
								for (int j = 0; j < colStr.size(); j++) {
									String title = colStr.get(j);
									if (StringUtils.isBlank(title)) {
										continue;
									}
									titleSort.put(j , title);
									if (StringUtils.equals(title, DBTableDefine.KEY_DEFINE)) {
										title = DBTableDefine.KEY_MARK;
									}
									if (!DBTableDefine.isKeyMainTitle(title)) {
										if (helperMap.get(title) != null && !helperList.contains(helperMap.get(title))) {
											helperList.add(helperMap.get(title));
										}
									}
								}
								continue;
							}
							TableKey tableKey = DatabaseFactory.eINSTANCE.createTableKey();
							table.getKeys().add(tableKey);
							String foreignTable = "";
							String foreignField = "";
							ExtensibleModelUtils.extend(res, tableKey, false);
							int ii = 0;
							for (int j = 0; j < colStr.size(); j++) {
								String value = colStr.get(j);
								if (StringUtils.isBlank(value)) {
									continue;
								}
								if (StringUtils.equals(titleSort.get(j), "")) {
									continue;
								}else if ((StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_DEFINE))) {
									if (StringUtils.isNotBlank(value)) {
										tableKey.setMark(value);
									}
								}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_NAME)) {
									if (StringUtils.isNotBlank(value)) {
										tableKey.setName(value);
									}
								}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_TYPE)) {
									if(StringUtils.isNotBlank(value)){
										tableKey.setType(key_type.get(value));
									}
								}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_COLUMNS)) {
									if (StringUtils.isNotBlank(value)) {
										String[] ics = StringUtils.split(value, ",");
										for (String ic : ics) {
											for(TableColumn col : table.getColumns()){
												if(StringUtils.equals(ic, col.getName())){
													tableKey.getColumns().add(col);
													break;
												}
											}
										}
									}
								}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_FOREIGN_KEY_TABLE)) {
									if (StringUtils.isNotBlank(value)) {
										foreignTable = value;
									}
								}else if (StringUtils.equals(titleSort.get(j), DBTableDefine.KEY_FOREIGN_KEY_FIELD)) {
									if (StringUtils.isNotBlank(value)) {
										foreignField = value;
									}
								}else {
									if (helperList.size() > ii) {
										helperList.get(ii).setValue(tableKey, value);
										ii ++;
									}
								}
							}
							if(StringUtils.isNotBlank(foreignTable)){
								String[] tmps = StringUtils.split(foreignField, ",");
								for(String tmp : tmps){
									ForeignKey foreignKey = DatabaseFactory.eINSTANCE.createForeignKey();
									foreignKey.setFieldName(tmp);
									foreignKey.setTableName(foreignTable);
									tableKey.getForeignKey().add(foreignKey);
								}
								if(StringUtils.isBlank(foreignField)){
									ForeignKey foreignKey = DatabaseFactory.eINSTANCE.createForeignKey();
									foreignKey.setTableName(foreignTable);
									tableKey.getForeignKey().add(foreignKey);
								}
							}
						}
					}
				}
				
				write(file, table);
			}
		}
		monitor.done();
		return result;
	}
	
	/**
	 * 设置表字段的列类型
	 * 
	 * @param project
	 * @param column 表字段
	 */
	private static void setTableFieldColumnType(IARESProject project ,TableColumn column) {
		String columnName = column.getFieldName();
		String columnCName = column.getChineseName();
		String columnDataType = column.getDataType();
		
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, columnName, true);
		if(null != ref) {
			StandardField filed = (StandardField) ref.getObject();
			if(StringUtils.equals(columnName, filed.getName()) &&
					StringUtils.equals(columnDataType, filed.getDataType())) {
				//字段名、字段中文名、字段类型都相同才为标准字段
				if (!hstool) {
					if (StringUtils.equals(columnCName, filed.getChineseName())) {
						column.setColumnType(ColumnType.STD_FIELD);
					}
				}else {
					column.setColumnType(ColumnType.STD_FIELD);
				}
				
			}
		}
	}
	
	private static boolean isCH(String key){
		if(key.matches("(^[a-z_][a-z0-9_]{0,49}$)")){
			return true;
		}
		return false;
	}
	
	/**
	 * 生成日志文件
	 * 
	 */
	public static void writeErrorMsgToFile(IARESProject project , String errorMsg ,String fileName ,IProgressMonitor monitor){

		final IFile report = project.getProject().getFile(fileName);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter w = new PrintWriter(out);
		w.print("/**********************************************************/\r\n");
		w.print(errorMsg);
		w.flush();
		w.close();
		try {
			report.create(new ByteArrayInputStream(out.toByteArray()), true, monitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		Display.getDefault().asyncExec(new Runnable(){
			public void run() {
				try {
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), report);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void importView(Map<String,Object> maps , IFolder folder){
		String viewName = maps.get(DBTableDefine.BASE_NAME)==null ? "":maps.get(DBTableDefine.BASE_NAME).toString();
		//基本信息
		ViewResourceData view = DatabaseFactory.eINSTANCE.createViewResourceData();
		view.setName(viewName);
		if (!maps.containsKey(DBTableDefine.VIEW_SQL_NAME)) {
			view.setSql(maps.get(DBTableDefine.MODIFY_DEFINE)==null?"":maps.get(DBTableDefine.MODIFY_DEFINE).toString());
		}else {
			view.setSql(maps.get(DBTableDefine.VIEW_SQL_NAME)==null ? "":maps.get(DBTableDefine.VIEW_SQL_NAME).toString());
		}
		view.setObjectId(maps.get(DBTableDefine.BASE_OBJECT_NUM)==null ? "":maps.get(DBTableDefine.BASE_OBJECT_NUM).toString());
		String isHis = maps.get("存在历史表")==null ? "":maps.get("存在历史表").toString();
		if (StringUtils.equals(isHis, "Y")) {
			view.setIsHistory(true);
		}
		view.setChineseName(maps.get(DBTableDefine.BASE_CHINESE_NAME)==null ? "":maps.get(DBTableDefine.BASE_CHINESE_NAME).toString());
		view.setDescription(maps.get(DBTableDefine.BASE_DESCRIPTION)==null ? "":maps.get(DBTableDefine.BASE_DESCRIPTION).toString());
		IFile file = folder.getFile(viewName + "." +IDatabaseResType.View);
		IARESResource res = (IARESResource) ARESCore.create(file);
		setValue(maps, res, view, DatabasePackage.Literals.VIEW_RESOURCE_DATA);
		write(file, view);
	}
	
	private static void importSequence(Map<String,Object> maps , IFolder folder){
		String seqName = maps.get(DBTableDefine.BASE_NAME)==null ? "":maps.get(DBTableDefine.BASE_NAME).toString();
		//基本信息
		SequenceResourceData sequence = OracleFactory.eINSTANCE.createSequenceResourceData();
		sequence.setName(seqName);
		sequence.setObjectId(maps.get(DBTableDefine.BASE_OBJECT_NUM)==null ? "":maps.get(DBTableDefine.BASE_OBJECT_NUM).toString());
		String isHis = maps.get("存在历史表")==null ? "":maps.get("存在历史表").toString();
		if (StringUtils.equals(isHis, "Y")) {
			sequence.setIsHistory(true);
		}else {
			sequence.setIsHistory(false);
		}
		String cyc = maps.get(DBTableDefine.SEQUENCE_CYC)==null ? "":maps.get(DBTableDefine.SEQUENCE_CYC).toString();
		if (StringUtils.equalsIgnoreCase(cyc, "Y")) {
			sequence.setCycle(true);
		}
		if (StringUtils.equalsIgnoreCase(cyc, "N")) {
			sequence.setCycle(false);
		}
		String cache = maps.get(DBTableDefine.SEQUENCE_CACHE)==null ? "":maps.get(DBTableDefine.SEQUENCE_CACHE).toString();
		if (StringUtils.equalsIgnoreCase(cache, "Y")) {
			sequence.setUseCache(true);
		}
		if (StringUtils.equalsIgnoreCase(cache, "N")) {
			sequence.setUseCache(false);
		}
		fillSequence(maps, sequence);
		sequence.setTableName(maps.get(DBTableDefine.SEQUENCE_DATABASE_NAME)==null ? "":maps.get(DBTableDefine.SEQUENCE_DATABASE_NAME).toString());
		sequence.setChineseName(maps.get(DBTableDefine.BASE_CHINESE_NAME)==null ? "":maps.get(DBTableDefine.BASE_CHINESE_NAME).toString());
		sequence.setDescription(maps.get(DBTableDefine.BASE_DESCRIPTION)==null ? "":maps.get(DBTableDefine.BASE_DESCRIPTION).toString());
		IFile file = folder.getFile(seqName + "." +IDatabaseResType.Sequence);
		IARESResource res = (IARESResource) ARESCore.create(file);
		setValue(maps, res, sequence, OraclePackage.Literals.SEQUENCE_RESOURCE_DATA);
		write(file, sequence);
	}
	
	private static void fillSequence(Map<String, Object> maps , SequenceResourceData sequence){
		Map<String , String> paramMap = new HashMap<String, String>();
		Object obj = maps.get(DBTableDefine.FIELD_DEFINE);
		if (obj instanceof List && ((List) obj).size() > 0) {
			List<List<String>> cols = (List<List<String>>) obj;
			for (int i = 1; i < cols.size(); i++) {
				List<String> item = cols.get(i);
				if (item.size() > 2) {
					paramMap.put(item.get(1), item.get(2));
				}
			}
		}
		if (maps.containsKey(DBTableDefine.SEQUENCE_INC)) {
			sequence.setIncrement(maps.get(DBTableDefine.SEQUENCE_INC)==null ? "":maps.get(DBTableDefine.SEQUENCE_INC).toString());
		}else {
			if (paramMap.containsKey("increment")) {
				sequence.setIncrement(paramMap.get("increment"));
			}
		}
		if (maps.containsKey(DBTableDefine.SEQUENCE_MIN)) {
			sequence.setMinValue(maps.get(DBTableDefine.SEQUENCE_MIN)==null ? "":maps.get(DBTableDefine.SEQUENCE_MIN).toString());
		}else {
			if (paramMap.containsKey("minvalue")) {
				sequence.setMinValue(paramMap.get("minvalue"));
			}
		}
		if (maps.containsKey(DBTableDefine.SEQUENCE_MAX)) {
			sequence.setMaxValue(maps.get(DBTableDefine.SEQUENCE_MAX)==null ? "":maps.get(DBTableDefine.SEQUENCE_MAX).toString());
		}else {
			if (paramMap.containsKey("maxvalue")) {
				sequence.setMaxValue(paramMap.get("maxvalue"));
			}
		}
		if (maps.containsKey(DBTableDefine.SEQUENCE_START)) {
			sequence.setStart(maps.get(DBTableDefine.SEQUENCE_START)==null ? "":maps.get(DBTableDefine.SEQUENCE_START).toString());
		}else {
			if (paramMap.containsKey("start")) {
				sequence.setStart(paramMap.get("start"));
			}
		}
		if (maps.containsKey(DBTableDefine.SEQUENCE_CACHE_SIZE)) {
			sequence.setCache(maps.get(DBTableDefine.SEQUENCE_CACHE_SIZE)==null ? "":maps.get(DBTableDefine.SEQUENCE_CACHE_SIZE).toString());
		}else {
			if (paramMap.containsKey("cache")) {
				sequence.setCache(paramMap.get("cache"));
			}
		}
	}
	
	private static List<Map<String,Object>> getMoudleSheet (HSSFWorkbook wb ,HSSFFormulaEvaluator evaluator , int startRow , int startCol){
		HSSFSheet sheet = wb.getSheet("模块信息");
		Map<Integer , String> titileMap = new HashMap<Integer, String>();
		List<Map<String,Object>> moduleValueMap = new ArrayList<Map<String,Object>>();
		if (sheet != null) {
			for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
				Map<String,Object> colMap = new HashMap<String, Object>();
				HSSFRow row = sheet.getRow(i);
				if (i == 0) {
					for (int j = 0; j <= row.getLastCellNum(); j++) {
						titileMap.put(j, POIUtils.getCellStringValue(row.getCell(j), evaluator).trim());
					}
					continue;
				}
				for (int j = 0; j <= row.getLastCellNum(); j++) {
					colMap.put(titileMap.get(j), POIUtils.getCellStringValue(row.getCell(j), evaluator));
				}
				moduleValueMap.add(colMap);
			}
		}
		return moduleValueMap;
	}
	
	
	private static void setValue(Map<String,Object> maps , IARESResource res , ExtensibleModel model ,EClass eclass){
		//基本信息的扩展信息
		Map<String, IAttributeHelper> helperMapMain = new HashMap<String, POIUtils.IAttributeHelper>();
		
		IExtensibleModelEditingSupport[] supportsMain = ExtensibleModelUtils.getEndabledEditingSupports(res, eclass);
		for (IExtensibleModelEditingSupport support : supportsMain) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, eclass)) {
				if (!desc.isDerived()) {
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMapMain.put(desc.getDisplayName(), new POIUtils.ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMapMain.put(desc.getDisplayName(), new POIUtils.ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
			}
		}
		ExtensibleModelUtils.extend(res, model, false);
		
		for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
			String text = iterator.next();
			if (DBTableDefine.isExtendsText(text)) {
				IAttributeHelper helper = helperMapMain.get(text);
				if (helper != null && maps.get(text) instanceof String && StringUtils.isNotBlank(maps.get(text).toString())) {
					String value = (String) maps.get(text);
					helper.setValue(model, value);
				}
			}
		}
	}
	
	private static void write(IFile file,ExtensibleModel model){
		// 2013.11.19 sundl 修改资源创建方式
		IARESResource res = (IARESResource) ARESCore.create(file);
		IARESModule module = res.getModule();
		try {
			module.createResource(file.getName(), model);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
//		try {
//			if (file.exists()) {
//				file.delete(true, new NullProgressMonitor());
//			}
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			EmfModelConverter converter = new EmfModelConverter();
//			converter.write(bos, model);
//			file.create(new ByteArrayInputStream(bos.toByteArray()), true, null);
//		} catch (CoreException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	static class ExtensibleValue {
		String moduleSpace = "";
		String tableType = "一般表";
		String splitField = "";
		String splitNum = "";
		String splitData = "";
		/**
		 * @return the moduleSpace
		 */
		public String getModuleSpace() {
			return moduleSpace;
		}
		/**
		 * @param moduleSpace the moduleSpace to set
		 */
		public void setModuleSpace(String moduleSpace) {
			this.moduleSpace = moduleSpace;
		}
		/**
		 * @return the tableType
		 */
		public String getTableType() {
			return tableType;
		}
		/**
		 * @param tableType the tableType to set
		 */
		public void setTableType(String tableType) {
			this.tableType = tableType;
		}
		/**
		 * @return the splitField
		 */
		public String getSplitField() {
			return splitField;
		}
		/**
		 * @param splitField the splitField to set
		 */
		public void setSplitField(String splitField) {
			this.splitField = splitField;
		}
		/**
		 * @return the splitNum
		 */
		public String getSplitNum() {
			return splitNum;
		}
		/**
		 * @param splitNum the splitNum to set
		 */
		public void setSplitNum(String splitNum) {
			this.splitNum = splitNum;
		}
		/**
		 * @return the splitData
		 */
		public String getSplitData() {
			return splitData;
		}
		/**
		 * @param splitData the splitData to set
		 */
		public void setSplitData(String splitData) {
			this.splitData = splitData;
		}
	}
	
	public static boolean isPrimaryKey(TableColumn coloumn){
		EObject data = coloumn.eContainer();
		if(data != null && data instanceof TableResourceData){
			for(TableKey key : ((TableResourceData)data).getKeys()){
				if(key.getType().equals(key_type.PRIMARY)){
					if(key.getColumns().contains(coloumn)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isUnique(TableColumn coloumn){
		EObject data = coloumn.eContainer();
		if(data != null && data instanceof TableResourceData){
			for(TableKey key : ((TableResourceData)data).getKeys()){
				if(key.getType().equals(key_type.UNIQUE)){
					if(key.getColumns().contains(coloumn)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
