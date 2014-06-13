/**
 * 源程序名称：ExtendPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.ui.excel;

import org.apache.commons.lang.ObjectUtils;

import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * @author sundl
 *
 */
public class ExtendPropertyHandler implements IPropertyHandler {
	
	private String key;

	public ExtendPropertyHandler(String key) {
		this.key = key;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		if (obj instanceof ExtensibleModel) {
			((ExtensibleModel) obj).getData().put(this.key, value);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue(java.lang.Object)
	 */
	@Override
	public String getValue(Object obj) {
		if (obj instanceof ExtensibleModel) {
			ExtensibleModel model = (ExtensibleModel) obj;
			Object value = model.getData().get(key);
			return ObjectUtils.toString(value);
		}
		return null;
	}

}
