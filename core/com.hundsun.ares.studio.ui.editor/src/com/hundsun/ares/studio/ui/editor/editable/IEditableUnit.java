/**
 * 源程序名称：IEditableControlUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：吕高
 */
package com.hundsun.ares.studio.ui.editor.editable;


/**
 * @author lvgao
 *
 */
public interface IEditableUnit {
	
	public static final String KEY_SYSTEM = "系统只读状态";

	public static final String EDITABLE_TRUE = "系统可编辑状态";
	
	public static final String EDITABLE_FALSE = "系统只读状态";
	
	/**
	 * 设置只读状态
	 * @param status
	 */
	public void setReadonlyStatus(String key,Object status);
}
