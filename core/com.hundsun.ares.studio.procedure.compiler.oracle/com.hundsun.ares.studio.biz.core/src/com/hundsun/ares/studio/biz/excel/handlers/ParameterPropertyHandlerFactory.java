/**
 * 源程序名称：ParameterPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;

/**
 * @author sundl
 *
 */
public class ParameterPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {

	public static ParameterPropertyHandlerFactory INSTANCE = new ParameterPropertyHandlerFactory();
	
	/** 参数属性处理对应列表 */
	public static Map<String, IPropertyHandler> PARAM_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();

	static {
		PARAM_PROPERTY_HANDLERS.putAll(BizPropertyHandler.PARAM_BASIC_HANDLERS);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return PARAM_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return BizPackage.Literals.PARAMETER;
	}

}
