package com.hundsun.ares.studio.ui.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IParent;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.ModuleRootProperty;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.ui.ARESResourceEditorInput;
import com.hundsun.ares.studio.ui.ARESUI;

public class OpenAndExpandARESElementAction extends Action implements ISelectionChangedListener{

	private IStructuredSelection selection;
	private IWorkbenchPage page;
	private TreeViewer viewer;
	
	public OpenAndExpandARESElementAction(IWorkbenchPage page, TreeViewer viewer) {
		this.page = page;
		setText("打开");
		setId(ARESUI.PLUGIN_ID + "openaction"); //$NON-NLS-1$
		this.viewer = viewer;
	}
	
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection s = event.getSelection();
		if(s instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection)s;
		} else {
			this.selection = null;
		}
	}
	
	protected IStructuredSelection getSelectioin() {
		return this.selection;
	}
	
	public void updateSelection(IStructuredSelection selection) {
		this.selection = selection;
	}

	public boolean isEnabled() {
		if(selection == null) {
			return false;
		}
		
		if(!(selection.getFirstElement() instanceof IARESElement)) {
			return false;
		}
		
//		IARESElement element = (IARESElement)selection.getFirstElement();
//		if(element.getResource().getType() == IResource.FILE) {
//			return true;
//		}
		
		return true;
	}
	
	public void run() {
		IARESElement element = (IARESElement)selection.getFirstElement();
		
		// 模块自动展开，并试图打开模块属性编辑器，如果没有，则创建。
		if (element instanceof IARESModule) {
			IARESModule module = (IARESModule)element;
			if (!module.isDefaultModule()) {
				IARESResource resoruce = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
				if ( (resoruce == null || !resoruce.exists())
						&& !module.isReadOnly()) {
					try {
						resoruce = module.createResource(IARESModule.MODULE_PROPERTY_FILE, new ModuleProperty());
					} catch (ARESModelException e) {
						e.printStackTrace();
						// TODO 提示错误
					}
				}
				if (resoruce != null && resoruce.exists())
					openResource(resoruce);
			}
		} else if (element instanceof IARESModuleRoot) {
			// 模块根如果配置了使用模块根属性文件，则要进行特殊处理
			IARESModuleRoot root = (IARESModuleRoot) element;
			ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
			IModuleRootDescriptor desc = reg.getModuleRootDescriptor(root.getType());
			if (desc.useProperty()) {
				IARESModule defaultModule = root.getModule(StringUtil.EMPTY_STR);
				if (defaultModule != null && defaultModule.exists()) {
					IARESResource proRes = defaultModule.getARESResource(IARESModuleRoot.PROPERTY_FILE);
					if (proRes == null || !proRes.exists() && !root.isArchive()) {
						try {
							proRes = defaultModule.createResource(IARESModuleRoot.PROPERTY_FILE, new ModuleRootProperty());
						} catch (ARESModelException e) {
							e.printStackTrace();
						}
					}
					if (proRes != null && proRes.exists())
						openResource(proRes);
				}
			}
		}
		
		if(element instanceof IParent) {
			expand(element);
			return;
		} 
		
		// Jar包里的文件
		if (element instanceof ArchiveARESResource) {
			ArchiveARESResource aresfile = (ArchiveARESResource)element;
			ARESResourceEditorInput input = new ARESResourceEditorInput(aresfile);
			boolean active = OpenStrategy.activateOnOpen();
			try {
				IEditorDescriptor editor = IDE.getEditorDescriptor(aresfile.getElementName());
				if (editor != null) {
					IDE.openEditor(page, input, editor.getId(), active);
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		} else
		// 资源或对应于一个文件的元素，打开对应的资源文件
		if(element.getResource() instanceof IFile) {
			IResource resource = element.getResource();
			IFile file = null;
			if(resource.getType() == IResource.FILE) {
				file = (IFile)element.getResource();
			} 			
			openFile(file);
		}
	}
	
	private void openFile(IFile file) {
		if(file.exists()) {
			try{
				IDE.openEditor(page, file, false);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void openResource(IARESResource resource) {
		IResource res = resource.getResource();
		if (res instanceof IFile && !resource.getRoot().isArchive())
			openFile((IFile)res);
	}
	
	protected void expand(IARESElement element) {
		viewer.setExpandedState(element, !viewer.getExpandedState(element));
	}
	
}
