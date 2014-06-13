/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.io.PrintStream;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESCore;

/**
 * 通用的注册表类，基于MultiMap的实现
 * @author sundl
 */
public abstract class CommonMapRegistry<T> implements ICommonRegistry<T>{

	private static final Logger logger = Logger.getLogger(CommonMapRegistry.class);
	
	protected Multimap<String, T> map = ArrayListMultimap.create();
	
	public CommonMapRegistry () {
		init();
	}
	
	protected void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(getExtensionPointPluginId(), getExtensionPointId());
		for (IConfigurationElement element : elements) {
			handleConfigElement(element);
		}
		logger.debug("扩展点" + getExtensionPointId() + "初始化完成!");
		for (String str : map.keySet()) {
			logger.debug(str + "===>" +map.get(str));
		}
	}
	
	protected String getExtensionPointPluginId() {
		return ARESCore.PLUGIN_ID;
	}
	
	public abstract String getExtensionPointId();
	
	protected abstract void handleConfigElement(IConfigurationElement element);
	
	public Collection<T> get(String id) {
		return map.get(id);
	}
	
	public Collection<T> getDescriptors() {
		return map.values();
	}
	
	public void print(PrintStream ps) {
		ps.println("Registry: " + getExtensionPointId());
		for (String str : map.keySet()) {
			ps.println(map.get(str));
		}
	}
}
