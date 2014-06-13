package com.hundsun.ares.studio.core.model.extendable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ExtendFieldModel implements IExtendFieldModel {
	private Map<String,String> extendStrings = null;

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extendable.IExtendFieldModel#getExtendString()
	 */
	public Map<String, String> getExtendStrings() {
		if(extendStrings == null){
			extendStrings = new HashMap<String, String>();
		}
		return extendStrings;
	};
	
}
