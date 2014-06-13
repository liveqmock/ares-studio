/**
 * 源程序名称：Data1PropertyProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * 
 * @author sundl
 */
public class Data1PropertyProvider implements IPropertyHandler {
	
	private String key;
	
	public Data1PropertyProvider(String key) {
		this.key = key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		if (obj instanceof ExtensibleModel) {
			((ExtensibleModel) obj).getData().put(key, value);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.IPropertyHandler#getValue(java.lang.Object)
	 */
	@Override
	public String getValue(Object obj) {
		if (obj instanceof ExtensibleModel) {
			return ((ExtensibleModel) obj).getData().get(key);
		}
		return null;
	}

}
