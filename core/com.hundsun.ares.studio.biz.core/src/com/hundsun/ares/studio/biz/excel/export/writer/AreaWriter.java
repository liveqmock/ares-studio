/**
 * 源程序名称：AreaWriter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import org.apache.poi.ss.usermodel.Sheet;

import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Block;

/**
 * @author sundl
 *
 */
public class AreaWriter extends ExcelSheetWriter implements Writer {
	
	protected Area area;
	
	/**
	 * @param startRow
	 * @param area
	 * @param sheet
	 */
	public AreaWriter(Area area, Sheet sheet, int startRow, ExcelWriter excelWriter) {
		super(excelWriter, sheet, startRow);
		this.area = area;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.export.writer.Writer#write()
	 */
	@Override
	public void write() {
		int rowNumber = startRow;
		for (Block block : area.blocks) {
			BlockWriter writer = getBlockWriter(block, sheet, rowNumber);
			writer.write();
			rowNumber += writer.rows;
			this.rows += writer.rows;
		}
	}

	protected BlockWriter getBlockWriter(Block block, Sheet sheet, int startRow) {
		return new BlockWriter(block, sheet, startRow, excelWriter);
	}
	
}
