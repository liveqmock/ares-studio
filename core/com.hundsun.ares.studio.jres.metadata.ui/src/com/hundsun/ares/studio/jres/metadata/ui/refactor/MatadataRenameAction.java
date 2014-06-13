/**
 * 源程序名称：MatadataRenameAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RefactoringSaveHelper;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameARESResourceWizard;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * @author qinyuan
 *
 */
public class MatadataRenameAction extends Action {
	
	private ColumnViewer viewer;
	private IEditorPart part;
	private IARESResource iaresResource;
	private EditingDomain editingDomain;
	
	/**
	 * @param editingDomain 
	 * @param iaresResource 
	 * 
	 */
	public MatadataRenameAction(EditingDomain editingDomain,String name, IEditorPart part,ColumnViewer viewer, IARESResource iaresResource) {
		super("重命名" + name);	
		this.part = part;
		this.viewer = viewer;
		this.iaresResource = iaresResource;
		this.editingDomain = editingDomain;
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/rename.png"));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		if (part.isDirty()) {
			// 重构必须要求编辑器已经保存
			if (MessageDialog.openQuestion(part.getSite().getShell(), "保存", "重命名之前需要进行保存，是否继续？")) {
				part.doSave(new NullProgressMonitor());
			} else {
				return;
			}
		}
		
		Object obj = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
		ARESRenameProcessor processor = null;
		
		if(obj instanceof MetadataItem) {
			MetadataItem item = (MetadataItem)obj;
			
			processor = new MatadataItemRenameProcessor(item, iaresResource,editingDomain);
			
			RenameRefactoring refactoring = new RenameRefactoring(processor);
			RenameARESResourceWizard wizard = new RenameARESResourceWizard(refactoring);
			RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
			try {
				RefactoringSaveHelper refactoringSaveHelper = new RefactoringSaveHelper();
				if(refactoringSaveHelper.saveEditors(part.getSite().getShell())){
					if (op.run(part.getSite().getShell(), "重命名") == IDialogConstants.OK_ID) {
						part.doSave(new NullProgressMonitor());
					}
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		if (iaresResource.getLib() != null) {
			return false;
		}
		Object obj = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
		
		try {
			if (editingDomain.isReadOnly(iaresResource.getInfo(MetadataResourceData.class).eResource())) {
				return false;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		if(obj instanceof MetadataItem ) {
			return true;
		}
		return false;
	}
}
