package com.hundsun.ares.studio.jres.service.ui.wizard;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.biz.excel.factories.BizSheetParserFactory;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.util.log.Log;

public class ServiceSheetParserFactory implements ISheetParserFactory{

	public static final ServiceSheetParserFactory INSTANCE = new ServiceSheetParserFactory();

	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		SheetParser parser = new SheetParser();
		parser.exlParser = exlParser;
		parser.log = log;
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		if (sheetName.startsWith("服务-") ||
				sheetName.startsWith("功能接口定义-") || sheetName.startsWith("功能接口-")) {
			parser.areaTags.add("功能号");
			parser.areaTags.add("对象号");
			parser.blocks.putAll(BizSheetParserFactory.BLOCK_TAGS);
			ISheetHandler handler = new ServiceSheetHandler();
			handler.init(parser, log);
			parser.handlers.add(handler);
			return parser;
		} 
		
		return null;
	}

}
