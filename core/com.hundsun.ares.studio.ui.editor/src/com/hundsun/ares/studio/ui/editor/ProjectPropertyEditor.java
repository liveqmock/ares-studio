package com.hundsun.ares.studio.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.core.model.converter.ProjectPropertyConverter;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.ui.editor.sync.IFileSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESDefaultSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESEditorSyncManager;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;


public class ProjectPropertyEditor extends AbstractHSExtendPointFormEditor<ARESProjectProperty> {

	private ProjectOverviewPage overview;
	private IFileSyncnizeUnit fileSyncUnit;
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
//		setUseUndoSupport(false);
		if (input instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput)input).getFile();
			info = new ARESProjectProperty();
			try {
				ProjectPropertyConverter.getInstance().read(file.getContents(), info);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileSyncUnit = new JRESDefaultSyncnizeUnit(this);
		JRESEditorSyncManager.getInstance().addSyncUnit(fileSyncUnit);
		
	}
	
	@Override
	protected Class getModelType() {
		return com.hundsun.ares.studio.internal.core.ARESProjectProperty.class;
	}
	
	@Override
	public void updatePartName() {
		if (isReadOnly()) {
			setPartName("项目属性(只读)");
		} else {
			setPartName("项目属性");
		}
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		 fileSyncUnit.beforeSave();
		 if (isReadOnly()) {
				MessageDialog.openInformation(getSite().getShell(), "无法保存", "当前资源是只读状态，无法进行保存");
		} 
		 else{
			 for (Object page : pages) {
					if (page instanceof ExtendPageWithMyDirtySystem) {
						try {
							((ExtendPageWithMyDirtySystem) page).doSave();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				IEditorInput input = getEditorInput();
				if (input instanceof IFileEditorInput) {
					IFile file = ((IFileEditorInput)input).getFile();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					try {
						ProjectPropertyConverter.getInstance().write(bos, info);
						file.setContents(new ByteArrayInputStream(bos.toByteArray()), true, true, monitor);
						dirty.setValue(false);
					} catch (Exception e) {
						 e.printStackTrace();
						 ARESEditorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ARESEditorPlugin.PLUGIN_ID, "写项目属性文件出错", e));
					}
				} 
		 }
		
	}	
	
	@Override
	protected void addPages() {
		overview = new ProjectOverviewPage(this);
		try {
			addPage(overview);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.addPages();
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.BasicAresFormEditor#dispose()
	 */
	@Override
	public void dispose() {
		JRESEditorSyncManager.getInstance().removeSyncUnit(fileSyncUnit);
		super.dispose();
		
	}

}
