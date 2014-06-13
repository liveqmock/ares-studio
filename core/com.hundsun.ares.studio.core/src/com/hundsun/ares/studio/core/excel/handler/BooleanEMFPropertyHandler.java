/**
 * 源程序名称：BooleanEMFPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.core.excel.handler;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * @author dollyn
 *
 */
public class BooleanEMFPropertyHandler extends EMFPropertyHandler{
	
	/** get value返回值的style, 是返回Y，否返回N */
	public static final int STYLE_Y_N = 1;
	/** get value返回值的style, 是返回true，否返回false */
	public static final int STYLE_TRUE_FALSE= 2;
	/** get value返回值的style, 是返回‘是’，否返回‘否’ */
	public static final int STYLE_CN = 3;

	private int style = STYLE_Y_N;
	
	/**
	 * @param eAttribute
	 */
	public BooleanEMFPropertyHandler(EAttribute eAttribute, int style) {
		super(eAttribute);
		this.style = style;
	}
	
	public BooleanEMFPropertyHandler(EAttribute eAttribute) {
		super(eAttribute);
	}

	@Override
	public void setValue(Object obj, String value) {
		if (obj instanceof EObject) {
			if (StringUtils.equalsIgnoreCase(value, "y") || StringUtils.equalsIgnoreCase(value, "true") || StringUtils.equalsIgnoreCase(value, "是")) {
				((EObject) obj).eSet(feature, true);
			} else {
				((EObject) obj).eSet(feature, false);
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue()
	 */
	@Override
	public String getValue(Object obj) {
		if (obj instanceof EObject) {
			Object value = ((EObject) obj).eGet(feature);
			if (StringUtils.equalsIgnoreCase(String.valueOf(value), "true")) {
				switch (style) {
				case STYLE_Y_N:
					return "Y";
				case STYLE_TRUE_FALSE:
					return "true";
				case STYLE_CN:
					return "是";
				default:
					break;
				}
			} else {
				switch (style) {
				case STYLE_Y_N:
					return "N";
				case STYLE_TRUE_FALSE:
					return "false";
				case STYLE_CN:
					return "否";
				default:
					break;
				}
			}
		}
		return null;
	}
}
