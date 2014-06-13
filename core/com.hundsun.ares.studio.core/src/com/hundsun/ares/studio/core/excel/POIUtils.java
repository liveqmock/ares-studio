/**
 * 源程序名称：POIUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 * @author sundl
 *
 */
public class POIUtils {
	
	public static String[] readRowStrings(HSSFRow row, HSSFFormulaEvaluator evaluator) {
		List<String> strings = new ArrayList<String>();
		for (Cell cell : row) {
			String str = getCellStringValue((HSSFCell)cell, evaluator);
			strings.add(str);
		}
		return strings.toArray(new String[0]);
	}
	
	public static String[] readRowStrings(HSSFRow row, int startCol, FormulaEvaluator evaluator) {
		List<String> strings = new ArrayList<String>();
		int max = row.getLastCellNum();
		for (int i = startCol; i < max; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell == null) {
				strings.add(StringUtils.EMPTY);
			} else {
				String str = getCellStringValue(cell, evaluator);
				strings.add(str);
			}
		}
		return strings.toArray(new String[0]);
	}
	
	public static String getCellStringValue(Cell cell, FormulaEvaluator evaluator) {
		if (cell != null) {
		    switch (evaluator.evaluateInCell(cell).getCellType()) {
		        case Cell.CELL_TYPE_BOOLEAN:
		        	return BooleanUtils.toStringTrueFalse(cell.getBooleanCellValue());
		        case Cell.CELL_TYPE_NUMERIC:
		        	// FIXME 这里其实排除了 浮点类型的数据
		        	return String.valueOf((long)cell.getNumericCellValue());
		        case Cell.CELL_TYPE_STRING:
		        	return cell.getStringCellValue().trim();
		        case Cell.CELL_TYPE_BLANK:
		            break;
		        case Cell.CELL_TYPE_ERROR:

		        // CELL_TYPE_FORMULA will never occur
		        case Cell.CELL_TYPE_FORMULA:
		            break;
		    }
		}
		return StringUtils.EMPTY;
	}
	
}
