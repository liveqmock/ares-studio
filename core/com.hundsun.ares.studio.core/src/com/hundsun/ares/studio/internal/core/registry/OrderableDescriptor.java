/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;

/**
 * 可排序的描述对象
 * @author sundl
 */
public class OrderableDescriptor extends CommonDescriptor {

	protected int order;
	
	public OrderableDescriptor(IConfigurationElement e) {
		super(e);
	}

	protected void loadFromExtension() {
		super.loadFromExtension();
		String orderStr = configElement.getAttribute(ICommonExtensionConstants.ORDER);
		try {
			order = Integer.parseInt(orderStr);
		} catch (NumberFormatException e) {
			order = Integer.MAX_VALUE;
		}
	}
	
	public int getOrder() {
		return order;
	}
	
}
