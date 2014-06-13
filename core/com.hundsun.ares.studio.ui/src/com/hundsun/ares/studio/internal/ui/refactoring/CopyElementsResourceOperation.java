/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.undo.AbstractWorkspaceOperation;
import org.eclipse.ui.ide.undo.CopyResourcesOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.core.CopyResourceElementsOperation;

/**
 * 复制ARES元素和对应的资源的操作，目前只支持复制资源。
 * @author sundl
 */
public class CopyElementsResourceOperation {

	private static final String INVALIDE_DESTINATION = "不能复制到这个位置";
	
	private Shell msgShell;
	
	private boolean canceled = false;
	private boolean alwaysOverwrite = false;
	
	static String getAutoNewNameFor(IARESElement element) {
		int counter = 1;
		
		if (element instanceof IARESResource) {
			IARESResource aresResource = (IARESResource)element;
			IARESModule module = aresResource.getModule();
			String type = aresResource.getType();
			String oldName = aresResource.getName();
			while (true) {
				String newName = oldName + "_" + counter;
				IARESResource res = module.getARESResource(newName, type);
				if (!(res != null && res.exists())) {
					return newName;
				}
				counter++;
			}
		} else if (element instanceof IARESModule) {
			IARESModule module = (IARESModule) element;
			String oldName = module.getElementName();
			IARESModuleRoot root = module.getRoot();
			while (true) {
				String newName = oldName + "_" + counter;
				IARESModule newModule = root.getModule(newName);
				if (!newModule.exists()) {
					return newModule.getShortName();
				}
				counter++;
			}
		}
		
		return null;
	}
	
	public CopyElementsResourceOperation(Shell shell) {
		this.msgShell = shell;
	}

	/**
	 * Returns whether this operation is able to perform on-the-fly
	 * auto-renaming of resources with name collisions.
	 * 
	 * @return <code>true</code> if auto-rename is supported, and
	 *         <code>false</code> otherwise
	 */
	protected boolean canPerformAutoRename() {
		return true;
	}
	
	public void copyElements(final IARESElement[] elemenets, final IARESElement destination) {

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				copyElements(elemenets, destination, monitor);
			}
		};

		try {
			PlatformUI.getWorkbench().getProgressService().run(false, true, op);
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}

	}
	
	/**
	 * 复制指定的元素到指定的位置
	 * @param elements
	 * @param destination
	 */
	public void copyElements(IARESElement[] elements, IARESElement destination, IProgressMonitor monitor) {
		if (destination.exists() && ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)
				&& destination instanceof IARESModule) {
			IARESElement parent = ARESElementUtil.getParent(elements);
			copyElements(elements, parent, destination, monitor);
		} else if (destination.exists() && ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
			IARESElement parent = ARESElementUtil.getModulesParent(ARESElementUtil.toARESModule(elements));
			copyElements(elements, parent, destination, monitor);
		}
	}
	
	private void copyElements(IARESElement[] elements, IARESElement parent, IARESElement destination, IProgressMonitor monitor) {
		if (parent.equals(destination)) {
			performCopyWithAutoRename(elements, destination, monitor);
		} else {
			IARESElement[] copyResources = validateNoNameCollisions(elements, destination);
			if (copyResources != null && copyResources.length > 0) {
				performCopy2(copyResources, destination);
			}
		}
	}
	
	private void performCopyWithAutoRename(IARESElement[] elements, IARESElement destination, IProgressMonitor monitor) {
		String[] newNames = new String[elements.length];
		
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			IARESModule module = (IARESModule)destination;
			for (int i = 0; i < elements.length; i++) {			
				IARESResource aresRes = (IARESResource)elements[i];
				IARESResource destRes = module.getARESResource(aresRes.getElementName());
				if (destRes.exists()) {
					String newName = getAutoNewNameFor(aresRes) + "." + aresRes.getType();
					newNames[i] = newName;
				}
			}
		} else if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
			for (int i = 0; i < elements.length; i++) {			
				IARESModule module = (IARESModule) elements[i];
				IARESModule destModule = null;
				if (destination instanceof IARESModuleRoot) {
					IARESModuleRoot root = (IARESModuleRoot) destination;
					destModule = root.getModule(module.getShortName());
					
				} else if (destination instanceof IARESModule) {
					IARESModule containerModule = (IARESModule) destination;
					IARESModuleRoot root = containerModule.getRoot();
					destModule = root.getModule(containerModule.getElementName() + "." + module.getShortName());
				}
				
				if (destModule != null && destModule.exists()) {
					String newName = getAutoNewNameFor(destModule);
					newNames[i] = newName;
				}
			}
		}
		
		try {
			CopyResourceElementsOperation operation = new CopyResourceElementsOperation(elements, new IARESElement[] {destination}, true);
			operation.setRenamingList(newNames);
			operation.runOperation(monitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void performCopy2(IARESElement[] elements, IARESElement dest) {
		CopyResourceElementsOperation operation = new CopyResourceElementsOperation(elements, new IARESElement[] {dest}, true);
		try {
			operation.runOperation(new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	private boolean performCopy(IResource[] resources, IPath destination, IProgressMonitor monitor) {
		try {
			AbstractWorkspaceOperation op = getUndoableCopyOrMoveOperation(
					resources, destination);
			//op.setModelProviderIds(getModelProviderIds());
			PlatformUI.getWorkbench().getOperationSupport()
					.getOperationHistory().execute(op, monitor,
							WorkspaceUndoUtil.getUIInfoAdapter(msgShell));
		} catch (ExecutionException e) {
			if (e.getCause() instanceof CoreException) {
				//recordError((CoreException) e.getCause());
				e.printStackTrace();
			} else {
				IDEWorkbenchPlugin.log(e.getMessage(), e);
				displayError(e.getMessage());
			}
			return false;
		}
		return true;
	}
	
	private IARESElement[] validateNoNameCollisions(IARESElement[] elements, IARESElement destination) {
		int overwrite = IDialogConstants.NO_ID;
		List<IARESElement> copyElements = new ArrayList<IARESElement>();
		for (int i = 0; i< elements.length; i++) {
			IARESElement element = elements[i];
			boolean needCheckOverwrite = false;
			
			if (element instanceof IARESResource) {
				IARESResource res = (IARESResource)elements[i];
				IARESModule module = (IARESModule)destination;
				IARESResource newRes = module.getARESResource(res.getElementName());
				if (newRes != null && newRes.exists()) {
					needCheckOverwrite = true;
				} 
			} else if (element instanceof IARESModule) {
				IARESModule module = (IARESModule) element;
				IARESModule destModule = null;
				
				if (destination instanceof IARESModuleRoot) {
					IARESModuleRoot root = (IARESModuleRoot) destination;
					destModule = root.getModule(module.getElementName());
				} else if (destination instanceof IARESModule) {
					IARESModule containModule = (IARESModule) destination;
					IARESModuleRoot root = containModule.getRoot();
					destModule = root.getModule(containModule.getElementName() + "." + module.getShortName());
				}
				
				if (destModule != null && destModule.exists()) {
					needCheckOverwrite = true;
				}
			}
			
			if (needCheckOverwrite) {
				if (overwrite != IDialogConstants.YES_TO_ALL_ID) {
					overwrite = checkOverwrite(element, destination);
				}
				
				if (overwrite == IDialogConstants.YES_ID || overwrite == IDialogConstants.YES_TO_ALL_ID) {
					copyElements.add(element);
				} else if (overwrite == IDialogConstants.CANCEL_ID) {
					canceled = true;
					return null;
				} 
			} else {
				copyElements.add(element);
			}
			
		}
		return copyElements.toArray(new IARESElement[0]);
	}
	
	protected String validateDestination(IARESElement[]	elements, IARESElement destination) {
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			if (destination instanceof IARESModule) {
				IARESModule module = (IARESModule)destination;
				if (module.getRoot().isArchive())
					return INVALIDE_DESTINATION;
				else
					return null;
			}			
		}
		return INVALIDE_DESTINATION;
	}
	
	private int checkOverwrite(IARESElement source, IARESElement destination) {
		int result;
		int resultId[] = {
				IDialogConstants.YES_ID,
				IDialogConstants.YES_TO_ALL_ID,
				IDialogConstants.NO_ID,
				IDialogConstants.CANCEL_ID
		};
		
		String labels[] = {
				IDialogConstants.YES_LABEL,
				IDialogConstants.YES_TO_ALL_LABEL,
				IDialogConstants.NO_LABEL,
				IDialogConstants.CANCEL_LABEL
		};
		
		String message = source.getElementName() /*+ "在" + destination.getElementName()*/ + "已经存在，确定要覆盖吗?";
		MessageDialog dialog = new MessageDialog(msgShell, 
				"资源已存在", 
				null, 
				message, 
				MessageDialog.QUESTION, 
				labels, 
				0);
		
		dialog.open();
		if (dialog.getReturnCode() == SWT.DEFAULT) {
			result = IDialogConstants.CANCEL_ID;
		} else {
			result = resultId[dialog.getReturnCode()];
		}
		
		return result;
	}
 	
//	private int checkOverwrite(IARESModule module, IARESElement destination) {
//		
//	}
	
	/**
	 * Opens an error dialog to display the given message.
	 * 
	 * @param message
	 *            the error message to show
	 */
	private void displayError(final String message) {
		msgShell.getDisplay().syncExec(new Runnable() {
			public void run() {
				MessageDialog.openError(msgShell, "错误", message);
			}
		});
	}

	protected AbstractWorkspaceOperation getUndoableCopyOrMoveOperation(
			IResource[] resources, IPath destinationPath) {
		return new CopyResourcesOperation(resources, destinationPath,
				IDEWorkbenchMessages.CopyFilesAndFoldersOperation_copyTitle);

	}
}
