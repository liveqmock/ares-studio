/**
 * 源程序名称：IPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

/**
 * 处理属性
 * @author sundl
 *
 */
public interface IPropertyHandler {
	
	void setValue(Object obj, String value);
	String getValue(Object obj);
}
