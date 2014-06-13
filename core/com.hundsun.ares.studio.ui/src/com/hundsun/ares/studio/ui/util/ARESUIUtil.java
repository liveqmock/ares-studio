/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.ui.ARESResourceEditorInput;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 
 * @author sundl
 */
public class ARESUIUtil {

	public static IProgressMonitor nullMonitor() {
		return new NullProgressMonitor();
	}
	
	public static IProgressMonitor progressMonitor(IProgressMonitor pm) {
		if (pm == null) 
			return nullMonitor();
		return pm;
	}
	
	private static void openFile(IFile file, IWorkbenchPage page) {
		if(file.exists()) {
			try{
				IDE.openEditor(page, file, false);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void openResource(IARESResource resource) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page == null)
			return;
		
		IResource res = resource.getResource();
		if (resource.getLib() != null) {
			ArchiveARESResource aresfile = (ArchiveARESResource) resource;
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
		} else	if (res instanceof IFile && !resource.getRoot().isArchive()) {
			openFile((IFile)res, page);
		}
	}

	/**
	 * 打开资源的工具方法
	 * 
	 * @param resource
	 * @return
	 */
	public static IEditorPart openEditor(IARESResource resource)
			throws PartInitException {
		IEditorPart editor = null;
		if (resource != null) {
			if (resource.getResource() instanceof IFile) {
				editor = IDE.openEditor(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage(),
						(IFile) resource.getResource(), false);
			}
			if (resource instanceof ArchiveARESResource) {
				IEditorDescriptor desc = IDE.getEditorDescriptor(resource
						.getElementName());
				if (desc != null) {
					editor = IDE
							.openEditor(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getActivePage(),
									new ARESResourceEditorInput(resource),
									desc.getId());
				}
			}
		}
		return editor;
	}

	public static void addLinkSupport(final Control control, final IAction action) {
		control.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				if (e.stateMask == SWT.CTRL) {
					Event event = new Event();
					event.button = e.button;
					event.data = e.data;
					event.widget = e.widget;
					event.x = e.x;
					event.y = e.y;
					action.runWithEvent(event);
				}
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
	}
}
