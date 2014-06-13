package com.hundsun.ares.studio.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.util.StringUtil;

public class CategoryPropertyTester extends PropertyTester {

	public static final String TYPE = "type";
	public static final String ISARCHIVE = "isArchive";
	public static final String PROJECT_NATURE = "nature";
	
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		ARESResourceCategory category = (ARESResourceCategory)receiver;
		if (category == null) return false;
		
		if (property.equals(TYPE)) {
			return category.getId().equals(expectedValue);
		} else if (property.equals(ISARCHIVE)) {
			boolean archive = category.getModule().getRoot().isArchive();
			return StringUtil.equalsByString(archive, expectedValue);
		} else if (PROJECT_NATURE.equals(property)) {
			try {
				IARESModule module = category.getModule();
				IProject proj = module.getARESProject().getProject();
				return proj != null && proj.isAccessible() && proj.hasNature(String.valueOf(expectedValue));
			} catch (CoreException e) {
				return false;
			}
		}
		return false;
	}

}
