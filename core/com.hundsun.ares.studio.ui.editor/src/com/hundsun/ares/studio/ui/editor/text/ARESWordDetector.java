/**
 */
package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * ¼òµ¥µÄµ¥´ÊÉ¨ÃèÆ÷¡£
 * <p>CreatedDate: 2008-2-3</p>
 * @author sundl
 */
public class ARESWordDetector implements IWordDetector {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	 */
	public boolean isWordPart(char c) {
		return Character.isJavaIdentifierPart(c);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	 */
	public boolean isWordStart(char c) {
		return Character.isJavaIdentifierStart(c);
	}

}
