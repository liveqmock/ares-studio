/**
 * 
 */
package com.hundsun.ares.studio.jres.database.resources;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.PersistentUtil;
import com.hundsun.ares.studio.jres.modelconvert.ModelConverterUtils;

/**
 * @author yanwj06282
 *
 */
public class ModelConverterHandle extends
		com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle {

	/**
	 * 
	 */
	public ModelConverterHandle() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleRead(com.hundsun.ares.studio.core.IARESResource, byte[], java.lang.Class)
	 */
	@Override
	public Object handleRead(IARESResource resource, byte[] contents,
			Class<?> clazz) throws Exception {
		{
			String cont = new String(contents,ModelConverterUtils.EMF_SAVE_OPTIONS.get(XMLResource.OPTION_ENCODING).toString());
			Document doc = PersistentUtil.readDocument(new StringReader(cont));
			Element root = doc.getRootElement();
			//去除标记列的扩展
			//对索引的标记扩展列进行处理
			for (Iterator<Element> itIndex = root.elementIterator("indexes"); itIndex.hasNext();) {
				Element indexs = itIndex.next();
				String flag = null;
				for (Iterator<Element> iterator = indexs.elementIterator("data2"); iterator.hasNext();) {
					Element ele = iterator.next();
					if (StringUtils.equals(ele.attributeValue("key"), "Stock3")) {
						ele = ele.element("value");
						if (ele != null) {
							if (StringUtils.equals(ele.attributeValue("type"), "stock3:Stock3IndexProperty")) {
								flag = ele.attributeValue("flag");
								iterator.remove();
							}
						}
					}
				}
				if (StringUtils.isNotBlank(flag)) {
					Attribute attribute = indexs.attribute("mark");
					if (attribute == null) {
						indexs.addAttribute("mark", flag);
					}else {
						attribute.setValue(flag);
					}
				}
				
			}
			//对字段的标记扩展列进行处理
			for (Iterator<Element> itIndex = root.elementIterator("columns"); itIndex.hasNext();) {
				Element column = itIndex.next();
				String flag = null;
				for (Iterator<Element> iterator = column.elementIterator("data2"); iterator.hasNext();) {
					Element ele = iterator.next();
					if (StringUtils.equals(ele.attributeValue("key"), "Stock3")) {
						ele = ele.element("value");
						if (ele != null) {
							if (StringUtils.equals(ele.attributeValue("type"), "stock3:Stock3ColumnProperty")) {
								flag = ele.attributeValue("flag");
								iterator.remove();
							}
						}
					}
				}
				if (StringUtils.isNotBlank(flag)) {
					Attribute attribute = column.attribute("mark");
					if (attribute == null) {
						column.addAttribute("mark", flag);
					}else {
						attribute.setValue(flag);
					}
				}
				
			}
			//去除stock3的扩展
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			writeDocument(bos, doc,true);
			String step = transferre(bos.toByteArray());
			
			String nameSpace = "xmlns:chouse=\"http://www.hundsun.com/ares/studio/jres/database/chouse/1.0.0\"";
			if (StringUtils.indexOf(step, nameSpace) > -1) {
				step = StringUtils.replace(step, "xmlns:stock3=\"http://www.hundsun.com/ares/studio/jres/database/stock3/1.0.0\"", "");
			}else {
				step = StringUtils.replace(step, "xmlns:stock3=\"http://www.hundsun.com/ares/studio/jres/database/stock3/1.0.0\"", nameSpace);
			}
			step = StringUtils.replace(step, "xmlns:stock3=\"http://www.hundsun.com/ares/studio/jres/database/stock3/1.0.0\"", "");
			step = StringUtils.replace(step, "stock3:", "chouse:");
			step = StringUtils.replace(step, "Stock3RevisionHistoryProperty", "RevisionHistoryProperty");
			step = StringUtils.replace(step, "Stock3TableProperty", "StockTableProperty");
			step = StringUtils.replace(step, "Stock3ColumnProperty", "StockColumnProperty");
			step = StringUtils.replace(step, "Stock3IndexProperty", "StockIndexProperty");
			step = StringUtils.replace(step, "Stock3TSRelationProperty", "StockTSRelationProperty");
			step = StringUtils.replace(step, "Stock3HistoryProperty", "StockHistoryProperty");
			step = StringUtils.replace(step, "Stock3ProjectProperty", "StockProjectProperty");
			step = StringUtils.replace(step, "Stock3DBContextProperty", "StockDBContextProperty");
			contents = step.getBytes(ModelConverterUtils.EMF_SAVE_OPTIONS.get(XMLResource.OPTION_ENCODING).toString());
		}
		return getNextHandle().handleRead(resource, contents, clazz);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleWrite(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	public byte[] handleWrite(IARESResource resource, Object info)
			throws Exception {
		return getNextHandle().handleWrite(resource, info);
	}
	
	private  void writeDocument(OutputStream os, Document document, boolean isEscapeText) {
		OutputFormat format2 = createCompactFormat();
		format2.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(os, format2);
			writer.write(document);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private  OutputFormat createCompactFormat() {
        OutputFormat format = new OutputFormat();
        format.setIndent(false);
        format.setNewlines(false);
        format.setTrimText(true);
        return format;
    }
	
	private String transferre(byte[] xml){
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try{
			reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(xml),"UTF-8"));
		if(reader!=null){
			String line = "";
			while((line =reader.readLine())!=null){
				if(StringUtils.endsWith(line, ">")){
					line=line+"\r\n";
				}else{
					line = line+ "&#xD;&#xA;";
				}
				sb.append(line);
				
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
