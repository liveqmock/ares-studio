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
 * <p>CreatedDate: 2008-2-18</p>
 * @author sundl
 */
public class ARESDefaultScanner extends RuleBasedScanner {

	public static class StdWordDetector implements IWordDetector {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
		 */
		public boolean isWordPart(char c) {
			return (c >= 'a' && c <= 'z') || (c>= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c == '_');
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
	
	public static final String[] keywords = new String[] {
		"and",
		"else",
		"for",
		"if",
		"or"
	};
	     
	public ARESDefaultScanner(ARESColorManager manager) {
		IToken keyword = new Token(new TextAttribute(manager.getColor(ARESColorManager.KEYWORD), null, SWT.BOLD));
		IToken var = new Token(new TextAttribute( manager.getColor(ARESColorManager.STDFILED) , null, SWT.BOLD));
		IToken defaultTooken = new Token(new TextAttribute(manager.getColor(ARESColorManager.DEFAULT)));
		
		//WordRule keywordRule = new WordRule(new HSWordDetector());
		List<IRule> rules = new ArrayList<IRule>();
//		rules.add(new WordRule(new StdWordDetector(), var));
		rules.add(new WhitespaceRule(new ARESWhitespaceDetector()));
		
		WordRule wordRule = new WordRule(new ARESWordDetector(), defaultTooken);
		for(String s : keywords) {
			wordRule.addWord(s, keyword);
		}
		
		rules.add(wordRule);
		
		IRule[] rs = new IRule[rules.size()];
		setRules(rules.toArray(rs));
		
		setDefaultReturnToken(defaultTooken);
	}
	
}
