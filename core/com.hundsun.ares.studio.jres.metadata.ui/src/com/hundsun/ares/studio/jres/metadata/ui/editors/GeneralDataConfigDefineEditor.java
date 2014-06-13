package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

public class GeneralDataConfigDefineEditor extends EMFFormEditor {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.GENERAL_DATA_CONFIG_LIST;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		try {
			addPage(new GeneralDataConfigDefinePage(this,
					"GeneralDataConfigDefinePage", "ÔªÊý¾ÝÅäÖÃ"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}


}
