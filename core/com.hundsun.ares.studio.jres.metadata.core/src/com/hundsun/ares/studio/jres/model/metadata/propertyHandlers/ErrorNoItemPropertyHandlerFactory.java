/**
 * 源程序名称：MetadataPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.model.metadata.propertyHandlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * 错误号
 * @author sundl
 *
 */
public class ErrorNoItemPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {
	
	public static ErrorNoItemPropertyHandlerFactory INSTANCE = new ErrorNoItemPropertyHandlerFactory();

	public static Map<String, IPropertyHandler> ERROR_NO_ITME_PROPERTIES = new HashMap<String, IPropertyHandler>();

	static {
		ERROR_NO_ITME_PROPERTIES.put("出错说明", NullPropertyHandler.INSTANCE);
		ERROR_NO_ITME_PROPERTIES.put("错误号", new EMFPropertyHandler(MetadataPackage.Literals.ERROR_NO_ITEM__NO));
		ERROR_NO_ITME_PROPERTIES.put("错误信息", new EMFPropertyHandler(MetadataPackage.Literals.ERROR_NO_ITEM__MESSAGE));
		ERROR_NO_ITME_PROPERTIES.put("错误说明", new EMFPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION));
		ERROR_NO_ITME_PROPERTIES.put("错误级别", new EMFPropertyHandler(MetadataPackage.Literals.ERROR_NO_ITEM__LEVEL));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return ERROR_NO_ITME_PROPERTIES;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return MetadataPackage.Literals.ERROR_NO_ITEM;
	}

}
