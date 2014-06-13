/**
 * 源程序名称：BizSheetParserFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.factories;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.biz.excel.handlers.ObjSheetHandler;
import com.hundsun.ares.studio.core.excel.BlockTypes;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * @author sundl
 *
 */
public class BizSheetParserFactory implements ISheetParserFactory{
	
	public static BizSheetParserFactory INSTANCE = new BizSheetParserFactory();

	public static final Map<String, Integer> BLOCK_TAGS = new HashMap<String, Integer>();
	
	static  {
		// 对象号，功能号都代表key-value block的开始
		BLOCK_TAGS.put("对象号", BlockTypes.KEY_VALUE);
		BLOCK_TAGS.put("功能号", BlockTypes.KEY_VALUE);
		BLOCK_TAGS.put("对象名", BlockTypes.KEY_VALUE);
		
		BLOCK_TAGS.put("输入参数", BlockTypes.TABLE);
		BLOCK_TAGS.put("输出参数", BlockTypes.TABLE);
		BLOCK_TAGS.put("变量", BlockTypes.TABLE);
		BLOCK_TAGS.put("业务处理流程", BlockTypes.TEXT);
		BLOCK_TAGS.put("内部变量", BlockTypes.TEXT);
		BLOCK_TAGS.put("流程变量", BlockTypes.TEXT);

		//2014-01-28 modified by zhuyf 添加业务说明（导入对外接口，时文档中有此格式信息）
		BLOCK_TAGS.put("业务说明", BlockTypes.TEXT);
		BLOCK_TAGS.put("说明", BlockTypes.KEY_VALUE);
		BLOCK_TAGS.put("出错说明", BlockTypes.TABLE);
		//2014-04-17 modified by zhuyf 添加操作提示（导入金融产品销售系统06香港版中有此信息，需去除。）
		BLOCK_TAGS.put("操作提示", BlockTypes.TEXT);
		BLOCK_TAGS.put("修改记录", BlockTypes.TEXT);
		// 输入输出是否数组可能夹杂在输入输出参数中，所以，必须作为单独的block处理
		//TASK #9511 现在名称为输入输出结果集
		BLOCK_TAGS.put("输入结果集", BlockTypes.KEY_VALUE);
		BLOCK_TAGS.put("输出结果集", BlockTypes.KEY_VALUE);
		//兼容性考虑 不删除原来的名字
		BLOCK_TAGS.put("输入是否数组", BlockTypes.KEY_VALUE);
		BLOCK_TAGS.put("输出是否数组", BlockTypes.KEY_VALUE);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetParserFactory#createParser(com.hundsun.ares.studio.biz.excel.ExcelParser, org.apache.poi.hssf.usermodel.HSSFSheet, com.hundsun.ares.studio.biz.excel.Log)
	 */
	@Override
	public SheetParser createParser(ExcelParser exlParser, HSSFSheet sheet, Log log) {
		//中文分隔符—也支持
		String sheetName = StringUtils.replace(sheet.getSheetName(), "—", "-");
		if (sheetName.equals("业务对象定义") 
				|| StringUtils.equals(sheetName, "对象")
				|| StringUtils.startsWith(sheetName, "对象-") 
				|| StringUtils.startsWith(sheetName, "业务对象-")) {
			SheetParser parser = new SheetParser();
			parser.exlParser = exlParser;
			parser.log = log;
			parser.areaTags.add("对象名");
			parser.blocks.putAll(BizSheetParserFactory.BLOCK_TAGS);
			parser.blocks.put("对象属性", BlockTypes.TABLE);
			
			ISheetHandler handler = new ObjSheetHandler();
			handler.init(parser, log);
			parser.handlers.add(handler);
			
			return parser;
		}
		return null;
	}
	
	
}
