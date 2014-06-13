/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.List;

import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;

/**
 * @author lvgao
 *
 */
public abstract class ModuleARESResourceNewWizard extends ARESResourceNewWizard{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.newwizard.ARESResourceNewWizard#addPages()
	 */
	@Override
	public void addPages() {
		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getResType());		
		ARESResourceNewWizardPage page = new ARESResourceNewWizardPage("新建一个" + resDescriptor.getName(), workbench, selectedElement, getResType()){
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.ARESResourceNewWizardPage#addValidators(java.util.List)
			 */
			@Override
			protected void addValidators(List<IWizardPageValidator> validators) {
				validators.add(new ElementSelectionValidator());
				validators.add(new ReourceNameValidator());
				validators.add(new ReourceNameModuleDuplicateValidator());
				validators.add(new GroupNameValidator());
			}
		};
		page.setDescription("新建一个" + resDescriptor.getName());
		addPage(page);
		
		//添加可往上下文中添加内容的页面
		addContextPage(page);
	}
}
