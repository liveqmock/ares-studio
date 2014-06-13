/**
 * 源程序名称：IServiceExtentionPoint.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.core.service;

import com.hundsun.ares.studio.core.ARESCore;

/**
 * @author gongyf
 *
 */
public interface IServiceExtentionPoint {
	public static final String NAMESPACE = ARESCore.PLUGIN_ID;
	public static final String EP_Name = "services";
	public static final String EP_Attribute_ID = "id";
	public static final String EP_Attribute_Type = "type";
	public static final String EP_Attribute_Factory = "factory";
}
