/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;

/**
 * 所有元素的统一接口。
 * @author sundl
 */
public interface IARESElement extends IAdaptable{

	int ARES_MODEL = 0;
	int ARES_PROJECT = 1;
	int ARES_RESOURCE = 100;

	int COMMON_MODULE_ROOT = 200;
	int COMMON_MODULE = 300;
	int REF_LIBRAYR = 400;
	int PROJECT_PROPERTY = 500;
	
	/**
	 * 返回元素的名称
	 * @return 元素的名称
	 */
	String getElementName();
	
	/**
	 * 元素的类型，不可重复。
	 * @return 每种类型元素的唯一类型ID，整型
	 */
	int getElementType();
	
	/**
	 * 返回直接父元素。
	 * @return 元素的直接父元素
	 */
	IARESElement getParent();
	
	/**
	 * 返回指定类型的祖先。
	 * 
	 * @param ancestorType 祖先元素的类型
	 * @return 具有指定类型的元素
	 */
	IARESElement getAncestor(int ancestorType);
	
	/**
	 * 返回元素所在的项目。
	 * @return 元素所在的项目
	 */
	IARESProject getARESProject();
	
	IARESModel getARESModel();
	
	/**
	 * 返回这个元素的对应的字符串表示，必要的时候，可以从字符串恢复这个元素。
	 * @return 元素的字符串表示
	 */
	String getHandleIdentifier();
	
	/**
	 * 包含元素的最接近的资源。
	 * 例如，对于资源，就返回资源对应的文件；Jar包里的资源，而且Jar包不是External的，则返回那个Jar包；
	 * @return 包含元素的最接近的资源。
	 */
	IResource getResource();
	
	/**
	 * 返回完全对应这个元素的资源。例如，一个资源对应一个文件，而如果是Jar包里的资源，则是null.
	 * @return 完全对应元素的资源
	 * @throws ARESModelException
	 */
	IResource getCorrespondingResource() throws ARESModelException;
	
	/**
	 * 包含元素的最接近的资源。
	 * 如果是外部(External)的Jar包中的资源，则返回这个jar在文件系统中的绝对路径。
	 * @return 包含元素的最接近的资源。
	 */
	IPath getPath();
	
	/**
	 * 是否只读。不等于对应资源是否只读，比如Jar包中的元素，Jar包本身不是只读的，但元素是只读的。
	 * @return 如果这个元素不能修改，则返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean isReadOnly();
	
	/**
	 * 返回这个元素是否真实存在的。
	 * 由于ARESElement都是所谓的Handle，可以存在或不存在对应的真实元素。
	 * @return 这个元素是否真实存在的
	 */
	boolean exists();
	
	/**
	 * 返回元素的结构是否已知的。例如，一个解析过程中出现异常的模块，这个方法返回false。
	 * @return 如果结构是已知的，则返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean isStructureKnown();
}
