/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.Map;

/**
 * @author lvgao
 *
 */
public interface IAresWizard {

	/**
	 * 获取向导上下文
	 * @return
	 */
	public Map<Object, Object> getContext();
}
