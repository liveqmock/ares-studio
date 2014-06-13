/**
 * 源程序名称：PDMStandardField.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：PDM导入时生成导入报告中的(标准字段信息)
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaogc
 *
 */
public class PDMStandardField {
	private String oldName;//原字段名(导入之前的字段名)
	private String oldChineseName;//原中文名
	private String oldBusType;//原业务类型(其实就是原来的真实类型)
	//private String belongTable;//所属表
	private String genName;//导入时产生的字段名(标准字段名称)
	private String newName;//评审之后的字段名(字段改名)
	private String newChineseName;//新中文名
	private String genBusType;//业务数据类型(用户不满足时可以修改newBusType)
	
	private String newBusType;//类型改名(用户不满足genBusType时可以通过修改newBusType来满足)
	private String dictId;//数据字典
	private String oldComment;//旧备注
	private String newComment;//新备注
	private String modefyDesc;//修改说明
	private String importPath;//导入文件的路径
	private List<String> belongTableList = new ArrayList<String>();
	private List<String> bolongSubSystemList =new ArrayList<String>();
	private boolean isChanged = false;//是否已经设置唯一名字
	
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
	 * @return the bolongSubSystemList
	 */
	public List<String> getBolongSubSystemList() {
		return bolongSubSystemList;
	}

	/**
	 * @return the belongTableList
	 */
	public List<String> getBelongTableList() {
		return belongTableList;
	}
	
	/**
	 * @return the oldName
	 */
	public String getOldName() {
		return oldName;
	}
	/**
	 * @param oldName the oldName to set
	 */
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	/**
	 * @return the genName
	 */
	public String getGenName() {
		return genName;
	}
	/**
	 * @param genName the genName to set
	 */
	public void setGenName(String genName) {
		this.genName = genName;
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
	 * @return the oldChineseName
	 */
	public String getOldChineseName() {
		return oldChineseName;
	}
	/**
	 * @param oldChineseName the oldChineseName to set
	 */
	public void setOldChineseName(String oldChineseName) {
		this.oldChineseName = oldChineseName;
	}
	/**
	 * @return the newChineseName
	 */
	public String getNewChineseName() {
		return newChineseName;
	}
	/**
	 * @param newChineseName the newChineseName to set
	 */
	public void setNewChineseName(String newChineseName) {
		this.newChineseName = newChineseName;
	}
	/**
	 * @return the oldBusType
	 */
	public String getOldBusType() {
		return oldBusType;
	}
	/**
	 * @param oldBusType the oldBusType to set
	 */
	public void setOldBusType(String oldBusType) {
		this.oldBusType = oldBusType;
	}
	/**
	 * @return the newBusType
	 */
	public String getNewBusType() {
		return newBusType;
	}
	/**
	 * @param newBusType the newBusType to set
	 */
	public void setNewBusType(String newBusType) {
		this.newBusType = newBusType;
	}
	/**
	 * @return the dictId
	 */
	public String getDictId() {
		return dictId;
	}
	/**
	 * @param dictId the dictId to set
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	/**
	 * @return the belongTable
	 *//*
	public String getBelongTable() {
		return belongTable;
	}
	*//**
	 * @param belongTable the belongTable to set
	 *//*
	public void setBelongTable(String belongTable) {
		this.belongTable = belongTable;
	}*/
	
	/**
	 * @return the modefyDesc
	 */
	public String getModefyDesc() {
		return modefyDesc;
	}
	/**
	 * @param modefyDesc the modefyDesc to set
	 */
	public void setModefyDesc(String modefyDesc) {
		this.modefyDesc = modefyDesc;
	}
	/**
	 * @return the importPath
	 */
	public String getImportPath() {
		return importPath;
	}
	/**
	 * @param importPath the importPath to set
	 */
	public void setImportPath(String importPath) {
		this.importPath = importPath;
	}
	/**
	 * @return the genBusType
	 */
	public String getGenBusType() {
		return genBusType;
	}
	/**
	 * @param genBusType the genBusType to set
	 */
	public void setGenBusType(String genBusType) {
		this.genBusType = genBusType;
	}
	/**
	 * @return the oldComment
	 */
	public String getOldComment() {
		return oldComment;
	}
	/**
	 * @param oldComment the oldComment to set
	 */
	public void setOldComment(String oldComment) {
		this.oldComment = oldComment;
	}
	/**
	 * @return the newComment
	 */
	public String getNewComment() {
		return newComment;
	}
	/**
	 * @param newComment the newComment to set
	 */
	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}
	

}
