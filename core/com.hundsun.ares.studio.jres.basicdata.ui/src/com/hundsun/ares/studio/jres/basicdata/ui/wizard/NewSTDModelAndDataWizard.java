package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.converter.IModelConverter;
import com.hundsun.ares.studio.core.model.converter.IModelConverter2;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizard;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ElementSelectionValidator;
import com.hundsun.ares.studio.ui.newwizard.IWizardPageValidator;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameModuleRootDuplicateValidator;
import com.hundsun.ares.studio.ui.newwizard.ReourceNameValidator;

public class NewSTDModelAndDataWizard extends ARESResourceNewWizard {

	@Override
	protected String getResType() {
		return IBasicDataRestypes.STDModelAndData;
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
		};
		page.setDescription("新建一个" + resDescriptor.getName());
//		page.setNewName(initText_Name);
		addPage(page);
		addContextPage(page);
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizard#createResource(java.util.Map)
	 */
	@Override
	protected boolean createResource(Map<Object, Object> context) {
		// 从上下文中获取创建资源所需要的数据
		String resname = context
				.get(ARESResourceNewWizardPage.CONTEXT_KEY_NAME).toString();
		String resCName = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_CNAME).toString();
		IARESElement selection = (IARESElement) context
				.get(ARESResourceNewWizardPage.CONTEXT_KEY_SELECTION);
		String restype = context.get(
				ARESResourceNewWizardPage.CONTEXT_KEY_RES_TYPE).toString();

		// 新的资源全名
		String resFullName = String.format("%s.%s", resname, restype);

		long t1 = System.currentTimeMillis();
		IResDescriptor resDescriptor = ARESResRegistry.getInstance()
				.getResDescriptor(restype);
		if (resDescriptor != null) {
			Object info = resDescriptor.createInfo();
			initNewResourceInfo(info);
			IModelConverter converter = resDescriptor.getConverter();
			if (selection.getResource().getType() == IResource.FOLDER) {
				IFolder folder = (IFolder) selection.getResource();
				IFile file = folder.getFile(resFullName);
				if (!file.exists()) {
					try {
						IARESResource resource = (IARESResource) ARESCore
								.create(file);
						file.create(
								new ByteArrayInputStream(
										((IModelConverter2) converter).write(
												resource, info)), true, null);
						StandardFieldModelAndData standardFieldModelAndData = (StandardFieldModelAndData) resource.getInfo(EObject.class);
						if(StringUtils.isNotBlank(resname)){
							standardFieldModelAndData.getData().setName(resname);
						}
						if(StringUtils.isNotBlank(resCName)){
							standardFieldModelAndData.getData().setChineseName(resCName);
						}
						resource.save(standardFieldModelAndData, true, null);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.resource = file;
				}
			}
		}
		long t2 = System.currentTimeMillis();
		logger.info("资源： " + resFullName + " 创建成功，用时" + (t2 - t1) + "ms.");
		return false;
	}

}
