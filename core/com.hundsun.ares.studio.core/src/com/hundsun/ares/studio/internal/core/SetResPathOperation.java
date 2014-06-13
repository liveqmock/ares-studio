/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 
 * @author sundl
 */
public class SetResPathOperation extends ARESModelOperation {

	ARESProject project;
	IResPathEntry[] newResPath;
	
	public SetResPathOperation(ARESProject project, IResPathEntry[] newResPath) {
		super(null, null, false);
		this.project = project;
		this.newResPath = newResPath;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.ARESModelOperation#excuteOperatioin()
	 */
	@Override
	protected void excuteOperation() throws ARESModelException {
		AresProjectInfo info = (AresProjectInfo) project.getElementInfo();
		IResPathEntry[] oldPath = info.resPath;
		project.writeResPath(newResPath);
	}

}
