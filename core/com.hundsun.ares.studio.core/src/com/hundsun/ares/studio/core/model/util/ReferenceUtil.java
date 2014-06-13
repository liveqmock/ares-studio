/**
 * 源程序名称：ReferenceUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.IReferenceProvider2;
import com.hundsun.ares.studio.core.model.Reference;

/**
 * @author gongyf
 *
 */
public class ReferenceUtil {

	public static ReferenceUtil INSTANCE = new ReferenceUtil();
	
	private ReferenceUtil() {
		
	}
	
	/**
	 * 使得一种解析器只有一个实例
	 */
	private Map<String, IReferenceParser> parserCache = new HashMap<String, IReferenceParser>();
	
	protected IReferenceParser getParser(String className) {
		
		IReferenceParser parser = parserCache.get(className);
		if (parser == null) {
			
//			IExtensionPoint extension = Platform.getExtensionRegistry().getConfigurationElementsFor(namespace, extensionPointName);
//			
//			.getExtensionPoint(PLUGIN_ID + ".adapterPlugins");
//			try {
//				Class<?> clazz = loader.loadClass(className);
//				parserCache.put(className, parser = (IReferenceParser) clazz.newInstance());
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
		}
		return parser;
	}
	
	/**
	 * 引用单元，一个类里可能有多个引用单元<BR>
	 * 引用单元能够获取这个单元所包含的所有引用
	 * @author gongyf
	 *
	 */
	static class ReferenceUnit {
		private EStructuralFeature feature;
		private IReferenceParser parser;
		private String[] parameters;
		
		/**
		 * @param parser
		 * @param feature
		 * @param parameters
		 */
		public ReferenceUnit(IReferenceParser parser,
				EStructuralFeature feature, String[] parameters) {
			super();
			this.parser = parser;
			this.feature = feature;
			this.parameters = parameters;
		}

		public List<Reference> analyse(EObject object) {
			return parser.analyse(object, feature, parameters);
		}
	}
	private Multimap<EClass, ReferenceUnit> unitMap = HashMultimap.create();

	/**
	 * 获取一个对象拥有的引用
	 * @param object
	 * @return
	 */
	public List<Reference> getReferences(EObject object) {
		EClass clazz = object.eClass();
		
		Collection<ReferenceUnit> untis = unitMap.get(clazz);
		if (untis == null || untis.isEmpty()) {
			EAnnotation anno = clazz.getEAnnotation(Constants.EANNOTATION_REF_SOURCE);
			if (anno != null) {
				for (Entry<String, String> entry : anno.getDetails().entrySet()) {
					// 所有引用的字段和引用解析类
					EStructuralFeature feature = clazz.getEStructuralFeature(entry.getKey());
					if (feature != null) {
						String[] strings = StringUtils.split(entry.getValue());
						IReferenceParser parser = getParser(strings[0]);
						String[] parameters = null;
						if (strings.length > 1) {
							parameters = new String[strings.length - 1];
							System.arraycopy(strings, 1, parameters, 0, strings.length - 1);
						}

						ReferenceUnit unit = new ReferenceUnit(parser, feature, parameters);
						unitMap.put(clazz, unit);
					}
				}
			}
			untis = unitMap.get(clazz);
		}
		
		List<Reference> references = Collections.emptyList(); 
		if (untis != null) {
			references = new ArrayList<Reference>();
			for (ReferenceUnit unit : untis) {
				references.addAll(unit.analyse(object));
			}
		}
		
		return references;

	}
	
	/**
	 * 某些特殊情况下，计算reference需要project对象，这个工具方法提供一个统一的方法来获取reference
	 * @param obj 
	 * @param project 如果有就传入，没有可以为null
	 * @return
	 */
	public List<Reference> getReferences(Object obj, IARESProject project) {
		List<Reference> references = null;
		if (project != null) {
			// 有project对象的情况下，首先尝试IReferenceProvider2
			IAdapterManager manager = Platform.getAdapterManager();
			IReferenceProvider2 provider = (IReferenceProvider2) manager.getAdapter(obj, IReferenceProvider2.class);
			if (provider != null) {
				references = provider.getReferences(obj, project);
			}
		} 
		
		// 没有project或者IReferenceProvider2失败，再尝试
		if (references == null) {
			if (obj instanceof IReferenceProvider) {
				references = ((IReferenceProvider) obj).getReferences();
			}
		}
		
		if (references == null) {
			references = Collections.emptyList();
		}
		return references;
	}
	
}
