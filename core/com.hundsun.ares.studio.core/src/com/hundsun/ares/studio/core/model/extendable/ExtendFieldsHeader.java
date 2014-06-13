/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/**
 * 
 */
package com.hundsun.ares.studio.core.model.extendable;

import com.hundsun.ares.studio.core.model.ICreateInstance;

/**
 * 一个拓展字段的信息
 * 包括字段ID
 * 字段名称
 * 字段类型
 * 字段长度等等
 * @author maxh
 */
public class ExtendFieldsHeader implements  ICreateInstance<ExtendFieldsHeader>, Cloneable{
	
	
	/**
	 * 普通文本型
	 */
	public static final int TYPE_COMMON = 0;
	/**
	 * 下拉列表型
	 */
	public static final int TYPE_COMBO = 1;
	
	
	public ExtendFieldsHeader() {
		// TODO Auto-generated constructor stub
	}
	
	public ExtendFieldsHeader(String id,String text){
		this.id = id;
		this.text = text;
	}
	
	public ExtendFieldsHeader(String id,String text,String type,String width,String value){
		this.id = id;
		this.text = text;
		this.type = type.equals(TYPE_COMBO)?1:0;
		try {
			this.width = Integer.valueOf(width);
		} catch (Exception e) {
			this.width = 100;
		}
		this.values = value;
	}

	/**
	 * 字段id，map中的key
	 */
	private String id = "";
	/**
	 * 字段名称 列头文本
	 */
	private String text = "";
	/**
	 * 字段类型 文本框还是下拉列表
	 */
	private int type = TYPE_COMMON;
	/**
	 * 字段宽度 列宽
	 */
	private int width = 100;
	/**
	 * 如果是下拉列表型 下拉列表的值
	 */
	private String values = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICreateInstance#getNewInstance()
	 */
	public ExtendFieldsHeader getNewInstance() {
		try {
			return (ExtendFieldsHeader)clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return new ExtendFieldsHeader();
	}
	
	
	

}
