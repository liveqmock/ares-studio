package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

public class IDRangeEditor extends EMFFormEditor {

	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.ID_RANGE_LIST;
	}

	@Override
	protected void addPages() {
		IDRangeListPage page = new IDRangeListPage(this, "list", "对象号范围");
		try {
			addPage(page);
			addPage(new RevisionHistoryListPage(this, "history", "修订信息"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
