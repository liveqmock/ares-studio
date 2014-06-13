/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * 依赖的描述
 * @author sundl
 */
public interface IDependenceDescriptor {

	/**
	 * 依赖库的ID
	 * @return
	 */
	String getId();
	
	/**
	 * 类型，例如Jres,或者Acide...
	 * @return
	 */
	String getType();
	
	/**
	 * 依赖库的版本限制
	 * @return
	 */
	String getVersionConstraint();
	
	/**
	 * 判断给定的版本是否满足约束条件
	 * @param version 版本
	 * @return 如果满足，则返回<code>true</code>,否则返回<code>false</code>
	 */
	boolean isValideVersion(String version);
}
