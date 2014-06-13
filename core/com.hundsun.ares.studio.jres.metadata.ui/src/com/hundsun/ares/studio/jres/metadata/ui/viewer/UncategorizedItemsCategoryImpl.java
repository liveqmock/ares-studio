/**
 * 源程序名称：UncategorizedItemsCategoryImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * @author gongyf
 *
 */
public class UncategorizedItemsCategoryImpl extends MetadataCategoryImpl {
	
	private MetadataResourceData<?> data;
	
	/**
	 * @param data
	 */
	public UncategorizedItemsCategoryImpl(MetadataResourceData<?> data) {
		super();
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getName()
	 */
	@Override
	public String getName() {
		return "未分组";
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getDescription()
	 */
	@Override
	public String getDescription() {
		return "当前资源中没有分组的条目";
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#setName(java.lang.String)
	 */
	@Override
	public void setName(String newName) {
		// dothing
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String newDescription) {
		// dothing
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl#getItems()
	 */
	@Override
	public EList<MetadataItem> getItems() {
		return new BasicEList<MetadataItem>(MetadataUtil.getUncategorizedItems(data));
	}
}
