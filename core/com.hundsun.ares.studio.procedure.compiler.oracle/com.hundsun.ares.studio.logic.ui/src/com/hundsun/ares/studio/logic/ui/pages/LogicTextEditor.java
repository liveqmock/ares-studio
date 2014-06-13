/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.ui.pages;

import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.ui.text.CRESDocumentProvider;
import com.hundsun.ares.studio.cres.extend.ui.text.CRESScanner;
import com.hundsun.ares.studio.logic.provider.LogicUI;
import com.hundsun.ares.studio.logic.ui.assistant.LogicTextAssistant;
import com.hundsun.ares.studio.logic.ui.assistant.LogicTextContentAssistant;
import com.hundsun.ares.studio.logic.ui.editor.LogicHyperlinkDetector;
import com.hundsun.ares.studio.ui.ARESColorManager;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.text.ARESPartitionScanner;
import com.hundsun.ares.studio.ui.editor.text.ARESSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.editor.text.GeneralSourceEditor;
import com.hundsun.ares.studio.ui.editor.text.NonRuleBasedDamagerRepairer;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;

/**
 * @author qinyuan
 *
 */
public class LogicTextEditor extends GeneralSourceEditor{

	//private HSPairMatcher matcher = new HSPairMatcher(new char[] {'(', ')'});
	DefaultCharacterPairMatcher defaultPairMatcher = new DefaultCharacterPairMatcher(new char[] {'(', ')', '{', '}', '[', ']'});
	private static final String MATCH_PREF_KEY = "hs_matchbra";
	private static final String MATCH_COLOR_KEY = "hs_match_color";
	static {
		IPreferenceStore store = LogicUI.getPlugin().getPreferenceStore();
		store.setDefault(MATCH_PREF_KEY, true);
		store.setValue(MATCH_PREF_KEY, true);
		store.setDefault(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
		store.setValue(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
	}

	private IARESElement aresResource;
	private EMFFormEditor emfEditor;
	private PseudoCodeListener listener =  null;
	/**
	 * 
	 */
	public LogicTextEditor(IARESElement aresResource) {
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
		return new LogicSourceViewerConfiguration(this, getPreferenceStore());
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
	 * @param atomFunctionEditor
	 */
	public void setEditor(EMFFormEditor atomFunctionEditor) {
		this.emfEditor = atomFunctionEditor;
		if(listener==null){
			listener = new PseudoCodeListener();
			IEditorInput input = this.getEditorInput();
			if(input instanceof TextBasedEditorInput){
				IDocument  document = getDocumentProvider().getDocument(input);
				document.addDocumentListener(listener);
			}
			
		}
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

class LogicSourceViewerConfiguration extends ARESSourceViewerConfiguration {

	/**
	 * @param editor
	 * @param store
	 */
	public LogicSourceViewerConfiguration(GeneralSourceEditor editor,
			IPreferenceStore store) {
		super(editor, store);
	}
	
	@Override
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		return new IHyperlinkDetector[]{new LogicHyperlinkDetector(editor)};
	}
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new LogicTextContentAssistant();
		assistant.setContentAssistProcessor(new LogicTextAssistant((IARESResource) editor.getAdapter(IARESResource.class)),IDocument.DEFAULT_CONTENT_TYPE);
		assistant.enableAutoActivation(true);
		assistant.enableAutoInsert(true);
		assistant.setAutoActivationDelay(100);
		assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return assistant;
	}


	@Override
	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType) {
		return new LogicTextHover(((IARESResource) editor.getAdapter(IARESResource.class)).getARESProject());
	}

	/*
	 * @see SourceViewerConfiguration#getInformationControlCreator(ISourceViewer)
	 * @since 2.0
	 */
	public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, SWT.NONE, new HTMLTextPresenter(false));
			}
		};
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, ARESPartitionScanner.HS_STRING,
				ARESPartitionScanner.HS_COMMENT, ARESPartitionScanner.HS_CHARACTOR, ARESPartitionScanner.HS_MACRO};
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(new CRESScanner(colorManager));
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		NonRuleBasedDamagerRepairer commentRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.COMMENT)));
		reconciler.setDamager(commentRepairer, ARESPartitionScanner.HS_COMMENT);
		reconciler.setRepairer(commentRepairer, ARESPartitionScanner.HS_COMMENT);

		NonRuleBasedDamagerRepairer stringRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.STRING)));
		reconciler.setDamager(stringRepairer, ARESPartitionScanner.HS_STRING);
		reconciler.setRepairer(stringRepairer, ARESPartitionScanner.HS_STRING);
		
		NonRuleBasedDamagerRepairer macroRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.BLUE)));
		reconciler.setDamager(macroRepairer, ARESPartitionScanner.HS_MACRO);
		reconciler.setRepairer(macroRepairer, ARESPartitionScanner.HS_MACRO);
//		DefaultDamagerRepairer tagDr = new DefaultDamagerRepairer(new MacroScanner(colorManager));
//		reconciler.setDamager(tagDr, ARESPartitionScanner.HS_MACRO);
//		reconciler.setRepairer(tagDr, ARESPartitionScanner.HS_MACRO);

		NonRuleBasedDamagerRepairer charactorRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.CHARACTOR)));
		reconciler.setDamager(charactorRepairer, ARESPartitionScanner.HS_CHARACTOR);
		reconciler.setRepairer(charactorRepairer, ARESPartitionScanner.HS_CHARACTOR);
		
		return reconciler;
	}
	
}
