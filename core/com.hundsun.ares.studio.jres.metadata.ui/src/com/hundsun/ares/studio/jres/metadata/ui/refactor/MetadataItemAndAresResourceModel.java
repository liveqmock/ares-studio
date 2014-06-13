/**
 * 源程序名称：MetadataItemAndAresResourceModel.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import com.hundsun.ares.studio.internal.core.ARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;

/**
 * @author qinyuan
 *
 */
public class MetadataItemAndAresResourceModel {

	private MetadataItem item;
	private ARESResource res;
	
	/**
	 * @param item
	 * @param res
	 */
	public MetadataItemAndAresResourceModel(MetadataItem item, ARESResource res) {
		super();
		this.item = item;
		this.res = res;
	}
	/**
	 * @return the item
	 */
	public MetadataItem getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(MetadataItem item) {
		this.item = item;
	}
	/**
	 * @return the res
	 */
	public ARESResource getRes() {
		return res;
	}
	/**
	 * @param res the res to set
	 */
	public void setRes(ARESResource res) {
		this.res = res;
	}

}
