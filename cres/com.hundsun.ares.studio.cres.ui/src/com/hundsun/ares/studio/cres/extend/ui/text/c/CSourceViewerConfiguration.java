package com.hundsun.ares.studio.cres.extend.ui.text.c;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.hundsun.ares.studio.ui.ARESColorManager;
import com.hundsun.ares.studio.ui.editor.text.NonRuleBasedDamagerRepairer;
import com.hundsun.ares.studio.ui.util.HSColorManager;

public class CSourceViewerConfiguration  extends SourceViewerConfiguration {
	
	ARESColorManager colorManager;
	
	public CSourceViewerConfiguration(ARESColorManager colorManager) {
		this.colorManager = colorManager;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return super.getConfiguredContentTypes(sourceViewer);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		CRuleBasedScanner scanner = new CRuleBasedScanner(colorManager);
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer commentRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(HSColorManager.COMMENT)));
		reconciler.setDamager(commentRepairer, CRuleBasedPartitionScanner.HS_COMMENT);
		reconciler.setRepairer(commentRepairer, CRuleBasedPartitionScanner.HS_COMMENT);

		NonRuleBasedDamagerRepairer stringRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(HSColorManager.STRING)));
		reconciler.setDamager(stringRepairer, CRuleBasedPartitionScanner.HS_STRING);
		reconciler.setRepairer(stringRepairer, CRuleBasedPartitionScanner.HS_STRING);

		NonRuleBasedDamagerRepairer charactorRepairer = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(HSColorManager.CHARACTOR)));
		reconciler.setDamager(charactorRepairer, CRuleBasedPartitionScanner.HS_CHARACTOR);
		reconciler.setRepairer(charactorRepairer, CRuleBasedPartitionScanner.HS_CHARACTOR);
		
		return reconciler;
	}
	
}