package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.EPackageMasterDetailPage;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

public class EPackageEditor extends EMFFormEditor {

	@Override
	protected EClass getInfoClass() {
		return BasicdataPackage.Literals.EPACAKGE_DEFINE_LIST;
	}

	@Override
	protected void addPages() {
		EPackageMasterDetailPage page = new EPackageMasterDetailPage(this, "epackacgeeditor", "表关联信息");
		try {
			addPage(page);
			addPage(new RevisionHistoryListPage(this, "history", "修订信息"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.EMFFormEditor#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
		IDialogSettings blockSettings = null;
		blockSettings = settings.getSection(this.getClass().getSimpleName());
		if (blockSettings == null) {
			blockSettings = settings.addNewSection(this.getClass().getSimpleName());
			blockSettings.put(ACTIVE_EDITOR_INDEX, 0);
		}
		
		return blockSettings;
	}


}
