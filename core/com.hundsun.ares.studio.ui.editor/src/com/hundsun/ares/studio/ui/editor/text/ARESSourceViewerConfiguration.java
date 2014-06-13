package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import com.hundsun.ares.studio.ui.ARESColorManager;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 默认的设计源代码配置，包含了注释，字符串的高亮、匹配大括号的折叠功能、超链接功能。
 * @author sundl
 */
public abstract class ARESSourceViewerConfiguration extends TextSourceViewerConfiguration {

	protected ARESColorManager colorManager = ARESUI.getPlugin().getColorManager();
	private ARESDefaultScanner defaultScanner;
	protected GeneralSourceEditor editor;

	public ARESSourceViewerConfiguration(GeneralSourceEditor editor, IPreferenceStore store) {
		super(store);
		this.editor = editor;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getDefaultScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

//		NonRuleBasedDamagerRepairer stdfRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(HSColorManager.STDFILED), null, SWT.BOLD));
//		reconciler.setDamager(stdfRepairer, HSFunctionPartitionScanner.HS_STDFIELD);
//		reconciler.setRepairer(stdfRepairer, HSFunctionPartitionScanner.HS_STDFIELD);
		
		NonRuleBasedDamagerRepairer commentRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.COMMENT)));
		reconciler.setDamager(commentRepairer, ARESPartitionScanner.HS_COMMENT);
		reconciler.setRepairer(commentRepairer, ARESPartitionScanner.HS_COMMENT);

		NonRuleBasedDamagerRepairer stringRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(ARESColorManager.STRING)));
		reconciler.setDamager(stringRepairer, ARESPartitionScanner.HS_STRING);
		reconciler.setRepairer(stringRepairer, ARESPartitionScanner.HS_STRING);
		
		return reconciler;
	}

	protected ARESDefaultScanner getDefaultScanner() {
		if (defaultScanner == null) {
			defaultScanner = new ARESDefaultScanner(ARESUI.getDefault().getColorManager());
		}
		return defaultScanner;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		ARESReconclingStrategy strategy = new ARESReconclingStrategy();
        strategy.setEditor(editor); 
        
        MonoReconciler reconciler = new MonoReconciler(strategy, false);
        return reconciler;    
	}

//  ContentType
//	@Override
//	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
//		return new String[] {IDocument.DEFAULT_CONTENT_TYPE, ARESPartitionScanner.HS_MACRO};
//	}

	@Override
	public String[] getDefaultPrefixes(ISourceViewer sourceViewer,	String contentType) {
		return new String[] { "//", "" };
	}
	
//	/*
//	 * (non-Javadoc)超链接
//	 * 
//	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getHyperlinkDetectors(org.eclipse.jface.text.source.ISourceViewer)
//	 */
//	@Override
//	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
//		return new IHyperlinkDetector[] {new HSHyperlinkDetector(editor)};
//	}
	
//	@Override
//	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
//		return super.getContentFormatter(sourceViewer);
//	}

//	@Override
//	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
//		return new HSTextHover(getHSElement().getHSProject());
//	}

//	protected IHSElement getHSElement() {
//		IEditorInput input = editor.getEditorInput();
//		if (input instanceof FileEditorInput) {
//			return HSCore.create(((FileEditorInput) input).getFile().getProject());
//		} else if(input instanceof TextBasedEditorInput) {
//			return ((TextBasedEditorInput)input).getHSElement();
//		}
//		
//		return (IHSElement)editor.getAdapter(IHSElement.class);
//	}
}
