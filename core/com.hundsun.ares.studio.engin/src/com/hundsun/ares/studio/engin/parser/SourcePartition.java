/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.parser;

import java.util.regex.Pattern;

public class SourcePartition {
	
	private String text;
	private int lineNum;
	private int start; // 包含
	private int length;
	private Pattern pattern;
	
//	public SourcePartition(String text, int lineNum, Pattern p) {
//		super();
//		this.text = text;
//		this.lineNum = lineNum;
//		this.pattern = p;
//	}

	public SourcePartition(String text, int lineNum, int start, int length, Pattern pattern) {
		super();
		this.text = text;
		this.lineNum = lineNum;
		this.start = start;
		this.length = length;
		this.pattern = pattern;
	}

	public int getStart() {
		return start;
	}
	
	public int getLength() {
		return length;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the lineNum
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

}
