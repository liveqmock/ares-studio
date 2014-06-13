/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * 
 * @author maxh
 */
public class ExtendPointUtil {
	public static IConfigurationElement[] readAllConfiguredElements(String id) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(id);
		if(extensionPoint == null){
			return new IConfigurationElement[]{};
		}
		IConfigurationElement points[] = extensionPoint.getConfigurationElements();
		return points;
	}
}
