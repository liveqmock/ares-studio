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
public interface IAresWizardPage {

	/**
	 * 设置上下文，可以放入
	 * context来自Wizard
	 * 1、用于检查的数据
	 * 2、page之间的数据传递
	 * 3、wizard和page之间传递数据
	 * @param context
	 */
	public void setContext(Map<Object, Object> context);
	
}
