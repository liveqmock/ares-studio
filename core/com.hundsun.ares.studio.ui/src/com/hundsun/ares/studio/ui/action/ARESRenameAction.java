/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RefactoringSaveHelper;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameARESResourceProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameARESResourceWizard;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameModuleProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameProjectProcessor;
import com.hundsun.ares.studio.internal.ui.refactoring.RenameSelectionState;

/**
 * 重命名
 * @author sundl
 */
public class ARESRenameAction extends SelectionListenerAction {

	private IWorkbenchSite site;
	
	public ARESRenameAction(IWorkbenchSite site) {
		super("重命名");	
		this.site = site;
	}

	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		if (selection.size() == 1) {
			Object obj = selection.getFirstElement();
			
			// 模块根可否重命名根据扩展点的配置决定
			if (obj instanceof IARESModuleRoot) {
				IARESModuleRoot moduleRoot = (IARESModuleRoot) obj;
				ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
				IModuleRootDescriptor desc = reg.getModuleRootDescriptor(moduleRoot.getType());
				return desc != null && desc.isRenameable() && !moduleRoot.isArchive();
			}
			
			// 模块和资源只要不是在引用包中就可以重命名
			IARESModuleRoot root = null;
			if (obj instanceof IARESResource) {
				root = ((IARESResource) obj).getRoot();
				ARESResRegistry reg = ARESResRegistry.getInstance();
				IResDescriptor desc = reg.getResDescriptor((IARESResource) obj);
				return desc != null && desc.isRenameable() && root != null && !root.isArchive();
			} else if (obj instanceof IARESModule) {
				root = ((IARESModule) obj).getRoot();
				return root != null && !root.isArchive();
			}
			/*if (obj instanceof IProject){
				return ARESProject.hasARESNature((IProject)obj);
			}*/
			
		}
		return false;
	}

	@Override
	public void run() {
		Object obj = getStructuredSelection().getFirstElement();
		ARESRenameProcessor processor = null;
		
		if (obj instanceof IProject){
			 obj= ARESCore.create((IProject)obj);
			processor = new RenameProjectProcessor((IARESElement) obj);
		}else if (obj instanceof IARESResource) {
			processor = new RenameARESResourceProcessor((IARESResource) obj);
		} else if (obj instanceof IARESModule) {
			processor = new RenameModuleProcessor((IARESElement) obj);
		}/* else if (obj instanceof IARESModuleRoot) {
			processor = new RenameModuleRootProcessor((IARESElement) obj);
		}*/
		RenameSelectionState state = new RenameSelectionState(obj);
		RenameRefactoring refactoring = new RenameRefactoring(processor);
		RenameARESResourceWizard wizard = new RenameARESResourceWizard(refactoring);
		RefactoringWizardOpenOperation op = new RefactoringWizardOpenOperation(wizard);
		RefactoringSaveHelper refactoringSaveHelper = new RefactoringSaveHelper();
		
		
		try {
			if(refactoringSaveHelper.saveEditors(site.getShell())){
				op.run(site.getShell(), "重命名");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object newElement = processor.getNewElement();
		if (newElement != null)
			state.restore(newElement);
	}

}
