/**
 * 源程序名称：SheetWriter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author sundl
 *
 */
public abstract class ExcelSheetWriter implements Writer{

	
	protected Sheet sheet;
	/** 从第几行开始 */
	protected int startRow;
	/** 占用了几行, 这个只在调用完write()方法后才有意义 */
	protected int rows;
	
	protected ExcelWriter excelWriter;
	
	public ExcelSheetWriter(ExcelWriter excelWriter, Sheet sheet, int startRow) {
		this.sheet = sheet;
		this.startRow = startRow;
		this.excelWriter = excelWriter;
	}
	
	protected CellStyle getLabelStyle() {
		return excelWriter.getLabelStyle();
	}
	
	protected CellStyle getTextStyle() {
		return excelWriter.getTextStyle();
	}
	
	protected CellStyle getBackgroundStyle() {
		return excelWriter.getBackgroundStyle();
	}
	
}
