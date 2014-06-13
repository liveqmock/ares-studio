/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

/**
 * 默认模块根注册信息描述
 * @author sundl
 */
public interface IDefaultModuleRootDescriptor extends ICommonDescriptor {

	String getRootType();
	String getPath();
	String getProjectType();
	
}
