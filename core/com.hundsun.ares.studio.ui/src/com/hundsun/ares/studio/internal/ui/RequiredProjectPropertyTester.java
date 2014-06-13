package com.hundsun.ares.studio.internal.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.ui.RefLibContainer.RequiredProject;

public class RequiredProjectPropertyTester extends PropertyTester {

	public static final String  HOST_PROJECT_NATURE = "hostProjectNature";
	
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		RequiredProject reqProject = (RequiredProject) receiver;
		if (StringUtils.equals(HOST_PROJECT_NATURE, property)) {
			IProject project = reqProject.getHostAresProject().getProject();
			try {
				return project.hasNature(String.valueOf(expectedValue));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
