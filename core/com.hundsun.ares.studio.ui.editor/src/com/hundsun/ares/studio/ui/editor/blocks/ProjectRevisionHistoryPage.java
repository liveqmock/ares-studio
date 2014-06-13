package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.ui.editor.ProjectPropertyEditor;
import com.hundsun.ares.studio.ui.editor.validate.EMFAllValidateUnit;

/**
 * 项目属性的修改记录页面
 * @author sundl
 *
 */
public class ProjectRevisionHistoryPage extends EMFESSFormPageWithValidate<ARESProjectProperty> {

	public ProjectRevisionHistoryPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	public boolean shouldLoad() {
		return true;
	}

	@Override
	protected EObject createModel() {
		EObject model = super.createModel();
		ProjectPropertyEditor editor = (ProjectPropertyEditor) getEditor();
		IARESElement element = editor.getARESElement();
		model.eResource().setURI(URI.createPlatformResourceURI(element.getResource().getFullPath().toString(), true));
		return model;
	}
	
	@Override
	protected EClass getEClass() {
		return CorePackage.Literals.PROJECT_REVISION_HISTORY_PROPERTY;
	}
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EMFAllValidateUnit(getModel()));
//		getValidateControl().setContext(ValidateUtil.getValidateContext(getResource()));
	}
	
	@Override
	protected String getMapKey() {
		return Constants.RevisionHistory.PROJECT_REVISION_EXT_KEY;
	}

	@Override
	protected void createSections(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		ProjectRevisionHistoryListViewerBlock block = new ProjectRevisionHistoryListViewerBlock(this, getEditingDomain(),getProblemPool());
		block.createControl(composite, toolkit);
		block.setInput(getModel());
		
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
	}

}
