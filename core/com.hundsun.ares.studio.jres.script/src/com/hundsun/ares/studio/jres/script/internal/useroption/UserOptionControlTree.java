/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.Map;

import org.eclipse.swt.widgets.Text;

/**
 * @author lvgao
 *
 */
public class UserOptionControlTree  extends UserOptionControl{

	@Override
	public void setControl(Object control) {
		super.setControl(control);
		((Text)control).setText(getDefaultValue());
	}
	
	@Override
	public void setOptionValue(Map<String, Object> option) {
		super.setOptionValue(option);
		if(null != getControl()){
			option.put(getID(),((Text)control).getText());
		}
	}
	
}
