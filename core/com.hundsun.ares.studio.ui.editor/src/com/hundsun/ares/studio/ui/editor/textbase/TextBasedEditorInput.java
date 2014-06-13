/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.textbase;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.hundsun.ares.studio.core.IARESElement;

public class TextBasedEditorInput implements IEditorInput{

	private ITextBased textbased;
	private IARESElement element;

	public TextBasedEditorInput(ITextBased textbased, IARESElement element) {
		this.textbased = textbased;
		this.element = element;
	}
	
	public ITextBased getTextbased() {
		return textbased;
	}
	
	public void setTextbased(ITextBased element) {
		this.textbased = element;
	}

	public IARESElement getARESElement() {
		return element;
	}

	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	
}
