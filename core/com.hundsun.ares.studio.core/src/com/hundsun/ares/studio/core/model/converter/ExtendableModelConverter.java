/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import java.io.InputStream;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hundsun.ares.studio.core.model.extendable.ExtendModelConverterManager;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.util.PersistentUtil;

/**
 * 供可扩展模型的
 * @author sundl
 */
public abstract class ExtendableModelConverter implements IModelConverter{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#read(java.io.InputStream, java.lang.Object)
	 */
	public final void read(InputStream in, Object info) throws Exception {
		Document doc = PersistentUtil.readDocument(in);
		Element root = doc.getRootElement();
		readNonExtendedProperties(root, info);
		ExtendModelConverterManager.getDefault().readExtendMap((IExtendAbleModel)info, root);
	}
	
	/**
	 * 读取非扩展部分。
	 * @param root xml根节点
	 * @param info 对象
	 */
	protected abstract void readNonExtendedProperties(Element root, Object info);

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#read(java.io.InputStream)
	 */
	public Object read(InputStream in) throws Exception {
		throw new Exception("this converter does not support the operation read(inputstream); use read(inputstream, object) instead. ");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#write(java.io.OutputStream, java.lang.Object)
	 */
	public final void write(OutputStream out, Object info) throws Exception {
		Document doc = PersistentUtil.createHSDocument();
		Element root = doc.getRootElement();
		writeNonExtendedProperties(root, info);
		ExtendModelConverterManager.getDefault().writeExtendMap((IExtendAbleModel)info, root);
		PersistentUtil.writeDocument(out, doc);
	}
	
	/**
	 * 写入非扩展部分
	 * @param root xml根节点
	 * @param info 对象
	 */
	protected abstract void writeNonExtendedProperties(Element root, Object info);

}
