/**
 * 源程序名称：TableField.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author liaogc
 *
 */
public class PDMTableField {

	private String newName ="";//表字段新名
	private String name = "";//表字段名
	private String table = "";//表字段所属表名
	private String desc = "";//表字段说明
	private String type = "";//原始类型
	private String busType = "";//原始类型对应的业务类型
	private String subSystem = "";//表字段所属字系统
	private boolean isChanged = false;//是否已经设置唯一名字
	
	

	

	private String chineseName = "";
	private String mandatory = "";
	private Set<String> beLongTable = new HashSet<String>();
	
	/**
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * @param isChanged the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * @return the beLongOtherTable
	 */
	public Set<String> getBeLongTable() {
		return beLongTable;
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
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the chineseName
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * @param chineseName the chineseName to set
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}

	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}


	/**
	 * @return the mandatory
	 */
	public String getMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * @param newName the newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}

	/**
	 * @return the subSystem
	 */
	public String getSubSystem() {
		return subSystem;
	}

	/**
	 * @param subSystem the subSystem to set
	 */
	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}



}
