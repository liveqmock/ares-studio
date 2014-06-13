package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.StructuredSelection;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

/**
 * 业务包配置编辑器
 * @author wangxh
 *
 */
public class BizPropertyConfigEditor extends MetadataEMFFormEditor {
	private BizPropertyConfigListPage page;
	@Override
	protected EMFFormPage addMetadataItemPage() {
		page = new BizPropertyConfigListPage(this, "list", "业务包配置列表");
		return page;
	}

	@Override
	protected void setSelection(Object element) {
		page.getEditor().setActivePage(page.getId());
		page.getViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
	}

	@Override
	protected EMFFormPage addMetadataItemOverViewPage() {
		return null;
	}

	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.BIZ_PROPERTY_CONFIG_LIST;
	}

}
