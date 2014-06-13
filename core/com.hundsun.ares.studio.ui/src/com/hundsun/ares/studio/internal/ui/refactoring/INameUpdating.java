/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.RefactoringStatus;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 
 * @author sundl
 */
public interface INameUpdating {

	public String getNewElementName();
	
	public void setNewElementName(String name);
	
	public String getCurrentElementName();
	
	/** 返回重命名后的Element对象，比如可以用于重命名后恢复selection状态 */
	public IARESElement getNewElement();
	
	public RefactoringStatus checkNewElementName(String name);
}
