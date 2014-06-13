/**
 * 源程序名称：ExtensibleModelManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * @author gongyf
 *
 */
public class ExtensibleModelManager {
	
	private final static String EP_NAME = "ExtensibleModelEditingSupports";
	private final static String EP_ATTRIBUTE_ID = "id";
	private final static String EP_ATTRIBUTE_Uri = "uri";
	private final static String EP_ATTRIBUTE_EClass = "eclass";
	private final static String EP_ATTRIBUTE_Order = "order";
	private final static String EP_ATTRIBUTE_Class = "class";
	
	private final static IExtensibleModelEditingSupport[] EMPTY = new IExtensibleModelEditingSupport[0];
	
	private static class EditingSupportDescription implements Comparable<EditingSupportDescription>{
		public String id;
		public String uri;
		public String eclass;
		public int order;
		public IExtensibleModelEditingSupport editingSupport;
		

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(EditingSupportDescription o) {
			return order - o.order;
		}
	}
	
	private ExtensibleModelManager() {
		loadEditingSupports();
	}
	private static ExtensibleModelManager instance;
	public static ExtensibleModelManager getInstance() {
		if (instance == null) {
			instance = new ExtensibleModelManager();
		}
		return instance;
	}
	
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 保存了对用扩展id拥有的扩展编辑支持列表
	 */
	private Multimap<EClass, EditingSupportDescription> editingSupportMap = HashMultimap.create();
	
	private void loadEditingSupports() {
		logger.info("开始加载扩展模型编辑支持扩展点");
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESEditorPlugin.PLUGIN_ID , EP_NAME);
		
		for (IConfigurationElement element : elements) {
			try {
				EditingSupportDescription d = new EditingSupportDescription();
				d.id = element.getAttribute(EP_ATTRIBUTE_ID);
				d.uri = element.getAttribute(EP_ATTRIBUTE_Uri);
				d.eclass = element.getAttribute(EP_ATTRIBUTE_EClass);
				d.order = NumberUtils.toInt(element.getAttribute(EP_ATTRIBUTE_Order), Integer.MAX_VALUE);
				d.editingSupport = (IExtensibleModelEditingSupport) element.createExecutableExtension(EP_ATTRIBUTE_Class);
				
				EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(d.uri);
				if (ePackage == null) {
					logger.warn(String.format("插件%s注册扩展列%s, 找不到填写的uri：%s", element.getContributor().getName(), d.id, d.uri));
				}
				EClass eClass = (EClass) ePackage.getEClassifier(d.eclass);
				
				editingSupportMap.put(eClass, d);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		logger.info("结束加载扩展模型编辑支持扩展点");
	}
	
	/**
	 * 获取指定扩展模型id的扩展编辑支持
	 * @param modelId
	 * @return
	 */
	public IExtensibleModelEditingSupport[] getExtensibleModelEditingSupports(EClass eClass) {
		Set<EditingSupportDescription> descriptionSet = new HashSet<EditingSupportDescription>();

		// 从继承树查找所有基类的扩展，合并后返回
		for (EClass superType : eClass.getEAllSuperTypes()) {
			descriptionSet.addAll(editingSupportMap.get(superType));
		}
		
		descriptionSet.addAll(editingSupportMap.get(eClass));

		// 排序
		EditingSupportDescription[] descriptions = descriptionSet.toArray(new EditingSupportDescription[descriptionSet.size()]);
		Arrays.sort(descriptions);
		
		IExtensibleModelEditingSupport[] supports = new IExtensibleModelEditingSupport[descriptions.length];
		for (int i = 0; i < supports.length; i++) {
			supports[i] = descriptions[i].editingSupport;
		}

		return supports;
	}
	
	
}
