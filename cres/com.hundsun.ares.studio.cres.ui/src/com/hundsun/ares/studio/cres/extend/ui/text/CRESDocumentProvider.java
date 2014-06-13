package com.hundsun.ares.studio.cres.extend.ui.text;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;

import com.hundsun.ares.studio.ui.editor.text.ARESElementDocumentProvider;
import com.hundsun.ares.studio.ui.editor.text.ARESPartitionScanner;

public class CRESDocumentProvider extends ARESElementDocumentProvider{

	public static final String[] CONTENT_TYPES = new String[] {
		ARESPartitionScanner.HS_CHARACTOR,
		ARESPartitionScanner.HS_STRING,
		ARESPartitionScanner.HS_COMMENT,ARESPartitionScanner.HS_MACRO,
	};

	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument doc = super.createDocument(element);
		if(doc != null) {
			FastPartitioner partitioner = new FastPartitioner(new CRESPartitionScanner(), CONTENT_TYPES);
			partitioner.connect(doc, false);
			doc.setDocumentPartitioner(partitioner);
		}
		return doc;
	}
}
