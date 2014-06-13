package com.hundsun.ares.studio.ui.editor.actions;

import com.hundsun.ares.studio.core.model.CorePackage;

public class RevisionHistoryPasteAction extends JresPasteAction {

	protected Object getFeature() {
		return CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES;
	}
	
}
