/**
 * 源程序名称：IExtendPropertyProvider2.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 通过程序提供相关实现Provider
 * @author sundl
 */
public interface IExtendedPropertyProvider2 {

	/**
	 * 根据提供的Element, EClass计算其包含的扩展属性列表
	 * @param element  可能为null, 为null时具体如何处理自由决定；推荐返回所有
	 * @param clazz Host Eclass
	 * @return
	 */
	IBasicExtendPropertyDescriptor[] getExtendProperties(IARESElement element, EClass clazz);
}
