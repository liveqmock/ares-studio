/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.part.MultiEditorInput;

/**
 * 
 * @author liaogc
 */
public class RefactoringSaveHelper {
	
	private static final Logger logger = Logger.getLogger(RefactoringSaveHelper.class);
	public boolean saveEditors(Shell shell) {
		final IEditorPart[] dirtyEditors =getDirtyEditors();
		if(dirtyEditors==null || dirtyEditors.length==0){
			return true;
		}
		ListDialog dialog= new ListDialog(shell) {};
		dialog.setTitle("必须保存以下级资源");
		dialog.setLabelProvider(createDialogLabelProvider());
		dialog.setMessage("必须保存以下级资源");
		dialog.setContentProvider(new ArrayContentProvider());
		dialog.setInput(Arrays.asList(dirtyEditors));
		int state = dialog.open() ;
		if(state == Window.OK){
			int count= dirtyEditors.length;
			for (int i= 0; i < count; i++) {
				IEditorPart editor= dirtyEditors[i];
				try{
					editor.doSave(new NullProgressMonitor());
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return state== Window.OK;
	}
	
	private ILabelProvider createDialogLabelProvider() {
		return new LabelProvider() {
			public Image getImage(Object element) {
				return ((IEditorPart) element).getTitleImage();
			}
			public String getText(Object element) {
				return ((IEditorPart) element).getTitle();
			}
		};
	}
	public  IEditorPart[] getDirtyEditors() {
		Set<IEditorInput> inputs= new HashSet<IEditorInput>();
		List<IEditorPart> result= new ArrayList<IEditorPart>(0);
		IWorkbench workbench= PlatformUI.getWorkbench();
		IWorkbenchWindow[] windows= workbench.getWorkbenchWindows();
		for (int i= 0; i < windows.length; i++) {
			IWorkbenchPage[] pages= windows[i].getPages();
			for (int x= 0; x < pages.length; x++) {
				IEditorPart[] editors= pages[x].getDirtyEditors();
				for (int z= 0; z < editors.length; z++) {
					IEditorPart ep= editors[z];
					IEditorInput input= ep.getEditorInput();
					if (inputs.add(input)) {
						if (isResourceEditorInput(input)) {
							result.add(ep);
						}
					}
				}
			}
		}
		return (IEditorPart[])result.toArray(new IEditorPart[result.size()]);
	}
	
	private  boolean isResourceEditorInput(IEditorInput input) {
		if (input instanceof MultiEditorInput) {
			IEditorInput[] inputs= ((MultiEditorInput) input).getInput();
			for (int i= 0; i < inputs.length; i++) {
				if (inputs[i].getAdapter(IResource.class) != null) {
					return true;
				}
			}
		} else if (input.getAdapter(IResource.class) != null) {
			return true;
		}
		return false;
	}

}
