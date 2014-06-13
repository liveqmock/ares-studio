/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.metadata.ui.model;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * 用于元数据总览列表的表格行对象
 * 
 * @author gongyf
 *
 */
public class MetadataOverviewElement {
	private IARESResource resource;
	private MetadataItem item;
	private boolean conflict;
	
	/**
	 * @param resource
	 * @param item
	 * @param conflict
	 */
	public MetadataOverviewElement(IARESResource resource, MetadataItem item) {
		super();
		this.resource = resource;
		this.item = item;
	}
	
	
	/**
	 * @param conflict the conflict to set
	 */
	public void setConflict(boolean conflict) {
		this.conflict = conflict;
	}


	/**
	 * @return the resource
	 */
	public IARESResource getResource() {
		return resource;
	}
	/**
	 * @return the item
	 */
	public MetadataItem getItem() {
		return item;
	}
	/**
	 * @return the conflict
	 */
	public boolean isConflict() {
		return conflict;
	}
	
	
	
}
