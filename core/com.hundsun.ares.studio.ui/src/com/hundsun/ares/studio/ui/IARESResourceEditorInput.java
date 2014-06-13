/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.ui.IEditorInput;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public interface IARESResourceEditorInput extends IEditorInput {

	/**
	 * 获取对应的ARES resource.
	 * 
	 */
	IARESResource getARESResource();
	
}
