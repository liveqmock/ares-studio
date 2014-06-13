/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;

/**
 * @author lvgao
 *
 */
public interface IScriptSysUtil {

	public static final String KEY_USER_NAME = "user.name";
	
	/**
	 * 获取首选项
	 * @param id
	 * @return
	 */
	public Object getConfig(String id);
	
	
	/**
	 * 获取配置
	 * @param id
	 * @return
	 */
	public Object get(String id);
	
}
