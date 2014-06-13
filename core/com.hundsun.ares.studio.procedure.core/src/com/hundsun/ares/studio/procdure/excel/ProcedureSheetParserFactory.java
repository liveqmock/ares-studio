package com.hundsun.ares.studio.procdure.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.biz.excel.factories.BizSheetParserFactory;
import com.hundsun.ares.studio.core.excel.BlockTypes;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.util.log.Log;

public class ProcedureSheetParserFactory implements ISheetParserFactory {
	
	public static final ProcedureSheetParserFactory INSTANCE = new ProcedureSheetParserFactory();

	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		SheetParser parser = new SheetParser();
		parser.exlParser = exlParser;
		parser.log = log;
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		if (sheetName.startsWith("原子过程-")) {
			parser.areaTags.add("对象号");
			parser.blocks.putAll(BizSheetParserFactory.BLOCK_TAGS);
			//--兼容证券二部
			parser.blocks.put("前置代码", BlockTypes.TEXT);
			parser.blocks.put("后置代码", BlockTypes.TEXT);
			//
			ISheetHandler handler = new ProcedureSheetHandler();
			handler.init(parser, log);
			parser.handlers.add(handler);
			return parser;
		}
		
		return null;
	}

}
