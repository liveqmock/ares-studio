/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.internal.core.ARESModelOperation;

/**
 * 
 * @author sundl
 */
public class ChangeResPathOperation extends ARESModelOperation{

	/**
	 * @param elementsToProElements
	 * @param force
	 */
	public ChangeResPathOperation(IARESProject project, IResPathEntry[]	newPath, boolean force) {
		super(new IARESElement[] {project}, null, force);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESModelOperation#excuteOperation()
	 */
	@Override
	protected void excuteOperation() throws ARESModelException {
	}
		
}
