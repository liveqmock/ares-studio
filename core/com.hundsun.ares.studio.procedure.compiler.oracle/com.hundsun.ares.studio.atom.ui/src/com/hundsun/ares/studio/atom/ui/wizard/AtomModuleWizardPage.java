package com.hundsun.ares.studio.atom.ui.wizard;

import java.util.regex.Pattern;

import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.ui.wizard.ARESModuleWizardPage;

public class AtomModuleWizardPage extends ARESModuleWizardPage {
	Pattern RES_NAME_PATTERN = Pattern.compile("^[a-z_][a-z0-9_]{0,49}$");
	
	public AtomModuleWizardPage(String pageName, IWorkbench workbench,
			IARESElement selection) {
		super(pageName, workbench, selection);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.ElementSelectionWizardPageWithNameInput#getNamePattern()
	 */
	@Override
	protected Pattern getNamePattern() {
		return RES_NAME_PATTERN;
	}
	
	@Override
	protected int[] getDisplayedElementTypes() {
		return new int[] {IARESElement.ARES_PROJECT, IARESElement.COMMON_MODULE_ROOT, IARESElement.COMMON_MODULE};
	}

	@Override
	protected String[][] getSelctingElementTypes() {
		return new String[][] { {String.valueOf(IARESElement.COMMON_MODULE_ROOT), "Ä£¿é¸ù"},
				{String.valueOf(IARESElement.COMMON_MODULE), "Ä£¿é"}};
	}
}
