/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;

/**
 * 
 * @author sundl
 */
public class ResValidatorDescriptor extends CommonDescriptor {

	private String resTypes;
	private IResValidator validator;
	
	public ResValidatorDescriptor(IConfigurationElement e) {
		super(e);
	}
	
	protected void loadFromExtension() {
		super.loadFromExtension();
		this.resTypes = configElement.getAttribute(ICommonExtensionConstants.RES_TYPES);
	}
	
	public String[] getResTypes() {
		return resTypes.split(",");
	}
	
	public IResValidator getValidator() {
		if (validator == null) {
			try {
				validator = (IResValidator) configElement.createExecutableExtension("class");
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return validator;
	}
}
