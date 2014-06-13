/**
 * 源程序名称：PDMBusinessDataType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liaogc
 *
 */
public class PDMBusinessDataType {

	
	private String typeName ;//类型名
	private String oldTypeName ;//类型旧名
	private String typeChineseName;//类型中文名
	private String standardTypeName;//标准类型
	private String length;//长度
	private String precision ;//精度
	private String defaultValue ;//默认值
	private String comment;//说明
	private List<String> belongStandardFieldList = new ArrayList<String>();//所属标准字段
	private List<String> subSyses = new ArrayList<String>();//所属子系统

	/**
	 * @return the subSyses
	 */
	public List<String> getSubSyses() {
		return subSyses;
	}
	/**
	 * @return the oldTypeName
	 */
	public String getOldTypeName() {
		return oldTypeName;
	}
	/**
	 * @param oldTypeName the oldTypeName to set
	 */
	public void setOldTypeName(String oldTypeName) {
		this.oldTypeName = oldTypeName;
	}
	/**
	 * @return the belongStandardFieldList
	 */
	public List<String> getBelongStandardFieldList() {
		return belongStandardFieldList;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the typeChineseName
	 */
	public String getTypeChineseName() {
		return typeChineseName;
	}
	/**
	 * @param typeChineseName the typeChineseName to set
	 */
	public void setTypeChineseName(String typeChineseName) {
		this.typeChineseName = typeChineseName;
	}
	/**
	 * @return the standardTypeName
	 */
	public String getStandardTypeName() {
		return standardTypeName;
	}
	/**
	 * @param standardTypeName the standardTypeName to set
	 */
	public void setStandardTypeName(String standardTypeName) {
		this.standardTypeName = standardTypeName;
	}
	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return precision;
	}
	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
