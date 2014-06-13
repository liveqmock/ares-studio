package com.hundsun.ares.studio.cres.extend.ui.text.c;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import com.hundsun.ares.studio.ui.ARESColorManager;
import com.hundsun.ares.studio.ui.editor.text.ARESWhitespaceDetector;
import com.hundsun.ares.studio.ui.editor.text.ARESWordDetector;

public class CRuleBasedScanner extends RuleBasedScanner {

	private final String[] keywords = new String[] {
			"auto",
			"enum",
			"unsigned",
			"break",
			"extern",
			"return",
			"void",
			"case",
			"float",
			"short",
			"volatile",
			"char",
			"for",
			"signed",
			"while",
			"const",
			"goto",
			"sizeof",
			"continue",
			"if",
			"static",
			"defaule",
			"struct",
			"do",
			"int",
			"switch",
			"double",
			"long",
			"typedef",
			"else",
			"register",
			"union",
			// ²¹³ä
			"NULL",
			
	};
	
	public CRuleBasedScanner(ARESColorManager colorManager) {
		IToken keyword = new Token(new TextAttribute(colorManager.getColor(ARESColorManager.KEYWORD), null, SWT.BOLD));
		IToken defaultToken = new Token(new TextAttribute(colorManager.getColor(ARESColorManager.DEFAULT)));
		
		List<IRule> rules = new ArrayList<IRule>();

		rules.add(new WhitespaceRule(new ARESWhitespaceDetector()));
		
		WordRule wordRule = new WordRule(new ARESWordDetector(), defaultToken);
		for(String s : keywords) {
			wordRule.addWord(s, keyword);
		}
		
		rules.add(wordRule);
		
		setRules(rules.toArray(new IRule[0]));
	}
	
}