/**
 * 源程序名称：HisSheetParserFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.core.util.log.Log;

/**
 * 
 * @author sundl
 */
public class HisSheetParserFactory implements ISheetParserFactory{
	
	public static final HisSheetParserFactory INSTANCE = new HisSheetParserFactory();

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.ISheetParserFactory#createParser(com.hundsun.ares.studio.core.excel.ExcelParser, org.apache.poi.hssf.usermodel.HSSFSheet, com.hundsun.ares.studio.core.util.log.Log)
	 */
	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		if (sheet.getSheetName().equals("版本页")) {
			SheetParser parser = new SheetParser();
			parser.exlParser = exlParser;
			parser.log = log;
			parser.areaTags.add("修改版本");
			parser.blocks.put("修改版本", BlockTypes.TABLE);
			parser.startRow = 11;
			
			ISheetHandler handler = new HisSheetHandler();
			handler.init(parser, log);
			parser.handlers.add(handler);
			
			return parser;
		}
		return null;
	}

}
