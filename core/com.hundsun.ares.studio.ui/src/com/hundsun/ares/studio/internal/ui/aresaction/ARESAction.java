/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import org.eclipse.ui.IWorkbenchPart;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.aresaction.IARESAction;

/**
 * IAresAction的默认实现
 * @author sundl
 */
public abstract class ARESAction implements IARESAction{

	protected IARESResource resource;
	protected IWorkbenchPart part;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESAction#init(com.hundsun.ares.studio.core.IARESResource)
	 */
	public void init(IARESResource res, IWorkbenchPart part) {
		this.resource = res;
		this.part = part;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESAction#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}
	
}
