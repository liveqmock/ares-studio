/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.usermacro.UserMacroItem;

/**
 * @author wangxh
 *
 */
public class UserMacroAssistantFilter implements IAssistantFilter {
	
	/**类型，只能从以下几种选取*/
	private String type = "";
	
	public static final String ATOM_TYPE = "原子";
	public static final String LOGIC_TYPE = "逻辑";
	public static final String PROCEDURE_TYPE = "过程";
	
	public UserMacroAssistantFilter(String type){
		this.type = type;
	}
	
	@Override
	public boolean filter(Object obj) {
		if(obj instanceof UserMacroItem){
			UserMacroItem item = (UserMacroItem)obj;
			if(StringUtils.contains(item.getType(),type)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void init() {
	}

}
