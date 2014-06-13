/**
 * 源程序名称：UserOptionControlBizProperty.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Button;

/**
 * 用户选项-- 业务属性
 * @author sundl
 *
 */
public class UserOptionControlBizProperty extends UserOptionControl {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setControl(java.lang.Object)
	 */
	@Override
	public void setControl(Object control) {
		super.setControl(control);
		((Button)control).setSelection(StringUtils.equals(defaultValue, "true"));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setOptionValue(java.util.Map)
	 */
	@Override
	public void setOptionValue(Map<String, Object> option) {
		super.setOptionValue(option);
		if(null != getControl()){
			option.put(getID(), ((Button)control).getSelection()?"true" : "false");
		}
	}
}
