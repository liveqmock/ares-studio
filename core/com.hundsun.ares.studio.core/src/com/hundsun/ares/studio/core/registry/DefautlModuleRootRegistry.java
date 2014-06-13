/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.internal.core.registry.DefaultModuleRootDescriptor;

/**
 * 默认模块根注册信息
 * @author sundl
 */
public class DefautlModuleRootRegistry extends CommonMapRegistry<IDefaultModuleRootDescriptor> {
	
	private static DefautlModuleRootRegistry instance;
	
	private DefautlModuleRootRegistry() {
		super();
	}

	public static DefautlModuleRootRegistry getInstance() {
		if (instance == null) {
			instance = new DefautlModuleRootRegistry();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return ICommonExtensionConstants.EP_ID_DEFAULT_MODULE_ROOTS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		IDefaultModuleRootDescriptor desc = new DefaultModuleRootDescriptor(element);
		map.put(desc.getProjectType(), desc);
	}
	
	public void createDefaultRoots(IProject project, String type) throws ARESModelException {
		IARESProject aresProj = ARESCore.create(project);
		Collection<IDefaultModuleRootDescriptor> roots = map.get(type);
		for (IDefaultModuleRootDescriptor rootDesc : roots) {
			aresProj.createRoot(rootDesc.getRootType(), rootDesc.getPath(), new NullProgressMonitor());
		}
	}

}
