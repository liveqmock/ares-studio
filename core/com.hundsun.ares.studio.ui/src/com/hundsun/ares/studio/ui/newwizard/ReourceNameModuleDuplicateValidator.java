/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author lvgao
 *
 */
public class ReourceNameModuleDuplicateValidator extends BaseWizardPageValidator  implements IWizardPageValidator{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.IWizardPageValidator#validate(java.util.Map)
	 */
	@Override
	public IStatus validate(Map<Object, Object> context) {
		Object selection = context.get(CommonElementSelectionPageEX.CONTEXT_KEY_SELECTION);
		String newName = context.get(ElementSelectionWizardPageWithNameInputEX.CONTEXT_KEY_NAME).toString();
		String resType = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_RES_TYPE).toString();
		if (!(selection instanceof IARESModule)) {
			return getErrorStatus("请选择一个应用");
		}

		IARESModule module = (IARESModule) selection;
		IARESResource exsit = module.findResource(newName, resType);
		if (exsit != null && exsit.exists()) {
			return getErrorStatus("同名资源已经存在！");
		}
		return Status.OK_STATUS;
	}
	

}
