/**
 * 源程序名称：IExcelHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hundsun.ares.studio.core.util.log.Log;

/**
 * @author sundl
 *
 */
public interface ISheetParserFactory {

	/**
	 * @param sheet
	 * @return
	 */
	SheetParser createParser(ExcelParser parser, HSSFSheet sheet, Log log);
	
}
