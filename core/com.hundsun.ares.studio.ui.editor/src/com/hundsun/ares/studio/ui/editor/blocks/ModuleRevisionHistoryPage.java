package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.editable.IEditableUnit;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 模块属性中的修改记录页面
 * @author sundl
 *
 */
public class ModuleRevisionHistoryPage extends EMFESSFormPageWithValidate<ModuleProperty> {

	public ModuleRevisionHistoryPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	public boolean shouldLoad() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return CorePackage.Literals.MODULE_REVISION_HISTORY_LIST;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		return Constants.RevisionHistory.MODULE_REVISION_EXT_KEY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		// FIXME problem pool
		RevisionHistoryListViewerBlock block = new RevisionHistoryListViewerBlock(this, getEditingDomain(), getEditorSite(), getResource(),getProblemPool()) {

			@Override
			protected void createColumns(TableViewer _viewer) {
				/**修订时间*/
				{
					TableViewerColumn comlumn = new TableViewerColumn(_viewer, SWT.LEFT);
					comlumn.getColumn().setText("修订位置");
					comlumn.getColumn().setWidth(130);
					EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(CorePackage.Literals.REVISION_HISTORY__LOCATION);
//					/provider.setDiagnosticProvider(problemView);
					comlumn.setLabelProvider(provider);
					comlumn.setEditingSupport(new TextEditingSupport(_viewer, CorePackage.Literals.REVISION_HISTORY__LOCATION));
				}
				super.createColumns(_viewer);
			}
			
		};
		// FIXME 
		block.setEditableControl(new IEditableControl() {
			@Override
			public void refreshResourceReadonlyStatus() {
				
			}
			
			@Override
			public void refreshAllUnit(Map<Object, Object> context) {
				
			}
			
			@Override
			public void putUserStatus(String key, Object status) {
				
			}
			
			@Override
			public void notifyUserStatus(String key) {
				
			}
			
			@Override
			public boolean getResourceReadonlyStatus() {
				return false;
			}
			
			@Override
			public void addEditableUnit(IEditableUnit unit) {
				
			}
		});
		block.createControl(composite, toolkit);
		block.setInput(getModel());
		
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
	}
	
}
