/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;

import java.util.Collection;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author liaogc
 *
 */
public interface IScriptReferenceUtil extends IJRESScript{
	/**
	 * 根据引用类型,引用名称以及项目名称获得引用此引用的资源列表
	 * @param refType
	 * @param refName
	 * @param project
	 * @return
	 */
	public Collection<IARESResource> getRefResources(String refType,String refName,IARESProject project) ;
}
