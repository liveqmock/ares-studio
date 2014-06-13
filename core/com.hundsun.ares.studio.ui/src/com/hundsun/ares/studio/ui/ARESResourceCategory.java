/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.runtime.PlatformObject;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResCategoryDescriptor;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 资源分类的模型
 * @author sundl
 */
public class ARESResourceCategory extends PlatformObject{

	private Object parent;
	
	/**　对应的资源类型　*/
	private String id;
	
	public ARESResourceCategory(Object parent, String id) {
		this.parent = parent;
		this.id = id;
	}
	
	/**
	 * 获取所在的模块
	 * @return
	 */
	public IARESModule getModule() {
		Object parent = this.parent;
		if (parent instanceof IARESModule) {
			return (IARESModule)parent;
		} else if (parent instanceof ARESResourceCategory) {
			return ((ARESResourceCategory)parent).getModule();
		}
		return null;
	}
	
	public boolean containsResource() {
		return getResources().length > 0;
	}
	
	/**
	 * 计算应该显示在分类下的所有资源
	 * @return 
	 */
	public IARESResource[] getResources() {
		ARESResRegistry reg = ARESResRegistry.getInstance();
		String[] types = reg.getRestypes(id);
		return getModule().getARESResources(types);
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		IResCategoryDescriptor desc = ARESResRegistry.getInstance().getCategory(id);
		if (desc != null) {
			return desc.getName();
		}
		return id;
	}
	
	public boolean isResTypeAllowed(String resType) {
		return ARESResRegistry.getInstance().isResTypeInCategory(resType, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ARESResourceCategory)) {
			return false;
		}
		ARESResourceCategory another = (ARESResourceCategory)obj;
		return this.parent.equals(another.parent) && this.id.equals(another.id);
	}
	
	@Override
	public int hashCode() {
		return Util.combineHashCodes(parent.hashCode(), id.hashCode());
	}
	
}
