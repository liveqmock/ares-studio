/**
 * 源程序名称：HisVersionPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * 修改记录的版本属性的PropertyHandler, 用于处理带v前缀和不带v前缀的兼容。
 * @author sundl
 */
public class HisVersionPropertyHandler extends EMFPropertyHandler {

	/**
	 * @param feature
	 */
	public HisVersionPropertyHandler() {
		super(CorePackage.Literals.REVISION_HISTORY__VERSION);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		super.setValue(obj, value);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue()
	 */
	@Override
	public String getValue(Object obj) {
		String value = super.getValue(obj);
		if (!StringUtils.startsWithIgnoreCase(value, "v")) {
			value = "V" + value;
		}
		return value;
	}
	
}
