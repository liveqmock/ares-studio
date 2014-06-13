/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.model.extendable.ExtendAbleModel;

/**
 * 项目属性
 * @author sundl
 */
public class ARESProjectProperty extends ExtendAbleModel implements IARESProjectProperty{

	private String id;
	private String name;
	private String provider;
	private String version;
	private String contact;
	private String note;
	private String publisher;
	private String pubTime;
	
	private Map<String, Object> properties = new HashMap<String, Object>();
	
	private List<IDependenceDescriptor> dependencies = new ArrayList<IDependenceDescriptor>();
	
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
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public IDependenceDescriptor[] getDependencies() {
		return this.dependencies.toArray(new IDependenceDescriptor[this.dependencies.size()]);
	}
	
	public List<IDependenceDescriptor> getDependencisList() {
		return this.dependencies;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getVersion()
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String name) {
		String value = getString(name);
		if (value != null)
			return Boolean.parseBoolean(value);
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getBoolean(java.lang.String, boolean)
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name);
		return value == null ? defaultValue : Boolean.parseBoolean(value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getInt(java.lang.String)
	 */
	public int getInt(String name) {
		String value = getString(name);
		return value == null ? 0 : Integer.parseInt(value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getInt(java.lang.String, int)
	 */
	public int getInt(String name, int defaultValue) {
		String value = getString(name);
		return value == null ? defaultValue : Integer.parseInt(value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getString(java.lang.String)
	 */
	public String getString(String name) {
		Object value = properties.get(name);
		String str = value == null ? null : String.valueOf(value);
		return str;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getString(java.lang.String, java.lang.String)
	 */
	public String getString(String name, String defaultValue) {
		String value = getString(name);
		if (value == null)
			value = defaultValue;
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#setValue(java.lang.String, java.lang.String)
	 */
	public void setValue(String name, String value) {
		properties.put(name, value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#setValue(java.lang.String, int)
	 */
	public void setValue(String name, int value) {
		properties.put(name, String.valueOf(value));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#setValue(java.lang.String, boolean)
	 */
	public void setValue(String name, boolean value) {
		properties.put(name, String.valueOf(value));
	}
		
	public Map<String, Object> getProperties() {
		return properties;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getPublisher()
	 */
	public String getPublisher() {
		return publisher;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProjectProperty#getPubTime()
	 */
	public String getPubTime() {
		return pubTime;
	}
	
}
