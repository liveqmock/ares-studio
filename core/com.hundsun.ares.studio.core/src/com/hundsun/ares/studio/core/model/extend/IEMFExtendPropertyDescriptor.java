/**
 * 源程序名称：IEMFExtendPropertyDescriptor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 基于EMF Feature的扩展属性
 * @author sundl
 */
public interface IEMFExtendPropertyDescriptor extends IBasicExtendPropertyDescriptor {
	
	String getKey();
	
	/**
	 * 编辑的特性
	 * @return
	 */
	EStructuralFeature getStructuralFeature();
	
}
