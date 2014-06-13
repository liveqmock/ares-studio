/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.assist.SimpleContentProposalAdapter;


/**
 * TEXT智能提示组件.
 * @author caizx
 * @version 1.0
 * @history
 */
public class TextContentAssist extends Text {

	private boolean popupOpen;
	private SimpleContentProposalProvider proposalProvider;

	/**
	 * @return the proposalProvider
	 */
	public SimpleContentProposalProvider getProposalProvider() {
		return proposalProvider;
	}

	/**
	 * @param proposalProvider the proposalProvider to set
	 */
	public void setProposalProvider(SimpleContentProposalProvider proposalProvider) {
		this.proposalProvider = proposalProvider;
	}

	protected SimpleContentProposalAdapter adapter;

	public TextContentAssist(Composite parent, int style) {
		this(parent,style,new String[]{});
	}
	
	/**
	 * @param parent 父容器
	 * @param style  样式
	 * @param items  填充内容
	 */
	public TextContentAssist(Composite parent, int style, String[] items) {
		super(parent, style);
		proposalProvider = new SimpleContentProposalProvider(new String[0]);
		String[] propsalsWithInfo = new String[items.length];
		for(int i = 0; i < items.length; i++) {
			propsalsWithInfo[i] = items[i];
		}
		proposalProvider.setProposals(propsalsWithInfo);
		proposalProvider.setFiltering(true);
		adapter = new SimpleContentProposalAdapter(this, new MyControlContentAdapter(), proposalProvider, null, null);
		adapter.setPropagateKeys(true);
		adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		adapter.addContentProposalListener(new IContentProposalListener2() {
			public void proposalPopupClosed(ContentProposalAdapter adapter) {
				Event e = new Event();
				e.data = new Boolean(false);
			}
			public void proposalPopupOpened(ContentProposalAdapter adapter) {
				Event e = new Event();
				e.data = new Boolean(true);
			}
		});
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.character=='/' && e.stateMask==SWT.ALT){
					adapter.openProposalPopup();
				}
			}		
		});
	}
	@Override
	protected void checkSubclass() {
		// TODO Auto-generated method stub
		//super.checkSubclass();
	}

	public void setProposals(String[] proposals) {
		proposalProvider.setProposals(proposals);
	}

	private class MyControlContentAdapter implements IControlContentAdapter {

		public String getControlContents(Control control) {
			return ((Text) control).getText();
		}

		
		public int getCursorPosition(Control control) {
			return ((Text) control).getCaretPosition();
		}

		
		public Rectangle getInsertionBounds(Control control) {
			Text text = (Text) control;
			Point caretOrigin = text.getCaretLocation();
			return new Rectangle(caretOrigin.x, caretOrigin.y, 1, text.getLineHeight());
		}

		
		public void insertControlContents(Control control, String text, int cursorPosition) {
			Point selection = ((Text) control).getSelection();
			((Text) control).insert(text);
			if (cursorPosition < text.length()) {
				((Text) control).setSelection(selection.x + cursorPosition, selection.x + cursorPosition);
			}
		}

		
		public void setControlContents(Control control, String text, int cursorPosition) {
			String s = text;
			((Text) control).setText(s);
			((Text) control).setSelection(cursorPosition, cursorPosition);
		}

		
		public void setCursorPosition(Control control, int position) {
			((Text) control).setSelection(new Point(position, position));
		}

	}

	/**
	 * @return the popupOpen
	 */
	public boolean isPopupOpen() {
		return popupOpen;
	}

}
