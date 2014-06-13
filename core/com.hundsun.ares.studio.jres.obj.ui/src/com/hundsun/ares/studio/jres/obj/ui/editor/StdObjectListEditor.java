package com.hundsun.ares.studio.jres.obj.ui.editor;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

public class StdObjectListEditor extends MetadataEMFFormEditor {

	private StdObjListPage page;
	
	@Override
	protected EMFFormPage addMetadataItemPage() {
		page = new StdObjListPage(this);
		return page;
	}

	@Override
	protected void setSelection(Object element) {
		
	}

	@Override
	protected EMFFormPage addMetadataItemOverViewPage() {
	
		return null;
	}

	@Override
	protected EClass getInfoClass() {
		return BizPackage.Literals.STANDARD_OBJ_FIELD_LIST;
	}

}
