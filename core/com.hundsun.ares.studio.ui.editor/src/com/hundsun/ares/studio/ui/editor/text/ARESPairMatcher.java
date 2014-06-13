package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * <p>CreatedDate: 2008-3-5</p>
 * @author sundl
 */
public class ARESPairMatcher implements ICharacterPairMatcher {

	private char[] pairs;
	
//	private int start;
//	private int end;
	private int anchor = RIGHT;
	
	private IDocument document;
	
	public ARESPairMatcher(char[] pairs) {
		this.pairs = pairs;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#clear()
	 */
	public void clear() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#dispose()
	 */
	public void dispose() {
		this.document = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#getAnchor()
	 */
	public int getAnchor() {
		return anchor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#match(org.eclipse.jface.text.IDocument, int)
	 */
	public IRegion match(IDocument iDocument, int offset) {
		
		if(offset < 0) 
			return null;
		
		this.document = iDocument;
		
//		fOffset= offset;
//
//		if (fOffset < 0)
//			return null;
//
//		fDocument= document;
//
//		if (fDocument != null && matchPairsAt() && fStartPos != fEndPos)
//			return new Region(fStartPos, fEndPos - fStartPos + 1);
		try {
			char preChar = document.getChar(offset - 1);
			int index = getCharactorIndex(preChar);
			
			if(index < 0) 
				return new Region(0, 0);
			
			if(index % 2 == 0) {
				char closingChar = pairs[index + 1];
				int end = serchForClosingPeer(offset, preChar, closingChar);
				if(end > offset) {
					anchor = RIGHT;
					return new Region(offset - 1 , end - offset + 1);
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return new Region(0, 0);
	}
	
	private boolean isStart(char ch) {
		for(int i = 0; i < pairs.length; i += 2) {
			if(pairs[i] == ch)
				return true;
		}
		return false;
	}
	
	private int getCharactorIndex(char ch) {
		for(int i = 0; i < pairs.length; i++) {
			if(pairs[i] == ch)
				return i;
		}
		return -1;
	}
	
	private int serchForClosingPeer(int offset, char open, char close) throws BadLocationException {
		
		int cur = offset;
		int end = document.getLength();
		int depth = 0;
		
		while(cur < end) {
			char ch = document.getChar(cur);
			if(depth == 0 && ch == close) {
				return cur;
			} else if (ch == open) {
				depth++;
			}
			cur++;
		}
		
		return -1;
	}

}
