/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.ui.editor.blocks.OpenResourceAction.EditorInput;

/**
 * 
 * @author yanwj06282
 */
public class OpenResourceEditor extends FormEditor{

	protected IARESElement element;
	
	public static final String EDITOR_ID = "com.hundsun.ares.studio.ui.openResourceEditor";
	
	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	protected void setInput(IEditorInput input) {
		if (input instanceof EditorInput) {
			element = ((EditorInput) input).getElement();
		}
		super.setInput(input);
	}

	@Override
	protected void addPages() {
		try {
			addPage(new OpenResourcePage(this, element, "openresource", "资源列表"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
}
