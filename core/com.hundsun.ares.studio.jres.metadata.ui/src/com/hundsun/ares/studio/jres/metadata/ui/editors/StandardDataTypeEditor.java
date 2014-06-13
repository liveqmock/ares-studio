/**
 * 源程序名称：StandardDataTypeEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.StructuredSelection;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;

/**
 * 标准数据类型编辑器
 * @author qinyuan
 *
 */
public class StandardDataTypeEditor extends MetadataEMFFormEditor {

	private StandardDataTypeListPage metadataItemPage;

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return MetadataPackage.Literals.STANDARD_DATA_TYPE_LIST;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor#addMetadataitemPage()
	 */
	@Override
	protected EMFFormPage addMetadataItemPage() {
		metadataItemPage = new StandardDataTypeListPage(this, "list", "标准数据类型");
		return metadataItemPage;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.editors.MetadataEMFFormEditor#setSelection(java.lang.Object)
	 */
	@Override
	protected void setSelection(Object element) {
		metadataItemPage.getEditor().setActivePage(metadataItemPage.getId());
		metadataItemPage.getStandardDataTypeViewerBlock().getColumnViewer().setSelection(new StructuredSelection(element), true);
	}

	@Override
	protected EMFFormPage addMetadataItemOverViewPage() {
		return new StandardDataTypeOverviewPage(this,"overview","总览列表");
	}
	
}
