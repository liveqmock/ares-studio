/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;


/**
 * @author lvgao
 *
 */
public interface IScriptOutUtil {

	/**
	 * 控制台输出
	 * @param message
	 */
	public void info(String message);
	
	/**
	 * 警告信息输出
	 * @param message
	 */
	public void warn(String message);
	
	/**
	 * 对话框输出
	 * @param message
	 */
	public void dialog(String message);
	
	/**
	 * 异常对话框输出
	 * 
	 * @param message
	 */
	public void dialogError(String message);
	
}
