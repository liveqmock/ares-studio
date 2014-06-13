/**
 */
package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * <p>CreatedDate: 2008-2-14</p>
 * @author sundl
 */
public class ARESWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
	 */
	public boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}

}
