/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

/**
 * 描述ARES model操作的异常原因。
 * @author sundl
 */
public interface IARESModelStatus extends IStatus {

	IARESElement[] getElements();
	
	IPath getPath();
	
	boolean isDoesNotExist();
}
