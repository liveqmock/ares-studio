/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IOpenable;
import com.hundsun.ares.studio.core.IParent;

/**
 * 依赖库。
 * @author sundl
 */
public interface IReferencedLibrary extends IOpenable, IParent, IARESElement, IBasicReferencedLibInfo, IResPathEntryElement, IDependencyUnit, IARESBundle{
	
	String PROPERTIE_FILE = ".aar";
	
	/**
	 * 这个库里有哪些根.
	 * @return
	 */
	IARESModuleRoot[] getRoots() throws ARESModelException;
	
	IBasicReferencedLibInfo getBasicInfo();
	
//	IARESResource findResource(String path);
	
	boolean isExternal();
}
