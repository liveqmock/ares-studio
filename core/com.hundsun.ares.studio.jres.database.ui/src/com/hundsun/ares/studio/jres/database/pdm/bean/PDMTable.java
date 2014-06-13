/**
 * 源程序名称：PDMTable.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaogc
 *
 */
public class PDMTable {
	private String packageName;//包名(PDM中的包名)
	private String name;//表名
	private String chineseName;//表中文名
	private String desc;//表说明
	private List<PDMTableColumn>columns = new ArrayList<PDMTableColumn>();//表格中的列
	private List<PDMTableIndex> indexes = new ArrayList<PDMTableIndex>();//表的索引
	/**
	 * @return the indexes
	 */
	public List<PDMTableIndex> getIndexes() {
		return indexes;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the chineseName
	 */
	public String getChineseName() {
		return chineseName;
	}
	/**
	 * @param chineseName the chineseName to set
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the columns
	 */
	public List<PDMTableColumn> getColumns() {
		return columns;
	}
	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
