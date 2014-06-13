/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.eclipse.core.resources.IProjectNature;

/**
 * ARES项目类型注册信息描述
 * @author sundl
 */
public interface IARESProjectDescriptor extends ICommonDescriptor {
	
	/**
	 * 获取关联的nature id，可以有多个
	 * @see IProjectNature
	 * @return 关联的Nature id
	 */
	String[] getNatures();
}
