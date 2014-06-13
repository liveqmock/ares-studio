/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.part.ResourceTransfer;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.ARESElementTransfer;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

/**
 * 复制操作
 * @author sundl
 */
public class ARESCopyAction extends SelectionListenerAction{

	private Shell shell;
	
	public ARESCopyAction(Shell shell) {
		super("复制");
		this.shell = shell;
		ISharedImages workbenchImages = getWorkbenchSharedImages();
		setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));		
	}

	@Override
    protected boolean updateSelection(IStructuredSelection selection) {
		int selectionLength = selection.toArray().length;
		IARESElement[] elements = getSelectedAresElements();
		
		// first, selection must be all IARESElement
		if (selectionLength != elements.length)
			return false;
		
		if (!RefactoringUtil.canCopy(elements))
			return false;
		
        return true;
    }

	private static ISharedImages getWorkbenchSharedImages() {
		return PlatformUI.getWorkbench().getSharedImages();
	}
	
	private IARESElement[] getSelectedAresElements() {
		List<IARESElement> elements = new ArrayList<IARESElement>();
		for (Iterator<?> e = getStructuredSelection().iterator(); e.hasNext();) {
			Object obj = e.next();
			IARESElement element = null;
			if (obj instanceof IARESElement) {
				element = (IARESElement) obj;
			} else if (obj instanceof IResource) {
				IARESElement ele = ARESElementUtil.toARESElement(obj);
				if (ele != null)
					element = ele;
			}
			if (element != null) {
				IARESModuleRoot root = getARESModuleRoot(element);
				
				if (root != null && !StringUtils.equals(root.getElementName(), "basicdata")) {
					elements.add(element);
				}
			}
		}
		return elements.toArray(new IARESElement[0]);
	}
	
	private IARESModuleRoot getARESModuleRoot(IARESElement element){
		if(element != null && element instanceof IARESModuleRoot){
			return (IARESModuleRoot)element;
		}
		IARESModuleRoot root = null;
		int totle = 0;
		while(root == null && totle < 20){
			if (element instanceof IARESModule) {
				root = ((IARESModule) element).getRoot();
			}
			if (element instanceof IARESResource) {
				root = ((IARESResource) element).getRoot();
			}
			totle++;
		}
		return root;
	}
	
	@Override
	public void run() {
		IARESElement[] elements = getSelectedAresElements();
		IResource[] resources = ARESElementUtil.toResource(elements);
		Set<String> fileNames = new HashSet<String>();
		StringBuffer buffer = new StringBuffer();
		int length = resources.length;
		for (int i = 0; i < length; i++) {
			IPath location = resources[i].getLocation();
			if (location != null) {
				fileNames.add(location.toOSString());
			}
			if (i > 0) 
				buffer.append("\n");
			buffer.append(resources[i].getName());
		}
		
		setClipboard(resources, fileNames.toArray(new String[0]), buffer.toString(), elements);
	}
	
	private void setClipboard(IResource[] resources, String[] fileNames, String names, IARESElement[] elements) {
		Clipboard cb = new Clipboard(shell.getDisplay());		
		try {
            // set the clipboard contents
            if (fileNames.length > 0) {
                cb.setContents(new Object[] { resources, fileNames,
                        names, elements},
                        new Transfer[] { ResourceTransfer.getInstance(),
                                FileTransfer.getInstance(),
                                TextTransfer.getInstance(),
                                ARESElementTransfer.getInstance()});
            } else if (resources.length != 0) {
                cb.setContents(new Object[] { resources, elements/*, names*/ },
                        new Transfer[] { ResourceTransfer.getInstance(),
                		 ARESElementTransfer.getInstance(),
                                /*TextTransfer.getInstance(),*/
                               });
            } else {
            	cb.setContents(new Object[] {elements}, 
            			new Transfer[] {ARESElementTransfer.getInstance()});
            }
        } catch (SWTError e) {
        	e.printStackTrace();
        } finally {
        	cb.dispose();
        }
	} 
	
}
