package com.hundsun.ares.studio.cres.extend.ui.text.c;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class CRuleBasedPartitionScanner extends RuleBasedPartitionScanner {

	public static final String HS_STRING = "__hs_string";
	public static final String HS_COMMENT = "__hs_comment";
	public static final String HS_CHARACTOR = "__hs_charactor";

	public CRuleBasedPartitionScanner() {

		IToken comment = new Token(HS_COMMENT);
		IToken string = new Token(HS_STRING);
		IToken charactor = new Token(HS_CHARACTOR);

		IPredicateRule[] rules = new IPredicateRule[4];
		rules[0] = new EndOfLineRule("//", comment);
		rules[1] = new MultiLineRule("/*", "*/", comment);
		rules[2] = new SingleLineRule("\"", "\"", string);
		rules[3] = new SingleLineRule("\'", "\'", charactor);

		setPredicateRules(rules);
	}
	
}