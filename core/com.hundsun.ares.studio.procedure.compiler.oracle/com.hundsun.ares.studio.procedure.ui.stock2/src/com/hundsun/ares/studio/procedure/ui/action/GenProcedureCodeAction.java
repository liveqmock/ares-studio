package com.hundsun.ares.studio.procedure.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.hundsun.ares.studio.cres.ui.action.GenModuleCodeType;
import com.hundsun.ares.studio.procedure.ui.support.GenProcedureModuleCodeDialog;

public class GenProcedureCodeAction extends GenProcedureCodeWithPathAction{
	
	GenProcedureModuleCodeDialog dialog;
	
	@Override
	protected boolean isGenCodeWithPath() {
		int type = dialog.getType();
		if (GenModuleCodeType.NODIR_GENMODULE.getType() != type) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean isGenCodeWithCNamePath() {
		int type = dialog.getType();
		if (GenModuleCodeType.CH_DIR_GENMODULE.getType() == type) {
			return true;
		}
		return false;
	}
	
	@Override
	public void run(IAction action) {
		dialog = new GenProcedureModuleCodeDialog(getShell()){
			protected String getTitle() {
				return "过程生成";
			};
			
			protected org.eclipse.swt.widgets.Control createDialogArea(org.eclipse.swt.widgets.Composite parent) {

				Composite composite = new Composite(parent, SWT.NONE);
				GridLayout layout = new GridLayout();
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				layout.verticalSpacing = 0;
				layout.horizontalSpacing = 0;
				composite.setLayout(layout);
				composite.setLayoutData(new GridData(GridData.FILL_BOTH));
				composite.setFont(parent.getFont());
				// Build the separator line
				Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL
						| SWT.SEPARATOR);
				titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			
				createExtendsAres(composite);
				return composite;
			};
		};
		if (dialog.open() == Window.OK) {
			isHeadCode = dialog.isHeadCode();
			isEndCode = dialog.isEndCode();
			super.run(action);
		}
	}
	
}
