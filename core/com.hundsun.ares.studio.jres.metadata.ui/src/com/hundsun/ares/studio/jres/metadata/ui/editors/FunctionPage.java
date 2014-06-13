package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.block.FunctionViewerBlock;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.validate.EReferenceValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class FunctionPage extends EMFFormPage {

	private FunctionViewerBlock functionViewerBlock;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public FunctionPage(EMFFormEditor editor,String id, String title) {
		super(editor, id, title);
	}

	@Override
	public void infoChange() {
		functionViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	public ColumnViewer getColumnViewer() {
		return functionViewerBlock.getColumnViewer();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureValidateControl()
	 */
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.MENU_LIST__FUNCTIONS));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getFeature() == MetadataPackage.Literals.MENU_LIST__FUNCTIONS
					|| notification.getNotifier() instanceof Function) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setFocus() {
		super.setFocus();
		functionViewerBlock.getColumnViewer().getControl().setFocus();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite body = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		createRevisionComposite(body, toolkit);
		body.setLayout(new FillLayout());
	}

	/**
	 * @param body
	 * @param toolkit
	 */
	private void createRevisionComposite(Composite body, FormToolkit toolkit) {
		functionViewerBlock = new FunctionViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		functionViewerBlock.setEditableControl(getEditableControl());
		functionViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(functionViewerBlock);
		addPropertyListener(functionViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(functionViewerBlock);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			ColumnViewer viewer = functionViewerBlock == null ? null : functionViewerBlock.getColumnViewer();
			if (viewer != null) {
				getSite().setSelectionProvider(viewer);
			} 
		} 
	}
}
