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
 * 用户xml配置对话框中的group
 * 
 * @author maxh
 */
public class DialogInterfaceGroup {
	/* 分组名 */
	private String groupName ="";
	/* 是否启用*/
	private boolean isUse =true;
	
	private List<DialogInterfaceItem> lstMenuInterfaceItem = new ArrayList<DialogInterfaceItem>();
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the lstMenuInterfaceItem
	 */
	public List<DialogInterfaceItem> getLstMenuInterfaceItem() {
		return lstMenuInterfaceItem;
	}
	/**
	 * @param lstMenuInterfaceItem the lstMenuInterfaceItem to set
	 */
	public void setLstMenuInterfaceItem(List<DialogInterfaceItem> lstMenuInterfaceItem) {
		this.lstMenuInterfaceItem = lstMenuInterfaceItem;
	}
	/**
	 * @return the isUse
	 */
	public boolean isUse() {
		return isUse;
	}
	/**
	 * @param isUse the isUse to set
	 */
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
}
