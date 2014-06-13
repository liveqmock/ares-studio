/**
 * 源程序名称：VarPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.atom.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.atom.excel;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;

/**
 * 内部变量的PropertyHandlerFactory
 * @author sundl
 *
 */
public class VarPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {

	public static final VarPropertyHandlerFactory INSTANCE = new VarPropertyHandlerFactory();
	
	public static Map<String, IPropertyHandler> VAR_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		// 内部变量
		VAR_PROPERTY_HANDLERS.put("变量", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__FLAGS));
		VAR_PROPERTY_HANDLERS.put("变量名", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID));
		VAR_PROPERTY_HANDLERS.put("参数名", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__ID));
		VAR_PROPERTY_HANDLERS.put("类型", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__TYPE));
		VAR_PROPERTY_HANDLERS.put("参数类型", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__TYPE));
		VAR_PROPERTY_HANDLERS.put("说明", new EMFPropertyHandler(BizPackage.Literals.PARAMETER__NAME));
		VAR_PROPERTY_HANDLERS.put("缺省值", NullPropertyHandler.INSTANCE);
		VAR_PROPERTY_HANDLERS.put("长度", NullPropertyHandler.INSTANCE);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return VAR_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return AtomPackage.Literals.INTERNAL_PARAM;
	}

}
