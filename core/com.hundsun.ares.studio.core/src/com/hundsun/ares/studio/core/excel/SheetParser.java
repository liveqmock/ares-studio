/**
 * 源程序名称：SheetParser.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.hundsun.ares.studio.core.util.log.Log;

/**
 * ExcelSheet解析器<p>
 * 这个解析器把一个Sheet分割成1个或多个area(比如对应于一个表、函数等对象的一个表格区域)；
 * 每个area又分割成多个Block，Block按类型分为KEY-Value，Text，Table等；参考{@link BlockTypes}<p>
 * 
 * 每个area和block都由可以自定义的开始tag确定，参考{@link SheetParser#areaTags}和{@link SheetParser#blocks}
 * 
 * Parser本身只解析文档结构，不处理业务逻辑； 业务逻辑是由Handler处理的。
 * 
 * @author sundl
 * @see BlockTypes
 */
public class SheetParser {
	
	private static final Logger logger = Logger.getLogger(SheetParser.class);

	private List<Integer> aresStart = new ArrayList<Integer>();
	
	private Map<Integer, Integer> blockStart = new HashMap<Integer, Integer>();
	
	/** 区域开始标签 */
	public List<String> areaTags = new ArrayList<String>();
	/** block开始标签 */
	public Map<String, Integer> blocks = new HashMap<String, Integer>();
	// 日志组件
	public Log log; 
	public List<ISheetHandler> handlers = new ArrayList<ISheetHandler>();
	public ExcelParser exlParser;
	public int startRow = -1;
	
	/** 解析公式用，必须赋值 */
	public FormulaEvaluator evaluator;
	
	private String currentBlockTag;
	private int currentBlockType;
	private boolean headerFound = false;
	private StringBuffer text = new StringBuffer();
	
	private int rowIndex = -1;
	private int culumnIndex = -1;
	
	public SheetParser() {
		// 默认的几个TAG， 可以用来解析sheet页中的模块信息声明
		areaTags.add("模块中文名");
		areaTags.add("模块英文名");
		areaTags.add("模块名");		// == 英文名
		
		blocks.put("模块中文名", BlockTypes.KEY_VALUE);
		blocks.put("模块名", BlockTypes.KEY_VALUE);
		blocks.put("模块英文名", BlockTypes.KEY_VALUE);
	}
	
	public List<Integer> getAresStart() {
		return aresStart;
	}

	public Map<Integer, Integer> getBlockStart() {
		return blockStart;
	}


	public int getCurrentRow() {
		return rowIndex;
	}
	
	public int getCurrentBlockType() {
		return currentBlockType;
	}
	
	public String getCurrentBlockTag() {
		return currentBlockTag;
	}
	
	public String getText() {
		return text.toString();
	}
	
	private void reset() {
		currentBlockType = -1;
		headerFound = false;
	}
	
	public void parse(Sheet sheet) { 
		logger.debug("开始解析Sheet页:" + sheet.getSheetName());
		startSheet(sheet);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		logger.debug("行数：" + rowCount);
		
		// 遇到表头区域的时候记录开始列
		int headerStartCol = 0;
		
		for (Row r : sheet) {
			HSSFRow row = (HSSFRow) r;
			if (row == null)
				continue;
			
			this.rowIndex = row.getRowNum();
			if (rowIndex < startRow) {
				continue;	
			}
			// sundl: row.getFirstCellNum()不是很稳定，第一列的空格有时候会是null，有时候又是空字符串，这样导致这个值不可信
			// 目前强制写成1， 后续可以考虑改成自己写找第一个非空列。
			int firstCellNum = row.getFirstCellNum();
			if (firstCellNum == -1)
				continue;
			else
				firstCellNum = 1; 		
			
			HSSFCell firstCell = row.getCell(firstCellNum);
			String firstcellString = getCellStringValue(firstCell, evaluator);
			
			if (!firstcellString.isEmpty()) {
				if (aresStart.contains(rowIndex)) {
					endArea();
					logger.debug("Area开始,行号: " + rowIndex);
					startArea(firstcellString);
				}else if (areaTags.indexOf(firstcellString) != -1) {
					endArea();
					logger.debug("Area开始,行号: " + rowIndex);
					startArea(firstcellString);
				}
				
				if (blockStart.keySet().contains(rowIndex)) {
					endBlock();
					startBlock(firstcellString, blockStart.get(rowIndex));
				}else if (blocks.containsKey(firstcellString)) {
					endBlock();
					startBlock(firstcellString, blocks.get(firstcellString));
					// Text区域第一行暂时不解析
					if (currentBlockType == BlockTypes.TEXT) {
						continue;
					}
				}
			}
			
			String[] strings = readRowStrings(row, 1, evaluator);
			switch (currentBlockType) {
				case BlockTypes.KEY_VALUE:
					parseKeyValue(row, 1);
					break;
				case BlockTypes.TABLE:
					if (!headerFound) {
						// header按理说不应该存在空字符串，开头应该不会，但结尾可能存在
						while (StringUtils.isEmpty(strings[strings.length - 1])) {
							strings = (String[]) ArrayUtils.remove(strings, strings.length - 1);
						}
						headerStartCol = firstCellNum;
						header(strings);
						headerFound = true;
					} else {
						strings = POIUtils.readRowStrings(row, headerStartCol, evaluator);
						row(strings);
					}
					break;
				case BlockTypes.TEXT:
					text.append(StringUtils.join(strings));
					text.append('\n');
					break;
			}
		}
		
		endBlock();
		endArea();
		endSheet();
	}
	
	public static String[] readRowStrings(HSSFRow row, int startCol, FormulaEvaluator evaluator) {
		List<String> strings = new ArrayList<String>();
		int max = row.getLastCellNum();
		for (int i = startCol; i < max; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell == null) {
				strings.add(StringUtils.EMPTY);
			} else {
				String str = getCellStringValue((HSSFCell)cell, evaluator);
				strings.add(str);
			}
		}
		return strings.toArray(new String[0]);
	}
	
	public static String getCellStringValue(HSSFCell cell, FormulaEvaluator evaluator) {
		if (cell != null) {
		    switch (evaluator.evaluateInCell(cell).getCellType()) {
		        case Cell.CELL_TYPE_BOOLEAN:
		        	return BooleanUtils.toStringTrueFalse(cell.getBooleanCellValue());
		        case Cell.CELL_TYPE_NUMERIC:
		        	// FIXME 这里其实排除了 浮点类型的数据
		        	return String.valueOf((int)cell.getNumericCellValue());
		        case Cell.CELL_TYPE_STRING:
		        	return cell.getStringCellValue();
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
	
	private void parseKeyValue(HSSFRow row, int startCol) {
		String key = null;
		String value = null;
		for (int i = startCol; i < row.getLastCellNum(); i++) {
			HSSFCell cell = row.getCell(i);
			String str = getCellStringValue(cell, evaluator);
			if (key == null) {
				if (StringUtils.isEmpty(str)) {
					//可能当前单元合并了几个单元格，之后还有信息，将return改为continue  by wangxh 2014年2月20日14:44:02
					continue;
				}
				key = str;
			} else {
				value = str;
				keyValue(key, value);
				key = null;
				value = null;
			}
		}
	}
	
	public void startSheet(Sheet sheet) {
		for (ISheetHandler handler : handlers) {
			try {
				handler.startSheet(sheet);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void startArea(String startTag) {
		for (ISheetHandler handler : handlers) {
			try {
				handler.startArea(startTag);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void startBlock(String startTag, int type) {
		currentBlockTag = startTag;
		currentBlockType = type;
		for (ISheetHandler handler : handlers) {
			try {
				handler.startBlock(startTag, type);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY, e);
			}
		}
	}
	
	public void keyValue(String key, String value) {
		for (ISheetHandler handler : handlers) {
			try {
				handler.keyValue(key, value);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void header(String[] header) {
		for (ISheetHandler handler : handlers) {
			try {
				handler.header(header);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void row(String[] row) {
		for (ISheetHandler handler : handlers) {
			try {
				handler.row(row);
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void endBlock() {
		for (ISheetHandler handler : handlers) {
			try {
				handler.endBlock();
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
		
		switch (currentBlockType) {
			case BlockTypes.TABLE:
				headerFound = false;
				break;
			case BlockTypes.TEXT:
				text = new StringBuffer();
				break;
		}
		
	}
	
	public void endArea() {
		for (ISheetHandler handler : handlers) {
			try {
				handler.endArea();
			} catch (Exception e) {
				logger.error(StringUtils.EMPTY,e);
			}
		}
	}
	
	public void endSheet() {
		for (ISheetHandler handler : handlers) {
			try {
				handler.endSheet();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
}
