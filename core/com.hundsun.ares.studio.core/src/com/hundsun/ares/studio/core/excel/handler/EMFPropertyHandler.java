/**
 * 源程序名称：EMFPropertyHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtendPropertyManager;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;

/**
 * 针对EMF对象的EAttribute类型的属性的处理类 
 * @author sundl
 *
 */
public class EMFPropertyHandler implements IPropertyHandler {
	
	private static final Logger LOGGER = Logger.getLogger(EMFPropertyHandler.class);
	
	public static IPropertyHandler OBJECT_ID_PROPERTY_HANDLER = new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
	/** 专为资源名处理的Handler, 会吧含有的点'.'替换成下划线 */
	public static IPropertyHandler NAME_PROPERTY_HANDLER = new ResNamePropertyHandler();
	public static IPropertyHandler CNAME_PROPERTY_HANDLER = new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
	public static IPropertyHandler DESCRIPTION_PROPERTY_HANDLER = new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
	
	public static Map<String, IPropertyHandler> BASIC_HANDLERS = new HashMap<String, IPropertyHandler>();


	static {
		// 
		BASIC_HANDLERS.put("对象号", OBJECT_ID_PROPERTY_HANDLER);
		BASIC_HANDLERS.put("功能号", NullPropertyHandler.INSTANCE);
		
		// 确定不需处理的属性，用NullPropertyHandler避免报错
		BASIC_HANDLERS.put("版本号", NullPropertyHandler.INSTANCE);
		BASIC_HANDLERS.put("更新日期", NullPropertyHandler.INSTANCE);
		
		BASIC_HANDLERS.put("服务名", NAME_PROPERTY_HANDLER);
		BASIC_HANDLERS.put("函数名称", NAME_PROPERTY_HANDLER);
		BASIC_HANDLERS.put("函数说明", CNAME_PROPERTY_HANDLER);
		BASIC_HANDLERS.put("服务名称", CNAME_PROPERTY_HANDLER);

	}
	
	public static IPropertyHandler getPropertyHandler(EClass eClass, String property, IARESProject project) {
		IPropertyHandlerFactory factory = PropertyHandlerFactoryManager.getPropertyHandlerFactory(eClass);
		if (factory != null) {
			IPropertyHandler handler = factory.getPropertyHandler(property, project);
			if (handler == null) {
				LOGGER.error(String.format("Eclass%s的属性%s没有对应的PropertyHandler", eClass, property));
			}
			return handler;
		}
		return null;
	}
	
	
	/**
	 * 创建指定的EClass的扩展属性对应的Handler
	 * @param eclass
	 * @return
	 */
	public static Map<String, IPropertyHandler> createExtendedHandlers(SheetParser sheetParser, IARESElement element, EClass eclass) {
		Map<String, IPropertyHandler> handlers = new HashMap<String, IPropertyHandler>();
		List<IBasicExtendPropertyDescriptor> descriptors = ExtendPropertyManager.getInstance().getExtendedProperties(element, eclass);
		for (IBasicExtendPropertyDescriptor desc : descriptors ) {
			// descriptor 可以直接实现PropertyHandler
			if (desc instanceof IPropertyHandler) {
				IPropertyHandler handler = (IPropertyHandler) desc;
				if (handler instanceof IPropertyHandlerExtension) {
					IPropertyHandlerExtension ext = (IPropertyHandlerExtension) handler;
					ext.init(sheetParser, element.getARESProject());
				}
				handlers.put(desc.getDisplayName(), handler);
			} else {
				handlers.put(desc.getDisplayName(), new ExtendedPropertyHandler(desc));
			}
		}
		return handlers;
	}
	
	protected EStructuralFeature feature;
	
	public EMFPropertyHandler(EStructuralFeature feature) {
		this.feature = feature;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#setValue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void setValue(Object obj, String value) {
		// 如果不是EMF对象，什么都不做
		if (obj instanceof EObject) {
			((EObject) obj).eSet(feature, value);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.handlers.IPropertyHandler#getValue()
	 */
	@Override
	public String getValue(Object obj) {
		if (obj instanceof EObject) {
			Object value = ((EObject) obj).eGet(getFeature(feature));
			if (value != null) {
				return String.valueOf(value);
			}
		}
		return null;
	}
	
	protected EStructuralFeature getFeature(EObject object) {
		return this.feature;
	}

}
