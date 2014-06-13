/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import org.eclipse.core.runtime.IPath;

/**
 * 资源路径上的一个条目（记录）。
 * 每个res-path entry都有自己的Content-Kind(source, or binary)，这个Kind取决于它关联的Module-Root的内容类型。
 * <p>
 * 一个res-path entry可以指向：<ul>
 * <li>本项目下的源代码。 这种情况下，res-path entry指向了一个模块根。</li>
 * <li>一个zip包。(即认为是binary类型的)扩展名待定，但格式必须是zip格式的，且不支持放在工作区间外的。</li>
 * <li>一个依赖项目。这种情况下，这个entry标识了一个工作区间下的项目。</li>
 * </p><p>
 * 可以通过{@link ARESCommons}里定义的方法来创建这个接口的实例。
 * @author sundl
 */
public interface IResPathEntry {
	
	/**
	 * 代表了一个Res-Path Entry是一个指向Libray。即zip格式的压缩包.
	 */
	int RPE_LIBRAY = 1;
	
	/**
	 * 标识依赖项目的entry类型
	 */
	int RPE_PROJECT = 2;
	
	/**
	 * 标识Source的entry类型
	 */
	int RPE_SOURCE = 3;

	/**
	 * 相对项目或者压缩包根目录的相对目录
	 * @return entry对应的路径
	 */
	IPath getPath();
	
	/**
	 * 类型，即指向的是源代码还是Libary.（zip）
	 * @return
	 */
	int getEntryKind();
	
	/**
	 * 内容类型，对应其指向的ModuleRoot的类型。
	 * 对于Source类型的，返回KIND_SOURCE，而对于Libary类型的，返回KIND_BINARY
	 * @return
	 */
	int getContentKind();
	
	/**
	 * 在getKind()为源代码的时候，返回指向的模块根的类型ID.
	 * 而在kind为引用工程或引用资源包的时候，表示引用包的类型。
	 * @return 当指向源代码类型的时候，返回所指向的模块根的类型ID
	 */
	String getType();
}
