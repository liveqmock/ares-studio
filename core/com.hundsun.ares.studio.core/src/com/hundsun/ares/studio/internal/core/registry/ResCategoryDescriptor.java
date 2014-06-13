/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.IResCategoryDescriptor;

/**
 * 资源分类的描述信息
 * @author sundl
 */
public class ResCategoryDescriptor extends OrderableDescriptor implements IResCategoryDescriptor{

	public ResCategoryDescriptor(IConfigurationElement e) {
		super(e);	
	}


}
