package com.hundsun.ares.studio.ui.editor.text.sql;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * This class defines the editor add-ons; content assist, content formatter,
 * highlighting, auto-indent strategy, double click strategy. 
 */
public class SQLSourceViewerConfiguration extends SourceViewerConfiguration {
    
    /**
     * This class implements a single token scanner.
     */
    static class SingleTokenScanner extends BufferedRuleBasedScanner {
        public SingleTokenScanner( TextAttribute attribute ) {
            setDefaultReturnToken( new Token( attribute ));
        }
    }

    /**
     * Constructs an instance of this class.
     */
    public SQLSourceViewerConfiguration() {
	}
    
    /**
     * Returns the configured partitioning for the given source viewer. The partitioning is
     * used when the querying content types from the source viewer's input document.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredDocumentPartitioning(org.eclipse.jface.text.source.ISourceViewer)
     */
    public String getConfiguredDocumentPartitioning( ISourceViewer sourceViewer ) {
        return SQLPartitionScanner.SQL_PARTITIONING;
    }

    /**
     * Creates, configures, and returns a presentation reconciler to help with 
     * document changes.
     * 
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(ISourceViewer)
     */
    public IPresentationReconciler getPresentationReconciler( ISourceViewer sourceViewer ) {

        // Get the color provider.
        SQLColorProvider colorProvider = ARESEditorPlugin.getSqlColorProvider();
        
        // Create a presentation reconciler to handle handle document changes.
        PresentationReconciler reconciler = new PresentationReconciler();
        String docPartitioning = getConfiguredDocumentPartitioning( sourceViewer );
        reconciler.setDocumentPartitioning( docPartitioning );

        // Add a "damager-repairer" for changes in default text (SQL code).
        SQLCodeScanner sqlCodeScanner = new SQLCodeScanner(ARESEditorPlugin.getSqlColorProvider());
//        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByVendorIdentifier(getSQLEditor().getConnectionInfo().getDatabaseVendorDefinitionId());
//        if (factory != null)
//        {
//            sqlCodeScanner.setSQLSyntax(factory.getSQLService().getSQLSyntax());
//        }
        sqlCodeScanner.setSQLSyntax(new DefaultSQLSyntax());
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(sqlCodeScanner );
        
        reconciler.setDamager( dr, IDocument.DEFAULT_CONTENT_TYPE );
        reconciler.setRepairer( dr, IDocument.DEFAULT_CONTENT_TYPE );
        
        // rule for multiline comments
        // We just need a scanner that does nothing but returns a token with
        // the corrresponding text attributes
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( colorProvider.createTextAttribute( SQLColorProvider.SQL_MULTILINE_COMMENT)));
        reconciler.setDamager(dr, SQLPartitionScanner.SQL_MULTILINE_COMMENT);
        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_MULTILINE_COMMENT);

        // Add a "damager-repairer" for changes witin one-line SQL comments.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( colorProvider.createTextAttribute( SQLColorProvider.SQL_COMMENT )));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_COMMENT );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_COMMENT );

        // Add a "damager-repairer" for changes witin quoted literals.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( colorProvider.createTextAttribute( SQLColorProvider.SQL_QUOTED_LITERAL )));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_STRING );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_STRING );

        // Add a "damager-repairer" for changes witin delimited identifiers.
        dr = new DefaultDamagerRepairer( new SingleTokenScanner( colorProvider.createTextAttribute( SQLColorProvider.SQL_DELIMITED_IDENTIFIER )));
        reconciler.setDamager( dr, SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER );
        reconciler.setRepairer( dr, SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER );

        return reconciler;
    }
    
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer)
    {
        return SQLPartitionScanner.SQL_PARTITION_TYPES;
    }

    /*
	 * @see SourceViewerConfiguration#getDefaultPrefixes(ISourceViewer, String)
	 *  @since 2.0
	 */
    public String[] getDefaultPrefixes(ISourceViewer sourceViewer, String contentType) 
    {
        return new String[] 
        {
            "--", "" 
        }
        ; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
	public int getTabWidth(ISourceViewer sourceViewer) {
		IPreferenceStore fPreferenceStore = EditorsPlugin.getDefault().getPreferenceStore();
		if (fPreferenceStore == null)
			return super.getTabWidth(sourceViewer);
		return fPreferenceStore.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH);
	}
} // end class
