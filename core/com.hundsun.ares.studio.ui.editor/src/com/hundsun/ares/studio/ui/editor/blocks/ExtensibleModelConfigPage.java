/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;

/**
 * @author gongyf
 *
 */
public class ExtensibleModelConfigPage extends EMFExtendSectionScrolledFormPage<ARESProjectProperty> {

	private Text configText;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ExtensibleModelConfigPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		
	}
	
	// FIXME 此处为兼容考虑，吧这常量在框架层代码进行了重复定义
	// 后续的处理，凡是需要扩展页面的工程都需要自己加上ARESCore.EXTEND_NATURE
	private static String MODULE_NATURE = "com.hundsun.ares.studio.jres.core.modulenature";
	private static String PRODUCT_NATURE = "com.hundsun.ares.studio.jres.core.productnature";
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
		
		try {
			IProject project = getARESProject().getProject();
			if (project.hasNature(ARESCore.EXTEND_NATURE)) {
				return true;
			} else {
				if(project.hasNature(MODULE_NATURE) || project.hasNature(PRODUCT_NATURE)
						|| project.hasNature("com.hundsun.ares.studio.jres.jresnature")) {
					return true;
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return CorePackage.Literals.EXTENSIBLE_MODEL_CONFIG_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		return Constants.EXTENSIBLE_MODEL_CONFIG_PROPERTY_KEY;
	}

	@Override
	protected ExtensibleModelConfigProperty getModel() {
		return (ExtensibleModelConfigProperty) super.getModel();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		
		FormToolkit toolkit = managedForm.getToolkit();
		Composite body = managedForm.getForm().getBody();
		
		configText = toolkit.createText(body, StringUtils.defaultString(getModel().getXml()) , SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		GridLayoutFactory.fillDefaults().applyTo(body);
		GridDataFactory.fillDefaults().hint(20, 20).grab(true, true).applyTo(configText);
		
		//数据绑定
		databinding();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.HSComponentbasedFormPage#setEditable(boolean)
	 */
	@Override
	public void setEditable(boolean editable) {
		configText.setEditable(editable);
		super.setEditable(editable);
	}
	
	private void databinding() {
		getBindingContext().bindValue(SWTObservables.observeText(configText, SWT.Modify), 
			EMFEditObservables.observeValue(getEditingDomain(), getModel(), CorePackage.Literals.EXTENSIBLE_MODEL_CONFIG_PROPERTY__XML));
	}

}
