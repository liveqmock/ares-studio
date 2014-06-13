/**
 * 源程序名称：SQLTextEditorInputDocumentProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.text.sql;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;

import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInputDocumentProvider;

/**
 * @author gongyf
 *
 */
public class SQLTextEditorInputDocumentProvider extends
		TextEditorInputDocumentProvider {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.TextEditorInputDocumentProvider#createDocument(java.lang.Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		TextEditorInput input = (TextEditorInput) element;
		
		Document document = new Document();
		document.set(input.getText());
		
		SQLPartitionScanner sqlPartitionSanner = new SQLPartitionScanner();
		IDocumentPartitioner partitioner = new FastPartitioner(sqlPartitionSanner, new String[]
               {
                   SQLPartitionScanner.SQL_COMMENT, SQLPartitionScanner.SQL_MULTILINE_COMMENT,
                   SQLPartitionScanner.SQL_STRING, SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER
               });
		partitioner.connect(document);
		document.setDocumentPartitioner(SQLPartitionScanner.SQL_PARTITIONING, partitioner);
		
		return document;
	}
}
