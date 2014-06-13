/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/**
 * 
 */
package com.hundsun.ares.studio.ui.userdialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户xml配置对话框
 * 
 * @author maxh
 */
public class DialogInterfaceXml {
	
	/**
	 * 对话框标题
	 */
	String title = "";
	
	String fileId = "";
	
	private List<DialogInterfaceGroup> lstMenuInterfaceGroup = new ArrayList<DialogInterfaceGroup>();

	/**
	 * @return the lstMenuInterfaceGroup
	 */
	public List<DialogInterfaceGroup> getLstMenuInterfaceGroup() {
		return lstMenuInterfaceGroup;
	}

	/**
	 * @param lstMenuInterfaceGroup the lstMenuInterfaceGroup to set
	 */
	public void setLstMenuInterfaceGroup(List<DialogInterfaceGroup> lstMenuInterfaceGroup) {
		this.lstMenuInterfaceGroup = lstMenuInterfaceGroup;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
}
