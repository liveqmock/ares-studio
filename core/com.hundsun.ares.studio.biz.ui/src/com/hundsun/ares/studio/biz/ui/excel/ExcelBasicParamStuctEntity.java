/**
 * 源程序名称：ExcelCellStuctEntity.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：yanwj06282
 */
package com.hundsun.ares.studio.biz.ui.excel;

/**
 * @author yanwj06282
 *
 */
public class ExcelBasicParamStuctEntity {

	/**
	 * cell的值
	 */
	private String value;
	/**
	 * cell长度，1个长度对应excel的一个cell,超过1个会在excel中合并单元格显示
	 */
	private int length = 1;
	/**
	 * cell的高度，1个长度对应excel的一个cell,超过1个会在excel中合并单元格显示
	 * 如果key和value单元格的高度不一致，以key的高度为准
	 */
	private int width = 1;
	
	public ExcelBasicParamStuctEntity(String value ,int length , int width) {
		this.value = value;
		this.length = length;
		this.width = width;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
}
