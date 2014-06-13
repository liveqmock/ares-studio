/**
 * 源程序名称：ExcelHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import org.apache.poi.ss.usermodel.Sheet;

import com.hundsun.ares.studio.core.util.log.Log;


/**
 * 
 * @author sundl
 *
 */
public interface ISheetHandler {
	
	void init(SheetParser sheetParser, Log log);

	void startSheet(Sheet sheet);
	void startArea(String startTag);
	void startBlock(String startTag, int type);
	
	/** key-value区域发现key-value对 */
	void keyValue(String key, String value);
	/** 表格区域中的表头 */
	void header(String[] header);
	/** 表格区域中的表 */
	void row(String[] row);
	
	void endBlock();
	void endArea();
	void endSheet();
}
