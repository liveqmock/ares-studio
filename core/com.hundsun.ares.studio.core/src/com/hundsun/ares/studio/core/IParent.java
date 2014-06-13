/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

/**
 * 包含其他元素的元素
 * @author sundl
 */
public interface IParent {

	IARESElement[] getChildren() throws ARESModelException;
	
	boolean hasChildren() throws ARESModelException;
}
