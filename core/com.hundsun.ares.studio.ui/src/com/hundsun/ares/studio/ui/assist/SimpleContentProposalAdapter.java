/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.assist;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * @author maxh
 */
public class SimpleContentProposalAdapter extends ContentProposalAdapter {

	/**
	 * @param control
	 * @param controlContentAdapter
	 * @param proposalProvider
	 * @param keyStroke
	 * @param autoActivationCharacters
	 */
	public SimpleContentProposalAdapter(Control control, IControlContentAdapter controlContentAdapter, IContentProposalProvider proposalProvider, KeyStroke keyStroke, char[] autoActivationCharacters) {
		super(control, controlContentAdapter, proposalProvider, keyStroke, autoActivationCharacters);
	}
	
	public void openProposalPopup() {
		super.openProposalPopup();
	}
}
