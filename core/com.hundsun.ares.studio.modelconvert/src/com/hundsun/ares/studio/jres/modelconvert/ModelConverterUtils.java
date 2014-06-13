/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.modelconvert;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author gongyf
 *
 */
public class ModelConverterUtils {
	public static final Map<Object, Object> EMF_LOAD_OPTIONS = new HashMap<Object, Object>();
	public static final Map<Object, Object> EMF_SAVE_OPTIONS = new HashMap<Object, Object>();
	public static final String CODING_TYPE_UTF_8 = "UTF-8";
	
	static XMLParserPool xmlParserPool = new XMLParserPoolImpl();
	/**
	 * 内部使用
	 */
	static ListMultimap<String, ModelConverterHandle> CONVERTER_HANDLE_MAP = ArrayListMultimap.create();
	static {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extension = registry.getExtensionPoint("com.hundsun.ares.studio.modelconvert.converterHandles");
		
		if (extension != null) {
			// 读取所有子节点
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if (elements != null) {
				for (IConfigurationElement element : elements) {
					if (StringUtils.equals("converterHandle", element.getName())) {
						try {
							Object obj = element.createExecutableExtension("class");
							if (obj instanceof ModelConverterHandle) {
								String type = element.getAttribute("type");
								CONVERTER_HANDLE_MAP.put(type, (ModelConverterHandle) obj);
							}
						} catch (CoreException e) {
						}
					}
				}
			}
		}
		
		/*
		 * DESIGN#序列化#龚叶峰#简单#吕高#对保存和读取选项的初始化
		 *
		 * 考虑读取和写入的效率问题
		 */
		EMF_SAVE_OPTIONS.put(XMLResource.OPTION_ENCODING, CODING_TYPE_UTF_8);
//		EMF_SAVE_OPTIONS.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		
		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_ANY_TYPE, XMLTypePackage.eINSTANCE.getAnyType());
		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_ANY_SIMPLE_TYPE, XMLTypePackage.eINSTANCE.getAnyType());
		
//		EMF_LOAD_OPTIONS.put(XMLResource.OPTION_USE_PARSER_POOL, xmlParserPool);
	}
}
