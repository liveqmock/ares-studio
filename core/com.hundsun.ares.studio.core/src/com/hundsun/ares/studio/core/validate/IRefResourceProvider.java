/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.validate;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IResourceDelta;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 关联资源提供者
 * @author sundl
 */
public interface IRefResourceProvider {

	/**
	 * 根据给定的delta对象，检查指定资源的关联资源。
	 * @param res 资源
	 * @param delta 资源更改
	 * @param contexts 上下文
	 * @return 给定的资源的关联资源，关联资源就是需要检查的资源.
	 */
	public Collection<IARESResource> getRefResources(IARESResource res, IResourceDelta delta, Map<String, IAresContext> contexts);
	
}
