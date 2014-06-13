/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.util.List;

import org.eclipse.jface.text.IDocument;

/**
 * @author wangxh
 */
public interface IAssistantLoader {

	/**加载智能提示项*/
	List<String> loadAssitant(String text,IDocument doc, int offset);
	
	/**设置过滤器*/
	void setFilter(IAssistantFilter filter);
	
	/**将提示框显示字符串转换为文档中应替换的字符串*/
	String getReplacement(String display);
	
	/**获取注释信息*/
	String getDescription(String display);
	
}
