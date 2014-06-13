/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author lvgao
 *
 */
public class ReourceNameValidator extends BaseWizardPageValidator  implements IWizardPageValidator{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.IWizardPageValidator#validate(java.util.Map)
	 */
	@Override
	public IStatus validate(Map<Object, Object> context) {
		String name = context.get(ElementSelectionWizardPageWithNameInputEX.CONTEXT_KEY_NAME).toString();
		
		if (name.equals("")) {
			return getErrorStatus("名字不能为空");
		} else {
			Pattern pt = getNamePattern();
			if (pt != null) {
				if (!pt.matcher(name).matches()) {
					return getErrorStatus("名字不合法(" + pt.toString() + ")");
				}
			}
		}
		return Status.OK_STATUS;
	}
	
	/** 名字验证表达式, 默认为资源的名字规则; 子类可以重写此方法, 返回null代表不限制    */
	protected Pattern getNamePattern() {
		return IARESResource.RES_NAME_PATTERN;
	}
	


}
