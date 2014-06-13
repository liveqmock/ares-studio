package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;
import org.eclipse.ui.part.FileEditorInput;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESResource;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;

public class LinkHelper implements ILinkHelper {

	public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
		if (aSelection == null || aSelection.isEmpty())
			return;
		Object obj = aSelection.getFirstElement();
		if (obj instanceof IARESResource) {
			IEditorInput input = null;
			if (obj instanceof ArchiveARESResource) {
				input = new ARESResourceEditorInput((IARESResource) obj);
			} else if (obj instanceof ARESResource) {
				input = new FileEditorInput((IFile) ((IARESResource) obj).getResource());
			}
				
			IEditorPart part = aPage.findEditor(input);
			if (part != null) {
				aPage.bringToTop(part);
			}
		}
	}

	public IStructuredSelection findSelection(IEditorInput anInput) {
		IARESResource res = null;
		if (anInput instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput) anInput).getFile();
			IARESElement ares = (IARESElement) ARESCore.create(file);
			if (ares instanceof IARESResource && ares.exists()) {
				res = (IARESResource) ares;
			}
		} else if (anInput instanceof ARESResourceEditorInput) {
			ARESResourceEditorInput input = (ARESResourceEditorInput) anInput;
			res = input.getARESResource();
		}
		
		if (res != null) {
			return new StructuredSelection(res);
		}
		
		return null;
	}

}
