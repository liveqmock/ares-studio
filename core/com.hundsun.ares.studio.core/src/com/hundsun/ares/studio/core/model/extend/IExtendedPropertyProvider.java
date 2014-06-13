/**
 * 源程序名称：IExtendPropertyProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * 扩展属性提供器.扩展属性的结构一般是这样的：
 * 一个Provider对应提供一个EObject放在Data2这个map中，这个EObject的一些属性就作为具体的扩展属性
 * @author sundl
 */
public interface IExtendedPropertyProvider {
	
	/**
	 * 是否启用
	 * @param element 可能为null
	 * @param clazz
	 * @return
	 */
	boolean isEnabled(IARESElement element, EClass clazz);

	/**
	 * 扩展对应的对象在Data2这个Map中的key
	 * @return
	 */
	String getKey();
	
	/**
	 * 创建一个用于编辑的对象，这个对象将存储在{@link ExtensibleModel#getData2()}的map中
	 * @return
	 */
	EObject createMapValueObject();
	
	/**
	 * 返回提供哪些属性
	 * @return
	 */
	IBasicExtendPropertyDescriptor[] getExtendProperties();
	
}
