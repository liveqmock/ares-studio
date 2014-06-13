/**
 * 源程序名称：NullPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

/**
 * 什么都不做的属性处理，避免报错（处理如版本号等不需要导入的属性）
 * @author sundl
 *
 */
public class NullPropertyHandler implements IPropertyHandler {
	
	public static NullPropertyHandler INSTANCE = new NullPropertyHandler();

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue(java.lang.Object)
	 */
	@Override
	public String getValue(Object obj) {
		return null;
	}

}
