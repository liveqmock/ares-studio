/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import com.hundsun.ares.studio.core.IARESProjectProperty;

/**
 * 单个属性的扩展字段
 * @author sundl
 */
public abstract class SingleExtensionFieldEditor extends ExtensionFieldEditor{
 
	protected IARESProjectProperty properties;
	private String id;
	private String name;
	
	public void init(IARESProjectProperty properties) {
		this.properties = properties;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
}
