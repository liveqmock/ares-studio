/**
 * 源程序名称：PDMTableResource.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author liaogc
 *
 */
public class PDMTableResource {
	private IARESResource resource;//表资源
	private TableResourceData tableInfo;//表资源中的表模型
	private String subSystem;//表所属子系统
	
	public PDMTableResource(IARESResource resource,TableResourceData tableInfo,String subSystem){
		this.resource = resource;
		this.tableInfo = tableInfo;
		this.subSystem = subSystem;
		
	}
	/**
	 * @return the resource
	 */
	public IARESResource getResource() {
		return resource;
	}
	
	/**
	 * @return the tableInfo
	 */
	public TableResourceData getTableInfo() {
		return tableInfo;
	}
	
	/**
	 * @return the suSystem
	 */
	public String getSubSystem() {
		return subSystem;
	}
	
	
}
