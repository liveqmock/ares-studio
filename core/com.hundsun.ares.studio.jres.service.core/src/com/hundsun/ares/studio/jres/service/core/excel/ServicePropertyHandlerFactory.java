/**
 * 源程序名称：ServicePropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.stock
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.core.excel;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfacePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.HisPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.service.ServicePackage;

/**
 * @author sundl
 *
 */
public class ServicePropertyHandlerFactory extends ExtensiblePropertyHandlerFactory implements IPropertyHandlerFactory {
	
	public static final ServicePropertyHandlerFactory INSTANCE = new ServicePropertyHandlerFactory();

	public static Map<String, IPropertyHandler> SERVICE_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();

	static{
		SERVICE_PROPERTY_HANDLERS.putAll(InterfacePropertyHandlerFactory.INTERFACE_PROPERTY_HANDLERS);
		SERVICE_PROPERTY_HANDLERS.put("功能号", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID));
		SERVICE_PROPERTY_HANDLERS.put("功能名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		//2014-01-28 modified by zhuyf 添加英文名（导入对外接口，时文档中有此格式信息）
		SERVICE_PROPERTY_HANDLERS.put("英文名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		SERVICE_PROPERTY_HANDLERS.put("功能中文名", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		SERVICE_PROPERTY_HANDLERS.put("功能名称", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		//现在在服务接口 名字为输入输出结果集
		SERVICE_PROPERTY_HANDLERS.put("输入结果集", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("输出结果集", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("结果集返回", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		
		//兼容性考虑  不删除原来的名字
		SERVICE_PROPERTY_HANDLERS.put("输入是否数组", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("输出是否数组", new InOutCollectionPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		SERVICE_PROPERTY_HANDLERS.put("是否公开", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("是否复核", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("复核类型", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("复核级数", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("业务处理流程", NullPropertyHandler.INSTANCE);
		SERVICE_PROPERTY_HANDLERS.put("账户2.0", NullPropertyHandler.INSTANCE);
		
		SERVICE_PROPERTY_HANDLERS.put("修改记录", new HisPropertyHandler(CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES));
	}
	
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return SERVICE_PROPERTY_HANDLERS;
	}
	
	@Override
	protected EClass geteEClass() {
		return ServicePackage.Literals.SERVICE;
	}
}
