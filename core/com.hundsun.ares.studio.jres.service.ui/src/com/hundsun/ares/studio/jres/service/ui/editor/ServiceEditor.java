package com.hundsun.ares.studio.jres.service.ui.editor;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage;
import com.hundsun.ares.studio.jres.service.ServicePackage;
import com.hundsun.ares.studio.jres.service.ui.page.ServiceInterfacePage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;

public class ServiceEditor extends EMFFormEditor{

	private static final String CONTRIBUTOR_ID = "com.hundsun.ares.studio.jres.biz.service.editor";

	private InterfacePage interfacePage;
	private ServiceBasicInfoPage basicPage;
	
	@Override
	protected EClass getInfoClass() {
		return ServicePackage.Literals.SERVICE;
	}

	@Override
	protected void addPages() {
		try {
			basicPage = new ServiceBasicInfoPage(this);
			addPage(basicPage);
			
			interfacePage = new ServiceInterfacePage(ServicePackage.Literals.SERVICE__INTERFACE, this, "interface", "接口");
			addPage(interfacePage);

			addPage(new RevisionHistoryListPage(this, "revisionHistory", "修订信息"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}