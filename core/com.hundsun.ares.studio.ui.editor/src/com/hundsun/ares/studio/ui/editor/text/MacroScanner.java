package com.hundsun.ares.studio.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.ui.ARESColorManager;

/**
 * <p>CreatedDate: 2008-2-14</p>
 * @author sundl
 */
public class MacroScanner extends RuleBasedScanner {

	public static class TagWordDetector implements IWordDetector {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
		 */
		public boolean isWordPart(char c) {
			return c == '[' || c == ']' || Character.isJavaIdentifierPart(c);
			//return Character.isJavaIdentifierPart(c);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
		 */
		public boolean isWordStart(char c) {
			return c == '[' || c == ']' || Character.isJavaIdentifierStart(c);
			//return Character.isJavaIdentifierStart(c);
		}
		
	}
	
	public static class StdWordDetector implements IWordDetector {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
		 */
		public boolean isWordPart(char c) {
			return (c >= 'a' && c <= 'z') || (c>= 'A' && c <= 'Z') || (c == '_') || (c >= '0' && c <= '9') ;
			//return Character.isJavaIdentifierPart(c);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
		 */
		public boolean isWordStart(char c) {
			return c == '@';
			//return Character.isJavaIdentifierStart(c);
		}
		
	}
	
	public static final String[] tagKeywords = new String[] {
		"from", "FROM",
		"select", "SELECT",
		"where", "WHERE"
	};
	            
	public MacroScanner(ARESColorManager manager) {
		IToken var = new Token(new TextAttribute( manager.getColor(ARESColorManager.STDFILED) , null, SWT.BOLD));
		IToken keywords = new Token(new TextAttribute(manager.getColor(ARESColorManager.KEYWORD), null, SWT.BOLD));
		IToken def = new Token(new TextAttribute(manager.getColor(ARESColorManager.STRING)));
		
		
		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WordRule(new StdWordDetector(), var));
		rules.add(new WhitespaceRule(new ARESWhitespaceDetector()));
		
		WordRule wordRule = new WordRule(new ARESWordDetector());
		for(String s : tagKeywords) {
			wordRule.addWord(s, keywords);
		}
		
		rules.add(wordRule);
		
		IRule[] rs = new IRule[rules.size()];
		setRules(rules.toArray(rs));
		
		setDefaultReturnToken(def);
	}
}
