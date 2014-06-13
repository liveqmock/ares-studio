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

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;


/**
 * 
 * @author sundl
 *
 */
public class GroupWriter implements Writer {

	protected Group group;
	protected Workbook workbook;
	protected Sheet sheet;
	private ExcelWriter excelWriter;
	
	private List<String> areaLocations = new ArrayList<String>();
	
	/** 是否创建新Sheet页,如果为false，则在当前workbook中查找 */
	public boolean newSheet = true;
	/** 开始行 */
	public int startRow = 1;
	
	public GroupWriter(Group group, ExcelWriter excelWriter) {
		this.group = group;
		this.excelWriter = excelWriter;
		this.workbook = excelWriter.workbook;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.export.writer.Writer#write()
	 */
	@Override
	public void write() {
		if (newSheet) {
			sheet = workbook.createSheet(group.name);
			sheet.setColumnWidth(0, 3*256);
			if (group.columnWidth == null)
				group.columnWidth = new int[] {15, 25, 15, 35, 10, 15};
			
			for (int i = 0; i < group.columnWidth.length; i++) {
				sheet.setColumnWidth(i + 1, group.columnWidth[i] * 256);
			}
			
			// 用这种方式改变默认样式，实现所有单元格默认的颜色填充
			CellStyle defaultStyle = sheet.createRow(0).createCell(0).getCellStyle();
			defaultStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			defaultStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			defaultStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		} else {
			sheet = workbook.getSheet(group.name);
		}
		
		
		if (group.index != -1) {
			workbook.setSheetOrder(group.name, group.index);
		}
		
		for (Area area : group.areas) {
			AreaWriter writer = getAreaWriter(area, sheet, startRow);
			writer.write();
			String location = String.format("'%s'!%s", group.name, "B" + (startRow + 1));
			areaLocations.add(location);
			startRow += (writer.rows + 1); // +1: area后空一行
		}
		
		// group --> locations
		@SuppressWarnings("unchecked")
		Multimap<String, String> groupLocations = (Multimap<String, String>) excelWriter.context.get(ExcelWriter.CONTEXT_GROUP_AREA_LOCATIONS);
		if (groupLocations == null) {
			groupLocations = ArrayListMultimap.create();
			excelWriter.context.put(ExcelWriter.CONTEXT_GROUP_AREA_LOCATIONS, groupLocations);
		}
		groupLocations.putAll(group.name, areaLocations);
		
	}
	
	public List<String> getAreaLocations() {
		return areaLocations;
	}
	
	protected AreaWriter getAreaWriter(Area area, Sheet sheet, int startRow) {
		return new AreaWriter(area, sheet, startRow, excelWriter);
	}
	
}
