/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.assist;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.cellEditor.ContentProposalAdapter;

/**
 * 带内容辅助提示的文本框
 * @author sundl
 */
public class TextWithAssist extends Composite {
    private static final char[] CHAR_ARRAY = new char[128];
    
    static {
        for (int i = 127; i >= 0; i--) {
            CHAR_ARRAY[i] = (char) i;
        }
    }
    
	private Text text;
	private ContentProposalAdapter adapter;
	
	public TextWithAssist(Composite parent) {
		super(parent, SWT.NONE);
		init();
	}

	private void init() {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(this);
		text = new Text(this, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
		adapter = new ContentProposalAdapter(text, new TextContentAdapter(), 
													null, 
													KeyStroke.getInstance(SWT.ALT, '/'), 
													new char[0]);
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
	}
	
	public void setAutoActivation(boolean auto) {
		adapter.setAutoActivationCharacters(CHAR_ARRAY);
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return this.text.getText();
	}
	
	public void addModifyListener(ModifyListener listener) {
		this.text.addModifyListener(listener);
	}
	
	/**
	 * 
	 * @return
	 */
	public ContentProposalAdapter getAdapter() {
		return adapter;
	}
	
	public Text getControl() {
		return text;
	}
	
}
