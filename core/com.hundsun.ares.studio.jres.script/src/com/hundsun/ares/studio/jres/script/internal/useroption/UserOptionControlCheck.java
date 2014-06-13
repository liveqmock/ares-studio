/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Button;

/**
 * @author lvgao
 *
 */
public class UserOptionControlCheck  extends UserOptionControl{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		boolean isTrue = StringUtils.equals(defaultValue, "true");
		if(isTrue){
			this.defaultValue = "true";
		}else{
			this.defaultValue = "false";
		}
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
