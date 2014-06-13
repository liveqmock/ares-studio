/**
 * 源程序名称：InOutCollectionPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.core.excel;

import org.eclipse.emf.ecore.EAttribute;

import com.hundsun.ares.studio.core.excel.handler.BooleanEMFPropertyHandler;
import com.hundsun.ares.studio.jres.service.Service;

/**
 * 专门处理输入输出结果集的PropertyHandler
 * @author sundl
 *
 */
public class InOutCollectionPropertyHandler extends BooleanEMFPropertyHandler {

	/**
	 * @param eAttribute
	 */
	public InOutCollectionPropertyHandler(EAttribute eAttribute) {
		super(eAttribute, BooleanEMFPropertyHandler.STYLE_CN);
	}
	
	@Override
	public void setValue(Object obj, String value) {
		if (obj instanceof Service) {
			super.setValue(((Service) obj).getInterface(), value);
		}
	}

	@Override
	public String getValue(Object obj) {
		if (obj instanceof Service) {
			return super.getValue(((Service) obj).getInterface());
		}
		return super.getValue(false);
	}
}
