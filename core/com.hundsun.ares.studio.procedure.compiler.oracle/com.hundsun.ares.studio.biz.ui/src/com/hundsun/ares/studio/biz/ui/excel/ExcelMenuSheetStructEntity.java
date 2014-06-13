/**
 * 源程序名称：ExcelMenuSheetStructEntity.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.biz.ui.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 菜单页实体
 * @author yanwj06282
 *
 */
public class ExcelMenuSheetStructEntity {
	
	private String sheetName;
	/**
	 * sheet的序号，从0开始，0表示第一个sheet(菜单页一般设置为0,如果是模块属性，需要放在最后，可以设置一个很大的值)
	 * 如果为-1，则按顺序生成，不做特殊处理
	 */
	private int sheetIndex  = 0;
	
	/**
	 * 超链接字段，从0开始，对应条目数组中的下标
	 * 如果这个属性为-1，则表示没有超链接
	 * 
	 */
	private int hyperlinkIndex = 3;
	
	/**
	 * 默认列宽
	 */
	private int defaultColumnWidth = 6000;
	
	/**
	 * 列宽
	 * 本数组的列宽和menuItems内的条目意义对应，如果长度不一样，则取默认长度
	 * 
	 */
	private int[] columnWidths;
	
	/**
	 * 菜单条目
	 */
	private List<ExcelMenuItemEntity> menuItems = new ArrayList<ExcelMenuItemEntity>();

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public List<ExcelMenuItemEntity> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<ExcelMenuItemEntity> menuItems) {
		this.menuItems = menuItems;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getHyperlinkIndex() {
		return hyperlinkIndex;
	}

	public void setHyperlinkIndex(int hyperlinkIndex) {
		this.hyperlinkIndex = hyperlinkIndex;
	}

	public int[] getColumnWidths() {
		return columnWidths;
	}

	public void setColumnWidths(int[] columnWidths) {
		this.columnWidths = columnWidths;
	}

	public int getDefaultColumnWidth() {
		return defaultColumnWidth;
	}

}
