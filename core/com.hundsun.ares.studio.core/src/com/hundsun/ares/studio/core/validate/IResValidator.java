/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.validate;

import java.util.Collection;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public interface IResValidator {

	/**
	 * 检查指定的资源，并返回对应的错误信息。
	 * @param resource 资源 
	 * @return 不能返回<code>null</code>
	 */
	public Collection<IARESProblem> validate(IARESResource resource, Map<String, IAresContext> contexts);
	
}
