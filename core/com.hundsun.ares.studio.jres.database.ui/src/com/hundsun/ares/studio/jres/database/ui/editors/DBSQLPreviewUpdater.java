/**
 * 源程序名称：DBSQLPreviewUpdater.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.hundsun.ares.studio.jres.database.utils.DBTableGenCodeUtils;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author gongyf
 *
 */
public class DBSQLPreviewUpdater implements IPageChangedListener {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IPageChangedListener#pageChanged(org.eclipse.jface.dialogs.PageChangedEvent)
	 */
	@Override
	public void pageChanged(PageChangedEvent event) {
		if (event.getSelectedPage() instanceof DBSQLPreviewPage) {
			DBSQLPreviewPage page = (DBSQLPreviewPage) event.getSelectedPage();
			EObject eObj = page.getEditor().getInfo();
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(DBTableGenCodeUtils.genTableFullCode(page.getEditor().getARESResource(), eObj));
			buffer.append(DBTableGenCodeUtils.genTablePatchCode(page.getEditor().getARESResource(), eObj));
			
			page.setInput(new TextEditorInput(buffer.toString()));

		}
	}
}
