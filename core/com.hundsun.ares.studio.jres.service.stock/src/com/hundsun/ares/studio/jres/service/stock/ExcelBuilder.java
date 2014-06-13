/**
 * 源程序名称：ExcelBuilder.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.stock;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.KeyValueBlock;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.biz.excel.export.TextBlock;
import com.hundsun.ares.studio.biz.excel.export.writer.ExcelWriter;
import com.hundsun.ares.studio.biz.excel.export.writer.GroupWriter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.excel.AbstractSheetHandler;
import com.hundsun.ares.studio.core.excel.BlockTypes;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.POIUtils;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServicePackage;

/**
 * @author sundl
 *
 */
public class ExcelBuilder extends AbstractBuilder {
	
	public ExcelBuilder(IARESProject project) {
		super(project);
	}

	
	/** 互联网模块的接口属性 */
	static String[] BASIC_PRO_INTERNET = new String[] {
		"功能号", "老功能号", "更新日期", 
		"功能名称", "版本号", "结果集返回", 
		"英文名", "投资者范围", 
		"格式", "HTTP请求方式", "访问授权限制", 
		"业务范围", "产品范围", "功能状态", 
		"功能描述"
	};
	
	/** 普通模块下的接口基本信息属性 */
	static String[] BASIC_PRO_NORMAL = new String[] {
		"功能号", "老功能号", "更新日期", 
		"功能名称", "版本号", "结果集返回", 
		"英文名", "投资者范围", 
		/*"格式", "HTTP请求方式", "访问授权限制", */ // 只有互联网模块下有这些属性
		"业务范围", "产品范围", "功能状态", 
		"功能描述"
	};
	
	static String[] OBJ_BASIC_INFO_PROPERTIES = new String[] {
		"对象名", "更新日期",
		"对象中文名", "对象状态"
	};
	
	// 服务接口基本信息的属性占位信息， 数字代表占用单元格的个数
	static int[] BASIC_INFO_SPANS_INTERNET = new int[] {
		1, 1, 1,
		1, 1, 1,
		3, 1,
		1, 1, 1,
		1, 1, 1,
		5
	};
	static int[] BASIC_INFO_SPANS_NORMAL = new int[] {
		1, 1, 1,
		1, 1, 1,
		3, 1,
		/* 1, 1, 1, */
		1, 1, 1,
		5
	};
	
	static int[] OBJ_BASIC_INFO_SPANS = new int[] {
		2, 1,
		2, 1
	};
	
	// 输入输出参数表头
	static String[] INPUT_PARAM_PROPERTIES = new String[] {"输入参数", "参数名", "类型", "说明", "必须", "缺省值"};
	static String[] OUTPUT_PARAM_PROPERTIES = new String[] {"输出参数", "参数名", "类型", "说明", "必须", "缺省值"};
	static String[] ERROR_INFO_PROPERTIES = new String[] {"出错说明", "错误号", "错误信息", "错误说明", "错误级别", ""};
	// 对象属性表头
	static String[] OBJ_ATTR_PROPERTIES = new String[] {"对象属性", "参数名", "类型", "说明", };

	
	static String[] SHEET_NAMES = new String[] {
		"封面", "版本页", "约定释义", "标准字段", "数据字典", "对象列表", "对象-全部",
		"功能列表", "服务接口-全部", "消息主推类别", "主推接口列表", "主推接口"
	};
	
	Multimap<IARESModule, IARESResource> services;
	
	String fileName;
	// 子系统，对应封面第二行
	String subsysName;
	IARESModule module;
	// 模块名，对应封面第三行，例如（周边接口规范（期货－期权）V1.4）
	String moduleName;
	// 模板文件的路径
	String templatePath;
	
	// 暂存标准字段和数据字典信息，避免反复查找
	private Map<String, StandardField> stdFields = new HashMap<String, StandardField>();
	private Map<String, DictionaryType> dictTypes = new HashMap<String, DictionaryType>();
	
	// 引用到的数据字典项
	private Set<String> uesedDicts = new HashSet<String>();
	private Set<String> usedStdFields = new HashSet<String>();
	
	List<String> bizScopes;
	
	private Workbook workbook;
	
	public void build() {
		// 先处理只有一种的情况，如有需要后续可以支持多模块方式
		if (services.keySet().size() == 1) {
			module = (IARESModule) services.keySet().toArray()[0];
			// 初始化资源数据：元数据(标准字段、数据字典)、 服务资源列表、对象列表
			initMetadata();
			
			List<Service> servicesList = new ArrayList<Service>();
			for (IARESResource res : services.get(module)) {
				Service service = null;
				try {
					service = res.getInfo(Service.class);
				} catch (ARESModelException e) {
					logger.error(e);
				}
				if (service == null)
					continue;
				servicesList.add(service);
				// 收集这个服务用到的标准字段和数据字典信息
				collectStdFiledAndDicts(service);
			}
			
			Collections.sort(servicesList, new Comparator<Service>() {
				@Override
				public int compare(Service o1, Service o2) {
					return StringUtils.defaultString(o1.getObjectId()).compareTo(StringUtils.defaultString(o2.getObjectId()));
				}
			});
			
			IARESResource[] objResources = module.getARESResources(IBizResType.Object);
			List<ARESObject> objects = new ArrayList<ARESObject>();
			for (IARESResource res : objResources) {
				try {
					ARESObject object = res.getInfo(ARESObject.class);
					objects.add(object);
				} catch (ARESModelException e) {
					logger.error(e);
				}
			}
			
			// 根据模块名计算文件名
			subsysName = ARESElementUtil.getModuleCName(ARESElementUtil.getTopModule(module));
			String moduleFullName = ResourcesUtil.getChineseFileName("_", module);
			moduleName = ARESElementUtil.getModuleCName(module) + "接口规范(" + StringUtils.join(bizScopes, ',') + ")";
			fileName = "d:\\" + moduleFullName + "(" +StringUtils.join(bizScopes, ',') + ").xls";
			
			// 封面页、版本页带有部分模板性质，解析和替换其中的内容
			// 模板中的其他页面作为固定内容页面
			try {
				parseTemplate();
			} catch (IOException e) {
				logger.error(e);
			}
			
			createStdFieldGroup();
			createDictGroup();
			createObjectGroup(objects);
			createServiceGroup(servicesList);
			// 为了创建超链接，列表必须在上面的Service生成之后再生成
			createServiceListGroup(servicesList);
			createObjListGroup(objects);
		}
	}
	
	private void initMetadata() {
		IARESResource[] resource;
		try {
			resource = project.findResource(IMetadataResType.StdField);
			if (resource.length > 0) {
				IARESResource stdResource = resource[0];
				StandardFieldList stdList = stdResource.getInfo(StandardFieldList.class);
				for (StandardField f : stdList.getItems()) {
					stdFields.put(f.getName(), EcoreUtil.copy(f));
				}
			}
			
			resource = project.findResource(IMetadataResType.Dict);
			if (resource.length > 0) {
				IARESResource dictResource = resource[0];
				DictionaryList dictionaryList = dictResource.getInfo(DictionaryList.class);
				for (DictionaryType dict : dictionaryList.getItems()) {
					dictTypes.put(dict.getName(), EcoreUtil.copy(dict));
				}
			}
		} catch (ARESModelException e) {
			logger.error(e);
		}

	}
	
	// 扫描这个服务接口用到 的标准字段和对应的数据字典
	private void collectStdFiledAndDicts(Service service) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.addAll(service.getInterface().getInputParameters());
		parameters.addAll(service.getInterface().getOutputParameters());
		for (Parameter p : parameters) {
			StandardField field = this.stdFields.get(p.getId());
			if (field != null) {
				usedStdFields.add(p.getId());
				String dict = field.getDictionaryType();
				if (StringUtils.isNotEmpty(dict))
					uesedDicts.add(dict);
			}
		}
	}
	
	// 带有部分模板性质的页面
	private void parseTemplate() throws IOException {
		InputStream inputStream = null;
		if (StringUtils.isNotEmpty(templatePath)) {
			inputStream = new FileInputStream(templatePath);
		} else {
			URL url = ServiceStockExtPlugin.getDefault().getBundle().getEntry("tmpl.xls");
			inputStream = url.openStream();
		}
		try {
			workbook = new HSSFWorkbook(inputStream);
			setUpCover(workbook);
			setUpHisSheet(workbook);
			filterPushInterfaces(workbook);
			
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}
	
	/**
	 * 设置封面页的信息
	 * @param workbook
	 */
	private void setUpCover(Workbook workbook) {
		Sheet sheet = workbook.getSheet("封面");
		if (sheet != null) {
			Row row = sheet.getRow(8);
			Cell cell = row.getCell(0);
			cell.setCellValue(subsysName);
			
			row = sheet.getRow(9);
			cell  = row.getCell(0);
			cell.setCellValue(moduleName);
			
			row = sheet.getRow(24);
			cell = row.getCell(0);
			cell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		}
	}
	
	/**
	 * 解析和设置版本页信息，模板
	 * @param workbook
	 */
	private void setUpHisSheet(Workbook workbook) {
		Sheet sheet = workbook.getSheet("版本页");
		if (sheet != null) {
			SheetParser parser = new SheetParser();
			parser.areaTags.add("修改版本");
			parser.blocks.put("修改版本", BlockTypes.TABLE);
			Log log = new Log();
			
			final List<String> properties = new ArrayList<String>();
			final int[] startRow = new int[1];
			ISheetHandler handler = new AbstractSheetHandler() {
				@Override
				public void header(String[] header) {
					for (String h : header) {
						properties.add(h);
						startRow[0] = this.parser.getCurrentRow();
					}
				}
			};
			handler.init(parser, log);
			parser.evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			parser.handlers.add(handler);
			parser.parse(sheet);
			
			List<RevisionHistory> histories = RevisionHistoryUtil.getHistories(module);
			TableBlock blcok = buildTableBlock(properties.toArray(new String[0]), null, histories, getPropertyHandlerFactory(CorePackage.Literals.REVISION_HISTORY));
			Group group = new Group();
			group.name = "版本页";
			
			Area area = new Area();
			area.blocks.add(blcok);
			group.areas.add(area);
			groups.add(group);
		}
	}
	
	// 处理主推接口，暂时不用
	private void filterPushInterfaces(Workbook workbook) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		List<Row> rowsToDelete = new ArrayList<Row>();

		Sheet sheet = workbook.getSheet("主推接口列表");
		if (sheet != null) {
			int rowCount = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(4);
				String bizScope = POIUtils.getCellStringValue(cell, evaluator);
				if (!bizScopes.contains(bizScope)) {
					rowsToDelete.add(row);
				}
			}
			int deletedRows = 0;
			for (Row row : rowsToDelete) {
				sheet.shiftRows(row.getRowNum() -deletedRows + 2, rowCount - 1, -1);
				deletedRows++;
			}
		}
		
	}
	
	
	private void createStdFieldGroup() {
		List<StandardField> standardFields = new ArrayList<StandardField>();
		for (String id : usedStdFields) {
			StandardField f = stdFields.get(id);
			standardFields.add(f);
		}
		
		Collections.sort(standardFields, new Comparator<StandardField>() {
			@Override
			public int compare(StandardField o1, StandardField o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		
		int i = 1;
		for (StandardField f : standardFields) {
			f.getData().put("id", String.valueOf(i++));
		}
		
		StdFieldBlockBuilder builder = new StdFieldBlockBuilder(project, standardFields);
		builder.build();
		groups.addAll(builder.getGroups());
	}
	
	private void createServiceListGroup(List<Service> serviceList) {
		ServiceListGroupBuilder builder = new ServiceListGroupBuilder(project, serviceList);
		builder.build();
		groups.addAll(builder.getGroups());
	}
	
	private void createDictGroup() {
		List<DictionaryType> dictList = new ArrayList<DictionaryType>();
		int i = 1;
		for (String id : uesedDicts) {
			DictionaryType type = dictTypes.get(id);
			if (type != null) {
				type.getData().put("id", String.valueOf(i++));
				dictList.add(type);
			}
		}
		DictGroupBuilder builder = new DictGroupBuilder(project, dictList);
		builder.build();
		groups.addAll(builder.getGroups());
	}
	
	private void createObjListGroup(List<ARESObject> objects) {
		if (objects == null || objects.isEmpty())
			return;
		ObjectListBuilder builder = new ObjectListBuilder(project, objects);
		builder.build();
		groups.addAll(builder.getGroups());
	}
	
	private void createObjectGroup(List<ARESObject> objects) {
		if (objects == null || objects.isEmpty())
			return;
		
		IPropertyHandlerFactory objectPropertyHandlerFactory = getPropertyHandlerFactory(BizPackage.Literals.ARES_OBJECT);
		IPropertyHandlerFactory paramPropertyHandlerFactory = getPropertyHandlerFactory(BizPackage.Literals.PARAMETER);
		Group group = new Group();
		groups.add(group);
		group.name = "对象-全部";
		for (ARESObject object : objects) {
			Area area = new Area();
			group.areas.add(area);
			KeyValueBlock basicInfoBlock = buildKeyValueBlock(object, OBJ_BASIC_INFO_PROPERTIES, BASIC_INFO_SPANS_INTERNET, objectPropertyHandlerFactory);
			basicInfoBlock.propertyPerLine = 2;
			area.blocks.add(basicInfoBlock);
			TableBlock inputParamBlock = buildTableBlock(OBJ_ATTR_PROPERTIES, TABLE_COL_STYLES, object.getProperties(), paramPropertyHandlerFactory);
			area.blocks.add(inputParamBlock);
			TextBlock hisTextBlock = buildTextBlock("修改记录", 3, object, objectPropertyHandlerFactory, true);
			area.blocks.add(hisTextBlock);
		}
	}
	
	private void createServiceGroup(List<Service> services) {
		IPropertyHandlerFactory servicePropertyHandlerFactory = getPropertyHandlerFactory(ServicePackage.Literals.SERVICE);
		IPropertyHandlerFactory paramPropertyHandlerFactory = getPropertyHandlerFactory(BizPackage.Literals.PARAMETER);
		IPropertyHandlerFactory errorPropertyHandlerFactory = getPropertyHandlerFactory(BizPackage.Literals.ERROR_INFO);
		Group group = new Group();
		groups.add(group);
		group.name = "服务接口-全部";
		String[] properties = null;
		int[] spans = null;
		if (StringUtils.startsWith(subsysName, "互联网")) {
			properties = BASIC_PRO_INTERNET;
			spans = BASIC_INFO_SPANS_INTERNET;
		} else {
			properties = BASIC_PRO_NORMAL;
			spans = BASIC_INFO_SPANS_NORMAL;
		}
		for (Service service : services) {
			Area area = new Area();
			group.areas.add(area);
			KeyValueBlock basicInfoBlock = buildKeyValueBlock(service, properties, spans, servicePropertyHandlerFactory);
			area.blocks.add(basicInfoBlock);
			TableBlock inputParamBlock = buildTableBlock(INPUT_PARAM_PROPERTIES, TABLE_COL_STYLES, service.getInterface().getInputParameters(), paramPropertyHandlerFactory);
			area.blocks.add(inputParamBlock);
			TableBlock outputParamBlock = buildTableBlock(OUTPUT_PARAM_PROPERTIES, TABLE_COL_STYLES, service.getInterface().getOutputParameters(), paramPropertyHandlerFactory);
			area.blocks.add(outputParamBlock);
			TextBlock bizDescription = buildTextBlock("业务说明", service,  servicePropertyHandlerFactory, false);
			area.blocks.add(bizDescription);
			TableBlock errorInfoBlock = buildTableBlock(ERROR_INFO_PROPERTIES, TABLE_COL_STYLES, service.getInterface().getErrorInfos(), errorPropertyHandlerFactory);
			area.blocks.add(errorInfoBlock);
			TextBlock hisTextBlock = buildTextBlock("修改记录", service, servicePropertyHandlerFactory, true);
			area.blocks.add(hisTextBlock);
		}
	}
	
	public void writeFile() throws IOException {
		ExcelWriter writer = new ExcelWriter(workbook, groups) {
			@Override
			protected GroupWriter createGroupWriter(Group group) {
				GroupWriter writer = super.createGroupWriter(group);
				if (group.name.equals("版本页")) {
					writer.newSheet = false;
					writer.startRow = 11;
				}
				return writer;
			}
		};
		writer.write();
		
		setSheetOrders(SHEET_NAMES);
		int length = workbook.getNumberOfSheets();
		for (int i = 0; i < length; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			sheet.protectSheet("hundsun123");
		}

		FileOutputStream output = null;
		try {
			output = new FileOutputStream(fileName);
			workbook.write(output);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}
	
	private void setSheetOrders(String[] sheets) {
		int i = 0;
		for (String sheet : sheets) {
			if (workbook.getSheet(sheet) != null) {
				workbook.setSheetOrder(sheet, i++);
			}
		}
	}
	
}
