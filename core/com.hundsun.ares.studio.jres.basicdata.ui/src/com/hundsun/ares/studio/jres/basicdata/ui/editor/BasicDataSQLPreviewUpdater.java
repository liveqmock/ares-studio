package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.pages.BasicDataSQLPreviewPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

public class BasicDataSQLPreviewUpdater implements IPageChangedListener {
	
	@Override
	public void pageChanged(PageChangedEvent event) {
		if (event.getSelectedPage() instanceof BasicDataSQLPreviewPage) {
			BasicDataSQLPreviewPage page = (BasicDataSQLPreviewPage) event.getSelectedPage();
			EObject eObj = page.getEditor().getInfo();
			//BasicDataBaseModel
			StringBuffer buffer = new StringBuffer();
			IARESResource resource = page.getEditor().getARESResource();
			//安装模式
			buffer.append(BasicDataGenCodeUtils.genBasicDataFullCode(resource, eObj));
			//升级模式
			buffer.append(BasicDataGenCodeUtils.genBasicDataPatchCode(resource, eObj));
			
			page.setInput(new TextEditorInput(buffer.toString()));

		}
	}

}
