package com.hundsun.ares.studio.ui.editor.text;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class ARESPartitionScanner extends RuleBasedPartitionScanner {

	public static final String HS_STRING = "__hs_string";
	public static final String HS_COMMENT = "__hs_comment";
	public static final String HS_CHARACTOR = "__hs_charactor";
	public static final String HS_MACRO = "__hs_macro";
//	public static final String HS_STDFIELD = "__hs_stdf";
//	
//	public static class StdWordDetector implements IWordDetector {
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
//		 */
//		public boolean isWordPart(char c) {
//			return (c >= 'a' && c <= 'z') || (c>= 'A' && c <= 'Z') || (c == '_') || (c>= '0' && c <= '9');
//			//return Character.isJavaIdentifierPart(c);
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
//		 */
//		public boolean isWordStart(char c) {
//			return c == '@';
//			//return Character.isJavaIdentifierStart(c);
//		}
//		
//	}
	
	public ARESPartitionScanner() {

		IToken comment = new Token(HS_COMMENT);
		IToken macro = new Token(HS_MACRO);
		IToken string = new Token(HS_STRING);
		IToken charactor = new Token(HS_CHARACTOR);
//		IToken stdf = new Token(HS_STDFIELD);
		
		IPredicateRule[] rules = new IPredicateRule[5];
		rules[0] = new EndOfLineRule("//", comment);
		rules[1] = new MultiLineRule("/*", "*/", comment);
		rules[2] = new MultiLineRule("[", "]", macro);
		rules[3] = new SingleLineRule("\"", "\"", string);
		rules[4] = new SingleLineRule("\'", "\'", charactor);
//		rules[5] = new WordPatternRule(new StdWordDetector(), "@", null, stdf);
		
		setPredicateRules(rules);
	}

}
