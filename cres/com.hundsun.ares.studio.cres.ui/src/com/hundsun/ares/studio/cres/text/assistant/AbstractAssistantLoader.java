/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;


/**
 * @author wangxh
 */
public abstract class AbstractAssistantLoader implements IAssistantLoader {
	
	IAssistantFilter filter;

	@Override
	public void setFilter(IAssistantFilter filter) {
		this.filter = filter;
	}
	
	public IAssistantFilter getFilter() {
		return filter;
	}
	
	public String getReplacement(String display){
		return display;
	}
	
	@Override
	public String getDescription(String display) {
		return null;
	}

}
