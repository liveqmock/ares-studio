/**
 * 源程序名称：JresActionEditableUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editable;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.IAction;

/**
 * @author lvgao
 *
 */
public class JresActionEditableUnit implements IEditableUnit{

	IAction action;
	public JresActionEditableUnit(IAction action){
		this.action = action;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableUnit#setReadonlyStatus(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setReadonlyStatus(String key, Object status) {
		if(StringUtils.equals(KEY_SYSTEM, key)){
			if(EDITABLE_TRUE.equals(status)){
				this.action.setEnabled(true);
			}else{
				this.action.setEnabled(false);
			}
		}
	}

}
