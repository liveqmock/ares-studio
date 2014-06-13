/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import com.hundsun.ares.studio.core.IModuleRootConstructor;

/**
 * 
 * @author sundl
 */
public interface IRootConstructorDescriptor extends ICommonDescriptor{

	String getRootId();
	IModuleRootConstructor createConstructor();
	
}
