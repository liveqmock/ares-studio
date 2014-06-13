/**
 * 源程序名称：LogicPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.logic.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.logic.excel;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfacePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

/**
 * @author sundl
 *
 */
public abstract class LogicPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {

	public static Map<String, IPropertyHandler> LOGIC_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		LOGIC_PROPERTY_HANDLERS.putAll(InterfacePropertyHandlerFactory.INTERFACE_PROPERTY_HANDLERS);
		LOGIC_PROPERTY_HANDLERS.put("所属数据库", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("服务编号", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("是否复核", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("复核类型", NullPropertyHandler.INSTANCE);
		LOGIC_PROPERTY_HANDLERS.put("复核级数", NullPropertyHandler.INSTANCE);
		
		LOGIC_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE));
		LOGIC_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return LOGIC_PROPERTY_HANDLERS;
	}

}
