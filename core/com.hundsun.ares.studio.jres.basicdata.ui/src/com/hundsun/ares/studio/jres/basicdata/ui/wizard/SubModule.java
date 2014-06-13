/**
 * 
 */
package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import org.apache.commons.lang.StringUtils;

/**
 * @author yanwj06282
 *
 */
public class SubModule {
	private String moduleEName;
	private String moduleCName;
	private String resEName;
	private String resCName;
	private String relationTable;
	private String type;
	private String fileName;
	
	public SubModule(String moduleEName, String moduleCName, String resEName,
			String resCName, String relationTable, String type , String fileName) {
		this.moduleEName = moduleEName;
		this.moduleCName = moduleCName;
		this.resEName = resEName;
		this.resCName = resCName;
		this.relationTable = relationTable;
		this.type = type;
		this.fileName = fileName;
	}
	
	public String getModuleEName() {
		return moduleEName;
	}
	public String getModuleCName() {
		return moduleCName;
	}
	public String getResEName() {
		if (StringUtils.isBlank(resEName)) {
			return "r"+ImportBasicdataOperation.getEnglishName(resCName);
		}
		return resEName;
	}
	public String getResCName() {
		return resCName;
	}
	public String getRelationTable() {
		return relationTable;
	}
	public String getType() {
		return type;
	}
	
	public String getFileName() {
		return fileName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubModule) {
			return StringUtils.equals(((SubModule) obj).getResCName(), this.resCName) &&
			StringUtils.equals(((SubModule) obj).getResEName(), this.resEName)  &&
			StringUtils.equals(((SubModule) obj).getModuleEName(), this.moduleEName) &&
			StringUtils.equals(((SubModule) obj).getModuleCName(), this.moduleCName)&&
			StringUtils.equals(((SubModule) obj).getRelationTable(), this.relationTable)&&
			StringUtils.equals(((SubModule) obj).getType(), this.type);
		}
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		return this.resCName.hashCode();
	}
}
