/**
 * 源程序名称：ObjectPropertyHandlerFactory.java
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
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

/**
 * @author sundl
 *
 */
public class ObjectPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {
	
	public static final ObjectPropertyHandlerFactory INSTANCE = new ObjectPropertyHandlerFactory();

	private static Map<String, IPropertyHandler> OBJECT_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();

	static {
		OBJECT_PROPERTY_HANDLERS.putAll(EMFPropertyHandler.BASIC_HANDLERS);
		OBJECT_PROPERTY_HANDLERS.put("对象名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("对象中文名", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("对象中文名", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("对象描述", EMFPropertyHandler.DESCRIPTION_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("说明", EMFPropertyHandler.DESCRIPTION_PROPERTY_HANDLER);
		OBJECT_PROPERTY_HANDLERS.put("修改记录", NullPropertyHandler.INSTANCE);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return OBJECT_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return BizPackage.Literals.ARES_OBJECT;
	}

}
