/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.ARESCore;

/**
 * 通用注册表，基于List的实现。
 * @author sundl
 */
public abstract class CommonListRegistry<T> implements ICommonRegistry {
	
	protected List<T> list = new ArrayList<T>();

	public CommonListRegistry() {
		init();
	}
	
	protected void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(getExtensionPointPluginId(), ICommonExtensionConstants.EP_ID_MODULEROOT);
		for (IConfigurationElement element : elements) {
			handleConfigElement(element);
		}
	}
	
	protected String getExtensionPointPluginId() {
		return ARESCore.PLUGIN_ID;
	}
	
	protected abstract void handleConfigElement(IConfigurationElement element);

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.ICommonRegistry#print(java.io.PrintStream)
	 */
	public void print(PrintStream ps) {
		ps.println("Registry: " + getExtensionPointId());
		for (T t : list) {
			ps.println(t);
		}
	}

}
