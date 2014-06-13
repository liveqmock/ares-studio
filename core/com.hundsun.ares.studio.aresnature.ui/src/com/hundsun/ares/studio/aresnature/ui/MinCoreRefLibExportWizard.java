package com.hundsun.ares.studio.aresnature.ui;

import com.hundsun.ares.studio.core.BasicReferencedLibInfo;
import com.hundsun.ares.studio.internal.core.ReferencedLibraryInfo;
import com.hundsun.ares.studio.ui.wizard.AresLibExportWizard;

public class MinCoreRefLibExportWizard extends AresLibExportWizard {
	
	protected ReferencedLibraryInfo getRefLibInfo() {
		ReferencedLibraryInfo info = super.getRefLibInfo();
		((BasicReferencedLibInfo) info.getBasicInfo()).setType("com.hundsun.ares.studio.min");
		return info;
	}
	
}
