package com.hundsun.ares.studio.jres.script.internal.useroption.control;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;
import com.hundsun.ares.studio.internal.ui.aresaction.AresActionImplementationDescriptor;

public class ControlType extends CommonDescriptor {
	
	private IUserOptionControlProvider provider;
	
	/**
	 * @param e
	 */
	public ControlType(IConfigurationElement e) {
		super(e);
	}
	
	public IUserOptionControlProvider getControlProvider() {
		if (this.provider == null) {
			try {
				this.provider = (IUserOptionControlProvider) configElement.createExecutableExtension("class");
			} catch (CoreException e) {
				Logger.getLogger(AresActionImplementationDescriptor.class).error("", e);
			}
		}
		return this.provider;
	}

}