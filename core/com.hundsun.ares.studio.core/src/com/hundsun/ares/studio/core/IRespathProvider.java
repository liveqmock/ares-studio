/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.util.List;

import org.eclipse.core.resources.IResourceDelta;


/**
 * 扩展解析Res-path的逻辑，可以通过这个扩展点来增加res-path。
 * @author sundl
 */
public interface IRespathProvider {

	List<IExternalResPathEntry> getResPathEntries(IARESProject project);
	/**
	 * 获取Respath，第二个参数可以指定是否需要强制刷新。
	 * @param project
	 * @param refresh 如果为true说明需要强制刷新。
	 * @return
	 */
	List<IExternalResPathEntry> getResPathEntries(IARESProject project, boolean refresh);
	boolean containsRespathChange(IResourceDelta delta);
	
}
