package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.metadata.ui.block.FunctionProxyViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.block.MenuListViewerBlock;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

public class MenuListPage extends AbstractMetadataFormPage {

	private MenuListViewerBlock menuListViewerBlock;
	private FunctionProxyViewerBlock functionProxyViewerBlock;
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MenuListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * @return the menuListViewerBlock
	 */
	public MenuListViewerBlock getMenuListViewerBlock() {
		return menuListViewerBlock;
	};
	
	/**
	 * @return the functionProxyViewerBlock
	 */
	public FunctionProxyViewerBlock getFunctionProxyViewerBlock() {
		return functionProxyViewerBlock;
	};
	
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getNotifier() instanceof FunctionProxy) {
				return true;
			}

			if (notification.getFeature() == MetadataPackage.Literals.METADATA_CATEGORY__CHILDREN
					|| notification.getFeature() == MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS
					|| notification.getNotifier() instanceof MetadataItem
					|| notification.getNotifier() instanceof MetadataCategory) {
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#createMetadataComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	@Override
	protected void createMetadataComposite(Composite body, FormToolkit toolkit) {
		
		SashForm sashForm = new SashForm(body, SWT.VERTICAL);
		
		menuListViewerBlock = new MenuListViewerBlock(this, getEditingDomain(), getSite(), getEditor().getARESResource(), getProblemPool());
		menuListViewerBlock.setEditableControl(getEditableControl());
		menuListViewerBlock.createControl(sashForm, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(menuListViewerBlock);
		
		functionProxyViewerBlock = new FunctionProxyViewerBlock(getEditingDomain(), getEditor().getARESResource(), getProblemPool());
		functionProxyViewerBlock.setEditableControl(getEditableControl());
		functionProxyViewerBlock.createControl(sashForm, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(functionProxyViewerBlock);

		sashForm.setWeights(new int[] { 6, 4 });
		
		menuListViewerBlock.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					if (!selection.isEmpty()) {
						Object element = ((IStructuredSelection)selection).getFirstElement();
						functionProxyViewerBlock.setInput(element);
					}else {
						functionProxyViewerBlock.setInput(null);
					}
				}
			}
		});
		
		addPropertyListener(menuListViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(menuListViewerBlock);
		
		addPropertyListener(functionProxyViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(functionProxyViewerBlock);
	}

	@Override
	public void infoChange() {
		menuListViewerBlock.setInput(getInfo());
		super.infoChange();
	}
	
	@Override
	public void dispose() {
		removePropertyListener(menuListViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(menuListViewerBlock);
		
		removePropertyListener(functionProxyViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(functionProxyViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return menuListViewerBlock;
	}

}
