/**
 * 
 */
package com.hundsun.ares.studio.biz.ui.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.Language;
import com.hundsun.ares.studio.jres.metadata.ui.LanguageRegister;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IHeaderSorter;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

/**
 * @author yanwj06282
 * 
 */
public class ExcelStructCreateor {

	private static String BIZ_TYPE = "业务数据类型";
	private static String STD_TYPE = "标准数据类型";
	private final static String BIZ_TYPE_NAME = "标准类型";
	private final static String INPUT_RESULTSET = "输入结果集";
	private final static String OUTPUT_RESULTSET = "输出结果集";
	/**
	 * 菜单链接缓存
	 */
	private static Map<String, String> hyprelinkRefMap = new HashMap<String, String>();

	public static final String HYPERLINK = "HYPERLINK(\"#'%1$s'!A%2$s\",";
	
	private static Map<String, Integer> bizTypeHy = new HashMap<String, Integer>();
	private static Map<String, Integer> stdTypeHy = new HashMap<String, Integer>();
	
	/**
	 * 处理生成文件格式
	 * 
	 * @param project
	 * @param serviceMap
	 * @param objMap
	 * @param file
	 */
	public static void createExcelStruts(IARESProject project,
			ExportExcelEntity entity, String file) {
		OutputStream output = null;
		init();
		MessageDialog msgdialog = null;
		try {
			output = new FileOutputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook();
			
			/**
			 * 
			 * 先创建数据类型，包括业务数据类型，标准数据类型
			 */
			createColumnTypeAres(wb, project);
			
			// 创建明细页
			createDetailSheet(wb, entity, 0, 1);
			// 创建菜单页
			createMenuSheet(wb, entity, 1, 1);

			wb.setSheetOrder(BIZ_TYPE, wb.getNumberOfSheets()-1);
			wb.setSheetOrder(STD_TYPE, wb.getNumberOfSheets()-1);
			
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("导出文件不存在，请检查导出路径！" ,e);
		} catch (IOException e) {
			throw new RuntimeException("导出文件已被打开或无写入权限，请重新命名或关闭后再导出！" ,e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if (output != null) {
				IOUtils.closeQuietly(output);
			}
		}
	}

	private static void createColumnTypeAres(HSSFWorkbook wb,IARESProject project) throws ARESModelException{
		int bizTypeColLength = 0;
		int stdTypeColLength = 0;
		int startRowNum = 1;
		int startCellNum = 1;
		List<List<String>> bizTypeList = createBizTypeContent(project);
		List<List<String>> stdTypeList = createStdTypeContent(project);
		//创建业务数据类型
		BIZ_TYPE = createSheetName(wb, BIZ_TYPE);
		STD_TYPE = createSheetName(wb, STD_TYPE);
		HSSFSheet bizTypeSheet = wb.createSheet(BIZ_TYPE);
		setDefaultCellStyle(wb, bizTypeSheet);
		HSSFSheet stdTypeSheet = wb.createSheet(STD_TYPE);
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
	
	/**
	 * 创建对象明细页
	 * 
	 * @param wb
	 * @param objMap
	 * @param startRow
	 * @param startCell
	 */
	private static void createDetailSheet(HSSFWorkbook wb,
			ExportExcelEntity entity, int startRow, int startCell) {
		for (ExcelSheetStructEntity sheetEntity : entity.getSheetList()) {
			HSSFSheet sheet = null;
			String sheetName = sheetEntity.getCnamePrefix() + sheetEntity.getSheetCName();
			try {
				sheetName = createSheetName(wb, sheetName);
				sheet = wb.createSheet(sheetName);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if (sheetEntity.getSheetIndex() > -1) {
				wb.setSheetOrder(sheetName, sheetEntity.getSheetIndex());
			}
			setDefaultCellStyle(wb, sheet);
			
			int[] columnWidth = sheetEntity.getColumnWidths();
			int width = sheetEntity.getDefaultColumnWidth();
			if (sheetEntity.getEntityList().size() == 0) {
				continue;
			}
			int columns = sheetEntity.getEntityList().get(0).getBasicParmasMAXCellLength()*2;
			for (int i = 0; i < columns; i++) {
				try {
					width = columnWidth[i];
				} catch (Exception e) {
				}
				sheet.setColumnWidth(startCell + i, width);
			}
			for (int i = 0; i < startCell; i++) {
				sheet.setColumnWidth(i, 500);
			}
			// 遍历资源
			// 临时row的下标
			int rowIndex = startRow;
			// 临时cell的下标
			int cellIndex = startCell;
			//创建模块信息
			HSSFRow row = sheet.createRow(rowIndex);
			{
				//模块名
				createCell(row, cellIndex, "模块名", getLabelStyle(wb));
				cellIndex++;
				createCell(row, cellIndex, sheetEntity.getSheetEName(), getTextStyle(wb));
				cellIndex++;
				//模块中文名
				createCell(row, cellIndex, "模块中文名", getLabelStyle(wb));
				cellIndex++;
				createCell(row, cellIndex, sheetEntity.getSheetCName(), getTextStyle(wb));
				rowIndex += 2;
				cellIndex = startCell;
			}
			//开始创建资源信息
			for (BizResExcelStructEntity resStruct : sheetEntity
					.getEntityList()) {
				hyprelinkRefMap.put(
						resStruct.getHyperlinkKey(),
						getRefExpression(sheetName, rowIndex));
				int maxLength = resStruct.getBasicParmasMAXCellLength();
				int maxWidth = 1;
				row = sheet.createRow(rowIndex);
				List<Integer> widthList = new ArrayList<Integer>();
				//基础属性(包含扩展属性)
				ExcelBasicParamStuctEntity inputKey = null;
				ExcelBasicParamStuctEntity inputValue = null;
				ExcelBasicParamStuctEntity outputKey = null;
				ExcelBasicParamStuctEntity outputValue = null;
				for (Iterator<ExcelBasicParamStuctEntity> iterator = resStruct
						.getBasicParams().keySet().iterator(); iterator
						.hasNext();) {
					ExcelBasicParamStuctEntity key = iterator.next();
					ExcelBasicParamStuctEntity value = resStruct.getBasicParams().get(key);
					if (StringUtils.equals(key.getValue(), INPUT_RESULTSET) ) {
						inputKey = key;
						inputValue = value;
						continue;
					}
					if (StringUtils.equals(key.getValue(), OUTPUT_RESULTSET)) {
						outputKey = key;
						outputValue = value;
						continue;
					}
					//如果单元格超过1个单位,则需要合并
					if (cellIndex >= startCell + maxLength*2) {
						mergeRowAndCell(wb, sheet, rowIndex, cellIndex,
								startCell, maxWidth, widthList);
						rowIndex++;
						row = sheet.createRow(rowIndex);
						cellIndex = startCell;
						maxWidth = 1;
						widthList = new ArrayList<Integer>();
					}
					
					// 订好长度
					cellIndex = createCell(row, cellIndex, key,
							getLabelStyle(wb));
					widthList.add(cellIndex);
					cellIndex++;
					cellIndex = createCell(row, cellIndex, value,
							getTextStyle(wb));
					widthList.add(cellIndex);
					cellIndex++;
					// 订高度
					maxWidth = Math.max(maxWidth, key.getWidth());
					maxWidth = Math.max(maxWidth, value.getWidth());
				}
				mergeRowAndCell(wb, sheet, rowIndex, cellIndex, startCell,
						maxWidth, widthList);
				rowIndex += maxWidth -1;
				cellIndex = startCell;
				maxWidth = 1;
				widthList = new ArrayList<Integer>();
				
				int status = 1;
				for (Iterator<String> iterator = resStruct.getParameterMaps()
						.keySet().iterator(); iterator.hasNext();) {
					String key = iterator.next();
					ParameterStructEntity value = resStruct.getParameterMaps()
							.get(key);
					if (status == 1) {
						// 输入结果集，特殊处理
						if (inputKey != null) {
							rowIndex++;
							row = sheet.createRow(rowIndex);
							cellIndex = startCell;
							createCell(row, cellIndex, inputKey,getLabelStyle(wb));
							cellIndex++;
							createCell(row, cellIndex, inputValue,getTextStyle(wb));
							widthList.add(startCell + maxLength*2 - cellIndex);
							mergeRowAndCell(wb, sheet, rowIndex, cellIndex, cellIndex,
									maxWidth, widthList);
						}
					}
					if (status == 2) {
						// 输入结果集，特殊处理
						if (outputKey != null) {
							rowIndex++;
							row = sheet.createRow(rowIndex);
							cellIndex = startCell;
							createCell(row, cellIndex, outputKey,getLabelStyle(wb));
							cellIndex++;
							createCell(row, cellIndex, outputValue,getTextStyle(wb));
							mergeRowAndCell(wb, sheet, rowIndex, cellIndex, cellIndex,
									maxWidth, widthList);
						}
						widthList = new ArrayList<Integer>();
					}
					status ++;
					List<Integer> filters = new ArrayList<Integer>();
					int typeIndex = -1;
					for (int i = 0; i < value.getTotoleColumns().size(); i++) {
						ParameterItemStructEntity item = value.getTotoleColumns().get(i);
						rowIndex++;
						row = sheet.createRow(rowIndex);
						cellIndex = startCell;
						if (i == 0) {
							for (int j = 0; j < value.getFilterTitles().size(); j++) {
								String ft = value.getFilterTitles().get(j);
								if (item.getItem().contains(ft)) {
									filters.add(item.getItem().indexOf(ft));
								}
							}
							for (int j = 0; j < item.getItem().size(); j++) {
								if (!filters.contains(j)) {
									createCell(row, cellIndex + j, item.getItem().get(j),
											getLabelStyle(wb));
									if (StringUtils.equals(item.getItem().get(j), "类型")) {
										typeIndex = j;
									}
								}else {
									cellIndex --;
								}
							}
							continue;
						}
						for (int j = 0; j < item.getItem().size(); j++) {
							if (filters.contains(j)) {
								cellIndex --;
								continue;
							}
							if (j == 0) {
								createCell(row, cellIndex + j,
										item.getItem().get(j),
										getLabelStyle(wb));
							} else {
								if (value.getHyperlinkIndex() == j && StringUtils.isNotBlank(item.getHyprelinkKey())) {
									if (StringUtils.isNotBlank(hyprelinkRefMap.get(item.getHyprelinkKey()))) {
										createCellFormula(row, cellIndex + j, hyprelinkRefMap.get(item.getHyprelinkKey())+"\""+item.getItem().get(j)+"\")", getLinkStyle(wb));
										continue;
									}
								}
								Integer index = bizTypeHy.get(item.getItem().get(j));
								if (j == typeIndex && index != null) {
									String cv =  "HYPERLINK(\"#'" + BIZ_TYPE + "'!A" + (index + 2) + "\",\"" + item.getItem().get(j) + "\")";
									HSSFCell cell = row.createCell(cellIndex + j);
									cell.setCellFormula(cv);
									cell.setCellStyle(getLinkStyle(wb));
								}else {
									createCell(row, cellIndex + j,
											item.getItem().get(j),
											getTextStyle(wb));
								}
							}
						}
					}
				}
				rowIndex++;
				row = sheet.createRow(rowIndex);
				cellIndex = startCell;
				// 自定义区域
				for (Iterator<ExcelBasicParamStuctEntity> iterator = resStruct
						.getEndAres().keySet().iterator(); iterator.hasNext();) {
					ExcelBasicParamStuctEntity key = iterator.next();
					ExcelBasicParamStuctEntity value = resStruct
							.getEndAres().get(key);
					if (cellIndex >= startCell + maxLength*2) {
						mergeRowAndCell(wb, sheet, rowIndex, cellIndex,
								startCell, maxWidth, widthList);
						rowIndex++;
						row = sheet.createRow(rowIndex);
						cellIndex = startCell;
						maxWidth = 1;
						widthList = new ArrayList<Integer>();
					}
					// 订好长度
					cellIndex = createCell(row, cellIndex, key,
							getLabelStyle(wb));
					widthList.add(cellIndex);
					cellIndex++;
					cellIndex = createCell(row, cellIndex, value,
							getModifyTextStyle(wb));
					widthList.add(cellIndex);
					cellIndex++;
					// 订高度
					maxWidth = Math.max(maxWidth, key.getWidth());
					maxWidth = Math.max(maxWidth, value.getWidth());
				}
				mergeRowAndCell(wb, sheet, rowIndex, cellIndex, startCell,
						maxWidth, widthList);
				cellIndex = startCell;
				rowIndex += maxWidth -1;
				maxWidth = 1;
				widthList = new ArrayList<Integer>();
				rowIndex += 3;
			}
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
			}else if (sheetName.length() > 31) {
				sheetName = StringUtils.substring(sheetName, 0, 31);
			}else if (StringUtils.endsWith(sheetName, "-") ) {
				sheetName = StringUtils.substring(sheetName, 0, sheetName.length() - 1);
			}else {
				return sheetName;
			}
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 创建对象目录
	 * 
	 * @param wb
	 * @param objMap
	 * @param startRow
	 * @param startCell
	 */
	private static void createMenuSheet(HSSFWorkbook wb,
			ExportExcelEntity entity, int startRow, int startCell) {

		for (ExcelMenuSheetStructEntity menu : entity.getMenuList()){
			int tempRow = startRow;
			String sheetName = menu.getSheetName();
			int linkIndex = menu.getHyperlinkIndex();
			int sheetIndex = menu.getSheetIndex();
			HSSFSheet sheet = wb.createSheet(sheetName);
			if (sheetIndex > -1) {
				wb.setSheetOrder(sheetName, sheetIndex);
			}
			int[] columnWidth = menu.getColumnWidths();
			int width = menu.getDefaultColumnWidth();
			int columns = menu.getMenuItems().get(0).getItem().size();
			for (int i = 0; i < columns; i++) {
				try {
					width = columnWidth[i];
				} catch (Exception e) {
				}
				sheet.setColumnWidth(startRow + i, width);
			}
			for (int i = 0; i < startRow; i++) {
				sheet.setColumnWidth(i, 500);
			}
			setDefaultCellStyle(wb, sheet);
			HSSFRow row = sheet.createRow(tempRow);
			for (int i = 0; i < menu.getMenuItems().size(); i++) {
				ExcelMenuItemEntity menuItem = menu.getMenuItems().get(i);
				
				if (i == 0) {
					for (int j = 0;j<menuItem.getItem().size();j++) {
						String title = menuItem.getItem().get(j);
						createCell(row, startCell+j, title, getLabelStyle(wb));
					}
					continue;
				}
				tempRow++;
				row = sheet.createRow(tempRow);
				for (int j = 0;j < menuItem.getItem().size();j++) {
					if ( j == linkIndex) {
						if (StringUtils.isNotBlank(menuItem.getHyprelinkKey())) {
							createCellFormula(row, startCell+j, hyprelinkRefMap.get(menuItem.getHyprelinkKey())+"\""+menuItem.getItem().get(j)+"\")", getLinkStyle(wb));
							continue;
						}
					}
					createCell(row, startCell+j, menuItem.getItem().get(j), getTextStyle(wb));
				}
			}
		}
		
	}

	/**
	 * 合并单元格
	 * 
	 * @param wb
	 * @param sheet
	 * @param rowIndex
	 * @param cellIndex
	 * @param startCell
	 * @param maxWidth
	 * @param widthList
	 */
	private static void mergeRowAndCell(HSSFWorkbook wb, HSSFSheet sheet,
			int rowIndex, int cellIndex, int startCell, int maxWidth,
			List<Integer> widthList) {
		int tempRowIndex = rowIndex;
		for (int i = 1; i < maxWidth; i++) {
			tempRowIndex++;
			HSSFRow row = sheet.createRow(tempRowIndex);
			for (int j = startCell; j < cellIndex; j++) {
				createCell(row, j, "", getTextStyle(wb));
			}
		}
		int tempLength = startCell;
		for (int j = 0; j < widthList.size(); j++) {
			int len = widthList.get(j);
			if (j > 0) {
				len = widthList.get(j) - widthList.get(j - 1);
			}
			int rs = rowIndex;
			int re = rowIndex + maxWidth - 1;
			int cs = tempLength;
			int ce = tempLength + len-1;
			sheet.addMergedRegion(new CellRangeAddress(rs, re, cs, ce));
			tempLength += len;
		}
	}

	/**
	 * 填充cell
	 * 
	 * @param row
	 * @param cellIndex
	 * @param key
	 * @param style
	 * @return
	 */
	private static int createCell(HSSFRow row, int cellIndex,
			ExcelBasicParamStuctEntity key, HSSFCellStyle style) {
		createCell(row, cellIndex, key.getValue(), style);
		if (key.getLength() > 1) {
			for (int i = 1; i < key.getLength(); i++) {
				cellIndex++;
				createCell(row, cellIndex, "", style);
			}
		}
		return cellIndex;
	}

	/**
	 * 获取超链接表达式
	 * 
	 * @param packageName
	 * @param resourceName
	 * @param rowNum
	 * @return
	 */
	public static String getRefExpression(String packageName, int rowNum) {
		return String.format(HYPERLINK, packageName, rowNum);
	}

	/**
	 * 基本信息的扩展信息,每隔3个属性，自动换行，并且填充空白属性样式
	 * 
	 * @param res
	 * @param wb
	 * @param sheet
	 * @param eclass
	 * @param eachPair
	 *            每行放置的属性数量，以"对"为单位(标签，值为一对)
	 * @param startRow
	 * @param startCell
	 * @return
	 */
	public static int creareExtendsCells(IARESResource res, HSSFWorkbook wb,
			HSSFSheet sheet, EClass eclass, int eachPair, int startRow,
			int startCell) {
		int nextCol = 0;
		HSSFRow row = null;
		Map<String, String> extendsMap = getExtendsValue(res, eclass);
		for (Iterator<String> iterator2 = extendsMap.keySet().iterator(); iterator2
				.hasNext();) {
			if (nextCol % eachPair == 0) {
				startRow++;
				row = sheet.createRow(startRow);
			}
			String key = iterator2.next();
			String value = extendsMap.get(key);
			createCell(row, startCell + (nextCol % eachPair * 2), key,
					getLabelStyle(wb));
			createCell(row, startCell + (nextCol % eachPair * 2 + 1), value,
					getTextStyle(wb));
			nextCol++;
		}
		// 如果扩展信息，不是刚好eachPair的倍数，则填充空白信息
		if (nextCol % eachPair != 0) {
			for (int i = 1; i <= eachPair - nextCol % eachPair; i++) {
				createCell(row, startCell + (eachPair - i) * 2, "",
						getLabelStyle(wb));
				createCell(row, startCell + (eachPair - i) * 2 + 1, "",
						getTextStyle(wb));
			}
		}
		return startRow;
	}

	private static HSSFCellStyle titleStyle;
	private static HSSFCellStyle textStyle;
	private static HSSFCellStyle labelStyle;
	private static HSSFCellStyle modifytextStyle;
	private static HSSFFont titleFont;
	private static HSSFFont linkFont;
	private static HSSFCellStyle style;

	private static void init() {
		titleFont = null;
		titleStyle = null;
		textStyle = null;
		labelStyle = null;
		linkFont = null;
		style = null;
		modifytextStyle = null;
	}

	/**
	 * 获取扩展信息
	 * 
	 * @param res
	 * @param eclass
	 * @return
	 */
	private static Map<String, String> getExtendsValue(IARESResource res,
			EClass eclass) {
		Map<String, Object> helperMap = new HashMap<String, Object>();
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils
				.getEndabledEditingSupports(res, eclass);
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support
					.getPropertyDescriptors(res, eclass)) {
				if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
					helperMap
							.put(desc.getDisplayName(),
									new ExtensibleData2MapAttributeHelper(
											support.getKey(),
											desc.getStructuralFeature(),
											((IMapExtensibleModelPropertyDescriptor) desc)
													.getKey()));
				} else {
					helperMap.put(
							desc.getDisplayName(),
							new ExtensibleData2AttributeHelper(
									support.getKey(), desc
											.getStructuralFeature()));
				}
			}
		}

		Map<String, String> result = new HashMap<String, String>();

		EObject obj = null;
		try {
			obj = (EObject) res.getInfo();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		for (Entry<String, Object> entry : helperMap.entrySet()) {
			if (entry.getValue() instanceof IAttributeHelper) {
				result.put(entry.getKey(),
						((IAttributeHelper) entry.getValue()).getValue(obj));
			} else {
				result.put(entry.getKey(), entry.getValue().toString());
			}
		}

		return result;
	}

	/**
	 * 创建表格
	 * 
	 * @param row
	 * @param index
	 * @param value
	 * @param style
	 */
	private static void createCell(HSSFRow row, int index, String value,
			HSSFCellStyle style) {
		HSSFCell cell = row.createCell(index);
		cell.setCellValue(new HSSFRichTextString(value));
		if (style != null) {
			cell.setCellStyle(style);
		}
	}

	/**
	 * 创建超链接
	 * 
	 * @param row
	 * @param index
	 * @param value
	 * @param style
	 */
	private static void createCellFormula(HSSFRow row, int index, String value,
			HSSFCellStyle style) {
		HSSFCell cell = row.createCell(index);
		cell.setCellFormula(value);
		if (style != null) {
			cell.setCellStyle(style);
		}
	}
	
	/**
	 * 超链接的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getLinkStyle(HSSFWorkbook wb) {
		style = wb.createCellStyle();
		if (linkFont == null) {
			linkFont = wb.createFont();
			linkFont.setColor(HSSFColor.BLUE.index);
		}
		style.setFont(linkFont);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
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
	 * 修改记录文本框的样式
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle getModifyTextStyle(HSSFWorkbook wb) {
		if (modifytextStyle == null) {
			modifytextStyle = wb.createCellStyle();
			modifytextStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			modifytextStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			modifytextStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			modifytextStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			modifytextStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			modifytextStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			modifytextStyle.setBottomBorderColor(HSSFColor.BLACK.index);
//			modifytextStyle.setAlignment(HSSFCellStyle.VERTICAL_TOP);
			modifytextStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			modifytextStyle.setWrapText(true);
		}
		return modifytextStyle;
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
	 * 设置sheet默认样式
	 * 
	 * @param wb
	 * @param sheet
	 */
	private static void setDefaultCellStyle(HSSFWorkbook wb, HSSFSheet sheet) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < 15; i++) {
			sheet.setDefaultColumnStyle((short) i, style);
		}
	}

}
