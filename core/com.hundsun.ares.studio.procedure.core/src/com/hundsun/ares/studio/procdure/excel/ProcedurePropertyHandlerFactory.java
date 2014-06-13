/**
 * 源程序名称：ProcedurePropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.procedure.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.procdure.excel;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.handlers.InterfacePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.procdure.ProcdurePackage;

/**
 * @author sundl
 *
 */
public class ProcedurePropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {

	public static final ProcedurePropertyHandlerFactory INSTANCE = new ProcedurePropertyHandlerFactory();
	
	public static Map<String, IPropertyHandler> PROCEDURE_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		PROCEDURE_PROPERTY_HANDLERS.putAll(InterfacePropertyHandlerFactory.INTERFACE_PROPERTY_HANDLERS);
		PROCEDURE_PROPERTY_HANDLERS.put("所属数据库", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__DATABASE));
		PROCEDURE_PROPERTY_HANDLERS.put("所连数据库", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__DATABASE));
		PROCEDURE_PROPERTY_HANDLERS.put("所在数据库", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__DATABASE));
		PROCEDURE_PROPERTY_HANDLERS.put("接口标志", new EMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG));
		//--兼容证券二部
		PROCEDURE_PROPERTY_HANDLERS.put("业务类型", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__BIZ_TYPE));
		PROCEDURE_PROPERTY_HANDLERS.put("定义类型", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__DEFINE_TYPE));
		PROCEDURE_PROPERTY_HANDLERS.put("版本编号", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__CREATE_DATE));
		PROCEDURE_PROPERTY_HANDLERS.put("过程返回类型", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__RETURN_TYPE));
		PROCEDURE_PROPERTY_HANDLERS.put("过程名", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		PROCEDURE_PROPERTY_HANDLERS.put("过程名称", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		PROCEDURE_PROPERTY_HANDLERS.put("过程说明", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		PROCEDURE_PROPERTY_HANDLERS.put("功能说明", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME));
		
		PROCEDURE_PROPERTY_HANDLERS.put("业务处理流程", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__PSEUDO_CODE));
		PROCEDURE_PROPERTY_HANDLERS.put("出错说明", NullPropertyHandler.INSTANCE);
		//--兼容证券二部
		PROCEDURE_PROPERTY_HANDLERS.put("前置代码", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__BEGIN_CODE));
		PROCEDURE_PROPERTY_HANDLERS.put("后置代码", new EMFPropertyHandler(ProcdurePackage.Literals.PROCEDURE__END_CODE));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return PROCEDURE_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return ProcdurePackage.Literals.PROCEDURE;
	}

}
