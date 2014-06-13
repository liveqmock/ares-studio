/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.hundsun.ares.studio.ui.IARESResourceEditorInput;

/**
 * 文件同步编辑器
 * @author sundl
 */
public class FileSynchronizer implements IResourceChangeListener, IResourceDeltaVisitor {
	
	private IEditorPart editor;
	protected boolean fIsInstalled= false;
	
	public FileSynchronizer(IEditorPart editor) {
		this.editor = editor;
	}
	
	protected IFile getFile() {
		IEditorInput editorInput = editor.getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			return ((IFileEditorInput)editorInput).getFile();
		} else if (editorInput instanceof IARESResourceEditorInput) {
			return (IFile) ((IARESResourceEditorInput)editorInput).getARESResource().getLib().getResource();
		}
		return null;
	}
	
	public void install() {
		getFile().getWorkspace().addResourceChangeListener(this);
		fIsInstalled= true;
	}
	
	public void uninstall() {
		getFile().getWorkspace().removeResourceChangeListener(this);
		fIsInstalled= false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {
		IFile file = getFile();
		if(delta == null || file == null) {
			return false;
		}
		
		delta = delta.findMember(file.getFullPath());
		if(delta == null) {
			return false;
		}
		
		switch(delta.getKind()) {
			case IResourceDelta.CHANGED:
				if((IResourceDelta.CONTENT & delta.getFlags()) != 0) {
					// 内容变化，通知编辑器做出响应。
				}
				break;
			case IResourceDelta.REMOVED:
				if((IResourceDelta.MOVED_TO & delta.getFlags()) != 0) {
					// 文件被移动，通知编辑器做出响应。
				} else {
					editor.getSite().getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							editor.getSite().getWorkbenchWindow().getActivePage().closeEditor(editor, false);
						}
					});
				}
				break;			
		}
		
		return false;
	}

	public void resourceChanged(IResourceChangeEvent e) {
		IResourceDelta delta= e.getDelta();
		try {
			if (delta != null && fIsInstalled)
				delta.accept(this);
		} catch (CoreException x) {
			//handleCoreException(x, "FileDocumentProvider.resourceChanged"); //$NON-NLS-1$
		}
	}

}
