/**
 * 源程序名称：ObjectResTypes.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;


/**
 * @author sundl
 *
 */
public class ObjectRefTypes {
	
	public static ObjectRefTypes INSTANCE = new ObjectRefTypes();
	
	private List<String> types = new ArrayList<String>();
	
	private ObjectRefTypes() {
		init();
	}
	
	private void init() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg.getConfigurationElementsFor(BizCore.PLUGIN_ID, "objectRefTypes");
		for (IConfigurationElement element : elements) {
			String type = element.getAttribute("type");
			if (type != null) {
				types.add(type);
			}
		}
	}
	
	public static List<String> getRefTypes() {
		return INSTANCE.types;
	}
	
	/**
	 * 获取全部的对象资源
	 * @return
	 */
	public Collection<IARESResource> getObjectResources(IARESProject project, boolean useRequiredProject) {
		List<String> types = getRefTypes();
		Set<IARESResource> resources = new HashSet<IARESResource>();
		ReferenceManager manager = ReferenceManager.getInstance();
		for (String type : types) {
			List<ReferenceInfo> infos = manager.getReferenceInfos(project, type, useRequiredProject);
			for (ReferenceInfo ref : infos) {
				resources.add(ref.getResource());
			}
		}
		return resources;
	}
	
	public Collection<ReferenceInfo> getObjectReferences(IARESProject project, boolean useRequiredProjects) {
		init();
		List<String> types = getRefTypes();
		Set<ReferenceInfo> references = new HashSet<ReferenceInfo>();
		ReferenceManager manager = ReferenceManager.getInstance();
		for (String type : types) {
			List<ReferenceInfo> infos = manager.getReferenceInfos(project, type, useRequiredProjects);
			references.addAll(infos);
		}
		return references;
	}
	
}
