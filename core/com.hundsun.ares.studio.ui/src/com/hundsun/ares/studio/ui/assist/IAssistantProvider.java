/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.assist;

/**
 * 智能提示文本框提供器。
 * 
 * @author mawb
 */
public interface IAssistantProvider {
	
	/**
	 * 提供用于智能提示的内容
	 * @return
	 */
	public Object[] getProposals();
	
	/**
	 * 用于实际替换的内容
	 * 
	 * @param obj
	 * @return
	 */
	public String getContent(Object obj);

	/**
	 * 用于智能提示右侧的详细信息
	 * 
	 * @param obj
	 * @return
	 */
	public String getDescription(Object obj);

	/**
	 * 用于显示的内容，一般即为替换内容
	 * 
	 * @param obj
	 * @return
	 */
	public String getLabel(Object obj);
	
}
