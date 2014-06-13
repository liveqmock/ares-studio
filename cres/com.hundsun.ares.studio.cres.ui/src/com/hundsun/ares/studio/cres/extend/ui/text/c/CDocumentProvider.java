package com.hundsun.ares.studio.cres.extend.ui.text.c;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;

import com.hundsun.ares.studio.ui.editor.text.TextEditorInputDocumentProvider;

public class CDocumentProvider extends TextEditorInputDocumentProvider {

	private String[] CONTENT_TYPES = new String[] {
		CRuleBasedPartitionScanner.HS_CHARACTOR,
		CRuleBasedPartitionScanner.HS_STRING,
		CRuleBasedPartitionScanner.HS_COMMENT,
	};
	
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument doc = super.createDocument(element);
		
		FastPartitioner partitioner = 
			new FastPartitioner(new CRuleBasedPartitionScanner(), CONTENT_TYPES);
		
		partitioner.connect(doc, false);
		doc.setDocumentPartitioner(partitioner);

		return doc;
	}
	
}