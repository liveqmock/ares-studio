/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Wizard
 * @author sundl
 */
public abstract class TextInputWizardPage extends  UserInputWizardPage {

	public static final String PAGE_NAME = "TextInputPage";
	
	private String initialValue;
	private Text text;

	protected TextInputWizardPage(String name) {
		super(name);		
	}
	
	public TextInputWizardPage(String description, String initialValue) {
		super(PAGE_NAME);
		setDescription(description);
		this.initialValue = initialValue;
	}

	protected String getText() {
		if (text == null)
			return null;
		return text.getText();
	}
	
	protected Text getTextControl() {
		return text;
	}
	
	protected void setText(String text) {
		if (this.text == null)
			return;
		this.text.setText(text);
	}
	
	public String getInitialValue() {
		return initialValue;
	}
	
	/**
	 * Performs input validation. Returns a <code>RefactoringStatus</code> which
	 * describes the result of input validation. <code>Null<code> is interpreted
	 * as no error.
	 */
	protected RefactoringStatus validateTextField(String text){
		return null;
	}
	
	protected Text createTextInputField(Composite parent) {
		return createTextInputField(parent, SWT.BORDER);
	}
	
	protected Text createTextInputField(Composite parent, int style) {
		text = new Text(parent, style);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				textModified(getText());
			}
		});
		text.setText(initialValue);
//		TextFieldNavigationHandler.install(fTextField);
		return text;
	}
	
	protected void textModified(String text) {
		if ("".equals(text)){ //$NON-NLS-1$
			setPageComplete(false);
			setErrorMessage(null);
			restoreMessage();
			return;
		}
		if (initialValue.equals(text)){
			setPageComplete(false);
			setErrorMessage(null);
			restoreMessage();
			return;
		}

		RefactoringStatus status= validateTextField(text);
		if (status == null)
			status= new RefactoringStatus();
		setPageComplete(status);
	}
	
	protected void restoreMessage(){
		setMessage(null);
	}
	
	/* (non-Javadoc)
	 * Method declared in WizardPage
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			textModified(getText());
		}
		super.setVisible(visible);
		if (visible && text != null) {
			text.setFocus();
		}
	}
}
