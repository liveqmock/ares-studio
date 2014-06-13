package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

/**
 * <p>CreatedDate: 2008-2-19</p>
 * @author sundl
 */
public class ARESHyperlink implements IHyperlink {

	private final IAction openAction;
	private final IRegion region;
	
	public ARESHyperlink(IRegion region, IAction openAction) {
		this.region = region;
		this.openAction = openAction;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 */
	public IRegion getHyperlinkRegion() {
		return region;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 */
	public String getHyperlinkText() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getTypeLabel()
	 */
	public String getTypeLabel() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 */
	public void open() {
		if(openAction != null) openAction.run();
	}

}
