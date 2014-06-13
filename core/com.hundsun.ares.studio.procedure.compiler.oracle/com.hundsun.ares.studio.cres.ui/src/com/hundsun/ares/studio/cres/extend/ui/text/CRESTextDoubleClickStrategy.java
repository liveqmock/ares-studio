package com.hundsun.ares.studio.cres.extend.ui.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultTextDoubleClickStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

public class CRESTextDoubleClickStrategy  extends DefaultTextDoubleClickStrategy {

	@Override
	protected IRegion findWord(IDocument document, int offset) {
		IRegion region = CRESTextUtil.findWord(document, offset);
		if (region == null)
			return null;
		int regionStart = region.getOffset();
		int regionLenght = region.getLength();
		if (regionStart > 0) {
			char pre;
			try {
				pre = document.getChar(regionStart - 1);
				if (pre == '@') {
					regionStart--;
					regionLenght++;
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		return new Region(regionStart, regionLenght);
	}

}
