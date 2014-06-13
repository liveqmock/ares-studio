package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2AttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.ExtensibleData2MapAttributeHelper;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils.IAttributeHelper;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IMapExtensibleModelPropertyDescriptor;

public class ExportBasicdataUtil {
	public static final String dirSheetName = "基础数据目录";
	
	/**
	 * 获取扩展属性标题名和属性助手的映射
	 * @param resource
	 * @param eclass
	 * @return
	 */
	public static Map<String, IAttributeHelper> getExtendHelpMap(IARESResource resource,EClass eclass){
		IExtensibleModelEditingSupport[] supports = ExtensibleModelUtils.getEndabledEditingSupports(resource, eclass);
		// 标题名和属性助手的映射
		Map<String, IAttributeHelper> helperMap = new HashMap<String, POIUtils.IAttributeHelper>();
		for (IExtensibleModelEditingSupport support : supports) {
			for (IExtensibleModelPropertyDescriptor desc : support.getPropertyDescriptors(resource, eclass)) {
				if (!desc.isDerived()) {
					if (desc instanceof IMapExtensibleModelPropertyDescriptor) {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2MapAttributeHelper(support.getKey(), desc.getStructuralFeature(), ((IMapExtensibleModelPropertyDescriptor) desc).getKey()));
					} else {
						helperMap.put(desc.getDisplayName(), new ExtensibleData2AttributeHelper(support.getKey(), desc.getStructuralFeature()));
					}
				}
			}
		}
		return helperMap;
	}
	
	/**
	 * 获取主表信息
	 * @param resource
	 * @param info
	 * @param masterEclass
	 * @param includeGroup  是否需要分组信息
	 * @return
	 */
	public static List<List<String>> getMasterTableInfo(
			IARESResource resource, BasicDataBaseModelImpl info,
			EClass masterEclass, boolean includeGroup) {
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> titles = new ArrayList<String>();
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(masterEclass);//内容
		String[] names = new String[attrArray.length];//标题
		for (int i = 0; i < attrArray.length; i++) {
			names[i] = BasicDataEpackageUtil.getAttrColumnName(resource,attrArray[i]);
			titles.add(names[i]);
		}
		
		Map<String, IAttributeHelper> helperMap = getExtendHelpMap(resource, masterEclass);
		titles.addAll(helperMap.keySet());
		
		table.add(titles);
		
		//先添加未分组条目
		List<MetadataItem> unCateItems = MetadataUtil.getUncategorizedItems(info);
		for(MetadataItem item : unCateItems){
			List<String> content = new ArrayList<String>();
			for(EAttribute attr : attrArray){
				Object value = item.eGet(attr);
				if(value == null){
					content.add(StringUtils.EMPTY);
				}else{
					content.add(value.toString());
				}
			}
			for(Entry<String, IAttributeHelper> entry : helperMap.entrySet()){
				content.add(entry.getValue().getValue(item));
			}
			table.add(content);
		}
		//再添加分组下的条目
		for(MetadataCategory cate : info.getRoot().getChildren()){
			addCateItems(cate, table, titles.size(), new ArrayList<String>(), attrArray,helperMap);
		}
		return table;
	}
	
	private static void addCateItems(MetadataCategory cate , List<List<String>> tableCopy,int colLength , List<String> cats ,EAttribute[] attrArray, Map<String, IAttributeHelper> helperMap){
		String space = "";
		for (int i = 0; i < cats.size(); i++) {
			space += cats.get(i)+"/";
		}
		List<String> title = new ArrayList<String>();
		title.add(space + cate.getName());
		for (int i = 0; i < colLength-1; i++) {
			title.add(null);
		}
		tableCopy.add(title);
		if (!cats.contains(cate.getName())) {
			cats.add(cate.getName());
		}
		for(MetadataItem item : cate.getItems()){
			List<String> content = new ArrayList<String>();
			for(EAttribute attr : attrArray){
				Object value = item.eGet(attr);
				if(value == null){
					content.add(StringUtils.EMPTY);
				}else{
					content.add(value.toString());
				}
			}
			for(Entry<String, IAttributeHelper> entry : helperMap.entrySet()){
				content.add(entry.getValue().getValue(item));
			}
			tableCopy.add(content);
		}
		for(MetadataCategory subcate : cate.getChildren()){
			List<String> cats1 = new ArrayList<String>();
			cats1.addAll(cats);
			addCateItems(subcate, tableCopy, colLength,cats1 ,attrArray, helperMap);
		}
	}
	
	
	/**
	 * 导出基础数据
	 * @param excelStream
	 * @param tableMap
	 * @param sheetNames
	 * @param startCols
	 * @param startRows
	 */
	public static void exportBasicData(Map<String, List<String>> headInfo,
			OutputStream excelStream, Map<String, List<List<String>>> tableMap,String[] sheetNames, int[] startCols, int[] startRows) {
		try {
			init();
			HSSFWorkbook wb = new HSSFWorkbook();
			
			for (int i = 0; i < sheetNames.length; i++) {
				init();
				HSSFSheet sheet = wb.createSheet(sheetNames[i]);
				setDefaultCellStyle(wb, sheet);
				List<List<String>> sheetData = tableMap.get(sheetNames[i]);
				//说明列
				int descColumnIndex = -1;
				int startRow = startRows[i];
				//基础数据页还需要添加头信息
				if(!StringUtils.equalsIgnoreCase(dirSheetName, sheetNames[i])){
					HSSFRow row0 = sheet.createRow(startRow);
					for (int j = 0; j < 6; j++) {
						HSSFCell title = row0.createCell(j + startCols[i]);
						title.setCellValue(headInfo.get(sheetNames[i]).get(j));
						if(j%2==1) {
							title.setCellStyle(getTextStyle(wb));
						}else {
							title.setCellStyle(getTitleStyle(wb));
						}
					}
					++startRow;
					++startRow;
				}
				
				sheet.createFreezePane(0, startRow+1);
				for (int j = 0; j < sheetData.size(); j++) {
					HSSFRow row = sheet.createRow(startRow+j);
					List<String> data = sheetData.get(j);
					//标题
					if (j == 0) {
						for (int k = 0; k < data.size(); k++) {
							HSSFCell title = row.createCell(k+startCols[i]);
							title.setCellValue(data.get(k));
							title.setCellStyle(getTitleStyle(wb));
							if ("说明".equals(data.get(k))) {
								descColumnIndex = k+startCols[i];
							}
						}
					}else {
						//参数行
						boolean cateStatus = false;
						for (int k = 0; k < data.size(); k++) {
							String d = StringUtils.defaultString(data.get(k));
							if (k == 0) {
								if (StringUtils.isNotBlank(d)) {
									cateStatus = true;
								}
							}else {
								if (d != null) {
									cateStatus = false;
								}
							}
							HSSFCell cell = row.createCell(k+startCols[i]);
							if (d.length() > 32767) {
								d = StringUtils.substring(d, 0, 32767);
								logger.warn("sheet:[" +sheetNames[i] + "] ,位置 ：[" + row.getRowNum()+1 +"行,"+ cell.getColumnIndex() +"列]，数据超过单元格最大指定长度，被截取!");
							}
							cell.setCellValue(d);
							cell.setCellStyle(getTextStyle(wb));
						}
						if (cateStatus) {
							for (int k = 0; k < data.size()-1; k++) {
								row.getCell(k + startCols[i]).setCellStyle(getCateStyle(wb));
							}
							sheet.addMergedRegion(new CellRangeAddress(startRow+j, startRow+j ,startCols[i], startCols[i] + data.size()-1));
						}
					}
				}
//				//基础数据页还需要添加头信息
//				if(!StringUtils.equalsIgnoreCase(dirSheetName, sheetNames[i])){
//					HSSFRow row1 = sheet.createRow(startRow + sheetData.size());
//					List<String> data = sheetData.get(0);
//					HSSFCell t = row1.createCell(startCols[i]);
//					t.setCellValue("修改记录");
//					t.setCellStyle(getTitleStyle(wb));
//					for (int k = 1; k < data.size(); k++) {
//						HSSFCell title = row1.createCell(k+startCols[i]);
//						title.setCellValue("");
//						title.setCellStyle(getTextStyle(wb));
//					}
//					sheet.addMergedRegion(new CellRangeAddress(startRow + sheetData.size(), startRow + sheetData.size() ,
//							startCols[i]+1, startCols[i] + data.size()-1));
//				}
				
				
				setSheetWidth(sheet, startCols[i], sheetData.get(0).size());
				if (descColumnIndex > -1) {
					sheet.setColumnWidth(descColumnIndex, 10000);
				}
			}
			wb.write(excelStream);
			excelStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static HSSFFont titleFont;
	private static HSSFCellStyle titleStyle;
	private static HSSFCellStyle textStyle;
	private static HSSFCellStyle cateStyle;
	private static Logger logger = ConsoleHelper.getLogger();
	private static void init (){
		if (titleFont != null) {
			titleFont = null;
		}
		if (titleStyle != null) {
			titleStyle = null;
		}
		if (textStyle != null) {
			textStyle = null;
		}
		if (cateStyle != null) {
			cateStyle = null;
		}
	}
	/**
	 *设置列宽 
	 *
	 * @param sheet
	 * @param startCol
	 * @param size
	 */
	private static void setSheetWidth (HSSFSheet sheet , int startCol , int size){
		for (int i = startCol; i < startCol+size; i++) {
			sheet.autoSizeColumn(i);
			if (sheet.getColumnWidth(i) > 10000) {
				sheet.setColumnWidth(i, 10000);
			}
		}
	}
	
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
			// 2012-09-11 sundl 在开头有空格的情况下，而且列宽AutoWidth又不完美，导致内容被换到下一行，默认状态看不到.
			//textStyle.setWrapText(true);
		}
		return textStyle;
	}
	
	private static HSSFCellStyle getCateStyle(HSSFWorkbook wb) {
		if (cateStyle == null) {
			cateStyle = wb.createCellStyle();
			cateStyle.setFillForegroundColor(HSSFColor.TAN.index);
			cateStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cateStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cateStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			// 2012-09-11 sundl 在开头有空格的情况下，而且列宽AutoWidth又不完美，导致内容被换到下一行，默认状态看不到.
			//textStyle.setWrapText(true);
		}
		return cateStyle;
	}
	
	/**
	 * 设置sheet默认样式
	 * 
	 * @param wb
	 * @param sheet
	 */
	private static void setDefaultCellStyle(HSSFWorkbook wb, HSSFSheet sheet) {
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//
//		for (int i = 0; i < 15; i++) {
//			sheet.setDefaultColumnStyle((short) i, style);
//		}
		
		HSSFCellStyle defaultStyle = sheet.createRow(0).createCell(0).getCellStyle();
		defaultStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		defaultStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		defaultStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	}


}
