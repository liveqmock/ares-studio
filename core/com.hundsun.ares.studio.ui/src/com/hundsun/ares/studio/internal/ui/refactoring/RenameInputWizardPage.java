/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 重命名
 * @author sundl
 */
public class RenameInputWizardPage extends TextInputWizardPage {

	public RenameInputWizardPage(String description, String initialValue) {
		super(description, initialValue);		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite superComposite= new Composite(parent, SWT.NONE);
		setControl(superComposite);
		superComposite.setLayout(new GridLayout());
		Composite composite= new Composite(superComposite, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		GridLayout layout= new GridLayout();
		layout.numColumns= 2;
		layout.marginHeight= 0;
		layout.marginWidth= 0;
		
		composite.setLayout(layout);

		Label label= new Label(composite, SWT.NONE);
		label.setText(getLabelText());

		Text text= createTextInputField(composite);
		text.selectAll();
		GridData gd= new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint= convertWidthInCharsToPixels(25);
		text.setLayoutData(gd);
		
		// addOptionalUpdateReferencesCheckbox(composite);
	}
	
	private void addOptionalUpdateReferencesCheckbox(Composite parent) {
		final Button button = new Button(parent, SWT.CHECK);
		button.setText("更新引用");
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		button.setLayoutData(gd);
		final IReferenceUpdating refUpdateing = (IReferenceUpdating)getRefactoring().getAdapter(IReferenceUpdating.class);
		if (refUpdateing != null) {
			button.setSelection(refUpdateing.getUpdateReferences());
			button.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					refUpdateing.setUpdateReferences(button.getSelection());
				}
				
			});
		}
	}
	
	/**
	 * Performs input validation. Returns a <code>RefactoringStatus</code> which
	 * describes the result of input validation. <code>Null<code> is interpreted
	 * as no error.
	 */
	protected RefactoringStatus validateTextField(String text){
		INameUpdating nameUpdating= (INameUpdating)getRefactoring().getAdapter(INameUpdating.class);
		if (nameUpdating != null) {
			return nameUpdating.checkNewElementName(text);
		}
		return null;
	}
	
	public void setVisible(boolean visible) {
		if (visible) {
			INameUpdating nameUpdating= (INameUpdating)getRefactoring().getAdapter(INameUpdating.class);
			if (nameUpdating != null) {
				String newName= getNewName(nameUpdating);
				if (newName != null && newName.length() > 0 && !newName.equals(getInitialValue())) {
					Text textField= getTextControl();
					textField.setText(newName);
					textField.setSelection(0, newName.length());
				}
			}
		}
		super.setVisible(visible);
	}
	
	protected String getNewName(INameUpdating nameUpdating) {
		return nameUpdating.getNewElementName();
	}
	
	protected String getLabelText() {
		return "新名字:";
	}
	
}
