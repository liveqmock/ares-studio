/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.module.gencode.util;

/**
 * 模块代码生成资源统计信息
 * @author qinyuan
 *
 */
public class ModuleResourceStatisticsInfo {
	
	public ModuleResourceStatisticsInfo(String objectID,String name,String cName,String desc) {
		this.objectID = objectID;
		this.name = name;
		this.cName = cName;
		this.desc = desc;
	}
	
	/**
	 * @return the objectID
	 */
	public String getObjectID() {
		return objectID;
	}
	/**
	 * @param objectID the objectID to set
	 */
	public void setObjectID(String objectID) {
		this.objectID = objectID;
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
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}
	/**
	 * @param cName the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
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
	 * 对象号
	 */
	private String objectID;
	/**
	 * 英文名
	 */
	private String name;
	/**
	 * 中文名
	 */
	private String cName;
	/**
	 * 说明
	 */
	private String desc;

}
