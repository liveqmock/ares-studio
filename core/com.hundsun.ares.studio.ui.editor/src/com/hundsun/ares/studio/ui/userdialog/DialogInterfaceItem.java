/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/**
 * 
 */
package com.hundsun.ares.studio.ui.userdialog;

/**
 * 用户xml配置对话框中的控件
 * @author maxh
 */
public class DialogInterfaceItem {
	private String lableName = "";
	private String swtType = "";
	private String id = "";
	private String value = "";

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *         the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the lableName
	 */
	public String getLableName() {
		return lableName;
	}

	/**
	 * @param lableName
	 *         the lableName to set
	 */
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	/**
	 * @return the swtType
	 */
	public String getSwtType() {
		return swtType;
	}

	/**
	 * @param swtType
	 *         the swtType to set
	 */
	public void setSwtType(String swtType) {
		this.swtType = swtType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
