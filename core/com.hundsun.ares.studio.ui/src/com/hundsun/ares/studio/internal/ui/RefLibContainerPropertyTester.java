package com.hundsun.ares.studio.internal.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.ui.RefLibContainer;

public class RefLibContainerPropertyTester extends PropertyTester {

	public static final String NATURE = "projectNature";
	
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		RefLibContainer container = (RefLibContainer) receiver;
		if (StringUtils.equals(NATURE, property)) {
			IProject project = container.getProject().getProject();
			try {
				return project.hasNature(String.valueOf(expectedValue));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
