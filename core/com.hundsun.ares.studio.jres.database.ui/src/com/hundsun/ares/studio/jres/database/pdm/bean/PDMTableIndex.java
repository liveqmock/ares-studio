/**
 * 源程序名称：PDMTableIndex.java
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
public class PDMTableIndex {
	private String name;//索引名
	private boolean unique;//是否唯一
	private boolean cluster;//是否聚族索引
	private List<PDMTableIndexColumn> columns = new ArrayList<PDMTableIndexColumn>();//索引列
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
	 * @return the unique
	 */
	public boolean isUnique() {
		return unique;
	}
	/**
	 * @param unique the unique to set
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	/**
	 * @return the cluster
	 */
	public boolean isCluster() {
		return cluster;
	}
	/**
	 * @param cluster the cluster to set
	 */
	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}
	/**
	 * @return the columns
	 */
	public List<PDMTableIndexColumn> getColumns() {
		return columns;
	}
	
	
}
