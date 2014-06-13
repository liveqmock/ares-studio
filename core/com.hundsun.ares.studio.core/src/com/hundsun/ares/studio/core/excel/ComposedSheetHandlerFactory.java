/**
 * 源程序名称：ComposedSheetHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * 融合多个Factory
 * @author sundl
 *
 */
public class ComposedSheetHandlerFactory implements ISheetParserFactory{

	private List<ISheetParserFactory> factoryList = new ArrayList<ISheetParserFactory>();
	
	public ComposedSheetHandlerFactory(ISheetParserFactory[] factories) {
		this.factoryList.addAll(Arrays.asList(factories));
	}
	
	public void addFactory(ISheetParserFactory factory) {
		factoryList.add(factory);
	}
	
	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		for (ISheetParserFactory factory : factoryList) {
			SheetParser parser = factory.createParser(exlParser, sheet, log);
			if (parser != null)
				return parser;
		}
		return null;
	}

}
