/**
 * 源程序名称：RevisionHistoryListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;


import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.validate.EReferenceValidateUnit;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class RevisionHistoryListPage extends EMFFormPage {
	
	private static final Logger logger = Logger.getLogger(RevisionHistoryListPage.class);
	
	private RevisionHistoryListViewerBlock revisionHistoryListViewerBlock;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public RevisionHistoryListPage(EMFFormEditor editor,String id, String title) {
		super(editor, id, title);
	}

	@Override
	public void infoChange() {
		revisionHistoryListViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	public ColumnViewer getColumnViewer() {
		return revisionHistoryListViewerBlock.getColumnViewer();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#configureValidateControl()
	 */
	@Override
	protected void configureValidateControl() {
		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getFeature() == CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES
					|| notification.getNotifier() instanceof RevisionHistory) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setFocus() {
		super.setFocus();
		revisionHistoryListViewerBlock.getColumnViewer().getControl().setFocus();
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
		revisionHistoryListViewerBlock = new RevisionHistoryListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		revisionHistoryListViewerBlock.setEditableControl(getEditableControl());
		revisionHistoryListViewerBlock.createControl(body, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(revisionHistoryListViewerBlock);
		addPropertyListener(revisionHistoryListViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(revisionHistoryListViewerBlock);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			ColumnViewerBlock block = revisionHistoryListViewerBlock;
			ColumnViewer viewer = block == null ? null : block.getColumnViewer();
			if (viewer != null) {
				getSite().setSelectionProvider(viewer);
				logger.debug("Selection provider: " + viewer.hashCode() + " Site: " + getSite().hashCode());
			} else {
				logger.debug("Page actived, but no viewer!");
			}
		} 
	}
	
}
