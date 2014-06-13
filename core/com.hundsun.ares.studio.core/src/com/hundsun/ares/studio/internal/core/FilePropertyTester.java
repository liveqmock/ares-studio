package com.hundsun.ares.studio.internal.core;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.util.StringUtil;

public class FilePropertyTester extends PropertyTester {

	private static final String IS_ON_RESPATH = "isOnResPath";
	
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof IFile) {
			IFile file = (IFile)receiver;
			if (IS_ON_RESPATH.equals(property)) {
				boolean onResPath = false;
				IPath path = file.getProjectRelativePath();
				IProject project = file.getProject();
				IARESProject aresProject = ARESCore.create(project);
				for (IResPathEntry entry : aresProject.getRawResPath()) {
					if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY 
							&& entry.getPath().equals(path)) {
						onResPath = true;
					}
				}
				return StringUtil.equalsByString(expectedValue, onResPath);
			}
		}
		return false;
	}

}
