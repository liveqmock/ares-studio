package com.hundsun.ares.studio.logic.ui.wizard;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.provider.LogicUI;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard;

public class NewLogicFunctionWizard extends ModuleARESResourceNewWizard {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.wizard.ARESResourceWizard#getResType()
	 */
	@Override
	protected String getResType() {
		return "logicfunction";
	}
	

	@Override
	protected void initNewResourceInfo(Object info) {
		super.initNewResourceInfo(info);
		if(info instanceof LogicFunction){
			Map<Object, Object> context = getContext();
			String resname = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_NAME).toString();
			String resCName = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_CNAME).toString();
			if(StringUtils.isNotBlank(resname)){
				((LogicFunction)info).setName(resname);
			}
			if(StringUtils.isNotBlank(resCName)){
				((LogicFunction)info).setChineseName(resCName);
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(LogicUI.PLUGIN_ID, "icons/function.gif").createImage());
	}

}
