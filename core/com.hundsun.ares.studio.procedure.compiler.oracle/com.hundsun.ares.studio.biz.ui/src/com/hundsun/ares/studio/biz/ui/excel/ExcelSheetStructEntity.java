/**
 * 源程序名称：ExcelSheetStructEntity.java
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
 * Excel sheet 实体
 * @author yanwj06282
 *
 */
public class ExcelSheetStructEntity {

	//模块中文名前缀
	private String cnamePrefix = "";
	//模块中文名
	private String sheetCName ;
	//模块英文名
	private String sheetEName;
	
	/**
	 * sheet的序号，从0开始，0表示第一个sheet(菜单页一般设置为0,如果是模块属性，需要放在最后，可以设置一个很大的值)
	 * 如果为-1，则按顺序生成，不做特殊处理
	 */
	private int sheetIndex = -1;
	
	/**
	 * 默认列宽
	 */
	private int defaultColumnWidth = 6000;
	
	/**
	 * 列宽，此数组长度必须和menuItems长度保持一致，负责取默认列宽
	 * 
	 */
	private int[] columnWidths;
	
	/**
	 * 每个sheet中包含N个资源实体
	 * 
	 */
	private List<BizResExcelStructEntity> entityList = new ArrayList<BizResExcelStructEntity>();

	public String getCnamePrefix() {
		return cnamePrefix;
	}

	public void setCnamePrefix(String cnamePrefix) {
		this.cnamePrefix = cnamePrefix;
	}

	public String getSheetCName() {
		return sheetCName;
	}

	public void setSheetCName(String sheetCName) {
		this.sheetCName = sheetCName;
	}

	public String getSheetEName() {
		return sheetEName;
	}

	public void setSheetEName(String sheetEName) {
		this.sheetEName = sheetEName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public List<BizResExcelStructEntity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<BizResExcelStructEntity> entityList) {
		this.entityList = entityList;
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
