/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.TextEditorEMFFormPage;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLTextEditorInputDocumentProvider;

/**
 * @author qinyuan
 *
 */
public class ProcedurePreViewPage extends TextEditorEMFFormPage {

	private int scrollV=-1;
	private int scrollH=-1;
	private int cursorX=-1;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ProcedurePreViewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
		SQLSourceViewerConfiguration configuration = new SQLSourceViewerConfiguration();
		setSourceViewerConfiguration(configuration);
		
		setDocumentProvider(new SQLTextEditorInputDocumentProvider());
		
		IPreferenceStore[] stores = new IPreferenceStore[2];
		stores[0] = EditorsUI.getPreferenceStore();
		stores[1] = ARESEditorPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(new ChainedPreferenceStore(stores));
	}

	public void saveLocation() {
		scrollV = getSourceViewer().getTextWidget().getTopPixel();
		scrollH = getSourceViewer().getTextWidget().getHorizontalPixel();
		Point point = getSourceViewer().getTextWidget().getSelectionRange();
		cursorX = point.x;
	}
	
	public void restoreLocation() {
		if (scrollV != -1) {
			getSourceViewer().getTextWidget().setTopPixel(scrollV);
		}
		if (scrollH != -1) {
			getSourceViewer().getTextWidget().setHorizontalPixel(scrollH);
		}
		if (cursorX != -1) {
			selectAndReveal(cursorX, 0);
		}
		
	}

}
