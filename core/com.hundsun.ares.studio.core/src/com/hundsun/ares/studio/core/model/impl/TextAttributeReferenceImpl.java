/**
 * 源程序名称：TextAttributeReferenceImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * @author gongyf
 *
 */
public class TextAttributeReferenceImpl extends ReferenceImpl {
	
	private EObject object;
	private EAttribute attribute;
	
	/**
	 * @param object
	 * @param attribute
	 */
	public TextAttributeReferenceImpl(String type, EObject object, EAttribute attribute) {
		super();
		this.type = type;
		this.object = object;
		this.attribute = attribute;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#getValue()
	 */
	@Override
	public String getValue() {
		return String.valueOf(object.eGet(attribute));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		object.eSet(attribute, value);
	}
}
