/**
 * 源程序名称：IPropertyHandler2.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * PropertyHandler的增强接口，某些时候get, set还需要IARESProject等信息,所以实现这个接口可以由框架
 * 把IARESProject对象注入进去。
 * @author sundl
 */
public interface IPropertyHandler2 extends IPropertyHandler{
	
	void setProject(IARESProject project);

}
