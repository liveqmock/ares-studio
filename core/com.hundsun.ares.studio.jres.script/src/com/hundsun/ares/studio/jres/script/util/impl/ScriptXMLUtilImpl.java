/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.io.ByteArrayOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil;

/**
 * @author lvgao
 *
 */
public class ScriptXMLUtilImpl  implements IScriptXMLUtil{
	
	public static ScriptXMLUtilImpl instance = new ScriptXMLUtilImpl();
	
	static final Logger console = ConsoleHelper.getLogger();

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#createDoc(java.lang.String)
	 */
	@Override
	public Document createDoc(String rootName) {
		Element root = DocumentFactory.getInstance().createElement(rootName);
		Document doc = DocumentFactory.getInstance().createDocument(root);
		return doc;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#addElement(org.dom4j.Element, java.lang.String, java.lang.String)
	 */
	@Override
	public Element addElement(Element parent, String name, String value) {
		//测试根节点
		Element tmpElement = DocumentFactory.getInstance().createElement(name);
		if(!StringUtils.isEmpty(value)){
			tmpElement.setText(value);
		}
		parent.add(tmpElement);
		
		return tmpElement;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#addElement(org.dom4j.Document, java.lang.String, java.lang.String)
	 */
	@Override
	public Element addElement(Document doc, String name, String value) {
		return addElement(doc.getRootElement(), name, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#addAttr(org.dom4j.Element, java.lang.String, java.lang.String)
	 */
	@Override
	public void addAttr(Element parent, String name, String value) {
		parent.add(DocumentFactory.getInstance().createAttribute(parent, name, value));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#addAttr(org.dom4j.Document, java.lang.String, java.lang.String)
	 */
	@Override
	public void addAttr(Document doc, String name, String value) {
		addAttr(doc.getRootElement(), name, value);
	}



	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptXMLUtil#getContent(org.dom4j.Document, java.lang.String)
	 */
	@Override
	public String getContent(Document doc, String charset) {
		doc.setXMLEncoding(charset);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		XMLWriter writer;
		try {
			writer = new XMLWriter(out, format);
			writer.write(doc);
			return new String(out.toByteArray(),charset);
		} catch (Exception e) {
			e.printStackTrace();
			console.error("生成XML失败，详细信息:"+ e.getMessage());
		}
		return "";
	}

}
