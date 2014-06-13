/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;


/**
 * @author lvgao
 *
 */
public interface IScriptFileUtil {

	
	/**
	 * 写入文件
	 * @param filepath
	 * 		文件路径，可以是相对路径与绝对路径,如果不存在就创建
	 * @param content
	 * 		文件内容
	 * @param charsetName
	 * 		编码格式
	 * @return
	 */
	public  boolean write(String filepath, String content, String charsetName );
	/**
	 * 返回当前的前的文件绝对路径
	 * @return
	 */
	public String getAbsolutePath();
	
}
