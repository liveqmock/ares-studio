/**
 * 源程序名称：SelectionEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wangbin
 *
 */
public abstract class OracleUserPrivilegeSelectionCellEditor extends DialogCellEditor {

	/**
	 * 待选列表标题.
	 */
	private String fromText = "";
	
	/**
	 * 已选列表标题.
	 */
	private String toText = "";

	/**
	 * @return the fromText
	 */
	public String getFromText() {
		return fromText;
	}

	/**
	 * @param fromText the fromText to set
	 */
	public void setFromText(String fromText) {
		this.fromText = fromText;
	}

	/**
	 * @return the toText
	 */
	public String getToText() {
		return toText;
	}

	/**
	 * @param toText the toText to set
	 */
	public void setToText(String toText) {
		this.toText = toText;
	}

	public OracleUserPrivilegeSelectionCellEditor(Composite parent,String fromText, String toText) {
		super(parent);
		setFromText(fromText);
		setToText(toText);
	}

}
