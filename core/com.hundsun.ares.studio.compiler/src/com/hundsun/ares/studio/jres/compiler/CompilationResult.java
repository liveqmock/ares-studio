/**
 * 源程序名称：CompilationResult.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.compiler;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author gongyf
 *
 */
public class CompilationResult {
	private IStatus status = Status.OK_STATUS;
	
	/**
	 * @return the status
	 */
	public IStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(IStatus status) {
		this.status = status;
	}

}
