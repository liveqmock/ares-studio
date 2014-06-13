/**
 * 源程序名称：ResourceListSheetParserFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.factories;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.core.excel.BlockTypes;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.excel.handler.ResourceListHandler;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * 可以创建解析资源列表Sheet页的解析器的工厂
 * @author sundl
 *
 */
public class ResourceListSheetParserFactory implements ISheetParserFactory{
	
	public static final ResourceListSheetParserFactory INSTANCE = new ResourceListSheetParserFactory();

	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		if (sheet.getSheetName().equals("逻辑函数列表")
				|| sheet.getSheetName().equals("功能接口列表")
				|| sheet.getSheetName().equals("业务对象列表")) {
			SheetParser parser = new SheetParser();
			parser.areaTags.add("模块名");
			parser.areaTags.add("模块中文名") ;
			parser.exlParser = exlParser;
			parser.blocks.put("模块名", BlockTypes.TABLE);
			parser.blocks.put("模块中文名", BlockTypes.TABLE);
			ResourceListHandler handler = new ResourceListHandler();
			handler.init(parser, log);
			parser.handlers.add(handler);
			return parser;
		}
		return null;
	}

}
