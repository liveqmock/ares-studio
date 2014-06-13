/**
 * 源程序名称：ParamDescPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author sundl
 *
 */
public class ParamDescPropertyHandler extends ParameterRefPropertyHandler {

	/**
	 * @param targetFeature
	 */
	public ParamDescPropertyHandler(EStructuralFeature targetFeature, EStructuralFeature feature) {
		super(targetFeature, feature);
	}

}
