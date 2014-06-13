/**
 * 源程序名称：JRESTraceFlag.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.engin.trace;

import org.eclipse.core.runtime.Platform;

/**
 * @author Administrator
 *
 */
public class EngineTraceFlagHelper {
	
	public static final String UFTEngine_DEBUG_OPTION = "com.hundsun.ares.studio.uft.engin/debug";
	
	private static boolean is_debug = false;
	
	static{
		try {
			is_debug = "true".equalsIgnoreCase(Platform.getDebugOption(UFTEngine_DEBUG_OPTION));
		} catch (Exception e) {
		}
	}
	
	/**
	 * 获取debug跟踪标志
	 * @return
	 */
	public static boolean getDebugTraceFlag(){
		return is_debug;
	}
}
