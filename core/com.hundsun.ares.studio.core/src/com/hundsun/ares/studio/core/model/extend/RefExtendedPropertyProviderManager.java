/**
 * 源程序名称：RefExtendedPropertyHelperManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.util.ExtendPointUtil;

/**
 * 
 * @author sundl
 */
public class RefExtendedPropertyProviderManager {
	private static Logger logger = Logger.getLogger(RefExtendedPropertyProviderManager.class);
	private static String EXTENSION_POINT = ARESCore.PLUGIN_ID + ".refExtendedPropertyProvider";
	private static RefExtendedPropertyProviderManager INSTANCE;
	
	private Map<String, IRefExtendPropertyProvider> helpers = new HashMap<String, IRefExtendPropertyProvider>();

	private RefExtendedPropertyProviderManager() {
		init();
	}
	
	public static RefExtendedPropertyProviderManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new RefExtendedPropertyProviderManager();
		return INSTANCE;
	}
	
	private void init() {
		IConfigurationElement[] elements = ExtendPointUtil.readAllConfiguredElements(EXTENSION_POINT);
		for (IConfigurationElement ele : elements) {
			String id = ele.getAttribute("id");
			try {
				IRefExtendPropertyProvider helper = (IRefExtendPropertyProvider) ele.createExecutableExtension("class");
				helpers.put(id, helper);
			} catch (CoreException e) {
				logger.error("", e);
			}
		}
	}
	
	public IRefExtendPropertyProvider getProvider(String id) {
		return helpers.get(id);
	}
	
}
