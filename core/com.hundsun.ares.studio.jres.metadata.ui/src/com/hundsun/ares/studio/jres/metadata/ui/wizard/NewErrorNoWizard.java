package com.hundsun.ares.studio.jres.metadata.ui.wizard;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizard;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ElementSelectionValidator;
import com.hundsun.ares.studio.ui.newwizard.IWizardPageValidator;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameModuleRootDuplicateValidator;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameValidator;

public class NewErrorNoWizard  extends ARESResourceNewWizard {

	@Override
	protected String getResType() {
		return IMetadataResType.ErrNo;
	}
	
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
				validators.add(new ReourceNameModuleRootDuplicateValidator());
			}
			
			@Override
			protected void createText(Composite composite) {
				super.createText(composite);
				
				//设置只读
				txt_CName.setText("标准错误号");
				txt_CName.setEnabled(false);
				txt_Name.setText("errorno");
				txt_Name.setEnabled(false);
				
			}
		};
		page.setDescription("新建一个" + resDescriptor.getName());
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/errornoFile.png").createImage());
		addPage(page);
		addContextPage(page);
	}
}
