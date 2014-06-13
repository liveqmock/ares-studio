/**
 * 源程序名称：DBSQLPreviewPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.TextEditorEMFFormPage;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLTextEditorInputDocumentProvider;

/**
 * @author gongyf
 *
 */
public class DBSQLPreviewPage extends TextEditorEMFFormPage {

	/* 当前是否正在生成代码的标志位 */
	private boolean generating = false;
	/* 页面要显示的文本 */
	private String text;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DBSQLPreviewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
		SQLSourceViewerConfiguration configuration = new SQLSourceViewerConfiguration();
		setSourceViewerConfiguration(configuration);
		
		setDocumentProvider(new SQLTextEditorInputDocumentProvider());
		
		IPreferenceStore[] stores = new IPreferenceStore[2];
		stores[0] = EditorsUI.getPreferenceStore();
		stores[1] = ARESEditorPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(new ChainedPreferenceStore(stores));
	}
	
	public void setGenerating(boolean generating) {
		this.generating = generating;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void update() {
		IDocument doc = getDocumentProvider().getDocument(getEditorInput());
		if (generating) {
			doc.set("正在生成代码,请稍候...");
		} else {
			doc.set(text);
		}
	}

}