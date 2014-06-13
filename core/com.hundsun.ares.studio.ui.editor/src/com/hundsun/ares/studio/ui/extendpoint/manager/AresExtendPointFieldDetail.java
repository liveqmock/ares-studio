/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint.manager;

/**
 * 拓展点字段描述
 * @author maxh
 *
 */
public class AresExtendPointFieldDetail {
	
	static final public String TEXT_TYPE = "text";
	static final public String COMBO_TYPE = "combo";
	static final public String DATE_TYPE = "date";
	static final public String MULTI_TEXT_SMALL = "multiTextSmall";
	static final public String MULTI_TEXT_NORMAL = "multiTextNormal";
	static final public String MULTI_TEXT_LARGE = "multiTextLarge";
	
	String fieldName = "";
	String showName = "";
	String showPic = "";
	String showControlType = "";
	String value = "";
	boolean showInOutline = false;
	boolean genUi = false;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getShowPic() {
		return showPic;
	}
	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}
	public String getShowControlType() {
		return showControlType;
	}
	public void setShowControlType(String showControlType) {
		this.showControlType = showControlType;
	}
	public boolean isShowInOutline() {
		return showInOutline;
	}
	public void setShowInOutline(boolean showInOutline) {
		this.showInOutline = showInOutline;
	}
	public boolean isGenUi() {
		return genUi;
	}
	public void setGenUi(boolean genUi) {
		this.genUi = genUi;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
