/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.validate;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 上下文对象，一般用于描述当前项目，当前项目中的资源。<br>
 * 上下文用于错误检查，脚本等处描述执行当前操作的上下文环境，方便获取项目和资源信息操作。
 * 同时可以提高资源查找的效率。 <br>
 * 框架提供了默认的上下文，id为"default"，具体实现类为DefaultContextProvider. 用户可以
 * 提供自己的上下文实现，在框架内注册。
 * @author sundl
 */
public interface IAresContext {

	/** 默认上下文的id， 值为"default" */
	String DEFAULT_CONTEXT = "default";
		
	/**
	 * 初始化上下文
	 * @param project 当前项目, 禁止null
	 * @throws ARESModelException 如果初始化过程发生错误的话
	 */
	public void init(IARESProject project) throws ARESModelException;
	
}
