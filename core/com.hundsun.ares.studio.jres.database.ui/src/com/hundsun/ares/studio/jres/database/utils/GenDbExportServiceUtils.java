/**
 * 
 */
package com.hundsun.ares.studio.jres.database.utils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.Language;
import com.hundsun.ares.studio.jres.metadata.ui.LanguageRegister;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleDataAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.NormalAttributeHelper;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.TableBaseProperty;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.jres.model.database.oracle.impl.SequenceResourceDataImpl;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.script.util.IScriptStringUtil;
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
public class GenDbExportServiceUtils {

	private static final String MARK = "标记";
	
	private static final String PRIMARYKEY = "是否主键";
	
	private final static String[] titile = new String[] { "模块英文名","模块中文名", "对象号", "数据表名",
			"表中文名", "类型", "说明"};

	private final static String[] moduleTitle = new String[]{"模块名","表空间" ,"表类型","分区字段","分区个数","分区开始年月"};
	
	private static List<String> tableExtendsTitle = new ArrayList<String>();
	private static List<String> sequenceExtendsTitle = new ArrayList<String>();
	private static Map<String , String> tableType = new HashMap<String, String>();
	private static Map<String, Integer> bizTypeHy = new HashMap<String, Integer>();
	private static Map<String, Integer> stdTypeHy = new HashMap<String, Integer>();
	
	static {
		//FIXME 数组下标写死了，所以如果要增加，就写在最后面
		tableExtendsTitle.add("是否自定义表分区");
		tableExtendsTitle.add("表分区字段");
		tableExtendsTitle.add("分区个数");
		tableExtendsTitle.add("分区开始日期");
		tableExtendsTitle.add("存在历史表");
		tableExtendsTitle.add("存在冗余表");
		tableExtendsTitle.add("存在清算表");
		tableExtendsTitle.add("对象号");
		tableExtendsTitle.add("版本号");
		tableExtendsTitle.add("所在数据库");
		tableExtendsTitle.add("表类型");
		
		sequenceExtendsTitle.add("对象号");
		sequenceExtendsTitle.add("版本号");
		
		tableType.put("COMMON", "U");
		tableType.put("TEMP_NO_VALUE", "T");
		tableType.put("TEMP_WITH_VALUE", "M");
	}
	
	public static final String EXPORT_TARGET_DB_DIR = "export_target_db_dir";
	private static Map<String,String> packageMap = null;
	
	private static HSSFCellStyle style;
	private static HSSFFont linkFont;
	private static HSSFFont titleFont;
	private static HSSFCellStyle labelStyle;
	private static HSSFCellStyle titleStyle;
	private static HSSFCellStyle textStyle;
	private static HSSFCellStyle multTextStyle;
	/**
	 * 这个缓存主要用于记录超链接的定位
	 */
	private static Map<String, String> posMap = new HashMap<String, String>();

	private final static String MENU_SHEET = "数据表目录";
	private final static String MODULE_SHEET = "模块信息";
	private static String BIZ_TYPE = "业务数据类型";
	private static String STD_TYPE = "标准数据类型";
	private final static String BIZ_TYPE_NAME = "标准类型";
	/**
	 * 开始行
	 */
	private final static int startRowNum = 1;
	/**
	 * 开始列
	 */
	private final static int startCellNum = 1;

	// 将给定的对象转换为
	private static ICommonModel getCommonModel(Object info) {
		ICommonModel commonModel = null;
		if (info == null) {
			commonModel = null;
		} else if (info instanceof ICommonModel) {
			commonModel = (ICommonModel)info;
		} else if (info instanceof IAdaptable) {
			IAdaptable adapter = (IAdaptable)info;
			commonModel = (ICommonModel) adapter.getAdapter(ICommonModel.class);
		} else {
			IAdapterManager manager = Platform.getAdapterManager();
			commonModel = (ICommonModel)manager.getAdapter(info, ICommonModel.class);
		}
		return commonModel;
	}
	
	/**
	 * 导出数据库文档
	 * 
	 * @param dbMap
	 *            所有要导出的资源
	 * @param fileName
	 *            目标文件名，不能带.xls后缀
	 * @param split
	 *            是否按模块切分文档，如拆分则每个文档的命名规则为fileName_<模块名>.xls
	 * @return    返回excel文件名列表
	 */
	public static List<String> export(Map<String, List<IARESResource>> dbMap,
			String fileName,boolean defvCol , boolean split,IProgressMonitor monitor) {
		List<String> files = new ArrayList<String>();
		int total = 0;
		for(List<IARESResource> reses : dbMap.values()){
			total += reses.size();
		}
		monitor.beginTask("导出数据库文档", total);
		FileOutputStream fos = null;
		packageMap = new HashMap<String, String>();
		Map<String,Map<String, List<IARESResource>>> outputList = new HashMap<String,Map<String, List<IARESResource>>>();
		if (split) {
			Map<String,Map<String, List<IARESResource>>> seqPackMap = new HashMap<String, Map<String,List<IARESResource>>>();
			for (Entry<String, List<IARESResource>> entry : dbMap.entrySet()){
				String key = entry.getKey();
				String sk = StringUtils.substringBefore(key, "/");
				if (seqPackMap.get(sk) == null) {
					Map<String, List<IARESResource>> tempMap = new HashMap<String, List<IARESResource>>();
					tempMap.put(key, entry.getValue());
					seqPackMap.put(sk, tempMap);
				}else {
					seqPackMap.get(sk).put(key, entry.getValue());
				}
			}
			outputList.putAll(seqPackMap);
		} else {
			outputList.put("",dbMap);
		}
		for (Map.Entry<String,Map<String,List<IARESResource>>> tempMap : outputList.entrySet()) {
			init();
			try {
				String realFileName = fileName;
				if (StringUtils.endsWith(realFileName, ".xls")) {
					realFileName = StringUtils.replace(realFileName, ".xls", "");
				}
				if (split) {
					String mn = tempMap.getKey();
					realFileName += "_" + mn+"("+getFileName(tempMap.getValue(), mn)+")";
				}
				if (!StringUtils.endsWith(realFileName, ".xls")) {
					realFileName += ".xls";
				}
				files.add(realFileName);
				fos = new FileOutputStream(realFileName);
				HSSFWorkbook wb = new HSSFWorkbook();

				/**
				 * 
				 * 先创建数据类型，包括业务数据类型，标准数据类型
				 */
				createColumnTypeAres(wb, tempMap.getValue());
				
				createDetailSheet(wb, tempMap.getValue() ,defvCol ,monitor );

				createMenuSheet(wb, tempMap.getValue());
				
				createModuleSheet(wb, tempMap.getValue());

				// 因为要知道超链接的地址，所以目录页是最后创建的，这里把目录页重新放到第一个
				wb.setSheetOrder(MENU_SHEET, 0);
				wb.setSheetOrder(BIZ_TYPE, wb.getSheetIndex(MODULE_SHEET));
				wb.setSheetOrder(STD_TYPE, wb.getSheetIndex(MODULE_SHEET));
				wb.write(fos);
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(),e);
			} finally {
				try {
					if (fos != null)
						fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		monitor.done();
		return files;
	}

	private static String getFileName (Map<String, List<IARESResource>> tempMap ,String moduleName){
		
		String cName = "";
		IARESModuleRoot module = null;
		IARESModule mm = null;
		// 分块后每个Map中只有一条数据
		for (Map.Entry<String, List<IARESResource>> map : tempMap.entrySet()){
			module = map.getValue().get(0).getRoot();
			break;
		}
		try {
			IARESModule[] aresModule = module.getModules();
			for (IARESModule am : aresModule){
				if (StringUtils.equals(am.getElementName(), StringUtils.replace(moduleName, "/", "."))) {
					mm = am;
					break;
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		IARESResource property = mm.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		if (property != null && property.exists()) {
			try {
				Object info = property.getInfo();
				if (info != null) {
					ICommonModel model = getCommonModel(info);
					if (model != null) {
						Object obj = model.getValue(ICommonModel.CNAME);
						if (obj != null) {
							cName = obj.toString();
						}
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		
		return cName;
	}
	
	private static IARESModule getModule(Map<String, List<IARESResource>> tempMap ,String moduleName){
		IARESModuleRoot moduleRoot = null;
		IARESModule module = null;
		// 分块后每个Map中只有一条数据
		for (Map.Entry<String, List<IARESResource>> map : tempMap.entrySet()){
			moduleRoot = map.getValue().get(0).getRoot();
			break;
		}
		try {
			IARESModule[] aresModule = moduleRoot.getModules();
			for (IARESModule am : aresModule){
				if (StringUtils.equals(am.getElementName(), StringUtils.replace(moduleName, "/", "."))) {
					module = am;
					break;
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return module;
	}
	
	public static String getChineseFileName (String separator ,IARESModule module){
		StringBuffer sb = new StringBuffer();
		getModuleChineseName(module, sb ,separator);
		String[] ps = StringUtils.split(sb.toString(), separator);
		StringBuffer sbf = new StringBuffer();
		for (int i = ps.length-1; i > -1 && ps.length > 0; i--) {
			if (StringUtils.isNotBlank(sbf.toString())) {
				sbf.append(separator);
			}
			sbf.append(ps[i]);
		}
		return sbf.toString();
	}
	
	public static void getModuleChineseName (IARESModule module , StringBuffer sb , String separator){
		if (module != null) {
			IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
			if (property != null && property.exists()) {
				try {
					ModuleProperty info = property.getInfo(ModuleProperty.class);
					if (info != null) {
						Object obj = info.getValue(ICommonModel.CNAME);
						if (obj != null) {
							if (StringUtils.isNotBlank(sb.toString())) {
								sb.append(separator);
							}
							sb.append(obj.toString());
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			getModuleChineseName(module.getParentModule(), sb ,separator);
		}
	}
	
	/**
	 * 初始化样式，不初始化，会造成渲染异常
	 */
	private static void init() {
		style = null;
		linkFont = null;
		titleFont = null;
		labelStyle = null;
		titleStyle = null;
		textStyle = null;
		multTextStyle = null;
	}

	/**
	 * 创建超链接的Key
	 * 
	 * @return
	 */
	private static String getPosKey(IARESResource res) {
		return res.getFullyQualifiedName() + "." + res.getType();
	}

	/**
	 * 创建表数据的明细页
	 * 
	 * @param wb
	 * @param dbMap
	 * @throws Exception
	 */
	private static int rowNum = 1;
	
	private static void createDetailSheet(HSSFWorkbook wb,
			Map<String, List<IARESResource>> dbMap,boolean defvCol , IProgressMonitor monitor) throws Exception {

		List<String> packageNames = sortPackages(dbMap.keySet());
		for (String packageName : packageNames) {
			IARESModule module = getModule(dbMap, packageName);
			String sheetName = getChineseFileName( "-" ,module);
			if (StringUtils.isBlank(sheetName)) {
				continue;
			}
			HSSFSheet sheet = null;
			sheetName = createSheetName(wb, sheetName);
			packageMap.put(packageName, sheetName);
			if (wb.getSheet(sheetName) != null) {
				String dupIndex = StringUtils.substringAfterLast(sheetName, "_");
				if (StringUtils.isNotBlank(dupIndex)) {
					try {
						int ind = Integer.parseInt(dupIndex);
						ind++;
						sheetName = sheetName+"_"+ind;
					} catch (Exception e) {
					}
				}else {
					sheetName = sheetName+"_"+1;
				}
			}
			if (sheetName.length() > 31) {
				sheetName = StringUtils.substring(sheetName, 0, 31);
			}
			sheetName = createSheetName(wb, sheetName);
			
			sheet = wb.createSheet(sheetName);
			
			setDefaultCellStyle(wb, sheet);
			List<IARESResource> reses = dbMap.get(packageName);
			rowNum = 1;
			for (int i = 0; reses != null && i < reses.size(); i++) {
				IARESResource res = reses.get(i);
				monitor.worked(1);
				monitor.subTask(res.getElementName());
				posMap.put(getPosKey(res), "#'" + sheetName + "'!A" + rowNum);

				if (StringUtils.equals(IDatabaseResType.Table, res.getType())) {
					createTableSheet(wb, sheet, res , defvCol);
				} else if (StringUtils.equals(IDatabaseResType.View,
						res.getType())) {
					createViewSheet(wb, sheet, res);
				}else if (StringUtils.equals(IDatabaseResType.Sequence, res.getType())) {
					createSeuence(wb, sheet, res);
				}
			}
			sheet.setColumnWidth( 0, 500);
			sheet.setColumnWidth( 1, 3000);
			sheet.setColumnWidth( 2, 6000);
			sheet.setColumnWidth( 3, 6000);
			sheet.setColumnWidth( 4, 6000);
			sheet.setColumnWidth( 5, 4000);
			sheet.setColumnWidth( 6, 8000);
		}
	}

	private static String createSheetName (HSSFWorkbook wb , String sheetName ){
		HSSFSheet sheet = null;
		boolean dul = true;
		int i = 1;
		while (dul) {
			sheet = wb.getSheet(sheetName);
			if (sheet != null) {
				if (StringUtils.endsWith(sheetName, "_"+i)) {
					i = Integer.parseInt(StringUtils.substringAfter(sheetName, "_"))+1;
					int indLeg = ("_" + i).length();
					if (sheetName.length() + indLeg > 31) {
						sheetName = StringUtils.substring(sheetName, 0, 31 - indLeg);
					}else {
						sheetName = StringUtils.substringBeforeLast(sheetName, "_");
					}
				}else{
					int indLeg = ("_" + i).length();
					if (sheetName.length() + indLeg > 31) {
						sheetName = StringUtils.substring(sheetName, 0, 31 - indLeg);
					}
				}
				sheetName += "_" + i;
			}else {
				return sheetName;
			}
		}
		return StringUtils.EMPTY;
	}
	
	private static void createTableSheet(HSSFWorkbook wb, HSSFSheet sheet,
			IARESResource res ,boolean defvCol) throws Exception {
		if (res == null) {
			return;
		}
		TableResourceData table = res.getInfo(TableResourceData.class);
		if (table == null) {
			return;
		}
		String space = getExtendsData(table, OraclePackage.Literals.ORACLE_TABLE_PROPERTY, OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE);
		Map<String, Object> helperMap = new HashMap<String, Object>();
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.TABLE_RESOURCE_DATA);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.TABLE_RESOURCE_DATA)) {
				if (!desc.isDerived()) {
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				} else {
					helperMap.put(desc.getDisplayName(), desc.getLabelProvider().getText(space));
				}
				
			}
		}
		
		
		Map<String, String> result = new HashMap<String,String>();
		
		for (Entry<String, Object> entry : helperMap.entrySet()) {
			if (entry.getValue() instanceof IAttributeHelper) {
				result.put(entry.getKey(), ((IAttributeHelper)entry.getValue()).getValue(table));
			}else {
				result.put(entry.getKey(), entry.getValue().toString());
			}
		}
		
		HSSFRow row = sheet.createRow(rowNum);
		createCell(row, 1, "对象号", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 4));
		createCell(row, (short) 2, table.getObjectId(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "版本号", getLabelStyle(wb));
		String version = "";
		{
			//找出最新的版本号
			version = getCurrHis(res, table)[0];
		}
		createCell(row, (short) 6, version, getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "表名", getLabelStyle(wb));
		createCell(row, (short) 2, table.getName(), getTextStyle(wb));
		createCell(row, (short) 3, "表类型", getLabelStyle(wb));
		createCell(row, (short) 4, getTableType(result.get("表类型")), getTextStyle(wb));
		createCell(row, (short) 5, "所在数据库", getLabelStyle(wb));
		createCell(row, (short) 6, space.toString(), getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "中文名", getLabelStyle(wb));
		createCell(row, (short) 2, table.getChineseName(), getTextStyle(wb));
		createCell(row, (short) 3, tableExtendsTitle.get(0), getLabelStyle(wb));
		String obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__USER_SPLIT);
		createCell(row, (short) 4, BooleanUtils.toBoolean(obj)?"Y":"N", getTextStyle(wb));
		createCell(row, (short) 5, "", getLabelStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, tableExtendsTitle.get(1), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_FIELD);
		createCell(row, (short) 2, obj.toString(), getTextStyle(wb));
		createCell(row, (short) 3, tableExtendsTitle.get(2), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__SPLIT_NUM);
		createCell(row, (short) 4, obj.toString(), getTextStyle(wb));
		createCell(row, (short) 5, tableExtendsTitle.get(3), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__START_DATA);
		createCell(row, (short) 6, obj.toString(), getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, tableExtendsTitle.get(4), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__HISTORY);
		createCell(row, (short) 2, BooleanUtils.toBoolean(obj)?"Y":"N",getTextStyle(wb));
		createCell(row, (short) 3, tableExtendsTitle.get(5), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__IS_REDU);
		createCell(row, (short) 4, BooleanUtils.toBoolean(obj)?"Y":"N", getTextStyle(wb));
		createCell(row, (short) 5, tableExtendsTitle.get(6), getLabelStyle(wb));
		obj = getExtendsData(table, ChousePackage.Literals.TABLE_BASE_PROPERTY, ChousePackage.Literals.TABLE_BASE_PROPERTY__IS_CLEAR);
		createCell(row, (short) 6, BooleanUtils.toBoolean(obj)?"Y":"N", getTextStyle(wb));
		
		int cellIndex = 0;
		for (Entry<String, String> entry : result.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!tableExtendsTitle.contains(key)) {
				if (cellIndex % 6 == 0) {
					cellIndex = 0;
					row = createRow(sheet);
				}
				cellIndex++;
				createCell(row, (short) cellIndex, key, getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, value, getTextStyle(wb));
			}
		}
		if (cellIndex % 6 != 0) {
			int lastCell = (6 - cellIndex )/2;
			for (int i = 0; i < lastCell; i++) {
				cellIndex++;
				createCell(row, (short) cellIndex, "", getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, "", getTextStyle(wb));
			}
		}
		
		row = createRow(sheet);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, ++rowNum ,2, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum, 1, 1));
		createCell(row, (short) 1, "说明", getLabelStyle(wb));
		createCell(row, (short) 2, table.getDescription(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		rowNum--;
		row = createRow(sheet);
		createCell(row, (short) 1, "", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		
		String[] titles = new String[]{"字段", "字段名","中文名","字段类型","空值","备注"};
		EStructuralFeature[] features = new EStructuralFeature[]{
				DatabasePackage.Literals.TABLE_COLUMN__MARK,
				DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME, 
				DatabasePackage.Literals.TABLE_COLUMN__CHINESE_NAME,
				DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE,
				DatabasePackage.Literals.TABLE_COLUMN__NULLABLE,
				DatabasePackage.Literals.TABLE_COLUMN__COMMENTS};
		if (defvCol) {
			titles = new String[]{"字段", "字段名","中文名","字段类型","空值","备注" ,"默认值"};
			features = new EStructuralFeature[]{
					DatabasePackage.Literals.TABLE_COLUMN__MARK,
					DatabasePackage.Literals.TABLE_COLUMN__FIELD_NAME, 
					DatabasePackage.Literals.TABLE_COLUMN__CHINESE_NAME,
					DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE,
					DatabasePackage.Literals.TABLE_COLUMN__NULLABLE,
					DatabasePackage.Literals.TABLE_COLUMN__COMMENTS,
					DatabasePackage.Literals.TABLE_COLUMN__DEFAULT_VALUE};
		}
		int titleLength = titles.length;
		List< List<String> > table1 = POIUtils.exportExcelStringTable(table, DatabasePackage.Literals.TABLE_RESOURCE_DATA__COLUMNS,DatabasePackage.Literals.TABLE_COLUMN,
				titles, 
				features, 
				true, 
				new String[]{}, new String[]{}, res, null
		);
		for (int i = 0; i < table1.size(); i++) {
			List<String> datas = table1.get(i);
			row = createRow(sheet);
			String fieldName = datas.get(1);
			String chineseName = datas.get(2);
			String type = datas.get(3);
			String desc = datas.get(5);
			TableColumn tableColmn = null;
			for (TableColumn tc : table.getColumns()) {
				if (StringUtils.equals(tc.getFieldName(), fieldName)) {
					tableColmn = tc;
					break;
				}
			}
			if (tableColmn != null) {
				if (tableColmn.getColumnType() == ColumnType.STD_FIELD) {
					StandardField stdField = getStdField(res.getARESProject(),fieldName);
					if (stdField != null) {
						if(StringUtils.isBlank(chineseName))
							chineseName = stdField.getChineseName();
						if(StringUtils.isBlank(type))
							type = stdField.getDataType();
						StringBuffer text = new StringBuffer();
						String dt = stdField.getDictionaryType();
						ReferenceInfo dictInfo = ReferenceManager.getInstance().getFirstReferenceInfo(res.getARESProject(), IMetadataRefType.Dict, dt, false);
						if (dictInfo != null) {
							if (dictInfo.getObject() instanceof DictionaryType) {
								DictionaryType dictType = (DictionaryType) dictInfo.getObject();
								for(DictionaryItem item : ((DictionaryType) dictType).getItems()){
									String value = StringUtils.defaultString(item.getValue());
									String cn = StringUtils.defaultString(item.getChineseName());
									text.append(value);
									text.append(":");
									text.append(cn);
									text.append(" ");
								}
							}
						}
						
						if (StringUtils.isBlank(desc)) {
							desc = StringUtils.defaultIfBlank(text.toString(), stdField.getDescription());
						}
					}
				}else if (tableColmn.getColumnType() == ColumnType.NON_STD_FIELD) {
					if (StringUtils.isBlank(desc)) {
						desc = tableColmn.getDescription();
					}
				}
			}
				
			String nullAble = datas.get(4);
			
			for (int j = 0; j < datas.size(); j++) {
				if (i == 0) {
					if (j < titleLength) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
					if (j >= titleLength) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
				}else {
					if (j == 0) {
						createCell(row, (short) 1, datas.get(j), getLabelStyle(wb));
					}else if (j == 1){
						createCell(row, (short) 2, fieldName, getTextStyle(wb));
					}else if (j == 2){
						createCell(row, (short) 3, chineseName, getTextStyle(wb));
					}else if (j == 3){
						HSSFCell cell = row.createCell(4);
						Integer index = bizTypeHy.get(type);
						if (index != null) {
							String cv =  "HYPERLINK(\"#'" + BIZ_TYPE + "'!A" + (index + startRowNum + 1) + "\",\"" + type + "\")";
							cell.setCellFormula(cv);
							cell.setCellStyle(getLinkStyle(wb));
						}else {
							cell.setCellValue(type);
							cell.setCellStyle(getTextStyle(wb));
						}
					}else if (j == 4){
						createCell(row, (short) 5, BooleanUtils.toBoolean(nullAble)?"Y":"N", getTextStyle(wb));
					}else if (j == 5){
						createCell(row, (short) 6, desc, getMultTextStyle(wb));
					}else if (j == 6 && defvCol) {
						createCell(row, (short) 7, datas.size()>6?datas.get(j):StringUtils.EMPTY, getTextStyle(wb));
					}else {
						createCell(row, j+1, datas.get(j), getTextStyle(wb));
					}
				}
			}
		}
		
		List< List<String> > tableIndex = exportExcelStringTable(table, DatabasePackage.Literals.TABLE_RESOURCE_DATA__INDEXES,DatabasePackage.Literals.TABLE_INDEX,
				new String[]{"索引", "索引名称","唯一","聚簇","索引字段"}, 
				new EStructuralFeature[]{DatabasePackage.Literals.TABLE_INDEX__MARK,DatabasePackage.Literals.TABLE_INDEX__NAME, DatabasePackage.Literals.TABLE_INDEX__UNIQUE,DatabasePackage.Literals.TABLE_INDEX__CLUSTER,DatabasePackage.Literals.TABLE_INDEX__COLUMNS}, 
				true, 
				new String[]{}, new String[]{}, res, null
		);
		
		
		for (int i = 0; i < tableIndex.size(); i++) {
			List<String> datas = tableIndex.get(i);
			
			row = createRow(sheet);
			
			for (int j = 0; j < datas.size(); j++) {
				if (i == 0) {
					if (j < 6) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
					if (j >= 6) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
				}else {
					if (j == 0) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}else {
						String v = datas.get(j);
						if (StringUtils.equals("true", v)) {
							v = "Y";
						}else if (StringUtils.equals("false", v)) {
							v = "N";
						}
						if (j < 6) {
							createCell(row, j+1, v, getTextStyle(wb));
						}
						if (j >= 6 ) {
							createCell(row, j+1, v, getTextStyle(wb));
						}
					}
				}
			}
		}
		
		List< List<String> > tableKey = exportExcelStringTable(table, DatabasePackage.Literals.TABLE_RESOURCE_DATA__KEYS,DatabasePackage.Literals.TABLE_KEY,
				new String[]{"键约束","名称","类型","字段列表","外键参照表","外键参照列"}, 
				new EStructuralFeature[]{DatabasePackage.Literals.TABLE_KEY__MARK,DatabasePackage.Literals.TABLE_KEY__NAME, DatabasePackage.Literals.TABLE_KEY__TYPE,DatabasePackage.Literals.TABLE_KEY__COLUMNS,DatabasePackage.Literals.FOREIGN_KEY__TABLE_NAME,DatabasePackage.Literals.FOREIGN_KEY__FIELD_NAME}, 
				true, 
				new String[]{}, new String[]{}, res, null
		);
		
		
		for (int i = 0; i < tableKey.size(); i++) {
			List<String> datas = tableKey.get(i);
			
			row = createRow(sheet);
			
			for (int j = 0; j < datas.size(); j++) {
				if (i == 0) {
					if (j < 6) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
					if (j >= 6) {
						createCell(row, j+1, datas.get(j), getLabelStyle(wb));
					}
				}else {
					String v = datas.get(j);
					if(j==0){
						createCell(row, j+1, v, getLabelStyle(wb));
						continue;
					}
					if (j < 6) {
						createCell(row, j+1, v, getTextStyle(wb));
						continue;
					}
					if (j >= 6) {
						createCell(row, j+1, v, getTextStyle(wb));
					}
				}
			}
		}
		
		row = createRow(sheet);
		if (table.getHistories().size() > 0) {
			row.setHeight((short) (300+300*table.getHistories().size()));
		}
		createCell(row, (short) 1, "修改记录", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 6));
		createCell(row, (short) 2, StringUtils.trim(buildReviceHistory(table.getHistories())), getMultTextStyle(wb));
		createCell(row, (short) 3, "", getMultTextStyle(wb));
		createCell(row, (short) 4, "", getMultTextStyle(wb));
		createCell(row, (short) 5, "", getMultTextStyle(wb));
		createCell(row, (short) 6, "", getMultTextStyle(wb));
		// 空两行
		rowNum += 3;
	}

	private static String[] getCurrHis(IARESResource aresResource ,DatabaseResourceData obj){
		String[] strs = new String[]{"",""};
		List<RevisionHistory> hises = obj.getHistories();
		List<RevisionHistory> tempHis = (List<RevisionHistory>) EcoreUtil.copyAll(hises);
		Collections.sort(tempHis, new IRevHistoryVersionCompartor());
		if (hises.size() > 0) {
			strs[0] = tempHis.get(0).getVersion();
			strs[1] = tempHis.get(0).getModifiedDate();
		}else {
			//2013年5月24日14:43:41 如果没有修改记录信息，则取所在子系统当前版本号+1
			IARESModule topModule = null; 
			if (aresResource == null) {
				topModule = null; 
			} else {
				String rootType = aresResource.getRoot().getType(); 
				if (ARESElementUtil.isDatabaseRoot(rootType)) {
					topModule = ARESElementUtil.getTopModule(aresResource);
				} else if (ARESElementUtil.isMetadataRoot(rootType)) {
					// topModule为null的效果就是不计算模块
					topModule = null;
				} else {
					topModule = aresResource.getModule();
				}
			}
			
			// 当前已经保存的资源中的最大版本
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(topModule);
			if (his != null) {
				String currentVersion = his.getVersion();
				// 项目属性
				String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(aresResource.getARESProject());
				
				// 找上述3者最大值
				String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, projectVersion));
				// 第一次找不到任何记录的时候
				if (StringUtils.equals(currentVersion, versionStr)) {
					strs[1] = his.getModifiedDate();
				}
				if (StringUtils.isEmpty(versionStr)) {
					versionStr = "1.0.0.0";
				} 
				strs[0] = versionStr;
			}
		}
		return strs;
	}
	
	private static void createViewSheet(HSSFWorkbook wb, HSSFSheet sheet,
			IARESResource res) throws Exception {
		if (res == null) {
			return;
		}
		ViewResourceData view = res.getInfo(ViewResourceData.class);
		if (view == null) {
			return;
		}
		
		Map<String, Object> helperMap = new HashMap<String, Object>();
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, DatabasePackage.Literals.VIEW_RESOURCE_DATA);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, DatabasePackage.Literals.VIEW_RESOURCE_DATA)) {
				if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
				} else {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
				}
			}
		}
		
		Map<String, String> result = new HashMap<String,String>();
		
		for (Entry<String, Object> entry : helperMap.entrySet()) {
			if (entry.getValue() instanceof IAttributeHelper) {
				result.put(entry.getKey(), ((IAttributeHelper)entry.getValue()).getValue(view));
			}else {
				result.put(entry.getKey(), entry.getValue().toString());
			}
		}
		
		HSSFRow row = sheet.createRow(rowNum);
		createCell(row, 1, "对象号", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 4));
		createCell(row, (short) 2, view.getObjectId(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "版本号", getLabelStyle(wb));
		
		String version = StringUtils.EMPTY;
		{
			version = getCurrHis(res, view)[0];
		}
		
		createCell(row, (short) 6, version, getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "表名", getLabelStyle(wb));
		createCell(row, (short) 2, view.getName(), getTextStyle(wb));
		createCell(row, (short) 3, "表类型", getLabelStyle(wb));
		createCell(row, (short) 4, "V", getTextStyle(wb));
		createCell(row, (short) 5, "所在数据库", getLabelStyle(wb));
		createCell(row, (short) 6, result.get("表空间"), getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "中文名", getLabelStyle(wb));
		createCell(row, (short) 2, view.getChineseName(), getTextStyle(wb));
		createCell(row, (short) 3, "初始化清空表", getLabelStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "表配置串", getLabelStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "是否缓存", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "是否用于查询", getLabelStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "存在历史表", getLabelStyle(wb));
		createCell(row, (short) 6, BooleanUtils.toBoolean(view.isIsHistory())?"Y":"N", getTextStyle(wb));

		int cellIndex = 0;
		for (Entry<String, String> entry : result.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!sequenceExtendsTitle.contains(key)) {
				if (cellIndex % 6 == 0) {
					cellIndex = 0;
					row = createRow(sheet);
				}
				cellIndex++;
				createCell(row, (short) cellIndex, key, getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, value, getTextStyle(wb));
			}
		}
		if (cellIndex % 6 != 0) {
			int lastCell = (6 - cellIndex )/2;
			for (int i = 0; i < lastCell; i++) {
				cellIndex++;
				createCell(row, (short) cellIndex, "", getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, "", getTextStyle(wb));
			}
		}
		
		row = createRow(sheet);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, ++rowNum ,2, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum, 1, 1));
		createCell(row, (short) 1, "说明", getLabelStyle(wb));
		createCell(row, (short) 2, view.getDescription(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		rowNum--;
		row = createRow(sheet);
		createCell(row, (short) 1, "", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		
		row = createRow(sheet);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 2,2, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 2, 1, 1));
		createCell(row, (short) 1, "视图定义", getLabelStyle(wb));
		createCell(row, (short) 2, view.getSql(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));

		row = createRow(sheet);
		if (view.getHistories().size() > 0) {
			row.setHeight((short) (300+300*view.getHistories().size()));
		}
		createCell(row, (short) 1, "修改记录", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 6));
		createCell(row, (short) 2, StringUtils.trim(buildReviceHistory(view.getHistories())), getMultTextStyle(wb));
		createCell(row, (short) 3, "", getMultTextStyle(wb));
		createCell(row, (short) 4, "", getMultTextStyle(wb));
		createCell(row, (short) 5, "", getMultTextStyle(wb));
		createCell(row, (short) 6, "", getMultTextStyle(wb));
		// 空两行
		rowNum += 3;
	}

	/**
	 * 导出序列
	 * 
	 * @param wb
	 * @param sheet
	 * @param res
	 * @throws Exception
	 */
	private static void createSeuence (HSSFWorkbook wb, HSSFSheet sheet,
			IARESResource res) throws Exception{
		if (res == null) {
			return;
		}
		SequenceResourceDataImpl sequence = res.getInfo(SequenceResourceDataImpl.class);
		if (sequence == null) {
			return;
		}
		Map<String, Object> helperMap = new HashMap<String, Object>();
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(res, OraclePackage.Literals.SEQUENCE_RESOURCE_DATA);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(res, OraclePackage.Literals.SEQUENCE_RESOURCE_DATA)) {
				if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
				} else {
					helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
				}
			}
		}
		
		Map<String, String> result = new HashMap<String,String>();
		
		for (Entry<String, Object> entry : helperMap.entrySet()) {
			if (entry.getValue() instanceof IAttributeHelper) {
				result.put(entry.getKey(), ((IAttributeHelper)entry.getValue()).getValue(sequence));
			}else {
				result.put(entry.getKey(), entry.getValue().toString());
			}
		}
		
		HSSFRow row = sheet.createRow(rowNum);
		createCell(row, 1, "对象号", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 4));
		createCell(row, (short) 2, sequence.getObjectId(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "版本号", getLabelStyle(wb));
		
		String version = StringUtils.EMPTY;
		{
			version = getCurrHis(res, sequence)[0];
		}
		
		createCell(row, (short) 6, version, getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "表名", getLabelStyle(wb));
		createCell(row, (short) 2, sequence.getName(), getTextStyle(wb));
		createCell(row, (short) 3, "表类型", getLabelStyle(wb));
		createCell(row, (short) 4, "S", getTextStyle(wb));
		createCell(row, (short) 5, "步长", getLabelStyle(wb));
		createCell(row, (short) 6, sequence.getIncrement(), getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "中文名", getLabelStyle(wb));
		createCell(row, (short) 2, sequence.getChineseName(), getTextStyle(wb));
		createCell(row, (short) 3, "最小值", getLabelStyle(wb));
		createCell(row, (short) 4, sequence.getMinValue(), getTextStyle(wb));
		createCell(row, (short) 5, "最大值", getLabelStyle(wb));
		createCell(row, (short) 6, sequence.getMaxValue(), getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "起始值", getLabelStyle(wb));
		createCell(row, (short) 2, sequence.getStart(), getTextStyle(wb));
		createCell(row, (short) 3, "是否循环", getLabelStyle(wb));
		createCell(row, (short) 4, sequence.isCycle() ? "Y" :"N", getTextStyle(wb));
		createCell(row, (short) 5, "是否缓存", getLabelStyle(wb));
		createCell(row, (short) 6, sequence.isUseCache() ? "Y" :"N", getTextStyle(wb));
		row = createRow(sheet);
		createCell(row, (short) 1, "数据库表", getLabelStyle(wb));
		createCell(row, (short) 2, sequence.getTableName(), getTextStyle(wb));
		createCell(row, (short) 3, "缓存大小", getLabelStyle(wb));
		createCell(row, (short) 4, sequence.getCache(), getTextStyle(wb));
		createCell(row, (short) 5, "存在历史表", getLabelStyle(wb));
		createCell(row, (short) 6, BooleanUtils.toBoolean(sequence.isIsHistory())?"Y":"N", getTextStyle(wb));
		
		int cellIndex = 0;
		for (Entry<String, String> entry : result.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!sequenceExtendsTitle.contains(key)) {
				if (cellIndex % 6 == 0) {
					cellIndex = 0;
					row = createRow(sheet);
				}
				cellIndex++;
				createCell(row, (short) cellIndex, key, getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, value, getTextStyle(wb));
			}
		}
		if (cellIndex % 6 != 0) {
			int lastCell = (6 - cellIndex )/2;
			for (int i = 0; i < lastCell; i++) {
				cellIndex++;
				createCell(row, (short) cellIndex, "", getLabelStyle(wb));
				cellIndex++;
				createCell(row, (short) cellIndex, "", getTextStyle(wb));
			}
		}
		
		row = createRow(sheet);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, ++rowNum ,2, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum, 1, 1));
		createCell(row, (short) 1, "说明", getLabelStyle(wb));
		createCell(row, (short) 2, sequence.getDescription(), getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		rowNum--;
		row = createRow(sheet);
		createCell(row, (short) 1, "", getLabelStyle(wb));
		createCell(row, (short) 2, "", getTextStyle(wb));
		createCell(row, (short) 3, "", getTextStyle(wb));
		createCell(row, (short) 4, "", getTextStyle(wb));
		createCell(row, (short) 5, "", getTextStyle(wb));
		createCell(row, (short) 6, "", getTextStyle(wb));
		
		row = createRow(sheet);
		if (sequence.getHistories().size() > 0) {
			row.setHeight((short) (300+300*sequence.getHistories().size()));
		}
		createCell(row, (short) 1, "修改记录", getLabelStyle(wb));
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum ,2, 6));
		createCell(row, (short) 2, StringUtils.trim(buildReviceHistory(sequence.getHistories())), getMultTextStyle(wb));
		createCell(row, (short) 3, "", getMultTextStyle(wb));
		createCell(row, (short) 4, "", getMultTextStyle(wb));
		createCell(row, (short) 5, "", getMultTextStyle(wb));
		createCell(row, (short) 6, "", getMultTextStyle(wb));
		
		rowNum += 3;
		
	}
	
	
	private static void setDefaultCellStyle(HSSFWorkbook wb, HSSFSheet sheet) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < 15; i++) {
			sheet.setDefaultColumnStyle((short) i, style);
		}
	}

	/**
	 * 获取数据库表的修订记录
	 * 
	 * @param table
	 * @return
	 */
	private static String buildReviceHistory(List<RevisionHistory> histories) {

		List<List<String>> list = new ArrayList<List<String>>();

		{
			if (histories.size() > 0) {
				List<String> content = new ArrayList<String>();
				content.add("修改版本"+"   ");
				content.add("修改日期"+"   ");
				content.add("修改单"+"        ");
				content.add("申请人"+"   ");
				content.add("负责人"+"   ");
				content.add("修改内容"+"        ");
				content.add("修改原因"+"        ");
				content.add("备注"+"        ");
				list.add(content);
			}
		}

		for (RevisionHistory his : histories) {
			String version = his.getVersion();
			List<String> content = new ArrayList<String>();
			content.add("V" + version+"   ");
			String modifyDate = his.getModifiedDate();
			String newDate = StringUtils.substring(modifyDate, 0, 10).replaceAll("-", "");
			content.add(newDate+"   ");
			content.add(his.getOrderNumber()+"        ");
			content.add(his.getModifiedBy()+"   ");
			content.add(his.getCharger()+"   ");
			content.add(his.getModified()+"        ");
			content.add(his.getModifiedReason()+"        ");
			content.add(StringUtils.defaultString(his.getComment())+"        ");

			list.add(content);
		}

		return IScriptStringUtil.instance.genStringTable(list);
	}

	/**
	 * 获取索引字段
	 * 
	 * @param element
	 * @return
	 */
	private static String getIndexColumnText(TableIndex index) {
		EList<TableIndexColumn> columns = index.getColumns();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, length = columns.size(); i < length; i++) {
			buffer.append(columns.get(i).getColumnName());
			if (i < length - 1)
				buffer.append(",");
		}
		return buffer.toString();
	}

	/**
	 * 获取标准字段
	 * 
	 * @param res
	 * @param stdFieldName
	 * @return
	 */
	private static StandardField getStdField(IARESProject project,
			String stdFieldName) {
		if (project != null) {
			ReferenceManager rm = ReferenceManager.getInstance();
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, stdFieldName, false);
			
			if (info != null) {
				Object obj = info.getObject();
				if (obj instanceof StandardField) {
					return (StandardField)obj;
				}
			}
		}
		return null;
	}

	private static HSSFRow createRow(HSSFSheet sheet) {
		rowNum++;
		return sheet.createRow(rowNum);
	}

	private static void createCell(HSSFRow row, int index, String value,
			HSSFCellStyle style) {
		HSSFCell cell = row.createCell(index);
		cell.setCellValue(new HSSFRichTextString(value));
		if (style != null) {
			cell.setCellStyle(style);
		}
	}

	/**
	 * 创建菜单页
	 * 
	 * @param wb
	 * @param dbMap
	 * @return
	 * @throws Exception
	 */
	private static HSSFSheet createMenuSheet(HSSFWorkbook wb,
			Map<String, List<IARESResource>> dbMap) throws Exception {
		HSSFSheet sheet = wb.createSheet("数据表目录");
		HSSFRow row = sheet.createRow((short) 1);

		// 标题栏
		for (int i = 0; i < titile.length; i++) {
			HSSFCell cell = row.createCell(i+1);
			cell.setCellValue(new HSSFRichTextString(titile[i]));
			cell.setCellStyle(getTitleStyle(wb));
		}

		// 添加数据
		int rowNum = 2;
		int startPackage = 2;
		List<String> packageNames = sortPackages(dbMap.keySet());
		for (String packageName : packageNames) {
			List<IARESResource> dbs = dbMap.get(packageName);
			Set<IARESResource> resDupSet = new HashSet<IARESResource>();
			for (int i = 0; dbs != null && i < dbs.size(); i++) {
				IARESResource resource = dbs.get(i);
				if (resDupSet.contains(resource)) {
					continue;
				}
				DatabaseResourceData table = resource
						.getInfo(DatabaseResourceData.class);
				if (table == null) {
					continue;
				}
				resDupSet.add(resource);
				row = sheet.createRow((short) rowNum);
				String obj = table.getObjectId();
				createCell(row, 3, obj, null);
				HSSFCell cell = row.createCell(4);
				String value = posMap.get(getPosKey(resource));
				cell.setCellFormula("HYPERLINK(\"" + value + "\",\""
						+ resource.getName() + "\")");
				cell.setCellStyle(getLinkStyle(wb));
				createCell(row, (short) 5, table.getChineseName(), null);
				createCell(row, (short) 6, resource.getType(), null);
				createCell(row, (short) 7, table.getDescription(), null);
				rowNum++;
			}
			createCell(sheet.getRow(startPackage), (short) 1, packageName, null);
			sheet.addMergedRegion(new CellRangeAddress(startPackage, rowNum - 1, 1, 1));
			createCell(sheet.getRow(startPackage), (short) 2, packageMap.get(packageName), null);
			sheet.addMergedRegion(new CellRangeAddress(startPackage, rowNum - 1, 2, 2));
			startPackage = rowNum;
		}
		sheet.setColumnWidth( 0, 500);
		sheet.setColumnWidth( 1, 6000);
		sheet.setColumnWidth( 2, 8000);
		sheet.setColumnWidth( 3, 6000);
		sheet.setColumnWidth( 4, 6000);
		sheet.setColumnWidth( 5, 8000);
		sheet.setColumnWidth( 6, 3000);
		sheet.setColumnWidth( 7, 10000);
		return sheet;
	}
	
	private static void createModuleSheet(HSSFWorkbook wb,
			Map<String, List<IARESResource>> dbMap) throws Exception{
		List<String> packageNames = sortPackages(dbMap.keySet());
		rowNum = -1;
		HSSFSheet sheet = wb.createSheet(MODULE_SHEET);
		setDefaultCellStyle(wb, sheet);
		HSSFRow row = createRow(sheet);
		for(int i =0 ;i< moduleTitle.length ;i++){
			createCell(row, (short) i, moduleTitle[i], getTitleStyle(wb));
		}
		for (String moduleName : packageNames) {
			IARESModule module = getModule(dbMap, moduleName);
			String packageChineseName = "/数据库/" + getChineseFileName("/" , module);
			String moduleSpace = "";
			String tableType = "一般表";
			String splitField = "";
			String splitNum = "";
			String splitData = "";
			if (module != null) {
				IARESResource moduleRes = module.getARESResource("module.xml");
				try {
					ModuleProperty modulePro = moduleRes.getInfo(ModuleProperty.class);
//					ModuleExtensibleModel mem = (ModuleExtensibleModel) modulePro.getMap().get("ModuleExtensibleModel");
					Object obj = modulePro.getMap().get("ModuleExtensibleModel");
					if (obj != null) {
						if(obj instanceof ModuleExtensibleModel){
							ModuleExtensibleModel mem = (ModuleExtensibleModel)obj;
							OracleTableProperty moduleOP = (OracleTableProperty) mem.getData2().get(IOracleConstant.TABLE_DATA2_KEY);
							if (moduleOP != null) {
								moduleSpace = moduleOP.getSpace();
								table_type type = moduleOP.getTabletype();
								tableType = getTableTypeStr(type);
							}
							TableBaseProperty chouse = (TableBaseProperty) mem.getData2().get("chouse");
							if (chouse != null) {
								splitField = chouse.getSplitField();
								splitNum = chouse.getSplitNum();
								splitData = chouse.getStartData();
							}
						}else{
							throw new Exception(String.format("读取模块%s的扩展信息时发生异常", module.getElementName()));
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			row = createRow(sheet);
			createCell(row, (short) 0, packageChineseName, getTextStyle(wb));
			createCell(row, (short) 1, moduleSpace, getTextStyle(wb));
			createCell(row, (short) 2, tableType, getTextStyle(wb));
			createCell(row, (short) 3, splitField, getTextStyle(wb));
			createCell(row, (short) 4, splitNum, getTextStyle(wb));
			createCell(row, (short) 5, splitData, getTextStyle(wb));
		}
		
		sheet.setColumnWidth(0, 10000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
	}
	
	private static void createColumnTypeAres(HSSFWorkbook wb,Map<String, List<IARESResource>> dbMap) throws ARESModelException{
		IARESProject project = null;
		if (dbMap.values().size() > 0) {
			for(Iterator<List<IARESResource>> it = dbMap.values().iterator() ; it.hasNext();){
				List<IARESResource> resList = it.next();
				if (resList.size() > 0) {
					project = resList.get(0).getARESProject();
					break;
				}
			}
		}
		if (project == null) {
			return;
		}
		int bizTypeColLength = 0;
		int stdTypeColLength = 0;
		List<List<String>> bizTypeList = createBizTypeContent(project);
		List<List<String>> stdTypeList = createStdTypeContent(project);
		//创建业务数据类型
		BIZ_TYPE = createSheetName(wb, BIZ_TYPE);
		STD_TYPE = createSheetName(wb, STD_TYPE);
		HSSFSheet bizTypeSheet = wb.createSheet(createSheetName(wb, BIZ_TYPE));
		setDefaultCellStyle(wb, bizTypeSheet);
		HSSFSheet stdTypeSheet = wb.createSheet(createSheetName(wb, STD_TYPE));
		setDefaultCellStyle(wb, stdTypeSheet);
		
		int hyIndex = -1;
		for (int i = 0; i < bizTypeList.size(); i++) {
			List<String> rowContent = bizTypeList.get(i);
			bizTypeColLength = rowContent.size();
			HSSFRow bizRow = bizTypeSheet.createRow(i + startRowNum);
			HSSFCellStyle cs = getTextStyle(wb);
			if (i == 0) {
				cs = getTitleStyle(wb);
			}
			for (int j = 0; j < rowContent.size(); j++) {
				if (j == hyIndex) {
					HSSFCell cell = bizRow.createCell(j + startCellNum);
					Integer index = stdTypeHy.get(rowContent.get(hyIndex));
					if (index != null) {
						String cv = "HYPERLINK(\"#'" + STD_TYPE + "'!A" + (index + startRowNum + 1) + "\",\"" + rowContent.get(j) + "\")";
						cell.setCellFormula(cv);
						cell.setCellStyle(getLinkStyle(wb));
					}else {
						cell.setCellValue(rowContent.get(j));
						cell.setCellStyle(getTextStyle(wb));
					}
					continue;
				}
				HSSFCell cell = bizRow.createCell(j + startCellNum);
				cell.setCellValue(rowContent.get(j));
				cell.setCellStyle(cs);
			}
			if (i == 0) {
				for (int j = 0; j < rowContent.size(); j++) {
					if (StringUtils.equals(rowContent.get(j), BIZ_TYPE_NAME)) {
						hyIndex = j	;
					}
				}
			}
		}
		
		//创建标准数据类型
		for (int i = 0; i < stdTypeList.size(); i++) {
			List<String> rowContent = stdTypeList.get(i);
			stdTypeColLength = rowContent.size();
			HSSFRow bizRow = stdTypeSheet.createRow(i + startRowNum);
			HSSFCellStyle cs = getTextStyle(wb);
			if (i == 0) {
				cs = getTitleStyle(wb);
			}
			for (int j = 0; j < rowContent.size(); j++) {
				HSSFCell cell = bizRow.createCell(j + startCellNum);
				cell.setCellValue(rowContent.get(j));
				cell.setCellStyle(cs);
			}
		}
		
		for (int i = 0; i < bizTypeColLength; i++) {
			bizTypeSheet.setColumnWidth(i+startCellNum, 5000);
		}
		for (int i = 0; i < stdTypeColLength; i++) {
			stdTypeSheet.setColumnWidth(i+startCellNum, 3000);
		}
	}
	
	private static List<List<String>> createBizTypeContent(IARESProject project) throws ARESModelException{
		bizTypeHy = new HashMap<String, Integer>();
		List<List<String>> bizContents = new ArrayList<List<String>>();
		IARESResource bizTypeRes = project.findResource(IMetadataResType.BizType, IMetadataResType.BizType);
		if (bizTypeRes != null && bizTypeRes.exists()) {
			BusinessDataTypeList bizTypeList = bizTypeRes.getInfo(BusinessDataTypeList.class);
			bizContents = POIUtils.exportExcelStringTable(
					bizTypeList,
					MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,
					MetadataPackage.Literals.BUSINESS_DATA_TYPE,
					new String[] { "类型名", "类型名称", BIZ_TYPE_NAME,
							"长度", "精度", "默认值", "说明" },
							new EStructuralFeature[] {
							MetadataPackage.Literals.NAMED_ELEMENT__NAME,
							MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__STD_TYPE,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__LENGTH,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__PRECISION,
							MetadataPackage.Literals.BUSINESS_DATA_TYPE__DEFAULT_VALUE,
							MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION },
							true, ArrayUtils.EMPTY_STRING_ARRAY,
							ArrayUtils.EMPTY_STRING_ARRAY,
							bizTypeRes, null);
		}
		for (int i = 0; i < bizContents.size(); i++) {
			List<String> content = bizContents.get(i);
			if (content.size() > 0) {
				bizTypeHy.put(content.get(0), i);
			}
		}
		return bizContents;
	}
	
	private static List<List<String>> createStdTypeContent(IARESProject project) throws ARESModelException{
		stdTypeHy = new HashMap<String, Integer>();
		List<List<String>> stdTypeContents = new ArrayList<List<String>>();
		IARESResource bizTypeRes = project.findResource(IMetadataResType.StdType, IMetadataResType.StdType);
		if (bizTypeRes != null && bizTypeRes.exists()) {
			StandardDataTypeList bizTypeList = bizTypeRes.getInfo(StandardDataTypeList.class);
			Language[] languages = LanguageRegister.getInstance().getRegisteredLanguages();
			String[] languageIds = new String[languages.length];
			String[] languageTitles = new String[languages.length];
			for (int i = 0; i < languageIds.length; i++) {
				languageIds[i] = languages[i].getId();
				languageTitles[i] = languages[i].getName();
			}
			stdTypeContents = POIUtils
			.exportExcelStringTable(
					bizTypeList,
					MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS,
					MetadataPackage.Literals.STANDARD_DATA_TYPE,
					new String[] { "类型名", "名称", "说明" },
					new EStructuralFeature[] {
							MetadataPackage.Literals.NAMED_ELEMENT__NAME,
							MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
							MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION },
					true, languageTitles, languageIds,
					bizTypeRes, new IHeaderSorter() {

						@Override
						public void sort(List<String> header) {
							int index = header.indexOf("说明");
							if (index >= 0) {
								header.remove(index);
								header.add("说明");
							}
						}
					});
		}
		for (int i = 0; i < stdTypeContents.size(); i++) {
			List<String> content = stdTypeContents.get(i);
			if (content.size() > 0) {
				stdTypeHy.put(content.get(0), i);
			}
		}
		return stdTypeContents;
	}
	
	public static String getTableTypeStr(table_type type){
		if(type.getValue() == table_type.COMMON_VALUE) {
			return "一般表";
		}else if(type.getValue() == table_type.TEMP_NO_VALUE_VALUE){
			return "临时表(不保留数据)";
		}else if(type.getValue() == table_type.TEMP_WITH_VALUE_VALUE) {
			return "临时表(保留数据)";
		}
		return StringUtils.EMPTY;
	}
	
	public static table_type getTableTypeEnum(String type){
		if(StringUtils.equals(type.trim(), "一般表")) {
			return table_type.COMMON;
		}else if(StringUtils.equals(type.trim(), "临时表(不保留数据)")){
			return table_type.TEMP_NO_VALUE;
		}else if(StringUtils.equals(type.trim(), "临时表(保留数据)")) {
			return table_type.TEMP_WITH_VALUE;
		}
		return null;
	}
	
	/**
	 * 对set进行排序
	 * 
	 * @param strs
	 * @return
	 */
	private static List<String> sortPackages(Set<String> strs) {
		List<String> colls = new ArrayList<String>();
		if (strs != null) {
			colls.addAll(strs);
			Collections.sort(colls);
		}
		return colls;
	}

	/**
	 * 超链接的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getLinkStyle(HSSFWorkbook wb) {
		if (style == null) {
			style = wb.createCellStyle();
			if (linkFont == null) {
				linkFont = wb.createFont();
				linkFont.setColor(HSSFColor.BLUE.index);
			}
			style.setFont(linkFont);
		}
		return style;
	}

	/**
	 * 文本框的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getTextStyle(HSSFWorkbook wb) {
		if (textStyle == null) {
			textStyle = wb.createCellStyle();
			textStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			textStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			textStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			textStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		}
		return textStyle;
	}
	
	/**
	 * 文本框的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getMultTextStyle(HSSFWorkbook wb) {
		if (multTextStyle == null) {
			multTextStyle = wb.createCellStyle();
			multTextStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			multTextStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			multTextStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			multTextStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			multTextStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			multTextStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			multTextStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			multTextStyle.setWrapText(true);
		}
		return multTextStyle;
	}

	/**
	 * 文本框前label的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getLabelStyle(HSSFWorkbook wb) {
		if (labelStyle == null) {
			labelStyle = wb.createCellStyle();
			labelStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			labelStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			labelStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			labelStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			labelStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			labelStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			labelStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		}
		return labelStyle;
	}

	/**
	 * 标题样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
		if (titleStyle == null) {
			titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			if (titleFont == null) {
				titleFont = wb.createFont();
				titleFont.setFontName("宋体");
				titleFont.setFontHeightInPoints((short) 10);
				titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			}
			titleStyle.setFont(titleFont);
		}
		return titleStyle;
	}

	/**
	 * 根据EMF模型定义,读取模型信息
	 * 
	 * @param table
	 * @return
	 */
	private static String getExtendsData(DatabaseResourceData table , EClass eclass , EStructuralFeature feature) {
		try {
			for (Iterator<String> it = table.getData2().keySet().iterator(); it
					.hasNext();) {
				String key = (String) it.next();
				
				EObject obj = table.getData2().get(key);
				if (eclass.isInstance(obj) && obj.eGet(feature) != null) {
					return obj.eGet(feature).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 保存导出向导中的了路径
	 * @param file
	 */
	public static void saveParamter (String file){
		org.eclipse.core.runtime.Preferences preferences = DatabaseUI.getDefault().getPluginPreferences();
		preferences.setValue(EXPORT_TARGET_DB_DIR , file);
	}
	
	/**
	 * 读取导出向导中的了路径
	 * 
	 * @return
	 */
	public static String getParamter (){
		org.eclipse.core.runtime.Preferences preferences = DatabaseUI.getDefault().getPluginPreferences();
		return preferences.getString(EXPORT_TARGET_DB_DIR);
	}

	public static List< List<String> > exportExcelStringTable(EObject owner, EReference reference, EClass itemClass, String[] titles, EStructuralFeature[] features,
			boolean includeExtend, String[] titles2, String[] dataKeys, IARESElement element, IHeaderSorter sorter) {
		List<List<String>> result = new ArrayList<List<String>>();

		// 首先构建标题列
		List<String> header = new ArrayList<String>();
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		
		
		header.addAll(Arrays.asList(titles));
		for (int i = 0; i < titles.length; i++) {
			helperMap.put(titles[i], new NormalAttributeHelper(features[i]){
				@Override
				public String getValue(EObject model) {
					if (feature == DatabasePackage.Literals.TABLE_INDEX__COLUMNS) {
						StringBuffer sb = new StringBuffer();
						EList<TableIndexColumn> indexColumns = (EList<TableIndexColumn>) model.eGet(feature);
						for (TableIndexColumn tc : indexColumns) {
							String name = tc.getColumnName();
							if (StringUtils.isNotBlank(name)) {
								if (StringUtils.isNotBlank(sb.toString())) {
									sb.append(",");
								}
								sb.append(name);
							}
						}
						return sb.toString();
					}else if(feature == DatabasePackage.Literals.TABLE_KEY__COLUMNS){
						StringBuffer sb = new StringBuffer();
						EList<TableColumn> columns = (EList<TableColumn>) model.eGet(feature);
						for (TableColumn col : columns) {
							String name = col.getName();
							if (StringUtils.isNotBlank(name)) {
								if (StringUtils.isNotBlank(sb.toString())) {
									sb.append(",");
								}
								sb.append(name);
							}
						}
						return sb.toString();
					}else if(feature == DatabasePackage.Literals.FOREIGN_KEY__TABLE_NAME){
						EList<ForeignKey> fks = (EList<ForeignKey>) model.eGet(DatabasePackage.Literals.TABLE_KEY__FOREIGN_KEY);
						for (ForeignKey fk : fks) {
							return fk.getTableName();
						}
						return "";
					}else if(feature == DatabasePackage.Literals.FOREIGN_KEY__FIELD_NAME){
						StringBuffer sb = new StringBuffer();
						EList<ForeignKey> fks = (EList<ForeignKey>) model.eGet(DatabasePackage.Literals.TABLE_KEY__FOREIGN_KEY);
						for (ForeignKey fk : fks) {
							String name = fk.getFieldName();
							if (StringUtils.isNotBlank(name)) {
								if (StringUtils.isNotBlank(sb.toString())) {
									sb.append(",");
								}
								sb.append(name);
							}
						}
						return sb.toString();
					}
					return super.getValue(model);
				}
			});
		}
		
		if (includeExtend) {
			
			for (int i = 0; i < titles2.length; i++) {
				header.add(titles2[i]);
				helperMap.put(titles2[i], new ExtensibleDataAttributeHelper(dataKeys[i]));
			}
			
			IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(element, itemClass);
			for (IExtensibleModelEditingSupport support : supports) {
				for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(element, itemClass)) {
					if (!desc.isDerived()) {
						header.add(desc.getDisplayName());
						if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
							helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
						} else {
							helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
						}
					}
					
				}
			}
		}
		
		if (sorter != null) {
			sorter.sort(header);
		}
		
		// 根据实际标题名，排序属性助手，有可能存在空列
		List<IAttributeHelper> helperList = new ArrayList<IAttributeHelper>();
		for (String title : header) {
			helperList.add(helperMap.get(title));
		}
		
		result.add(header);
		
		
		
		// 其次构建内容
		List<EObject> contentObjectList = (List<EObject>) owner.eGet(reference);
		for (EObject eObject : contentObjectList) {
			List<String> content = new ArrayList<String>();
			for (IAttributeHelper helper : helperList) {
				content.add(helper.getValue(eObject));
			}
			
			result.add(content);
		}
		
		return result;
	}
	
	/**
	 * 获取数据库表类型(枚举)
	 * <p>和客户约定</p>
	 * <p>U : 一般表</p>
	 * <p>T : 临时表(不保留)</p>
	 * <p>M : 临时表(保留)</p>
	 * @param key
	 * @return
	 */
	public static String getTableType (String key){
		String value = tableType.get(key);
		return value == null ? StringUtils.EMPTY : value; 
	}
	
}
