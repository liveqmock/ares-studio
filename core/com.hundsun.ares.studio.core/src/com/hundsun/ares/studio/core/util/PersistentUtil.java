/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

/**
 * dom4j工具类
 * @author maxh
 */
public class PersistentUtil {

	// longger
	private static final Logger persistentLogger = Logger.getLogger("PersistentUtil");
	static {
		// 如想屏蔽logger的信息，在此处去掉下面语句的注释
		persistentLogger.setLevel(Level.OFF);
	}

	// 元素名定义
	public static final String HS_DOC = "hsdoc"; // 根元素的元素名

	// 属性名定义
	/**
	 * HS文档版本，为考虑版本兼容问题而设，暂时不用，默认为1.0.0 20081117 sundl 改默认值为1.1.0
	 */
	public static final String HS_DOC_VERSION = "version";

	/**
	 * 读取xml中根元素。
	 * 
	 * @param is
	 *            输入流。
	 * @return 根元素。
	 */
	public static Element readRoot(InputStream is) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			// e.printStackTrace();
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
			return document == null ? null : document.getRootElement();
		}
		return document == null ? null : document.getRootElement();
	}
	
	/**
	 * 读取xml中根元素。
	 * 
	 * @param is
	 *            输入流。
	 * @return 根元素。
	 */
	public static Element readRoot(InputStream is,StringBuffer errorBuffer) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			errorBuffer.append(e.getMessage());
			errorBuffer.append("\n");
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
			return document == null ? null : document.getRootElement();
		}
		return document == null ? null : document.getRootElement();
	}
	
	/**
	 * 读取xml中根元素。
	 * 
	 * @param is
	 *            输入流。
	 * @return 根元素。
	 */
	public static Element readRoot(Reader is,StringBuffer errorBuffer) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			errorBuffer.append(e.getMessage());
			errorBuffer.append("\n");
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
			return document == null ? null : document.getRootElement();
		}
		return document == null ? null : document.getRootElement();
	}
	
	/**
	 * 读取xml中根元素。
	 * 
	 * @param is
	 *            输入流。
	 * @return 根元素。
	 */
	public static Element readRoot(InputStream is,StringBuffer errorBuffer,String enCoding) {
		SAXReader reader = new SAXReader();
		reader.setEncoding(enCoding);
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			errorBuffer.append(e.getMessage());
			errorBuffer.append("\n");
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
			return document == null ? null : document.getRootElement();
		}
		return document == null ? null : document.getRootElement();
	}

	public static Document readDocument(IFile file) {
		try {
			return readDocument(file.getContents());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return createHSDocument();
	}

	public static Document readDocument(InputStream is) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
			return document;
		} catch (DocumentException e) {
			 e.printStackTrace();
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
		}
		return createHSDocument();
	}
	
	public static Document readDocument(Reader is) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
			return document;
		} catch (DocumentException e) {
			// e.printStackTrace();
			persistentLogger.log(Level.SEVERE, "读取xml文档出错：", e);
		}
		return createHSDocument();
	}

	/**
	 * 从文件中读入xml根元素， 如果出错，则返回null。
	 * 
	 * @param file
	 * @return
	 */
	public static Element readRoot(IFile file) {
		try {
			return readRoot(file.getContents());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 将构造好的Document写入文件
	 */
	public static void writeDocumentToFile(IFile file, Document doc) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeDocument(bos, doc);
		try {
			file.setContents(new ByteArrayInputStream(bos.toByteArray()), true, true, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获得attribule值数组
	 */
	public static String[] getAttributeValue(Element element, String[] keys) {
		String[] attributeValues = new String[keys.length];
		if (null != element) {

			for (int i = 0; i < keys.length; i++) {
				if (null != element.attribute(keys[i])){
//					attributeValues[i] = transFromXmlValue(element.attributeValue(keys[i]));
					attributeValues[i] = element.attributeValue(keys[i]);
				}
				else
					attributeValues[i] = "";
			}
		}
		return attributeValues;
	}

	/**
	 * 创建一个Document对象，并为其添加默认的hs文档的根元素。
	 * 
	 * @return
	 */
	public static Document createHSDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(HS_DOC);

		// 20081117 sundl 改默认值为1.1.0
		root.addAttribute(HS_DOC_VERSION, "1.1.0");
		return document;
	}

	/**
	 * 创建一个Document对象，并为其添加默认的hs文档的根元素，并且增加注释信息
	 * 
	 * @return
	 */
	public static Document createHSDocumentWithComment(String comment) {
		Document document = DocumentHelper.createDocument();
		if (!StringUtils.isBlank(comment)) {
			document.addComment("\r\n" + comment);
		}
		Element root = document.addElement(HS_DOC);
		
		root.addAttribute(HS_DOC_VERSION, "1.1.0");
		return document;
	}
	
	/**
	 * 创建一个Document对象，并为其添加默认的hs文档的根元素，可指定版本
	 * 
	 * @return
	 */
	public static Document createHSDocument(String ver) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(HS_DOC);
		root.addAttribute(HS_DOC_VERSION, ver);
		return document;
	}

	/**
	 * 获取指定文档的版本
	 * 
	 * @param doc
	 * @return
	 */
	public static String getHSDocumentVersion(Document doc) {
		return doc.getRootElement().attributeValue(HS_DOC_VERSION);
	}

	/**
	 * 在给定的父元素中创建一个新的XML元素，属性名数组和属性值的数组长度必须一一对应。
	 * 
	 * @param parent
	 *            父元素
	 * @param name
	 *            新建元素的名字
	 * @param keys
	 *            新建元素的属性名数组
	 * @param values
	 *            新建元素的值数组
	 * @return 新创建的元素 生成<Field Name="op_branch_no" Type="HsBranchNo" Width=""
	 *         Scale=""/>格式的xml
	 */
	public static Element createElement(Element parent, String name, String[] keys, String[] values) {
		if (keys.length != values.length) {
			persistentLogger.warning("key数组和value数组的长度不相等!");
			return null;
		}
		
		String[] checkedValues = new String[values.length];
		for(int i = 0; i < values.length; i++) {
			checkedValues[i] = values[i];
		}
		
		Element newElement = parent.addElement(name);
		for (int i = 0; i < keys.length; i++) {
			// 保证value不为null
			newElement.addAttribute(keys[i], StringUtil.getStringSafely(values[i]));
		}
		return newElement;
	}

	public static Element createElementWithText(Element parent, String name, String[] keys, String[] values) {
		if (keys.length != values.length) {
			persistentLogger.warning("key数组和value数组的长度不相等!");
			return null;
		}

		Element newElement = parent.addElement(name);
		for (int i = 0; i < keys.length; i++) {
			// 保证value不为null
			Element childElement = newElement.addElement(keys[i]);
			childElement.setText(values[i]);
		}
		return newElement;
	}

	public static Element createSingleElement(Element parent, String name, String key, String value) {

		Element newElement = parent.addElement(name);
		newElement.setText(StringUtil.getStringSafely(value));
		return newElement;
	}

	/**
	 * 在给定的父元素中添加一个新的子元素，在这个子元素中再添加与keys数组对应的子元素，子元素的Text值为对应的values数组元素值。
	 * keys与values数组必须一一对应。
	 * 
	 * @param parent
	 *            父元素
	 * @param name
	 *            子元素名
	 * @param keys
	 *            javaBean属性名列表。
	 * @param values
	 *            javaBean属性的字符串值列表。
	 * @return
	 */
	public static Element createElementWithTextSupport(Element parent, String name, String[] keys, String[] values) {
		if (keys.length != values.length) {
			persistentLogger.warning("key数组和value数组的长度不相等!");
			return null;
		}

		Element newElement = parent.addElement(name);
		for (int i = 0; i < keys.length; i++) {
			if (isEmpty(keys[i])) {
				continue;
			} else {
				Element child = newElement.addElement(keys[i]);
				child.setText(StringUtil.getStringSafely(values[i]));
			}
		}
		return newElement;
	}

	/**
	 * 在指定元素中读取与keys数组对应的Text值。
	 * 
	 * @param parent
	 *            父元素
	 * @param name
	 *            子元素，既指定元素。
	 * @param keys
	 *            javaBean属性名数组。
	 * @return 返回属性字符串值数组。
	 */
	public static String[] readElementWithTextSupport(Element parent, String name, String[] keys) {
		Element elm = parent.element(name);
		String[] values = new String[keys.length];
		if (null != elm) {
			for (int i = 0; i < keys.length; i++) {
				Element child = elm.element(keys[i]);
				if (null != child) {
					values[i] = child.getText();
				}
			}
		}
		return values;
	}

	/**
	 * 输出给定的Document对象。
	 * 
	 * @param os
	 *            输出流。
	 * @param document
	 *            xml文档对象
	 */
	public static void writeDocument(OutputStream os, Document document) {
		// 修改为需要转义
		writeDocument(os, document, true);
	}
	
	/**
	 * 输出给定的Document对象。
	 * 
	 * @param os
	 *            输出流。
	 * @param document
	 *            xml文档对象
	 * @param isEscapeText
	 *            是否转义节点文本
	 */
	public static void writeDocument(OutputStream os, Document document, boolean isEscapeText) {
		OutputFormat format2 = OutputFormat.createPrettyPrint();
		format2.setTrimText(false);
		// OutputFormat format = new OutputFormat();
		format2.setEncoding("UTF-8");
		// format.setEncoding("GB2312");
		try {
			XMLWriter writer = new XMLWriter(os, format2);
			writer.setEscapeText(isEscapeText);
			writer.write(document);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 输出给定的Document对象。
	 * <br>去掉空行
	 * @param os
	 *            输出流。
	 * @param document
	 *            xml文档对象
	 */
	public static void writeDocumentWithoutNewLine(OutputStream os, Document document) {
		OutputFormat format2 = OutputFormat.createPrettyPrint();
		format2.setTrimText(false);
		format2.setNewlines(false);
		format2.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(os, format2) {

				/* (non-Javadoc)
				 * @see org.dom4j.io.XMLWriter#escapeAttributeEntities(java.lang.String)
				 */
				@Override
				protected void writeEscapeAttributeEntities(String txt) throws IOException  {
					if (txt != null) {
//						String escapedText = transToXmlValue(txt);
//			            writer.write(escapedText);
						writer.write(txt);
			        }
				}
				
			};
			writer.setEscapeText(false);
			writer.write(document);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 给定的文档写入文件。
	 * 
	 * @param file
	 *            IFile类型，文件
	 * @param document
	 *            xml文档对象
	 */
	public static void writeDocument(IFile file, Document document) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeDocument(bos, document);
		try {
			file.setContents(new ByteArrayInputStream(bos.toByteArray()), true, false, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
//
//	/**
//	 * 如果给定的字符串为null，将null值转换为长度为0的字符串;
//	 * 
//	 * @param s
//	 *            需要转换的字符串
//	 * @return 如果不为null，返回字符串本身；否则返回长度为0的字符串。
//	 */
//	public static String convertString(String s) {
//		if (s == null) {
//			return "";
//		}
//		return s;
//	}
//
	/**
	 * 检查字符串是否为空。
	 * 
	 * @param target
	 * @return 若字符串对象为null或其内容为空，或只有空白字符，则返回true。
	 */
	public static boolean isEmpty(String target) {
		if (null == target || target.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
//
//	public static String switchNull(String s) {
//		return s == null ? "" : s;
//	}
//
//	//效率太差
//	private static String transToXmlValue2(String str) {
//		if(str == null || str.equals("")) {
//			return "";
//		}
//		str= str.replace("<", "&lt");
//		str=str.replace(">", "&gt");
//		str=str.replace("&", "&amp");
//		str=str.replace("\"", "&quot");
//		String str=str.replace("\'", "&apos");
//		return str;
//	}
//	/**
//	 * 把特殊字符转义
//	 * <br>效率很关键，如果效率不够高，再次优化本函数
//	 * <br>10w次  50长度，转义符在10个左右，耗时 400ms左右
//	 */
//	public static String transToXmlValue(String str) {
//		if(str == null || str.equals("")) {
//			return "";
//		}
//		StringBuilder sb = new StringBuilder();
//		int length = str.length();
//		for(int i = 0; i < length; i ++) {
//			char ch = str.charAt(i);
//			if(ch == '<') {
//				sb.append("&lt;");
//			}else if (ch == '>') {
//				sb.append("&gt;");
//			}else if(ch == '&') {
//				sb.append("&amp;");
//			}else if(ch =='\"') {
//				sb.append("&quot;");
//			}else if(ch =='\'') {
//				sb.append("&apos;");
//			}else {
//				sb.append(ch);
//			}
//		}
//		return sb.toString();
//	}
//	
//	/**
//	 * 恢复到正常字符
//	 * <br>效率很关键，如果效率不够高，再次优化本函数
//	 * <br>10w次  50长度，转义符在10个左右，耗时 400ms左右
//	 * @param str
//	 * @return
//	 */
//	private static String transFromXmlValue(String str) {
//		if(str == null || str.equals("")) {
//			return "";
//		}
//		StringBuilder sb = new StringBuilder();
//		
//		int length = str.length();
//		for(int i = 0; i < length; i ++) {
//			char ch = str.charAt(i);
//			if(ch == '&') {
//				if(i + 5 < length) {//quot;的长度是5
//					if(checkTransformed(str, "&quot;", i )) {
//						sb.append('\"');
//						i = i +5;
//						continue;
//					}else if(checkTransformed(str, "&apos;", i )) {
//						sb.append('\'');
//						i = i +5;
//						continue;
//					}
//				}
//				if(i + 4 < length){//amp;的长度是4
//					if(checkTransformed(str, "&amp;", i )) {
//						sb.append('&');
//						i = i +4;
//						continue;
//					}
//				}
//				if(i + 3 < length){//lt;的长度是3
//					if(checkTransformed(str, "&lt;", i )) {
//						sb.append('<');
//						i = i +3;
//						continue;
//					}else if(checkTransformed(str, "&gt;", i )) {
//						sb.append('>');
//						i = i +3;
//						continue;
//					}
//				}else {
//					sb.append(ch);
//				}
//			}else {
//				sb.append(ch);
//			}
//
//		}
//		return sb.toString();
//	}
//	
//	/**
//	 * 检查是否是转义过的字符串
//	 * @return
//	 */
//	private static boolean checkTransformed(String allString, String transString, int index ) {
//		int length = transString.length();
//		if(index + length < allString.length()) {
//			for(int i = 0 ; i < length; i++) {
//				if(allString.charAt(index + i) != transString.charAt(i)) {
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	
//	
//	
//	
//	public static void main(String[] a) {
//		long startTime=System.currentTimeMillis();   //获取开始时间   
//		
//		doSomeThing();  //测试的代码段   
//		long endTime=System.currentTimeMillis(); //获取结束时间   
//		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");   
//		
//		
//		
//		
//		 startTime=System.currentTimeMillis();   //获取开始时间   
//		doSomeThing2();  //测试的代码段   
//		 endTime=System.currentTimeMillis(); //获取结束时间   
//		System.out.println("程序运行时间2： "+(endTime-startTime)+"ms");   
//		
//		String text = "sdfjein<sudhf>sdfgwereurhk&sdhf/'/'sadfsd\"aabd&%#><Ksdf>sdf";
//		String t1 = transToXmlValue(text);
//		String t2 = transFromXmlValue(t1);
//		System.out.println("\naaaa "+t1);  
//		System.out.println("\naaaa "+t2);  
//		System.out.println("\naaaa "+t2.equals(text));  
//		
//		
//	}
//
//	/**
//	 * 
//	 */
//	private static void doSomeThing2() {
//		// TODO Auto-generated method stub
//		for(int i = 0; i < 100000; i++) {
//			 transFromXmlValue("sdfjein&ltsudhf&gtsdfgwereurhk&ampsdhf/&apos/&apossadfsd&quotaabd&amp%#&gt&ltKsdf&gtsdf");
//		}
//	}
//
//	/**
//	 * 
//	 */
//	private static void doSomeThing() {
//		for(int i = 0; i < 100000; i++) {
//			transToXmlValue("sdfjein<sudhf>sdfgwereurhk&sdhf/'/'sadfsd\"aabd&%#><Ksdf>sdf ");
//		}
//		
//	}
	
}
