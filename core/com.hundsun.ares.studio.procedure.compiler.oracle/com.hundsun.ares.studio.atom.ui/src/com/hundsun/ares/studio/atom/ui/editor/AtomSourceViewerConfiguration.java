/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.editor;

import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
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
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.atom.ui.assistant.AtomTextAssistant;
import com.hundsun.ares.studio.atom.ui.assistant.AtomTextContentAssistant;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.ui.text.CRESScanner;
import com.hundsun.ares.studio.ui.ARESColorManager;
import com.hundsun.ares.studio.ui.editor.text.ARESPartitionScanner;
import com.hundsun.ares.studio.ui.editor.text.ARESSourceViewerConfiguration;
import com.hundsun.ares.studio.ui.editor.text.GeneralSourceEditor;
import com.hundsun.ares.studio.ui.editor.text.NonRuleBasedDamagerRepairer;

/**
 * @author qinyuan
 *
 */
public class AtomSourceViewerConfiguration extends ARESSourceViewerConfiguration {

	/**
	 * @param editor
	 * @param store
	 */
	public AtomSourceViewerConfiguration(GeneralSourceEditor editor,
			IPreferenceStore store) {
		super(editor, store);
	}
	
	@Override
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		return new IHyperlinkDetector[]{new AtomHyperlinkDetector(editor)};
	}
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new AtomTextContentAssistant();
		assistant.setContentAssistProcessor(new AtomTextAssistant((IARESResource) editor.getAdapter(IARESResource.class)),IDocument.DEFAULT_CONTENT_TYPE);
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
		return new AtomTextHover(((IARESResource) editor.getAdapter(IARESResource.class)).getARESProject());
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
