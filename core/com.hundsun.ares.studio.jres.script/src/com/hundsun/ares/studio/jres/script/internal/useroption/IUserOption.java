/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.Map;

/**
 * @author lvgao
 *
 */
public interface IUserOption {

	/**
	 * 填充Option对应的值
	 * 2014-3-26 sundl： 这个方法不如改成getValue更合适...
	 * @param option
	 */
	public void setOptionValue(Map<String, Object> option);
	
}
