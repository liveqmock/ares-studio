/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Combo;

/**
 * @author lvgao
 *
 */
public class UserOptionControlCombo  extends UserOptionControl{

	List<String> values = new ArrayList<String>();
	
	List<String> options = new ArrayList<String>();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		super.setValue(value);
		for(String item:StringUtils.split(value, ',')){
			options.add(item);
			if(StringUtils.contains(item, '_')){
				values.add(StringUtils.split(item, '_')[0]);
			}else{
				values.add(item);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		if(values.contains(defaultValue)){
			this.defaultValue = defaultValue;
		}else{
			this.defaultValue = values.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setControl(java.lang.Object)
	 */
	@Override
	public void setControl(Object control) {
		super.setControl(control);
		((Combo)control).setItems(options.toArray(new String[0]));
		int index = values.indexOf(defaultValue);
		if(index >= 0){
			((Combo)control).setText(options.get(index));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setOptionValue(java.util.Map)
	 */
	@Override
	public void setOptionValue(Map<String, Object> option) {
		super.setOptionValue(option);
		if(null != getControl()){
			option.put(getID(), StringUtils.split(((Combo)control).getText(), '_')[0]);
		}
	}
	
}
