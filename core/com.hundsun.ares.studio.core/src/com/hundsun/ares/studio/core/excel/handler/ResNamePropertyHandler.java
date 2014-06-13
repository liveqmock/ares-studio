/**
 * 源程序名称：ResNamePropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * 特殊处理资源英文名的Handler，替换资源名中的'.'字符为'_'
 * @author sundl
 *
 */
public class ResNamePropertyHandler extends EMFPropertyHandler {

	public ResNamePropertyHandler() {
		super(CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
	}

	@Override
	public void setValue(Object obj, String value) {
		if (value != null) {
			value = value.replace('.', '_');
		}
		super.setValue(obj, value);
	}
	
}
