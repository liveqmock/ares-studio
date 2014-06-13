/**
 * 源程序名称：TextEditorInputDocumentProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.texteditor.AbstractDocumentProvider;

/**
 * @author gongyf
 *
 */
public class TextEditorInputDocumentProvider extends AbstractDocumentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createDocument(java.lang.Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		TextEditorInput input = (TextEditorInput) element;
		
		IDocument document = new Document();
		document.set(input.getText());
		return document;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createAnnotationModel(java.lang.Object)
	 */
	@Override
	protected IAnnotationModel createAnnotationModel(Object element)
			throws CoreException {
		return new AnnotationModel();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor, java.lang.Object, org.eclipse.jface.text.IDocument, boolean)
	 */
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element,
			IDocument document, boolean overwrite) throws CoreException {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#getOperationRunner(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#isReadOnly(java.lang.Object)
	 */
	@Override
	public boolean isReadOnly(Object element) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#isModifiable(java.lang.Object)
	 */
	@Override
	public boolean isModifiable(Object element) {
		return false;
	}


}
