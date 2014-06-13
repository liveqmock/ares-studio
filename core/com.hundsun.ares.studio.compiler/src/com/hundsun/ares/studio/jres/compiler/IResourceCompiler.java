/**
 * 源程序名称：IResourceCompiler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：吕高
 */
package com.hundsun.ares.studio.jres.compiler;

import java.util.Map;

/**
 * 编译一个资源
 * @author gongyf
 *
 */
public interface IResourceCompiler {
	/**
	 * 对一个资源进行编译，返回一个编译结果
	 * 
	 * TODO 可以空编译，即只返回编译可能的文件集
	 * @param resource
	 * @param unitFactory
	 * @param context
	 * @return
	 */
	CompilationResult compile(Object resource, Map<Object, Object> context);

	/**
	 * 清除资源编译产生的文件
	 * @param resource
	 * @param context
	 */
	public void clean(Object resource,Map<Object, Object> context);
}
