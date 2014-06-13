/**
 * 源程序名称：InterfacePropertyHandlerFactory.java
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
import com.hundsun.ares.studio.core.excel.handler.BooleanEMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * @author sundl
 *
 */
public class InterfacePropertyHandlerFactory extends ExtensiblePropertyHandlerFactory implements IPropertyHandlerFactory {
	
	public static InterfacePropertyHandlerFactory INSTANCE = new InterfacePropertyHandlerFactory();
	
	/** 接口对象一般属性的handler */
	public static Map<String, IPropertyHandler> INTERFACE_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();

	static {
		// 基于Interface的一套属性和处理器列表； 子类尽量不要直接像这些map中加数据，以免子类之间互相干扰
		INTERFACE_PROPERTY_HANDLERS.put("对象号", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID));
		// 确定不需处理的属性，用NullPropertyHandler避免报错
		INTERFACE_PROPERTY_HANDLERS.put("版本号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("更新日期", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("服务名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("函数名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("过程名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("函数说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("过程说明", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("服务名称", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("结果集返回", new BooleanEMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		INTERFACE_PROPERTY_HANDLERS.put("接口标志", new EMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG));
		INTERFACE_PROPERTY_HANDLERS.put("说明", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("功能号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("所属数据库", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("操作角色", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求类别", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求编号", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("需求级别", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("业务描述", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		//2014-01-28 modified by zhuyf 添加功能描述（导入对外接口，时文档中有此格式信息）
		INTERFACE_PROPERTY_HANDLERS.put("功能描述", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		//2014-01-28 modified by zhuyf 添加业务说明（导入对外接口，时文档中有此格式信息）
		INTERFACE_PROPERTY_HANDLERS.put("业务说明", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("修改记录", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("变量", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("内部变量", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("流程变量", NullPropertyHandler.INSTANCE);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return INTERFACE_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return BizPackage.Literals.ARES_OBJECT;
	}
	
}
