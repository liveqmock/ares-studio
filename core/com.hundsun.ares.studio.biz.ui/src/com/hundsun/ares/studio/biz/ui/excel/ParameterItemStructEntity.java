/**
 * 源程序名称：ParameterItemStructEntity.java
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
 * @author yanwj06282
 *
 */
public class ParameterItemStructEntity {

	/**
	 * @see BizResExcelStructEntity#hyperlinkKey
	 * 如果要实现超链接，这两个属性必须一致
	 */
	private String hyprelinkKey;
	
	/**
	 * 参数条目中的条目
	 */
	private List<String> item = new ArrayList<String>();
	
	public ParameterItemStructEntity() {
	}

	public ParameterItemStructEntity(String hyprelinkKey ,List<String> item) {
		this.hyprelinkKey = hyprelinkKey;
		this.item = item;
	}

	public String getHyprelinkKey() {
		return hyprelinkKey;
	}

	public void setHyprelinkKey(String hyprelinkKey) {
		this.hyprelinkKey = hyprelinkKey;
	}

	public List<String> getItem() {
		return item;
	}

	public void setItem(List<String> item) {
		this.item = item;
	}
	
}
