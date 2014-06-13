package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.texteditor.AbstractDocumentProvider;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.textbase.ITextBased;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;


/**
 * 对于输入元素类型为ITextBased类型的文档提供程序。
 * <p>CreatedDate: 2008-2-1</p>
 * @author sundl
 */
public class ARESElementDocumentProvider extends AbstractDocumentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createAnnotationModel(java.lang.Object)
	 */
	@Override
	protected IAnnotationModel createAnnotationModel(Object element) throws CoreException {
		if (element instanceof TextBasedEditorInput) {
			return new AnnotationModel();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createDocument(java.lang.Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument doc = new Document();
		ITextBased textBasedInfo = getTextBased(element);
		if (textBasedInfo != null) {
			doc.set(textBasedInfo.getText());
			return doc;
		}
		doc.set("");
		return doc;
	}
	
	protected final ITextBased getTextBased(Object element) {
		if(element instanceof TextBasedEditorInput) {
			return ((TextBasedEditorInput)element).getTextbased();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor, java.lang.Object, org.eclipse.jface.text.IDocument, boolean)
	 */
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element,
		IDocument document, boolean overwrite) throws CoreException {
		ITextBased textBasedInfo = getTextBased(element);
		if (textBasedInfo != null) {
			textBasedInfo.setText(document.get());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#getOperationRunner(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#isModifiable(java.lang.Object)
	 */
	@Override
	public boolean isModifiable(Object element) {
		return !getResource(element).isReadOnly();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#isReadOnly(java.lang.Object)
	 */
	@Override
	public boolean isReadOnly(Object element) {
		return getResource(element).isReadOnly();
	}
	
	private IARESResource getResource(Object element) {
		if(element instanceof TextBasedEditorInput) {
			return (IARESResource) ((TextBasedEditorInput)element).getARESElement();
		}
		return null;
	}
	
}
