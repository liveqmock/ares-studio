/**
 * 源程序名称：AbstractEMPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * @author gongyf
 *
 */
public abstract class AbstractEMPropertyDescriptor implements
		IExtensibleModelPropertyDescriptor {

	private String category;
	private String displayName;
	private String description;
	private EStructuralFeature structuralFeature;
	
	
	/**
	 * @param structuralFeature
	 */
	public AbstractEMPropertyDescriptor(EStructuralFeature structuralFeature) {
		super();
		this.structuralFeature = structuralFeature;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @param structuralFeature the structuralFeature to set
	 */
	public void setStructuralFeature(EStructuralFeature structuralFeature) {
		this.structuralFeature = structuralFeature;
	}
	
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#getStructuralFeature()
	 */
	@Override
	public EStructuralFeature getStructuralFeature() {
		return structuralFeature;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelPropertyDescriptor#isDerived()
	 */
	@Override
	public boolean isDerived() {
		return false;
	}
}
