/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;

/**
 * @author lvgao
 *
 */
public interface IWizardPageValidator {

	/**
	 * 错误检查
	 * @param context   上下文
	 * @return
	 */
	public IStatus validate(Map<Object, Object> context);
	
	
}
