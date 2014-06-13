/**
 * 源程序名称：SourceAttributeReferenceImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * 用于对象中一个代码文本内部存在引用
 * @author gongyf
 *
 */
public class SourceAttributeReferenceImpl extends ReferenceImpl {
	
	private EObject object;
	private EAttribute attribute;
	private int pos;
	private int length;
	
	/**
	 * @param object
	 * @param attribute
	 */
	public SourceAttributeReferenceImpl(String type, EObject object, EAttribute attribute, int pos, int length) {
		super();
		this.type = type;
		this.object = object;
		this.attribute = attribute;
		this.pos = pos;
		this.length = length;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#getValue()
	 */
	@Override
	public String getValue() {
		return StringUtils.substring(String.valueOf(object.eGet(attribute)), pos, pos + length);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.model.core.impl.ReferenceImpl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		String source = String.valueOf(object.eGet(attribute));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.substring(source, 0, pos));
		sb.append(value);
		sb.append(StringUtils.substring(source, pos + length));
		object.eSet(attribute, sb.toString());
		
	}
}
