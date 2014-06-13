/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.io.InputStream;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * ARES资源的统一接口。
 * @author sundl
 */
public interface IARESResource extends IARESElement, IOpenable, ISourceManipulation {
	
	Pattern RES_NAME_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]{0,49}$");
	
	/**
	 * 进行错误检查
	 * @return 错误检查的结果，如果没有错误，则应该返回一个空的数组。不允许null
	 * @deprecated 使用validator扩展点进行错误检查
	 */
	IARESProblem[] check();
	
	/**
	 * 资源的名字，一般是短名,不带扩展名。
	 * @return 资源的短名
	 */
	String getName();
	
	/**
	 * 资源的全名，即其他资源引用此资源的时候，应该使用的名字。
	 * @return 资源的全名(不带扩展名)
	 */
	String getFullyQualifiedName();
	
	/**
	 * 资源的类型，一般以扩展名做为类型。
	 * @return 资源的类型
	 */
	String getType();
	
	/**
	 * 获取读取资源的输入流，使用者负责关闭。
	 * @return 输入流
	 * @throws ARESModelException 保存出错。可能的原因有：
	 */
	InputStream openStream() throws ARESModelException;
	
	/**
	 * 获取资源所在的模块根。
	 * @return
	 */
	IARESModuleRoot getRoot();
	
	IARESModule getModule();
		
	IReferencedLibrary getLib();
	
	/**
	 * 返回资源所在的Bundle：如果是在引用资源包里，则返回引用资源包，否则返回的是项目。
	 * @return
	 */
	IARESBundle getBundle();
	
	/**
	 * 获取代表资源信息的对象。
	 * 调用者不应该试图修改这个对象，如需修改，应该调用{@link #getWorkingCopy()}
	 * @return 代表资源信息的对象
	 * @throws ARESModelException 保存出错。可能的原因有：
	 * <ul>
	 *  <li> 这个元素不存在 (ELEMENT_DOES_NOT_EXIST) </li>
	 * </ul> 
	 * @deprecated see {@link #getInfo(Class)}
	 */
	Object getInfo() throws ARESModelException;
	
	/**
	 * 获取代表资源信息的对象的工作副本，用于修改和保存。
	 * @return 资源信息的对象的工作副本
	 * <ul>
	 *  <li> 这个元素不存在 (ELEMENT_DOES_NOT_EXIST)</li>
	 * </ul> 
	 * @deprecated see {@link #getWorkingCopy(Class)}
	 */
	Object getWorkingCopy() throws ARESModelException;
	
	/**
	 * 可以获取指定类型的资源信息对象，用于老模型的兼容
	 * 调用者不应该试图修改这个对象，如需修改，应该调用{@link #getWorkingCopy(Class)}
	 * @param <T>
	 * @param clazz 需要返回的类型
	 * @return 代表资源信息的对象
	 * @throws ARESModelException 保存出错。可能的原因有：
	 * <ul>
	 *  <li> 这个元素不存在 (ELEMENT_DOES_NOT_EXIST) </li>
	 * </ul> 
	 */
	<T> T getInfo(Class<T> clazz) throws ARESModelException;
	
	/**
	 * 可以获取指定类型的资源信息对象副本，用于老模型的兼容
	 * <p>只有当info-class被注册为{@link IAdaptable}的子类时才能完整实现这个方法的特性</p>
	 * @param <T>
	 * @param clazz
	 * @return
	 * @throws ARESModelException 保存出错。可能的原因有：
	 * <ul>
	 *  <li> 这个元素不存在 (ELEMENT_DOES_NOT_EXIST) </li>
	 * </ul> 
	 */
	<T> T getWorkingCopy(Class<T> clazz) throws ARESModelException;
	
	/**
	 * 保存元素信息。
	 * @param info 资源信息对象。
	 * @param force 是否强制保存
	 * @param monitor The Progress monitor
	 * @throws ARESModelException 保存出错。可能的原因有：
	 * <ul>
	 * <li> 资源不存在(ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> 资源是只读的(READ_ONLY)</li>
	 * <li> 资源信息不正确，例如类型不对，或者其他异常(INVALID_INFO)</li>
	 * </ul>
	 */
	void save(Object info, boolean force, IProgressMonitor monitor) throws ARESModelException;

}
