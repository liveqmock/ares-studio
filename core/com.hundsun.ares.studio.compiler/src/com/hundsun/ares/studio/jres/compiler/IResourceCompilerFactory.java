/**
 * 源程序名称：IResourceCompilerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.compiler;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * 资源编译器工厂，通过传入对应的资源来创建合适的资源编译器
 * @author gongyf
 *
 */
public interface IResourceCompilerFactory {
	
	/**
	 * 当前资源编译器工厂是否可在当前工程上使用
	 * @param project
	 * @return
	 */
	boolean isSupport(IARESProject project);
	
	/**
	 * 为一个资源创建一个新的资源编译器
	 * @param resource
	 * @return
	 */
	IResourceCompiler create(Object resource);
	

}
