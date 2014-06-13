/**
 * 源程序名称：ExtensiblePropertyHandlerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.ExtendPropertyManager;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;

/**
 * @author sundl
 *
 */
public abstract class ExtensiblePropertyHandlerFactory implements IPropertyHandlerFactory {
	
	@Override
	public IPropertyHandler getPropertyHandler(String key, IARESProject project) {
		IPropertyHandler handler = getExtendedPropertyHandlers(geteEClass(), project).get(key);
		if (handler == null)
			handler = getStaticHandlers().get(key);
		
		if (handler == null)
			handler = (IPropertyHandler) getDynamicHandlers().get(key); 
		
		if (handler instanceof IPropertyHandler2) {
			((IPropertyHandler2) handler).setProject(project);
		}
		return handler;
	}
	
	/** 静态handler,一般做单例处理  */
	protected abstract Map<String, IPropertyHandler> getStaticHandlers();
	/** 动态handler，有些和project相关的handler，不能做简单的静态单例，所以分开处理 */
	@SuppressWarnings("unchecked")
	protected Map<String, IPropertyHandler2> getDynamicHandlers() {
		return Collections.EMPTY_MAP;
	};
	
	protected abstract EClass geteEClass();

	protected Map<String, IPropertyHandler> getExtendedPropertyHandlers(EClass eClass, IARESProject project) {
		if(project == null) {//空处理
			return new HashMap<String, IPropertyHandler>();
		}
		return createExtendedHandlers(project, eClass);
	}
	
	public static Map<String, IPropertyHandler> createExtendedHandlers(IARESElement element, EClass eclass) {
		Map<String, IPropertyHandler> handlers = new HashMap<String, IPropertyHandler>();
		List<IBasicExtendPropertyDescriptor> descriptors = ExtendPropertyManager.getInstance().getExtendedProperties(element, eclass);
		for (IBasicExtendPropertyDescriptor desc : descriptors ) {
			handlers.put(desc.getDisplayName(), new ExtendedPropertyHandler(desc));
		}
		return handlers;
	}
}
