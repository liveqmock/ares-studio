/**
 * 源程序名称：ExtendedPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;

/**
 * @author sundl
 *
 */
public class ExtendedPropertyHandler implements IPropertyHandler {

	private IBasicExtendPropertyDescriptor descriptor;
	
	public ExtendedPropertyHandler(IBasicExtendPropertyDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		if (obj instanceof ExtensibleModel) {
			descriptor.setValue(((ExtensibleModel) obj), value);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue(java.lang.Object)
	 */
	@Override
	public String getValue(Object obj) {
		if (obj instanceof ExtensibleModel) {
			return descriptor.getValue((ExtensibleModel) obj);
		}
		return null;
	}

}
