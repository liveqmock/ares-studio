/**
 * 源程序名称：ResGroup.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.util.ARESElementUtil;

/**
 * 资源分组， 是另外一个对资源进行分类的概念。
 * @author sundl
 */
public class ResGroup {
	
	private static Logger logger = Logger.getLogger(ResGroup.class);

	/**
	 * 分类名字
	 */
	private String name;
	
	/**
	 * 所属的模块
	 */
	private IARESModule module;
	
	/**
	 * 分组显示在资源分类下级的情况下,保存一个分类ID
	 */
	private String cateId;

	public static Set<ResGroup> getGroups(IARESModule module) {
		IARESResource[] resources = module.getARESResources();
		return getGroups(resources);
	}
	
	public static Set<ResGroup> getGroups(IARESResource[] resources) {
		IARESModule module = null;
		if (resources.length > 0) {
			module = resources[0].getModule();
		}
		Set<ResGroup> groups = new HashSet<ResGroup>();
		for (IARESResource res : resources) {
			String groupName = ARESElementUtil.getGroup(res);
			if (StringUtils.isNotEmpty(groupName)) {
				groups.add(new ResGroup(groupName, module));
			}
		}
		return groups;
	}
	
	public static List<IARESResource> getNoGroupResources(IARESModule module) {
		IARESResource[] resources = module.getARESResources();
		return getNoGroupResources(resources);
	}
	
	public static List<IARESResource> getNoGroupResources(IARESResource[] resources) {
		List<IARESResource> noGroupResources = new ArrayList<IARESResource>();
		for (IARESResource res : resources) {
			String groupName = ARESElementUtil.getGroup(res);
			if (StringUtils.isEmpty(groupName)) {
				noGroupResources.add(res);
			}
		}
		return noGroupResources;
	}
	
	public static List<IARESResource> getResourcesOfGroup(IARESResource[] resources, String group) {
		List<IARESResource> result = new ArrayList<IARESResource>();
		for (IARESResource res : resources) {
			if (StringUtils.equals(group, ARESElementUtil.getGroup(res))) {
				result.add(res);
			}
		}
		return result;
	}

	public ResGroup(String name, IARESModule module) {
		super();
		this.name = name;
		this.module = module;
	}
	
	/**
	 * @param name
	 * @param module
	 * @param resType
	 */
	public ResGroup(String name, IARESModule module, String resType) {
		super();
		this.name = name;
		this.module = module;
		this.cateId = resType;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IARESModule getModule() {
		return module;
	}

	public void setModule(IARESModule module) {
		this.module = module;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String resType) {
		this.cateId = resType;
	}
	
	/**
	 * 返回分组下的资源； 目前的实现是每次计算，如果有性能问题可以考虑缓存，但是缓存怎么刷新和释放是个问题。
	 * @return
	 */
	public IARESResource[] getResources() {
		IARESResource[] resources = module.getARESResources();
		if (cateId != null) {
			ARESResRegistry reg = ARESResRegistry.getInstance();
			String[] types = reg.getRestypes(cateId);
			resources = getModule().getARESResources(types);
		} 
		return getResourcesOfGroup(resources, name).toArray(new IARESResource[0]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((cateId == null) ? 0 : cateId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResGroup other = (ResGroup) obj;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (cateId == null) {
			if (other.cateId != null)
				return false;
		} else if (!cateId.equals(other.cateId))
			return false;
		return true;
	}

}
