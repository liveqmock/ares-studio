package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.TextEditorEMFFormPage;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLTextEditorInputDocumentProvider;

public class BasicDataSQLPreviewPage extends TextEditorEMFFormPage {

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public BasicDataSQLPreviewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
		SQLSourceViewerConfiguration configuration = new SQLSourceViewerConfiguration();
		setSourceViewerConfiguration(configuration);
		
		setDocumentProvider(new SQLTextEditorInputDocumentProvider());
		
		IPreferenceStore[] stores = new IPreferenceStore[2];
		stores[0] = EditorsUI.getPreferenceStore();
		stores[1] = ARESEditorPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(new ChainedPreferenceStore(stores));
	}

}
