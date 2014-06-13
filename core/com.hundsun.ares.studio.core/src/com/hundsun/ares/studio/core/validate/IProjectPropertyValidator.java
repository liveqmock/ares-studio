/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.validate;

import java.util.Collection;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProjectProperty;

/**
 * 
 * @author sundl
 */
public interface IProjectPropertyValidator {

	public Collection<IARESProblem> validate(IARESProjectProperty property, Map<String, IAresContext> contexts);
	
}
