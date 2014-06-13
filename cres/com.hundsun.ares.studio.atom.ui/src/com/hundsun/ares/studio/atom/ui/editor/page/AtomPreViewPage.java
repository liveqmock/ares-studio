/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.editor.page;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;

import com.hundsun.ares.studio.cres.extend.ui.text.c.CDocumentProvider;
import com.hundsun.ares.studio.cres.extend.ui.text.c.CSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.ARESUI;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.TextEditorEMFFormPage;

/**
 * @author qinyuan
 *
 */
public class AtomPreViewPage extends TextEditorEMFFormPage {

	private int scrollV=-1;
	private int scrollH=-1;
	private int cursorX=-1;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AtomPreViewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		CSourceViewerConfiguration config = new CSourceViewerConfiguration(ARESUI.getPlugin().getColorManager());
		setSourceViewerConfiguration(config);
		
		setDocumentProvider(new CDocumentProvider());
		
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
