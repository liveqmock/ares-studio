/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.EventObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleExtensibleModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;

/**
 * 模块属性的扩展页面
 * @author liaogc
 */
public class ExtensibledModulePropertyPage extends EMFESSFormPageWithValidate<ModuleProperty>{
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ExtensibledModulePropertyPage(FormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	private ExtensibleModelComposite emc;

	@Override
	protected ModuleExtensibleModel getModel() {
		return (ModuleExtensibleModel) super.getModel();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = createSectionWithTitle(managedForm.getForm().getBody(), toolkit, "扩展信息", true);
		emc = new ExtensibleModelComposite(section, toolkit);
		emc.setProblemPool(getProblemPool());
		
		
		emc.setInput(getResource(), getModel());
		
		section.setClient(emc);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#processModelOnCreate(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void processModelOnCreate(EObject model) {
		super.processModelOnCreate(model);
		ExtensibleModelUtils.extend(getResource(), (ExtensibleModel) model, false);
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return CorePackage.Literals.MODULE_EXTENSIBLE_MODEL;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		return Constants.MODULE_EXTENSIBLEMODEL_KEY;
	}
}
