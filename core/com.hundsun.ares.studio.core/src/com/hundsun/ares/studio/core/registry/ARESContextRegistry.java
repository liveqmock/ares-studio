/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.validate.IAresContext;

/**
 * Context提供者注册表
 * @author sundl
 */
public class ARESContextRegistry extends CommonMapRegistry<AresContextDescriptor>{
	
	private static final Logger logger = Logger.getLogger(ARESContextRegistry.class);
	
	private static ARESContextRegistry instance;
	
	public static ARESContextRegistry getInstance() {
		if (instance == null) {
			instance = new ARESContextRegistry();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "aresContextProvider";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		AresContextDescriptor desc = new AresContextDescriptor(element);
		map.put(desc.getId(), desc);
		logger.debug("ContextProvider found: " + desc.getId());
	}

	public Map<String, IAresContext> createContexts(IARESProject project) {
		Map<String, IAresContext> contexts = new HashMap<String, IAresContext>();
		for (String id : map.keys()) {
			Collection<AresContextDescriptor> desc = map.get(id);
			if (desc.size() >= 1) {
				IAresContext context = desc.toArray(new AresContextDescriptor[1])[0].createContext();
				try {
					long t1 = System.currentTimeMillis();
					context.init(project);
					long t2 = System.currentTimeMillis();
					logger.debug("初始化Context: " + id + " 成功，用时" + (t2 - t1) + "ms");
				} catch (ARESModelException e) {
					logger.error("创建Context: " + id + " 的时候，发生错误。" , e);
				}
				contexts.put(id, context);
			}
		}
		return contexts;
	}
	
}
