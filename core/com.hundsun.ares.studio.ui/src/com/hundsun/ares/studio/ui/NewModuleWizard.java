/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * 
 * @author 
 */
public class NewModuleWizard extends Wizard implements INewWizard {

	NewModulePage page;
	IARESModuleRoot root;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection.getFirstElement() instanceof IARESModuleRoot) {
			root = (IARESModuleRoot)selection.getFirstElement();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page = new NewModulePage("新建模块");
		
	}	

	class NewModulePage extends WizardPage {

		Text text ;
		/**
		 * @param pageName
		 */
		protected NewModulePage(String pageName) {
			super(pageName);
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		public void createControl(Composite parent) {
			parent.setLayout(new GridLayout(2,false));
			Label label = new Label(parent, SWT.NONE);
			label.setText("Name");
			label.setLayoutData(new GridData());
			text = new Text(parent, SWT.NONE);
			text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
		
		public void finishPage() {
			IFolder rootFolder = (IFolder) root.getResource();
			String input = text.getText();
			String[] names ;
			if (input.indexOf('.') > 0) {
				names = input.split("\\.");
			} else {
				names = new String[] {input};
			}
			
			IFolder moduleFolder;
			for (String name : names) {
				
			}
		}
		
	}

}
