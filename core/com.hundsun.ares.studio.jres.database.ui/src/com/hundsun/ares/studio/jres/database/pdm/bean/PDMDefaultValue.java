/**
 * 源程序名称：PDMDefaultValue.java
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
public class PDMDefaultValue {

	private String name;//类型名
	private String chineseName;//类型中文名
	private String comment;//说明
	private Map<String,String> languageDefaultValue = new LinkedHashMap<String,String>();
	/**
	
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
		return languageDefaultValue.keySet();
		
	}
	
	public String getDefaultValueByLanguage(String language) {
		return languageDefaultValue.get(language);
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


}
