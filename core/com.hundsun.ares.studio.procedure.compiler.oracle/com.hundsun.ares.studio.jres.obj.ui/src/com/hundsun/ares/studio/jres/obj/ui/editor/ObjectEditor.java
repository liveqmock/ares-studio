package com.hundsun.ares.studio.jres.obj.ui.editor;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

public class ObjectEditor extends EMFFormEditor{

	ObjectPage objectPage;

	@Override
	protected EClass getInfoClass() {
		return BizPackage.Literals.ARES_OBJECT;
	}

	@Override
	protected void addPages() {
		try {
			objectPage = new ObjectPage(this, "object", "基本信息");
			addPage(objectPage);

			addPage(new ObjectParameterPage(this, "parm", "属性"));
			
			addPage(new RevisionHistoryListPage(this, "revisionHistory", "修订信息"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}