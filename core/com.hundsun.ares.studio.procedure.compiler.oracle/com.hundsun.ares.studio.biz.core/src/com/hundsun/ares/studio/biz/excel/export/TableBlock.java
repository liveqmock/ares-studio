/**
 * 源程序名称：TableBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 表格式的Block
 * @author sundl
 *
 */
public class TableBlock extends Block{
	
	private static Logger logger =  Logger.getLogger(TableBlock.class);
	
	public static class Column{
		public static int LABEL_STYLE = 1;
		public static int TEXT_STYLE = 2;
		public static int LINK_STYLE = 3;
		
		public int style;
		public String header;
		public List<String> valueList = new ArrayList<String>();
	}
	
	public int numOfRows;
	public List<Column> columns = new ArrayList<TableBlock.Column>();
	
	/** 第几列链接， 暂时只支持1列链接到另外一个group的area列表;
	 *  -1代表没有链接列
	 */
	public int linkColumn = -1;
	public String linkedGroup;
	
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		for (Column column : columns) {
			headers.add(column.header);
		}
		return headers;
	}
	
	public Column addColumn(String header) {
		Column c = new Column();
		c.header = header;
		columns.add(c);
		return c;
	}
	
	public void addRow(List<String> row) {
		if (row.size() != columns.size()) {
			logger.error("表头和行的列数不一致");
			return;
		}
		
		for (int i = 0; i < columns.size(); i++) {
			columns.get(i).valueList.add(row.get(i));
		}
		numOfRows++;
	}
	
}
