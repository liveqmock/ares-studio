/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 描述了模块根类型和资源类型的映射关系
 * @author sundl
 */
public class ModuleRootType2ResTypeMap {

	private static final Logger logger = Logger.getLogger(ModuleRootType2ResTypeMap.class.getName());
	
	private static ModuleRootType2ResTypeMap instance;
	
	// 描述映射关系的Map，使用了apache的MultiMap，允许一个key有多个value
	private Multimap<String, String> map = ArrayListMultimap.create();
	
	private ModuleRootType2ResTypeMap() {
		init();
	}
	
	public static ModuleRootType2ResTypeMap getInstance() {
		if (instance == null) {
			instance = new ModuleRootType2ResTypeMap();
		}
		return instance;
	}
	
	private void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(ARESCore.PLUGIN_ID, ICommonExtensionConstants.EP_ID_MODULEROOT_RESTYPE);
		for (IConfigurationElement element : elements) {
			String rootType = element.getAttribute(ICommonExtensionConstants.ROOT_TYPE);
			String restypes = element.getAttribute(ICommonExtensionConstants.RES_TYPES);
			if (!(StringUtil.isEmpty(restypes) || StringUtil.isEmpty(rootType))) {
				// single res-type
				if (restypes.indexOf(',') == -1) {
					map.put(rootType.trim(), restypes.trim());
					logMapFound(rootType, restypes);
				} else {	// multi res-type's
					String[] types = restypes.split(",");
					for (String type : types) {
						map.put(rootType.trim(), type.trim());
						logMapFound(rootType, type);
					}
				}
			}
		}
	}
	
	private void logMapFound(String rootType, String resType) {
		logger.fine("R-R Map found: (" + rootType.trim() + " --> " + resType.trim());
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isAllowed(String rootType, String resType) {
		List allowedTypes = (List)map.get(rootType);
		for (Object type : allowedTypes) {
			if (type.equals(resType)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 返回指定的模块根允许的资源类型
	 * @param rootType 模块根的类型
	 * @return 允许的资源类型id
	 */
	public String[] getAllowedResTypes(String rootType) {
		List<String> allowedTypes = (List<String>)map.get(rootType);
		return allowedTypes.toArray(new String[0]);
	}
	
	/**
	 * 返回指定的资源类型的模块根类型
	 * @param resType 资源类型
	 * @return 模块类型数组
	 */
	public String[] getAllowedRootTypes(String resType) {
		Set<String> rootTypes = new HashSet<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			for (String value : map.get(key)) {
				if (value.equals(resType)) {
					rootTypes.add(key) ;
				}
			}
		}
		return rootTypes.toArray(new String[rootTypes.size()]);
	}
	
}
