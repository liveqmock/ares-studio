/**
 * 源程序名称：ParameterStructEntity.java
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
 * 参数实体
 * @author yanwj06282
 *
 */
public class ParameterStructEntity {

	/**
	 * 超链接字段，从0开始，对应条目数组中的下标
	 * 如果这个属性为-1，则表示没有超链接
	 * 
	 */
	private int hyperlinkIndex = 3;
	
	public ParameterStructEntity(List<ParameterItemStructEntity> totoleColumns ,List<String> filterTitles){
		this.totoleColumns = totoleColumns;
		this.filterTitles = filterTitles;
	}
	
	/**
	 * 参数数据数组，第一行是标题
	 */
	private List<ParameterItemStructEntity> totoleColumns = new ArrayList<ParameterItemStructEntity>();
	
	/**
	 * Excel中实际生成的标题，这个数组的顺序将是属性列的顺序
	 * 如果这个数组为空，则全部显示数据
	 */
	private List<String> filterTitles = new ArrayList<String>();

	public List<ParameterItemStructEntity> getTotoleColumns() {
		return totoleColumns;
	}

	public void setTotoleColumns(List<ParameterItemStructEntity> totoleColumns) {
		this.totoleColumns = totoleColumns;
	}

	public List<String> getFilterTitles() {
		return filterTitles;
	}

	public void setFilterTitles(List<String> filterTitles) {
		this.filterTitles = filterTitles;
	}

	public int getHyperlinkIndex() {
		return hyperlinkIndex;
	}

	public void setHyperlinkIndex(int hyperlinkIndex) {
		this.hyperlinkIndex = hyperlinkIndex;
	}
	
}
