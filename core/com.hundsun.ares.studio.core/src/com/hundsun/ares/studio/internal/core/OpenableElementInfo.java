/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

/**
 * IOpenableElement用的Element Info
 * @author sundl
 */
public class OpenableElementInfo extends ARESElementInfo {

	protected boolean isStructureKnown = false;

	public boolean isStructureKnown() {
		return isStructureKnown;
	}

	public void setStructureKnown(boolean isStructureKnown) {
		this.isStructureKnown = isStructureKnown;
	}
	
}
