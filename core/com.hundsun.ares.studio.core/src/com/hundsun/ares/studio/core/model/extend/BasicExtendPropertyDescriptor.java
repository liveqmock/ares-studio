/**
 * 源程序名称：BasicExtendPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;


/**
 * 
 * @author sundl
 */
public abstract class BasicExtendPropertyDescriptor implements IBasicExtendPropertyDescriptor {

	private String category;
	private String displayName;
	private String description;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getCategory()
	 */
	@Override
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

}
