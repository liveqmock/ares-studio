package com.hundsun.ares.studio.atom.ui.editor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.constants.IProcedureRefType;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.text.OpenLinkAction;
import com.hundsun.ares.studio.ui.editor.text.TextUtil;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;

public class AtomHyperlinkDetector implements IHyperlinkDetector {
	
	private ITextEditor editor;

	public AtomHyperlinkDetector(ITextEditor editor) {
		this.editor = editor;
	}
	
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks) {
		IDocument document = textViewer.getDocument();
		int offset = region.getOffset();
		
		IRegion r = TextUtil.findHyperlink(document, offset);
		if(r == null) return null;
		String name = "";
		try {
			name = document.get(r.getOffset() + 1, r.getLength() -2);			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		IEditorInput input = editor.getEditorInput();		
		if(!(input instanceof TextBasedEditorInput)) return null;
		
		IARESProject project = ((TextBasedEditorInput)input).getARESElement().getARESProject();
		
		IARESElement element = null;
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IAtomRefType.ATOM_FUNCTION_CNAME, name, true);
		if(info == null){
			info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IProcedureRefType.PROCEDURE_CNAME, name, true);
		}
		if(info == null){
			info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IDatabaseRefType.Table, name, true);
		}
		if(info != null){
			element = info.getResource();
		}
		
		OpenLinkAction action = null;
		
		if(! (element == null)) {
			action = new OpenLinkAction(element);
		} else {
			return null;
		}
		
		
		AtomHyperlink link = new AtomHyperlink(r, action);
		
		IHyperlink[] result = new IHyperlink[] {link};
		return result;
	}

}
