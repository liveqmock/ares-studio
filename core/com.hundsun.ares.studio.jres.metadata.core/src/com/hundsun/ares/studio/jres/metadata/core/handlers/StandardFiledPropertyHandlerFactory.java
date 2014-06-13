/**
 * 源程序名称：StandardFiledPropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.metadata.core.handlers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.excel.handler.Data1PropertyProvider;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author sundl
 *
 */
public class StandardFiledPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory{
	
	public static StandardFiledPropertyHandlerFactory INSTANCE = new StandardFiledPropertyHandlerFactory();
	
	public static Map<String, IPropertyHandler> STD_FIELD_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();

	static {
		STD_FIELD_PROPERTY_HANDLERS.put("字段名", new EMFPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__NAME));
		STD_FIELD_PROPERTY_HANDLERS.put("字段名称", new EMFPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME));
		STD_FIELD_PROPERTY_HANDLERS.put("备注", new EMFPropertyHandler(MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION));
		STD_FIELD_PROPERTY_HANDLERS.put("数据字典", new EMFPropertyHandler(MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE));
		STD_FIELD_PROPERTY_HANDLERS.put("序号", new Data1PropertyProvider("id"));
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return STD_FIELD_PROPERTY_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return MetadataPackage.Literals.STANDARD_FIELD;
	}

}
