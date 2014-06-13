/**
 * 源程序名称：DictionaryListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

//import org.eclipse.core.commands.Command;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.BasicDataValidateUnit;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.LinkDetailViewerBlock;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.SingleTableListViewerBlock;
import com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;
import com.hundsun.ares.studio.validate.ValidateUtil;
/**
 * 数据字典编辑器详细界面
 *
 */
public class MasterLinkTablePage extends AbstractMetadataFormPage {
		
	private static Logger logger = Logger.getLogger(MasterLinkTablePage.class);
	
	private SingleTableListViewerBlock masterListViewerBlock;
	private LinkDetailViewerBlock linkDetailViewerBlock;
	
	IKeyTableLocator locator;
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MasterLinkTablePage(EMFFormEditor editor, String id, String title,IKeyTableLocator locator) {
		super(editor, id, title);
		this.locator = locator;
	}
	
	/**
	 * @return the dictionaryListViewerBlock
	 */
	public SingleTableListViewerBlock getMasterListViewerBlock() {
		return masterListViewerBlock;
	}
	
	/**
	 * @return the dictionaryDetailViewerBlock
	 */
	public LinkDetailViewerBlock getSlaveDetailViewerBlock() {
		return linkDetailViewerBlock;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataListPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			Object obj = notification.getNotifier();
			if (obj instanceof DynamicEObjectImpl) {
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
		EPackage epackage = null;
		try {
			epackage = BasicDataEpackageFactory.eINSTANCE.createEPackage(getEditor().getARESResource());
		} catch (Exception e) {
			logger.error("编辑基础数据时，读取EPackage失败", e);
			return;
		}
		
		SashForm sashForm = new SashForm(body, SWT.VERTICAL);
		
		masterListViewerBlock = new SingleTableListViewerBlock(this, getEditingDomain(), 
				getSite(), 
				getEditor().getARESResource(),
				getProblemPool(),
				epackage,
				IBasicDataEpacakgeConstant.MasterItem);
		masterListViewerBlock.setEditableControl(getEditableControl());
		masterListViewerBlock.createControl(sashForm, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(masterListViewerBlock);
		
		linkDetailViewerBlock = new LinkDetailViewerBlock(getEditingDomain(), 
				getEditor().getARESResource(), 
				getInfo(),
				getProblemPool(),
				epackage,
				locator
				);
		linkDetailViewerBlock.setEditableControl(getEditableControl());
		linkDetailViewerBlock.createControl(sashForm, toolkit);
		getEditor().getActionBarContributor().addGlobalActionHandlerProvider(linkDetailViewerBlock);

		sashForm.setWeights(new int[] { 6, 4 });
		
		masterListViewerBlock.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
					Object element = ((IStructuredSelection)selection).getFirstElement();
					if (!(element instanceof MetadataCategory)) {
						linkDetailViewerBlock.setInput(element);
					}
					
				}
			}
		});
		
		addPropertyListener(masterListViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(masterListViewerBlock);
		
		addPropertyListener(linkDetailViewerBlock);
		getEditingDomain().getCommandStack().addCommandStackListener(linkDetailViewerBlock);
	}

	@Override
	public void infoChange() {
		masterListViewerBlock.setInput(getInfo());
		super.infoChange();
		
//		masterListViewerBlock.getOperationControl().setData(getInfo());
//		masterListViewerBlock.getOperationControl().setContext(createScriptContext());
	}
	
	@Override
	public void dispose() {
		removePropertyListener(masterListViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(masterListViewerBlock);
		
		removePropertyListener(linkDetailViewerBlock);
		getEditingDomain().getCommandStack().removeCommandStackListener(linkDetailViewerBlock);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.AbstractMetadataFormPage#getViewerBlock()
	 */
	@Override
	protected ColumnViewerBlock getViewerBlock() {
		return masterListViewerBlock;
	}
	
	/**
	 *重新关联
	 */
	public void relink(){
		ISelection selection = masterListViewerBlock.getSelection();
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
			Object element = ((IStructuredSelection)selection).getFirstElement();
			if (!(element instanceof MetadataCategory)) {
				linkDetailViewerBlock.setInput(element);
			}
		}
	}
	
	@Override
	protected void configureValidateControl() {
//		getValidateControl().addValidateUnit(new EReferenceValidateUnit(getInfo(), MetadataPackage.Literals.METADATA_RESOURCE_DATA__ROOT));
		getValidateControl().addValidateUnit(new BasicDataValidateUnit(getInfo(),IBasicDataEpacakgeConstant.Attr_Master_Items));
		getValidateControl().addValidateUnit(new BasicDataValidateUnit(getInfo(),IBasicDataEpacakgeConstant.Attr_Info_Items));
		getValidateControl().setContext(ValidateUtil.getValidateContext(getEditor().getARESResource()));
		getValidateControl().getContext().put(IBasicDataEpacakgeConstant.Info_Table_Locator_Helper, locator);
		
	}
	
}
