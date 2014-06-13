/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;


import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.part.ResourceTransfer;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.ui.refactoring.CopyElementsResourceOperation;
import com.hundsun.ares.studio.ui.ARESElementTransfer;
import com.hundsun.ares.studio.ui.ARESResourceCategory;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

/**
 * 粘贴操作
 * @author sundl
 */
public class ARESPasteAction extends SelectionListenerAction {

	protected Shell shell;
	protected Clipboard clipboard;
	
	public ARESPasteAction(Shell shell, Clipboard clipboard) {
		super("粘贴");
		this.shell = shell;
		this.clipboard = clipboard;
		ISharedImages sharedImages = getWorkbenchSharedImages();
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
	}
	
	private static ISharedImages getWorkbenchSharedImages() {
		return PlatformUI.getWorkbench().getSharedImages();
	}
	
	@Override
    protected boolean updateSelection(IStructuredSelection selection) {
		// 粘贴的时候只能选中一个目标
		if (selection.toArray().length != 1) {
			return false;
		}
		
		// 初步判断target是否支持粘贴
		IARESModule module = null;
		IARESModuleRoot root = null;
		IARESProject project = null;
		
		boolean isRoot = false;
		boolean isModule = false;
		boolean isCategory = false;
		
		Object obj = selection.getFirstElement();
		if (obj instanceof IARESElement) {
			IARESElement element = (IARESElement)obj;
			switch (element.getElementType()) {
			case IARESElement.COMMON_MODULE_ROOT:
				root = (IARESModuleRoot)element;
				if (root.isArchive())
					return false;
				
				project = root.getARESProject();
				isRoot = true;
				break;
			case IARESElement.COMMON_MODULE:
				module = (IARESModule)element;
				root = module.getRoot();
				project = module.getARESProject();
				isModule = true;
				if (root.isArchive())
					return false;
				break;
			}			
		} else if (obj instanceof ARESResourceCategory) {
			module = ((ARESResourceCategory)obj).getModule();
			root = module.getRoot();
			project = root.getARESProject();
			isCategory = true;
			// 引用资源包上不能粘贴
			if (root.isArchive())
				return false;
		}
		
		// 其他节点上都无法粘贴
		if (!(isRoot || isModule || isCategory))
			return false;
		
		// 进一步根据内容进行判断
		// 首先尝试IResource
		IResource[] resources = getResourceInClipboard(clipboard);
		IARESElement[] elements = null;
		if (resources == null || resources.length == 0) {
			elements = getElementsInClipboard(clipboard);
		} else {
			elements = ARESElementUtil.toARESElement(resources);
			if (resources.length > 0 && elements.length != resources.length)
				return false;
		}
		
		if (elements != null && elements.length > 0) {
			// if (resources.length == elements.length) {
				// 如果选中的是资源
				if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
					IARESResource[] aresResources = ARESElementUtil.toARESResource(elements);
					String type = ARESElementUtil.getResourcesType(aresResources);
					if (obj instanceof IARESModule) {
						return ModuleRootType2ResTypeMap.getInstance().isAllowed(root.getType(), type);
					} else if (obj instanceof ARESResourceCategory) {
						ARESResourceCategory cat = (ARESResourceCategory) obj;
						boolean archive = cat.getModule().getRoot().isArchive();
						return !archive && cat.isResTypeAllowed(type);
					}
					return false;
				} else if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
					// 如果是模块
					IARESModule[] modules = ARESElementUtil.toARESModule(elements);
					if (obj instanceof IARESModule || obj instanceof IARESModuleRoot) {
						return RefactoringUtil.canPaste(modules, (IARESElement)obj);
					} 
					return false;
				}
//			} else {
//				return false;
//			}
		} 
		
		// 尝试File
		FileTransfer fileTransfer = FileTransfer.getInstance();
	    String[] fileData = (String[]) clipboard.getContents(fileTransfer);
	    
	    return fileData != null;
	}

	@Override
	public void run() {
		Clipboard cb = this.clipboard;
		
		Object selection = getStructuredSelection().getFirstElement();
		IARESElement dest = null;
		if (selection instanceof IARESElement) {
			dest = (IARESElement)selection;
		} else if (selection instanceof ARESResourceCategory) {
			dest = ((ARESResourceCategory)selection).getModule();
		}
		
		// first, try resource
        IResource[] resourceData = getResourceInClipboard(cb);
        IARESElement[] elements = null;
        if (resourceData != null && resourceData.length > 0) {
        	elements = ARESElementUtil.toARESElement(resourceData);
        } else {
        	elements = getElementsInClipboard(cb);
        }
        
        if (elements != null && elements.length > 0) {
    		CopyElementsResourceOperation op = new CopyElementsResourceOperation(shell);
    		op.copyElements(elements, dest);
    		return;
    	}
        
        // 操作系统直接
    	FileTransfer fileTransfer = FileTransfer.getInstance();
        String[] fileData = (String[]) clipboard.getContents(fileTransfer);

        if (fileData != null && (allFiles(fileData) || allFolders(fileData))) {
            // enablement should ensure that we always have access to a container
            IContainer container = getContainer();
            if (container == null)
            	return;
            
            CopyFilesAndFoldersOperation operation = new CopyFilesAndFoldersOperation(this.shell);
            operation.copyFiles(fileData, container);
        }
	}
	
	protected IContainer getContainer() {
		Object selection = getStructuredSelection().getFirstElement();
		IARESElement dest = null;
		if (selection instanceof IARESElement) {
			dest = (IARESElement)selection;
		} else if (selection instanceof ARESResourceCategory) {
			dest = ((ARESResourceCategory)selection).getModule();
		}
		
		if (dest instanceof IARESModule) {
			return (IContainer) ((IARESModule)dest).getResource();
		} else if (dest instanceof IARESModuleRoot) {
			return (IContainer) ((IARESModuleRoot) dest).getResource();
		}
		
		return null;
	}
	
	protected IResource[] getResourceInClipboard(Clipboard clipboard) {
		ResourceTransfer resTransfer = ResourceTransfer.getInstance();
        IResource[] resourceData = (IResource[]) clipboard.getContents(resTransfer);
        return resourceData;
	}
	
	protected IARESElement[] getElementsInClipboard(Clipboard clipboard) {
		ARESElementTransfer resTransfer = ARESElementTransfer.getInstance();
        IARESElement[] elementsData = (IARESElement[]) clipboard.getContents(resTransfer);
        return elementsData;
	}
	
	protected boolean allFolders(String[] files) {
		for (String path : files) {
			File file = new File(path);
			if (!file.isDirectory())
				return false;
		}
		return true;
	}
	
	protected boolean allFiles(String[] files) {
		for (String path : files) {
			File file = new File(path);
			if (file.isDirectory())
				return false;
		}
		return true;
	}
	
}
