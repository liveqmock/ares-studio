/**
 * 源程序名称：IRefResourceForBuilderProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.core.validate;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IResourceDelta;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author liaogc
 */
public interface IRefResourceForBuilderProvider {


	/**
	 * 根据给定的delta对象，检查指定资源的关联资源。
	 * @param res 资源
	 * @param delta 资源更改
	 * @param contexts 上下文
	 * @return 给定的资源的关联资源，关联资源就是需要检查的资源.
	 */
	public Collection<IARESResource> getRefForBuildResources(IARESResource res, IResourceDelta delta, Map<String, IAresContext> contexts);
	

}
