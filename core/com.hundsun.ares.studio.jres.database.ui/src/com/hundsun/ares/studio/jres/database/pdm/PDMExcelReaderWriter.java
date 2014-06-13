/**
 * 源程序名称：PDMExcelReaderWriter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.hundsun.ares.studio.jres.database.pdm.bean.PDMBusinessDataType;
import com.hundsun.ares.studio.jres.database.pdm.bean.PDMStandardField;
import com.hundsun.ares.studio.jres.metadata.ui.wizards.POIUtils;


/**
 * @author liaogc
 * 完成PDM导入时对Excel的读与写
 *
 */
public class PDMExcelReaderWriter {
	
	private  HSSFCellStyle textStyle;
	public static final String STD_SHEET = "标准字段列表";
	public static final String BT_SHEET = "业务数据类型";
	
	/**
	 * 生成标准字段评审文档
	 * 
	 * @param wb excel工作页
	 * @param stdList 模型对象数组
	 */
	public void standardFieldWriter(HSSFWorkbook wb ,List<PDMStandardField> stdList){
		HSSFSheet sheet = wb.getSheet(STD_SHEET);
		for (int i = 0; i < stdList.size(); i++) {
			HSSFRow row = sheet.createRow(i+2);
			PDMStandardField std = stdList.get(i);
			//1
			HSSFCell cell = row.createCell(1);
			cell.setCellValue(std.getOldName());
			cell.setCellStyle(getTextStyle(wb));
			//2
			cell = row.createCell(2);
			cell.setCellValue(std.getOldChineseName());
			cell.setCellStyle(getTextStyle(wb));
			
			//3
			cell = row.createCell(3);
			cell.setCellValue(std.getOldBusType());
			cell.setCellStyle(getTextStyle(wb));
			//4
			cell = row.createCell(4);
			String tables ="";
			for(int j = 0;j< std.getBelongTableList().size();j++){
				if(j!=0){
					tables = tables+",";	
				}
				tables = tables+std.getBelongTableList().get(j);
			}
			cell.setCellValue(tables);
			cell.setCellStyle(getTextStyle(wb));
			//5
			cell = row.createCell(5);
			String bolongSubSystem = "";
			if(std.getBolongSubSystemList().size()>0){
				bolongSubSystem = std.getBolongSubSystemList().get(0);
			}
			cell.setCellValue(bolongSubSystem);
			cell.setCellStyle(getTextStyle(wb));
			//6
			cell = row.createCell(6);
			cell.setCellValue(std.getGenName());
			cell.setCellStyle(getTextStyle(wb));
			//7
			cell = row.createCell(7);
			cell.setCellValue(std.getNewName());
			cell.setCellStyle(getTextStyle(wb));
			//8
			cell = row.createCell(8);
			cell.setCellValue(std.getNewChineseName());
			cell.setCellStyle(getTextStyle(wb));
			//9
			cell = row.createCell(9);
			cell.setCellValue(std.getGenBusType());
			cell.setCellStyle(getTextStyle(wb));
			//10
			cell = row.createCell(10);
			cell.setCellValue(std.getNewBusType());
			cell.setCellStyle(getTextStyle(wb));
			//11
			cell = row.createCell(11);
			cell.setCellValue(std.getOldComment());
			cell.setCellStyle(getTextStyle(wb));
			//12
			cell = row.createCell(12);
			cell.setCellValue(std.getNewComment());
			cell.setCellStyle(getTextStyle(wb));
			//13
			cell = row.createCell(13);
			cell.setCellValue(std.getDictId());
			cell.setCellStyle(getTextStyle(wb));
			//14
			cell = row.createCell(14);
			cell.setCellValue(std.getModefyDesc());
			cell.setCellStyle(getTextStyle(wb));
			//15
			cell = row.createCell(15);
			cell.setCellValue(std.getImportPath());
			cell.setCellStyle(getTextStyle(wb));
		}
	}
	
	/**
	 * 标准字段列表读取
	 * 
	 * @param is 需要读取的文件流
	 */
	public List<PDMStandardField> standardFieldReader(HSSFWorkbook wb){
		List<PDMStandardField> stdList = new ArrayList<PDMStandardField>();
		HSSFSheet sheet = wb.getSheet(STD_SHEET);
		if (sheet == null) {
			return stdList;
		}
		HSSFFormulaEvaluator evaluator =  wb.getCreationHelper().createFormulaEvaluator();
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			PDMStandardField std = new PDMStandardField();
			stdList.add(std);
			//1
			HSSFCell cell = row.getCell(1);
			std.setOldName(POIUtils.getCellStringValue(cell, evaluator));
			//2
			cell = row.getCell(2);
			std.setOldChineseName(POIUtils.getCellStringValue(cell, evaluator));
			
			//3
			cell = row.getCell(3);
			std.setOldBusType(POIUtils.getCellStringValue(cell, evaluator));
			//4
			cell = row.getCell(4);
			String[] tableNameArray = StringUtils.split(StringUtils.defaultIfBlank(POIUtils.getCellStringValue(cell, evaluator), ""), ",，");
			for(String tableName:tableNameArray){
				std.getBelongTableList().add(tableName);
			}
			//5
			cell = row.getCell(5);
			std.getBolongSubSystemList().add((POIUtils.getCellStringValue(cell, evaluator)));
			//6
			cell = row.getCell(6);
			std.setGenName(POIUtils.getCellStringValue(cell, evaluator));
			//7
			cell = row.getCell(7);
			std.setNewName(POIUtils.getCellStringValue(cell, evaluator));
			//8
			cell = row.getCell(8);
			std.setNewChineseName(POIUtils.getCellStringValue(cell, evaluator));
			//9
			cell = row.getCell(9);
			std.setGenBusType(POIUtils.getCellStringValue(cell, evaluator));
			//10
			cell = row.getCell(10);
			std.setNewBusType(POIUtils.getCellStringValue(cell, evaluator));
			//11
			cell = row.getCell(11);
			std.setOldComment(POIUtils.getCellStringValue(cell, evaluator));
			//12
			cell = row.getCell(12);
			std.setNewComment(POIUtils.getCellStringValue(cell, evaluator));
			//13
			cell = row.getCell(13);
			std.setDictId(POIUtils.getCellStringValue(cell, evaluator));
			//14
			cell = row.getCell(14);
			std.setModefyDesc(POIUtils.getCellStringValue(cell, evaluator));
			//15
			cell = row.getCell(15);
			std.setImportPath(POIUtils.getCellStringValue(cell, evaluator));
		}
		return stdList;
	}
	
	/**
	 * 业务数据类型生成
	 * 
	 * @param wb excel工作页
	 * @param btList 模型对象数组
	 */
	public  void BusTypeWriter(HSSFWorkbook wb ,List<PDMBusinessDataType> btList){
		HSSFSheet sheet = wb.getSheet(BT_SHEET);
		for (int i = 0; i < btList.size(); i++) {
			HSSFRow row = sheet.createRow(i+2);
			PDMBusinessDataType bt = btList.get(i);
			//1
			HSSFCell cell = row.createCell(1);
			cell.setCellValue(bt.getTypeName());
			cell.setCellStyle(getTextStyle(wb));
			//2
			cell = row.createCell(2);
			cell.setCellValue(bt.getTypeChineseName());
			cell.setCellStyle(getTextStyle(wb));
			//3
			cell = row.createCell(3);
			cell.setCellValue(bt.getStandardTypeName());
			cell.setCellStyle(getTextStyle(wb));
			//4
			cell = row.createCell(4);
			cell.setCellValue(bt.getLength());
			cell.setCellStyle(getTextStyle(wb));
			//5
			cell = row.createCell(5);
			cell.setCellValue(bt.getPrecision());
			cell.setCellStyle(getTextStyle(wb));
			//6
			cell = row.createCell(6);
			cell.setCellValue(bt.getDefaultValue());
			cell.setCellStyle(getTextStyle(wb));
			//7
			cell = row.createCell(7);
			cell.setCellValue(bt.getComment());
			cell.setCellStyle(getTextStyle(wb));
		}
	}
	
	/**
	 * 业务数据类型页读取
	 * 
	 * @param is 需要读取的文件流
	 */
	public  List<PDMBusinessDataType> BusTypeReader(HSSFWorkbook wb){
		List<PDMBusinessDataType> btList = new ArrayList<PDMBusinessDataType>();
		HSSFSheet sheet = wb.getSheet(BT_SHEET);
		if (sheet == null) {
			return btList;
		}
		HSSFFormulaEvaluator evaluator =  wb.getCreationHelper().createFormulaEvaluator();
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			
			HSSFRow row = sheet.getRow(i);
			PDMBusinessDataType bt = new PDMBusinessDataType();
			//1
			HSSFCell cell = row.getCell(1);
			bt.setTypeName(POIUtils.getCellStringValue(cell, evaluator));
			//2
			cell = row.getCell(2);
			bt.setTypeChineseName(POIUtils.getCellStringValue(cell, evaluator));
			//3
			cell = row.getCell(3);
			bt.setStandardTypeName(POIUtils.getCellStringValue(cell, evaluator));
			//4
			cell = row.getCell(4);
			bt.setLength(POIUtils.getCellStringValue(cell, evaluator));
			//5
			cell = row.getCell(5);
			bt.setPrecision(POIUtils.getCellStringValue(cell, evaluator));
			//6
			cell = row.getCell(6);
			bt.setDefaultValue(POIUtils.getCellStringValue(cell, evaluator));
			//7
			cell = row.getCell(7);
			bt.setComment(POIUtils.getCellStringValue(cell, evaluator));
			btList.add(bt);
			}
		return btList;
	}
	
	
	/**
	 * 文本框的样式
	 * 
	 * @param wb
	 * @return
	 */
	private  HSSFCellStyle getTextStyle(HSSFWorkbook wb) {
		if (textStyle == null) {
			textStyle = wb.createCellStyle();
			textStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			textStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			textStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			textStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			textStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			textStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			textStyle.setWrapText(true);
		}
		return textStyle;

	}
	
}
