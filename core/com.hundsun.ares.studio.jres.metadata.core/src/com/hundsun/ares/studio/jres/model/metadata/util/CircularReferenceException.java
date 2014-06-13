/**
 * 源程序名称：CircularReferenceException.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：元数据模型定义、错误检查相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

/**
 * @author gongyf
 *
 */
public class CircularReferenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4538139916507993100L;

	/**
	 * 
	 */
	public CircularReferenceException() {
		super();
	}

	/**
	 * @param message
	 */
	public CircularReferenceException(String message) {
		super(message);
	}

	
}
