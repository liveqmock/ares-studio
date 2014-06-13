/**
 * 源程序名称：ParameterPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler2;

/**
 * 参数模型的PropertyHandler, 特殊处理某些列。
 * @author sundl
 *
 */
public class ParameterPropertyHandler extends EMFPropertyHandler implements IPropertyHandler2{

	private IARESProject project;
	
	/**
	 * @param feature
	 */
	public ParameterPropertyHandler(EStructuralFeature feature) {
		super(feature);
	}

	@Override
	public String getValue(Object obj) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.IPropertyHandler2#setProject(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public void setProject(IARESProject project) {
		this.project = project;
	}
	
}
