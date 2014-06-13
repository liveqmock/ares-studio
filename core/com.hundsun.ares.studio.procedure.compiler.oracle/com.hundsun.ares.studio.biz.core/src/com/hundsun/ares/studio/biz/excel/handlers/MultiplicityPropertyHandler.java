/**
 * 源程序名称：MultiplicityPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.Multiplicity;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;

/**
 * @author sundl
 *
 */
public class MultiplicityPropertyHandler extends EMFPropertyHandler{
	
	private static final Pattern PATTERN = Pattern.compile("\\[[01]\\S+[1n]\\]");

	/**
	 * @param eAttribute
	 */
	public MultiplicityPropertyHandler(EAttribute eAttribute) {
		super(eAttribute);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		if (value == null)
			return;
		
		if (PATTERN.matcher(value).matches()) {
			value = value.substring(0, 2) + ".." + value.substring(value.length() - 2, value.length());
		}
		
		// 如果不是EMF对象，什么都不做
		if (obj instanceof EObject) {
			Multiplicity m = Multiplicity.get(value);
			if (m == null) {
				m = Multiplicity.ONE_ONE;
			}
			((EObject) obj).eSet(eAttribute, m);
		}
	}

}
