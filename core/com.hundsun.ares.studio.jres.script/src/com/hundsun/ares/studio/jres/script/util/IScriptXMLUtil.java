/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author lvgao
 *
 */
public interface IScriptXMLUtil {

	/**
	 * 指定
	 * @param rootName
	 * @return
	 */
	public Document createDoc(String rootName);
	
	/**
	 * 获取内容
	 * @param doc
	 * @return
	 */
	public String getContent(Document doc,String charset);
	
	/**
	 * 添加子节点
	 * @param parent
	 * @param name
	 * @param value
	 */
	public Element addElement(Element parent,String name,String value);
	
	/**
	 *添加节点
	 * @param doc
	 * @param name
	 * @param value
	 */
	public Element addElement(Document doc,String name,String value);
	
	/**
	 * 添加属性
	 * @param parent
	 * @param name
	 * @param value
	 */
	public void addAttr(Element parent,String name,String value);
	
	/**
	 * 添加属性
	 * @param doc
	 * @param name
	 * @param value
	 */
	public void addAttr(Document doc,String name,String value);
	
}
