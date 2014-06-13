/**
 * 源程序名称：BlockWriter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.excel.export.Block;
import com.hundsun.ares.studio.biz.excel.export.KeyValueBlock;
import com.hundsun.ares.studio.biz.excel.export.KeyValueBlock.KeyValue;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.biz.excel.export.TableBlock.Column;
import com.hundsun.ares.studio.biz.excel.export.TextBlock;

/**
 * @author sundl
 *
 */
public class BlockWriter extends ExcelSheetWriter implements Writer {
	
	protected Block block;
	protected int startColumn = 1;
	
	/**
	 * @param block
	 * @param sheet
	 */
	public BlockWriter(Block block, Sheet sheet, int startRow, ExcelWriter excelWriter) {
		super(excelWriter, sheet, startRow);
		this.block = block;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.export.writer.Writer#write()
	 */
	@Override
	public void write() {
		if (block instanceof KeyValueBlock) {
			writeBlock((KeyValueBlock) block);
		} else	if (block instanceof TableBlock) {
			writeBlock((TableBlock) block);
		} else if (block instanceof TextBlock) {
			writeBlock((TextBlock) block);
		}
	}
	
	protected void writeBlock(KeyValueBlock block) {
		int rowNumber = startRow;
		int column = startColumn;
		
		Row row = null;
		
		for (int i = 0; i < block.kvList.size(); i++) {
			KeyValue kv = block.kvList.get(i);
			if (column == startColumn)
				row = sheet.createRow(rowNumber);
			
			CellUtil.createCell(row, column, kv.key, getLabelStyle());
			column++;
			CellUtil.createCell(row, column, kv.value, getTextStyle());
			
			// 某些属性要多个属性位
			if (kv.span > 1) {
				int endcol = column + kv.span - 1;
				CellUtil.createCell(row, endcol, StringUtils.EMPTY, getTextStyle());
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, column, endcol));
				column += kv.span;
			} else {
				column++;
			}
			
			// 如果已经写了3个属性，换行
			if (column >= block.propertyPerLine * 2 + startColumn) {
				column = startColumn;
				rowNumber++;
				rows++;
			}
		}
	}
	
	protected void writeBlock(TableBlock block) {
		int rowNumber = startRow;
		Row headeRow = sheet.createRow(rowNumber);
		
		int column = startColumn;
		for (String head : block.getHeaders()) {
			CellUtil.createCell(headeRow, column, head, getLabelStyle());
			column++;
		}
		
		rowNumber++;
		this.rows++;
		
		String[] linkLocations = null;
		if (block.linkColumn != -1) {
			@SuppressWarnings("unchecked")
			Multimap<String, String> groupLocations = (Multimap<String, String>) excelWriter.context.get(ExcelWriter.CONTEXT_GROUP_AREA_LOCATIONS);
			if (groupLocations != null) {
				Collection<String> locationList = groupLocations.get(block.linkedGroup);
				linkLocations = locationList.toArray(new String[0]);
			}
		}

		
		int numOfColumns = block.getHeaders().size();
		for (int i = 0; i < block.numOfRows; i++) {
			Row valueRow = sheet.createRow(rowNumber);
			
			for (int j = 0; j < numOfColumns; j++) {
				Column col = block.columns.get(j);
				String value = col.valueList.get(i);
				CellStyle cellStyle = null;
				// 如果是链接列
				if (j == block.linkColumn) {
					cellStyle = excelWriter.getLinkStyle();
				} else if (col.style == Column.LABEL_STYLE) {
					cellStyle = getLabelStyle();
				} else {
					cellStyle = getTextStyle();
				}
				
				if (block.categoryRows.contains(i)) {
					cellStyle = excelWriter.getCategoryStyle();
				}
				
				Cell cell = CellUtil.createCell(valueRow, startColumn + j, value, cellStyle);
				if (j == block.linkColumn) {
					// 不应该会是null，保险起见
					if (linkLocations != null) {
						Hyperlink link = excelWriter.workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_DOCUMENT);
						link.setAddress(linkLocations[i]);
					    cell.setHyperlink(link);
					}
				}
			}
			
			rowNumber++;
			this.rows++;
		}
	}

	protected void writeBlock(TextBlock block) {
		int rowNumber = startRow;
		Row row = sheet.createRow(rowNumber);
		CellUtil.createCell(row, startColumn, block.label, getLabelStyle());
		CellUtil.createCell(row, startColumn + 1, StringUtils.EMPTY, getLabelStyle());
		fillAndMerge(row, startColumn + 2, startColumn + block.textColumns, getLabelStyle());
		
		rowNumber++;
		rows++;
		
		if (StringUtils.isEmpty(block.text))
			return;
		
		if (block.newRow) {
			String[] lines = block.text.split("\n");
			for (String line : lines) {
				row = sheet.createRow(rowNumber);
				CellUtil.createCell(row, startColumn, StringUtils.EMPTY, getLabelStyle());
				CellUtil.createCell(row, startColumn + 1, line, getTextStyle());
				fillAndMerge(row, startColumn + 2, startColumn+ block.textColumns, getTextStyle());
				rowNumber++;
				rows++;
			}
		} else {
			row = sheet.createRow(rowNumber);
			CellUtil.createCell(row, startColumn, StringUtils.EMPTY, getLabelStyle());
			CellUtil.createCell(row, startColumn + 1, block.text, getTextStyle());
			fillAndMerge(row, startColumn + 2, startColumn + block.textColumns, getTextStyle());
			rowNumber++;
			rows++;
		}
	}
	
	private void fillAndMerge(Row row, int col, int endCol, CellStyle style) {
		for (int i = 0; i < (endCol - col + 1); i++) {
			CellUtil.createCell(row, col + i, "", style);
		}
		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), col - 1, endCol));
	}
}
