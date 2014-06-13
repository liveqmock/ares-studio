package com.hundsun.ares.studio.atom.ui.wizard.atomservice;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.provider.AtomUI;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard;

public class NewAtomServiceWizard  extends ModuleARESResourceNewWizard {

	@Override
	protected String getResType() {
		return "atomservice";
	}
	
	@Override
	protected void initNewResourceInfo(Object info) {
		super.initNewResourceInfo(info);
		if(info instanceof AtomService){
			Map<Object, Object> context = getContext();
			String resname = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_NAME).toString();
			String resCName = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_CNAME).toString();
			if(StringUtils.isNotBlank(resname)){
				((AtomService)info).setName(resname);
			}
			if(StringUtils.isNotBlank(resCName)){
				((AtomService)info).setChineseName(resCName);
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(AtomUI.PLUGIN_ID, "icons/service.gif").createImage());
	}
}