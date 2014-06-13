/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

/**
 * 修订记录信息整理
 * 
 * @author lvgao
 */
public interface IResourceModifyHistory {

	/**
	 * 获取修订记录
	 * 
	 * @param commentMark
	 * @return
	 */
	public String getModifyHistory(String commentMark);
}
