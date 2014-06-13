/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.hundsun.ares.studio.jres.database.ui.editors.DBSQLPreviewPage;
import com.hundsun.ares.studio.jres.database.utils.DBTableGenCodeUtils;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author liaogc
 *
 */
public class SequenceSQLPreviewUpdater implements IPageChangedListener {

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