/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * 
 * @author yanwj06282
 */
public class OpenResourceAction implements IObjectActionDelegate {

	private IWorkbenchPart part;
	private IARESElement element;

	@Override
	public void run(IAction action) {
		if(element != null) {
			try {
				IDE.openEditor(part.getSite().getPage(), new EditorInput(element) , OpenResourceEditor.EDITOR_ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection)selection;
			Object obj = ss.getFirstElement();
			if(obj instanceof IARESModuleRoot) {
				IARESModuleRoot root = (IARESModuleRoot)obj;
				element = root;
				action.setEnabled(true);
				return;
				
			} else if(obj instanceof IARESModule) {
				element = (IARESModule)obj;
				action.setEnabled(true);
				return;
			}
		}
		element = null;
		action.setEnabled(false);
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	public class EditorInput implements IEditorInput{

		private IARESElement element;
		
		public EditorInput(IARESElement elements) {
			this.element = elements;
		}
		
		public IARESElement getElement() {
			return element;
		}

		@Override
		public Object getAdapter(Class adapter) {
			return null;
		}

		@Override
		public boolean exists() {
			return false;
		}

		@Override
		public ImageDescriptor getImageDescriptor() {
			return null;
		}

		@Override
		public String getName() {
			return element.getElementName();
		}

		@Override
		public IPersistableElement getPersistable() {
			return null;
		}

		@Override
		public String getToolTipText() {
			return element.getElementName();
		}
		
	}

}
