/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.editor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.provider.AtomUI;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.ui.text.CRESDocumentProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.text.GeneralSourceEditor;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;

/**
 * @author qinyuan
 *
 */
public class AtomTextEditor extends GeneralSourceEditor{

	//private HSPairMatcher matcher = new HSPairMatcher(new char[] {'(', ')'});
	DefaultCharacterPairMatcher defaultPairMatcher = new DefaultCharacterPairMatcher(new char[] {'(', ')', '{', '}', '[', ']'});
	private static final String MATCH_PREF_KEY = "hs_matchbra";
	private static final String MATCH_COLOR_KEY = "hs_match_color";
	static {
		IPreferenceStore store = AtomUI.getPlugin().getPreferenceStore();
		store.setDefault(MATCH_PREF_KEY, true);
		store.setValue(MATCH_PREF_KEY, true);
		store.setDefault(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
		store.setValue(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
	}
	
	private EMFFormEditor emfEditor;
	private IARESElement aresResource;
	private PseudoCodeListener listener =  null;
	/**
	 * 
	 */
	public AtomTextEditor(IARESElement aresResource) {
		super();
		this.aresResource = aresResource;
		
		
	}
	
	
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "com.hundsun.ares.studio.ui.cres.context.source" });  //$NON-NLS-1$
	}
	
	@Override
	public IDocumentProvider customGetDocumentProvider() {
		return new CRESDocumentProvider();
	}
	
	@Override
	public SourceViewerConfiguration getConfiguration() {
		return new AtomSourceViewerConfiguration(this, getPreferenceStore());
	}
	
	@Override
	protected void createActions() {
		super.createActions();
	}
	
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		
		support.setCharacterPairMatcher(defaultPairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(MATCH_PREF_KEY, MATCH_COLOR_KEY);		
		
		super.configureSourceViewerDecorationSupport(support);
		
		//support.install(HSUIPlugin.getDefault().getPreferenceStore());
	}

	public void setEditor(EMFFormEditor emfEditor){
		this.emfEditor = emfEditor;
		if(listener==null){
			listener = new PseudoCodeListener();
			IEditorInput input = this.getEditorInput();
			if(input instanceof TextBasedEditorInput){
				IDocument  document = getDocumentProvider().getDocument(input);
				document.addDocumentListener(listener);
			}
			
		}
	}
	@Override
	public void dispose() {
		if(defaultPairMatcher != null) {
			defaultPairMatcher.dispose();
		}
		if(listener != null){
			IEditorInput input = this.getEditorInput();
			if(input instanceof TextBasedEditorInput){
				IDocument  document = getDocumentProvider().getDocument(input);
				document.removeDocumentListener(listener);
			}
			
		}
		super.dispose();
	}

	public ISourceViewer getMySourceViewer() {
		return getSourceViewer();
	}
	
	@Override
	public Object getAdapter(Class c) {
		if(c.equals(IARESResource.class)) {
			return this.aresResource;
		}else if(c.equals(EMFFormEditor.class)){
			return this.emfEditor;
		}
		return super.getAdapter(c);
	}	
	
	/**
	 * 监听伪代码中的文本变化
	 * @author liaogc
	 *
	 */
	private class PseudoCodeListener implements IDocumentListener{

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.IDocumentListener#documentAboutToBeChanged(org.eclipse.jface.text.DocumentEvent)
		 */
		@Override
		public void documentAboutToBeChanged(DocumentEvent event) {
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.IDocumentListener#documentChanged(org.eclipse.jface.text.DocumentEvent)
		 */
		@Override
		public void documentChanged(DocumentEvent event) {
			String text = event.getDocument().get();
			IEditorInput input = getEditorInput();
			if(input instanceof TextBasedEditorInput){
				((TextBasedEditorInput)input).getTextbased().setText(text);
			}
		
		}
		
	}

}
