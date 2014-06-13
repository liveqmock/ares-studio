package com.hundsun.ares.studio.procedure.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;

import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenModuleCodeDialog;
import com.hundsun.ares.studio.cres.ui.action.GenModuleCodeType;

public class GenEndCodeAction extends GenEndCodeWithPathAction{
	
	GenModuleCodeDialog dialog;
	
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
		dialog = new GenModuleCodeDialog(getShell());
		if (dialog.open() == Window.OK) {
			super.run(action);
		}
	}
	
}
