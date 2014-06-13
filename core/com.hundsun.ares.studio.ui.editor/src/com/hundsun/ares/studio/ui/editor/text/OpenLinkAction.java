package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.ui.ARESResourceEditorInput;

/**
 * <p>CreatedDate: 2008-2-29</p>
 * @author sundl
 */
public class OpenLinkAction extends Action {
	
	private IARESElement element;
	
	public OpenLinkAction(IARESElement element) {
		this.element = element;
	}
	
	public void run() {
		try {
			if(element.getResource() instanceof IFile) {
				IFile file = (IFile)element.getResource();
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IDE.openEditor(page, file);
			} else {
				// 2012-04-13 sundl 添加处理引用包里的资源
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
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
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
