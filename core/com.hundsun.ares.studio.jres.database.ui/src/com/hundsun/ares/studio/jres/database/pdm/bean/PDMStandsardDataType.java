/**
 * 源程序名称：PDMStandsardDataType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liaogc
 *
 */
public class PDMStandsardDataType {
	private String typeName;//类型名
	private String typeChineseName;//类型中文名
	private String comment;//说明
	private Map<String,String> languageTypeValue = new LinkedHashMap<String,String>();
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
	
	public Set<String> getLanguages(){
		return languageTypeValue.keySet();
		
	}
	
	public String getTypeValueByLanguage(String language) {
		return languageTypeValue.get(language);
	}

}
