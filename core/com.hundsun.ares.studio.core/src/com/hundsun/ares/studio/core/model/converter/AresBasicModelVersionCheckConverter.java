/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import java.io.InputStream;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.Element;

import com.hundsun.ares.studio.core.util.PersistentUtil;

/**
 * 带版本检查的序列化器
 * @author maxh
 */
public abstract class AresBasicModelVersionCheckConverter implements IModelConverter{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#read(java.io.InputStream, java.lang.Object)
	 */
	public void read(InputStream in, Object info) throws Exception {
		Element root = readRoot(in);
		if(root != null){
			read(root, info);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#read(java.io.InputStream)
	 */
	public Object read(InputStream in) throws Exception {
		Element root = readRoot(in);
		if(root != null){
			return read(root);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#write(java.io.OutputStream, java.lang.Object)
	 */
	public void write(OutputStream out, Object info) throws Exception {
		Document doc = PersistentUtil.createHSDocument(getRealVersion());
		write(doc.getRootElement(), info);
		PersistentUtil.writeDocument(out, doc);
	}
	
	/**
	 * 获取root节点 获取过程中如果有版本不兼容的问题则会抛出异常
	 * @param in
	 * @return
	 * @throws Exception
	 */
	Element readRoot(InputStream in) throws Exception {
		Element root = PersistentUtil.readRoot(in);
		if(root != null){
			if(!root.getName().equals(PersistentUtil.HS_DOC) || root.attributeValue(PersistentUtil.HS_DOC_VERSION) == null){
				throw new NoDocVersionInfoException();
			}
			String[] docVersions = root.attributeValue(PersistentUtil.HS_DOC_VERSION).split("\\.");
			if(docVersions.length != 4){
				throw new AresDocVersionException(getRealVersion(),root.attributeValue(PersistentUtil.HS_DOC_VERSION));
			}
			docVersions[0] = docVersions[0].replaceAll("v", "").replaceAll("V", "");
			if(!docVersions[0].equals(String.valueOf(getVersionOne())) || 
				!docVersions[1].equals(String.valueOf(getVersionTwo())) || 
				!docVersions[2].equals(String.valueOf(getVersionThree()))){
				throw new AresDocVersionException(getRealVersion(),root.attributeValue(PersistentUtil.HS_DOC_VERSION));
			}
		}
		return root;
	}
	
	/**
	 * 获得序列器文档版本
	 * @return
	 */
	public String getRealVersion(){
		return "V"   + String.valueOf(getVersionOne()) + "."
					 + String.valueOf(getVersionTwo()) + "."
					 + String.valueOf(getVersionThree()) + "."
					 + String.valueOf(getVersionFour());
	}
	
	
	/**
	 * 获得版本号第1位
	 * @return
	 */
	public abstract int getVersionOne();
	/**
	 * 获得版本号第2位
	 * @return
	 */
	public abstract int getVersionTwo();
	/**
	 * 获得版本号第3位
	 * @return
	 */
	public abstract int getVersionThree();
	/**
	 * 获得版本号第4位
	 * @return
	 */
	public abstract int getVersionFour();
	/**
	 * 传入的root节点是hsdoc节点
	 * @param root
	 * @param info
	 * @throws Exception
	 */
	public abstract void read(Element root, Object info) throws Exception;
	/**
	 * 传入的root节点是hsdoc节点
	 * @param root
	 * @return
	 * @throws Exception
	 */
	public abstract Object read(Element root) throws Exception;
	/**
	 * 传入的root节点是hsdoc节点
	 * @param root
	 * @param info
	 * @throws Exception
	 */
	public abstract void write(Element root, Object info) throws Exception;
}
