/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.registry.IProjectValidatorDescriptor;
import com.hundsun.ares.studio.core.validate.IProjectPropertyValidator;

/**
 * 
 * @author sundl
 */
public class ProjectValidatorDescriptor  extends CommonDescriptor implements IProjectValidatorDescriptor {

	private String nature;
	
	private IProjectPropertyValidator validator;
	
	public ProjectValidatorDescriptor(IConfigurationElement e) {
		super(e);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IProjectValidatorDescriptor#getProjectTypeId()
	 */
	public String getNature() {
		return nature;
	}
	
	protected void loadFromExtension() {
		super.loadFromExtension();
		this.nature = configElement.getAttribute(ICommonExtensionConstants.NATURE);
	}
	
	public IProjectPropertyValidator getValidator() {
		if (validator == null) {
			try {
				validator = (IProjectPropertyValidator) configElement.createExecutableExtension("class");
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return validator;
	}

}
