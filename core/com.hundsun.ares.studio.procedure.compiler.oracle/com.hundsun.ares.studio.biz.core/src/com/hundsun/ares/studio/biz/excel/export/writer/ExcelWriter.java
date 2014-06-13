/**
 * 源程序名称：ExcelWriter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.biz.excel.export.TableBlock.Column;

/**
 * @author sundl
 *
 */
public class ExcelWriter {
	
	/** 上下文, 可以用于下面的areawriter, blockwriter之间交互， 慎用。 */
	Map<Object, Object> context = new HashMap<Object, Object>();
	
	public static final String CONTEXT_GROUP_AREA_LOCATIONS = "group_link_area_locations";
	
	private CellStyle labelStyle;
	private CellStyle textStyle;
	private CellStyle backgroundStyle;
	private CellStyle linkStyle;
	
	private Font font = null;
	
	protected Workbook workbook;
	protected List<Group> groups;
	protected CreationHelper helper;
	
	public ExcelWriter(Workbook wb, List<Group> groups) {
		this.workbook = wb;
		this.groups = groups;
		helper = workbook.getCreationHelper();
	}
	
	public void write() {
		for (Group group : groups) {
			Writer writer = createGroupWriter(group);
			if (writer != null) {
				writer.write();
			}
		}
	}
	
	protected Writer createGroupWriter(Group group) {
		return new GroupWriter(group, this);
	}
	
	public CellStyle getLabelStyle() {
		if (labelStyle == null) {
			labelStyle = workbook.createCellStyle();
			labelStyle.setAlignment(CellStyle.ALIGN_CENTER);
			labelStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			labelStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			labelStyle.setBorderTop(CellStyle.BORDER_THIN);
			labelStyle.setBorderBottom(CellStyle.BORDER_THIN);
			labelStyle.setBorderLeft(CellStyle.BORDER_THIN);
			labelStyle.setBorderRight(CellStyle.BORDER_THIN);
			labelStyle.setFont(getFont());
		}
		return labelStyle;
	}
	
	public CellStyle getTextStyle() {
		if (textStyle == null) {
			textStyle = workbook.createCellStyle();
			textStyle.setAlignment(CellStyle.ALIGN_LEFT);
			textStyle.setBorderTop(CellStyle.BORDER_THIN);
			textStyle.setBorderBottom(CellStyle.BORDER_THIN);
			textStyle.setBorderLeft(CellStyle.BORDER_THIN);
			textStyle.setBorderRight(CellStyle.BORDER_THIN);
			textStyle.setFont(getFont());
		}
		return textStyle;
	}
	
	public CellStyle getBackgroundStyle() {
		if (backgroundStyle == null) {
			backgroundStyle = workbook.createCellStyle();
			backgroundStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			backgroundStyle.setBorderTop(CellStyle.BORDER_NONE);
			backgroundStyle.setBorderBottom(CellStyle.BORDER_NONE);
			backgroundStyle.setBorderLeft(CellStyle.BORDER_NONE);
			backgroundStyle.setBorderRight(CellStyle.BORDER_NONE);
		}
		return backgroundStyle;
	}
	
	public CellStyle getLinkStyle() {
		if (linkStyle == null) {
			linkStyle = workbook.createCellStyle();
			
			Font hlink_font = workbook.createFont();
		    hlink_font.setUnderline(Font.U_SINGLE);
		    hlink_font.setColor(IndexedColors.BLUE.getIndex());
		    linkStyle.setFont(hlink_font);
		    
		    linkStyle.setAlignment(CellStyle.ALIGN_LEFT);
			linkStyle.setBorderTop(CellStyle.BORDER_THIN);
			linkStyle.setBorderBottom(CellStyle.BORDER_THIN);
			linkStyle.setBorderLeft(CellStyle.BORDER_THIN);
			linkStyle.setBorderRight(CellStyle.BORDER_THIN);
		}
		return linkStyle;
	}
	
	public Font getFont() {
		if (font == null) {
			 font = workbook.createFont();
			 font.setFontHeightInPoints((short)10);
			 font.setFontName("Courier New");
		}
		return font;
	}
	
	public static void main(String[] args) {
		try {
			
//			FileInputStream in = new FileInputStream("D:\\template.xls");
//			HSSFWorkbook twb = new HSSFWorkbook(in);
//			in.close();
//			Sheet tSheet = twb.getSheetAt(0);
//			for (int i=0;i<100;i++) {
//				CellStyle style = tSheet.getColumnStyle(i);
//				if (style != null) {
//					System.out.println(style.getFillForegroundColor());;
//				} else {
//					System.out.println("null");
//				}
//				
//				System.out.println("hidden: " + i + ": " + tSheet.isColumnHidden(i));
//			}
//			
//			Sheet sheet2 = twb.cloneSheet(0);
//			
//			twb.removeSheetAt(0);
//			System.out.println(twb.getSheetAt(0));
//			
//			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");
//		    twb.write(fileOut);
//		    fileOut.close();
		    
		    
			//System.out.println(sheet2.get);
			
			Workbook wb = new HSSFWorkbook();
			TableBlock block = new TableBlock();
			Column col = block.addColumn("名称");
			col.style = 1;
			col.valueList.add("名称1");

			col = block.addColumn("名称222");
			col.style = 2;
			col.valueList.add("名称2222");
			
			block.numOfRows = 1;
			
			Area area = new Area();
			area.blocks.add(block);
			
			Group group = new Group();
			group.areas.add(area);
			group.name = "test";
			
			List<Group> groups = new ArrayList<Group>();
			groups.add(group);
			
			ExcelWriter writer = new ExcelWriter(wb, groups);
			writer.write();
			
			
			FileOutputStream fileOut = new FileOutputStream("D:\\workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
