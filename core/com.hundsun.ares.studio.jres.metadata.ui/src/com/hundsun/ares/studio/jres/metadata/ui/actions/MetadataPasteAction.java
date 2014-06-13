package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.JresPasteAction;

public class MetadataPasteAction extends JresPasteAction {

	@Override
	protected Object getFeature() {
		return MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS;
	}
	
	@Override
	public Command createCommand(Collection<?> selection) {
		if (selection.size() == 1) {
			return PasteFromClipboardCommand.create(domain, getOwner(selection), getFeature());
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

}
