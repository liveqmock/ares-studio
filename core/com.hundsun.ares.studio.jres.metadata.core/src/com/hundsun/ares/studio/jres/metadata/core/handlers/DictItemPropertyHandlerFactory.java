/**
 * 源程序名称：DictItemPropertyHandlerFactory.java
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

import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author sundl
 *
 */
public class DictItemPropertyHandlerFactory extends ExtensiblePropertyHandlerFactory {
	
	public static final DictItemPropertyHandlerFactory INSTANCE = new DictItemPropertyHandlerFactory();

	public static final Map<String, IPropertyHandler> DICT_ITME_HANDLERS = new HashMap<String, IPropertyHandler>();
	
	static {
		DICT_ITME_HANDLERS.put("字典项", new EMFPropertyHandler(MetadataPackage.Literals.DICTIONARY_ITEM__VALUE));
		DICT_ITME_HANDLERS.put("字典项名称", new EMFPropertyHandler(MetadataPackage.Literals.DICTIONARY_ITEM__CHINESE_NAME));
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#getStaticHandlers()
	 */
	@Override
	protected Map<String, IPropertyHandler> getStaticHandlers() {
		return DICT_ITME_HANDLERS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.ExtensiblePropertyHandlerFactory#geteEClass()
	 */
	@Override
	protected EClass geteEClass() {
		return MetadataPackage.Literals.DICTIONARY_ITEM;
	}

}
