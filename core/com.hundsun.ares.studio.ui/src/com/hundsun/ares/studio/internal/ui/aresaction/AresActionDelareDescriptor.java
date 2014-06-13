/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;
import com.hundsun.ares.studio.ui.aresaction.IAresActionAdvisor;

/**
 * 
 * @author sundl
 */
public class AresActionDelareDescriptor extends CommonDescriptor{

	public AresActionDelareDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESActionDescriptor#createAction()
	 */
	public IAresActionAdvisor createAdvisor() {
		try {
			return (IAresActionAdvisor)configElement.createExecutableExtension("advisor");
		} catch (CoreException e) {
			//e.printStackTrace();
			// cann't create instance, use default
		}
		return null;
	}
}
